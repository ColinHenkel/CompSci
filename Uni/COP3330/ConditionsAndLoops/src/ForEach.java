/* forEach is an iterative class that allows traversal of iterable variable collections */

public class ForEach {
	public static void main(String[] args) {
		int[] intArr = {12, 45, 63};
		
		for (int element : intArr) // forEach loop syntax: (elementNo (i) : traversingArray)
			System.out.print(element + " ");
	}
}