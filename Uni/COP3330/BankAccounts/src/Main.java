public class Main {
	public static void main(String[] args) {
		// creating and instantiating acc1 and acc2
		Account acc1 = new Account();
		Account acc2 = new Account("John Rich", 123.50);
		acc1.setName("Ericka Jones");
		acc1.setBalance(100);
		
		acc2.deposit(250.35);
		acc1.withdraw(100);
		
		// printing account data
		System.out.println(acc1);
		System.out.println(acc2);
		System.out.println("# of Individual Accounts: " + Account.getCount()); // as getCount is static, class name can be used to call
	}
}

/*class for individual accounts*/
class Account {
	// data for accounts
	private String name;
	private double balance;
	private static int count; // static variable for number of individual accounts created
	
	// constructor with default values
	public Account() {
		name = "NoName";
		balance = -1;
		Account.count++;
	}
	// constructor with specified values
	public Account(String name, double balance) {
		setName(name);
		setBalance(balance);
		Account.count++;
	}
	
	// setters and getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public static int getCount() {
		return Account.count;
	}
	
	// methods for depositing and withdrawing money
	public void deposit(double amount) {
		balance += amount;
	}
	public boolean withdraw(double amount) {
		if(amount > balance) return false;
		balance -= amount;
		return true;
	}
	
	
	// toString method for displaying data via Sysout(account) needs @Override
	@Override public String toString () {
		return "Name: " + name + " | Balance: $" + balance;
	}
}

/*class for joint accounts*/
class Joint {
	// data for accounts
	private String name;
	private static int count;
	private static double balance; // only balance and count are static as this is joint account class
	
	public Joint() {
		name = "NoName";
		Joint.balance = -1;
		Joint.count++;
	}
	public Joint(String name, double balance) {
		setName(name);
		setBalance(balance);
		Joint.count++;
	}
	
	// setters and getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static double getBalance() {
		return balance;
	}
	public static void setBalance(double balance) {
		Joint.balance = balance; // since balance is static it must be modified using className.balance
	}
	public static int getCount() {
		return Joint.count;
	}
	
	public void deposit(double amount) {
		Joint.balance += amount;
	}
	public boolean withdraw(double amount) {
		if(amount > Joint.balance) return false;
		Joint.balance -= amount;
		return true;
	}
	
	@Override public String toString () {
		return "Name: " + name + " | Balance: $" + balance;
	}
}
