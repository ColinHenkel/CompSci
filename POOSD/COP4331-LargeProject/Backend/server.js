const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();

const MongoClient = require('mongodb').MongoClient;
const url = 'mongodb+srv://group7mongo:PyQShF7GsAUsp6dk@cluster0.r4cqh.mongodb.net/COP4331?retryWrites=true&w=majority&appName=Cluster0';

const client = new MongoClient(url);
client.connect();

app.use(cors());
app.use(bodyParser.json());

app.use((req, res, next) =>
{
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader(
    'Access-Control-Allow-Headers',
    'Origin, X-Requested-With, Content-Type, Accept, Authorization'
  );
  res.setHeader(
    'Access-Control-Allow-Methods',
    'GET, POST, PATCH, DELETE, OPTIONS'
  );
  next();
});

app.post('/api/login', async (req, res, next) =>
{
    // input: Login, Password
    // output: UserId, FirstName, LastName, error

    var error = '';

    const { login, password } = req.body;

    const db = client.db();
    const results = await db.collection('Users').find({Login:login,Password:password}).toArray();

    // var id = -1;
    var fn = '';
    var ln = '';

    if( results.length > 0 ) {
        // id = results[0].UserId;
        fn = results[0].FirstName;
        ln = results[0].LastName;
    }

    var ret = {id:id, firstName:fn, lastName:ln, error:''};
    res.status(200).json(ret);
});

app.post('/api/signup', async (req, res, next) =>
{
    // input: FirstName, LastName, Login, Password
    // output: error

    var error = '';

    const {firstname, lastname, login, password } = req.body;

    const db = client.db();
    const newUser = {FirstName:firstname, LastName:lastname, Login:login, Password:password};

    const results = await db.collection('Users').find({Login:login}).toArray();

    if( results.length > 0 ) {
        error = 'User already exists';
    } else {
        try {
            const results = db.collection('Users').insertOne(newUser);
        }
        catch(e) {
            error = e.toString();
        }
    }

    var ret = {error:error};
    res.status(200).json(ret);
});

app.listen(5000);