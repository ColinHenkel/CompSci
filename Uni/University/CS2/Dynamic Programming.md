# Algorithm Design Techniques
- So far in this class we have covered:
	- [[Backtracking]] algorithms
	- [[Greedy Algorithm]]
	- [[Divide Conquer]] Algorithm
	- These notes will cover: Dynamic Programming Algorithms
- These are the 4 common types of algorithms used to solve problems
	- For many problems, it is very likely that some variation of at least one of these methods will work
# Dynamic Programming Intro
- This class has covered several algorithms that involve recursion
- In some situations, these algorithms solve fairly difficult problems efficiently
	- *BUT* in other cases they are ***inefficient because they recalculate certain function values many times***
	- An example of this would be **Fibonacci**
## Fibonacci
- ***Fibonacci numbers*** are defined recursively, thus, coding them is quite easy based on the original mathematical definition:
	- $$F_0=0,F_1=1,F_n=F_{n-1}+F_{n-2}, n>1$$
	- List of Fibonacci numbers:
		- 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610 ,,...
	- Basic function:
```java
int fib(int n) {
	if(n <= 1)
		return n;
	else
		return fib(n-1) + fib(n-2);
}
```
- Run-time analysis of above code:
	- Fib is called two times with n-1 and n-2
	- For simplicity of calculation, I will write approximately
	- $$T(n)=2T(n-1)+1$$
	- So using master theorem of decreasing function:
		- a=2, b=1, f(n) = 1
		-  $$T(n)=O(a^\frac{n}{d}f(n))=O(2^n)$$
	- Therefore, the function is exponential
# Dynamic Programming Methods
- Dynamic Programming offers two methods to solve a problem:
	- **Top-down** with Memoization
	- **Bottom-up** with Tabulation
- ## Memoization Method:
	- The goal of the Memoization method is to solve the bigger problem by recursively finding the solution to smaller sub-problems
	- Whenever it solves a sub-problem, the result is cached so that it doesn't end up solving it repeatedly if it's called multiple times
	- Instead, it can just return the saved result
	- [Slide 10 = Memoization Example](https://webcourses.ucf.edu/courses/1433664/files/101210147?module_item_id=17353131)
	- Using Memoization, we can reduce the Fibonacci runtime:
		- $$O(2^n)\gg O(n)$$
	- Fibonacci with Memoization Code:
```java
class Fibonacci {
	public int CalculateFibonacci(int n) {
		int memoize[] = new int[n+1];
		Arrays.fill(memoize, -1);
		return CalculateFibonacciRecursive(memoize, n);
	}
	public int FibRecursive(int[] memoize, int n) {
		if(n < 2)
			return n;
		if(memoize[n] != -1)
			return memoize[n];
		memoize[n] = FibRecursive(memoize, n-1) + FibRecursive(memoize, n-2);
		return memoize[n];
	}
}
```
- ## Tabulation Method
	- The Memoization code was recursive in order to make it efficient
	- Tabulation will be a *bottom-up* approach that is without recursion
	- Tabulation is the opposite of *top-down* and avoids recursion, as in this approach all related sub-problems are solved first
	- This is typically done by filling up an n-dimensional table
	- Based on the results in the table, the solution to the original problem is then computed
	- [Slide 19 = Tabulation Example](https://webcourses.ucf.edu/courses/1433664/files/101210147?module_item_id=17353131)
	- Fibonacci with Tabulation Code:
```java
class Fibonacci {
	public int CalculateFibonacci(int n) {
		if(n==0) return 0;
		int dp[] = new int[n+1];
		// base cases
		dp[0] = 0;
		dp[1] = 1;

		for(int i = 2; i <= n; i++)
			dp[i] = dp[i-1] + dp[i-2];
		return dp[n];
	}
}
```


