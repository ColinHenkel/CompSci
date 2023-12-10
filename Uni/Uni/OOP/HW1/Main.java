import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {
		// all necessary string variables and string arrays
		String id = "", name = "", class1info = "", class2info = "";
		String[] class1items = null, class2items = null;
		
		// scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		// doubles for cost calculation
		double creditCost = 120.25;
		double class1Cost, class2Cost;
		double hidFee = 35.00;
		
		// decimal format for double values
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		// prompting and taking user input
		System.out.print("Enter the Student's Id: ");
		id = scanner.nextLine();
		
		System.out.print("Enter the Student's full name: ");
		name = scanner.nextLine();
		
		// splitting course info for course 1
		System.out.println("Enter crn/credit hours for the first class (ex. 5665/3)");
		class1info = scanner.nextLine();
		class1items = class1info.split("/");
		try { // doing course cost calculations and using try-catch to ensure credit hour input can be parsed to an integer
			class1Cost = creditCost * Integer.parseInt(class1items[1]);
		} catch(NumberFormatException e) {
			System.out.println("Invalid input for credit hours in course 1. Please re-run and enter an integer.");
			scanner.close();
			return;
		}		
		
		// splitting course info for course 2
		System.out.println("Enter crn/credit hours for the second class (ex. 5665/3)");
		class2info = scanner.nextLine();
		class2items = class2info.split("/");
		try {
			class2Cost = creditCost * Integer.parseInt(class2items[1]);
		} catch(NumberFormatException e) {
			System.out.println("Invalid input for credit hours in course 2. Please re-run and enter an integer.");
			scanner.close();
			return;
		}
		
		// printing final fee invoice
		double totalCost = hidFee + class1Cost + class2Cost;
		
		System.out.println("\nThank you!");
		System.out.println("HERE IS THE FEE INVOICE...\n");
		System.out.println("\n\tSIMPLE COLLEGE\n\tORLANDO FL 10101");
		System.out.println("\t*************************\n");
		System.out.println("\tFee Invoice Prepared for: ");
		System.out.println("\t[" + name + "]" + " [" + id + "]\n");
		System.out.println("\t1 Credit Hour = $" + creditCost + "\n");
		System.out.println("\tCRN\tCREDIT HOURS");
		System.out.println("\t" + class1items[0] + "\t" + class1items[1] + "\t\t\t$" + df.format(class1Cost));
		System.out.println("\t" + class2items[0] + "\t" + class2items[1] + "\t\t\t$" + df.format(class2Cost));
		System.out.println("\n\t\tHealth & id fees\t$" + df.format(hidFee) + "\n");
		System.out.println("\t----------------------------------------");
		System.out.println("\t\tTotal Payments\t\t$" + df.format(totalCost));
		
		scanner.close();
	}
}
