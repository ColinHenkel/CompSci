import java.util.*;

public class Main {
	public static void main(String[] args) {
		 MyClass obj = new MyClass();
		 obj.print();
	}

}

abstract class MyAbstract {
	public int i;
	public void print(int i) {
		i = 6;
		System.out.println(this.i);
	}
}
class MyClass extends MyAbstract {
	public void print() {
		i = 5;
		System.out.println(i);
	}
	public MyClass() {
		i = 4;
	}
}