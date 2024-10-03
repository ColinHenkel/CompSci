import java.util.*;

public class Lab1 {
    static Scanner in = new Scanner(System.in);
    static void printMedian(int n) {
        // max heap
        PriorityQueue<Integer> leftHalf = new PriorityQueue<>(Collections.reverseOrder());
        // min heap
        PriorityQueue<Integer> rightHalf = new PriorityQueue<>();
        
        // taking first heap input
        int data = in.nextInt();
        double median = data; // first number is the median when alone
        leftHalf.add((int)median);
        System.out.println(median);

        // taking further heap inputs
        for(int i = 1; i < n; i++) {
            data = in.nextInt();

            if(leftHalf.size() > rightHalf.size()) { // leftHalf >
                if(data < median) {
                    // data belongs in leftHalf
                    rightHalf.add(leftHalf.poll());
                    leftHalf.add(data);
                } else {
                    // data belongs in rightHalf
                    rightHalf.add(data);
                }
                median = (double)(rightHalf.peek() + leftHalf.peek()) / 2;
                System.out.println(median);
            } else if(leftHalf.size() == rightHalf.size()) { // halves ==
                if(data < median) {
                    leftHalf.add(data);
                    median = (double)leftHalf.peek();
                } else {
                    rightHalf.add(data);
                    median = (double)rightHalf.peek();
                }
                System.out.println(median);
            } else if(rightHalf.size() > leftHalf.size()) { // rightHalf >
                if(data < median) {
                    // data belongs in leftHalf
                    leftHalf.add(data);
                } else {
                    // data belongs in rightHalf
                    leftHalf.add(rightHalf.poll());
                    rightHalf.add(data);
                }
                median = (double)(rightHalf.peek() + leftHalf.peek()) / 2;
                System.out.println(median);
            }
        }
        in.close();
    }

    public static void main(String args[]) {
        System.out.println("Input the total amount of numbers: ");
        int n = in.nextInt();
        printMedian(n);
    }
}
