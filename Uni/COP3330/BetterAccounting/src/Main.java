import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static String menu() {
		Scanner scanner = new Scanner(System.in);
		
		String option = "";
		System.out.println("\n\t\t\t - - - - - M E N U - - - - -\n");
		System.out.println("\t\tA - Add New Account");
		System.out.println("\t\tR - Remove Account");
		System.out.println("\t\tD - Deposit Money To An Account");
		System.out.println("\t\tW - Withdraw Money From An Account");
		System.out.println("\t\tT - Transfer Money");
		System.out.println("\t\tP - Print All Accounts");
		System.out.println("\t\tI - Print Infomation Of An Account");
		System.out.println("\t\tE - Exit Program");
		System.out.print("Enter Your Selection: ");
		
		option = scanner.nextLine();
		option = option.toUpperCase();
		
		scanner.close();
		return option;
	}

	public static void main(String[] args) {
		String selection;
		Bank bank = new Bank();
		Account account = new Account();
		String name, id, senderId, receiverId;
		double amount;
		Scanner scanner = new Scanner(System.in);
		
		do {
			selection = menu();
			switch (selection) {
			
			case "A":
				System.out.print("Enter Account Id: ");
				id = scanner.nextLine();
				
				System.out.print("Enter Name: ");
				name = scanner.nextLine();
				
				System.out.print("Enter starting balance: ");
				amount = scanner.nextDouble();
				
				account = new Account(id, name, amount);
				bank.addAccount(account);
				scanner.nextLine();
				
				break;
			case "D":
				System.out.print("Enter the Account id: ");
				id = scanner.nextLine();
				
				System.out.print("Enter how much to deposit: ");
				amount = scanner.nextDouble();
				
				if (bank.deposit(id, amount)) System.out.println("Done!");
				System.out.println("Error: wrong id!");
				
				break;
			case "P": bank.printAccounts();
				break;
			case "W":
				System.out.print("Enter the Account id: ");
				id = scanner.nextLine();
				
				System.out.print("Enter how much to withdraw: ");
				amount = scanner.nextDouble();
				
				if (bank.withdraw(id, amount) == true) System.out.println("Done!");
				System.out.println("Error: wrong id or insuffucient funds!");
				
				break;
			case "R":
				System.out.print("Enter the Account Id to remove: ");
				id = scanner.nextLine();
				
				if (bank.removeAccount(id))
					System.out.println(id + "removed!");
				else
					System.out.println(id + " doesn't exit!");
				
				break;
			case "T":
				System.out.print("Enter the Account Id of Sender: ");
				senderId = scanner.nextLine();
				
				System.out.print("Enter the Account Id of Receiver: ");
				receiverId = scanner.nextLine();
				
				System.out.print("Enter amount to transfer: ");
				amount = scanner.nextDouble();
				
				bank.transferMoney(senderId, receiverId, amount);
				
				break;
			case "I":
				System.out.print("Enter the Account Id to view: ");
				id = scanner.nextLine();
				
				bank.printAccountInformation(id);
				
				break;
			case "E":
				System.out.println("Take Care!\n");
				
				break;
			default:
				System.out.println("Invalid Selection...");
			}
		} while (selection.equals("E") == false);
		scanner.close();
	}
}

class Bank {
	private ArrayList<Account> list;

	public Bank() {
		list = new ArrayList<>(100);
	}

	public void addAccount(Account account) {
		list.add(account);
	}

	private int search(String id) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if ((list.get(i)).getId().equals(id) == true) {
				index = i;
				break;
			}
		}
		return index;
	}

	public boolean removeAccount(String id) {
		int index = search(id);
		if (index == -1)
			return false;
		list.remove(index);
		return true;
	}

	public void printAccounts() {
		System.out.println("\t\t L I S T O F A C C O U N T S ");
		for (Account account : list) {
			System.out.println(account);
		}
	}

	public boolean deposit(String id, double amount) {
		int index = search(id);
		if (index == -1)
			return false;
		(list.get(index)).deposit(amount);
		return true;
	}

	public boolean withdraw(String id, double amount) {
		int index = search(id);
		if (index == -1)
			return false;
		return (list.get(index)).withdraw(amount);
	}

	public void printAccountInformation(String id) {
		int index = search(id);
		if (index == -1)
			return;
		(list.get(index)).printAccountInfo();
	}

	public boolean transferMoney(String senderId, String receiverId, double amount) {
		int senderIndex, receiverIndex;
		senderIndex = search(senderId);
		receiverIndex = search(receiverId);
		
		if (receiverIndex == -1 || senderIndex == -1 || receiverIndex == senderIndex)
			return false;
		if (!withdraw(senderId, amount))
			return false;
		
		deposit(receiverId, amount);
		return true;
	}
}

class Account {
	private String id;
	private double balance;
	private String name;
	private static int count;

	public static int getCount() {
		return count;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account() {
		id = "000";
		name = "NoName";
		balance = -1;
		count++;
	}

	public Account(String id, String name, double balance) {
		this.id = id;
		setBalance(balance);
		setName(name);
		count++;
	}

	@Override
	public String toString() {
		return "Account ID: " + id + " | Name: " + name + " | Balance: " + balance;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public boolean withdraw(double amount) {
		if (amount > balance)
			return false;
		balance -= amount;
		return true;
	}

	public void printAccountInfo() {
		System.out.println("*************************");
		System.out.println(toString());
		System.out.println("*************************");
	}
}
