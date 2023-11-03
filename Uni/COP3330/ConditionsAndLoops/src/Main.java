import java.util.*;

public class Main {
	public static void main(String[] args) {
		int score = 0;
		String letterGrade = "x";
		Scanner scan = new Scanner(System.in);
		boolean firstTime = true;
		
//		System.out.print("Enter the score: ");
//		score = scan.nextInt();
//		
//		while(score > 100 || score < 0) {
//			System.out.print("Invalid score. Please re-enter score: ");
//			score = scan.nextInt();
//		}
		
		do {
			if(!firstTime) System.out.println("Please enter a valid score.");
			
			System.out.print("Enter the score: ");
			score = scan.nextInt();
			firstTime = false;
		} while(score > 100 || score < 0);
		
		letterGrade = score >= 90 ? "A" : score >= 80 ? "B" : score >= 70 ? "C" : score >= 60 ? "D" : "F";
		System.out.println("The letter grade for the score " + score + " is: " + letterGrade);
		scan.close();
	}
}