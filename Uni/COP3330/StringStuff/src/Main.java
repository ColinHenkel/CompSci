import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
//		System.out.println(str.charAt(0)); // charAt returns character @ passed index
//		System.out.println(str.indexOf('c')); // indexOf returns index of first instance of passed character
//		System.out.println(str.lastIndexOf('a')); // lastIndexOf returns index of last occurrence of passed character
//		System.out.println(str.isEmpty()); // isEmpty returns true iff str.length() = 0
		
		Scanner scanner = new Scanner(System.in);
		String name = "";
		int age;
		
		System.out.print("Enter your age: ");
		age = scanner.nextInt(); scanner.nextLine(); // nextLine is there to take EOF character from enter key
		
		// scanner = new Scanner(System.in); could also reassign scanner and use garbage collection, but there is chance of memleak
		
		System.out.print("Enter your full name: ");
		name = scanner.nextLine(); // scanner.next only takes first word, nextLine takes whole input line
		
		System.out.println("Age: " + age);
		System.out.println("Your name is: " + name);
		System.out.println("First letter of your name: " + name.charAt(0));
		System.out.println("Length w/o space: " + (name.length() - 2));
		
		scanner.close();
	}
}