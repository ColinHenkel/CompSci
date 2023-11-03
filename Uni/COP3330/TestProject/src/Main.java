import java.util.*;

public class Main {

	public static void main(String[] args) {
		int x, y, sum;
		
		Scanner myScanner = new Scanner(System.in);
		
		System.out.print("Enter the first number: ");
		x = myScanner.nextInt();
		
		System.out.print("Enter the second number: ");
		y = myScanner.nextInt();
		
		sum = x + y;
		
		myScanner.close();
		System.out.println("Sum: " + sum);
	}
}
