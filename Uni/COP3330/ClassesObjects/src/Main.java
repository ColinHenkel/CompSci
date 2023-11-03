public class Main {
	public static void main(String[] args) {
		/* new is same as calloc, erases previous pointee and replaces with default values; java default is 0 and null */
		// instantiation of acc1 and acc2
		Account acc1 = new Account();
		Account acc2 = new Account();
		
		acc1.setBalance(100); // sets balance for acc1 to 100.0
		
		// created array of references w/ size 120 and allocating memory for 120 accounts via "new"
		Account[] bankAccounts = new Account[120]; 
		
		// instantiation of each account in array bankAccounts
		int noAccounts = bankAccounts.length;
		for (int i = 0; i < noAccounts; i++) {
			bankAccounts[i] = new Account();
			bankAccounts[i].setBalance(acc1.getBalance()); // assigns acc1 balance to each new account in array
		}
		
		System.out.println(bankAccounts[52].getBalance()); // prints balance of account in index # 52
		System.out.println(acc2.getBalance()); // prints default balance assigned in class
	}
}

/* class data by default should always be private and accessed via getters and setters */
class Account {
	private String name;
	private double balance;
	
	// constructor for outside access and default values applied to each object via new Account();
	public Account () {
		balance = -1;
		name = "noName";
	}
	
	/* TO GENERATE GETTER AND SETTER GO TO SOURCE->GENERATE GETTERS AND SETTERS */
	public String getName() { // public methods are required to access private data in other classes such as main
		return name;
	}

	public void setName(String name) { // public setter for name value via passed name
		this.name = name; // "this" specifies the variable being created by constructor
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) { // public setter for balance value via passed balance value
		this.balance = balance;
	}
}