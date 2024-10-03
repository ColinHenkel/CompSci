public class TwoChocolates {
    public static void main(String[] args) {
        // main method to test buyChoco
        int[] prices = {1, 2, 2};
        int money = 3;
        System.out.println(buyChoco(prices, money));
    }

    public static int buyChoco(int[] prices, int money) {
        // You are given an integer array prices representing the prices of chocolates in a shop.
        // You have a total of money dollars to spend on chocolates.
        // You must buy exactly two chocolates in such a way that you stil have money dollars left.
        // You would like to minimize the sum of the prices of the two chocolates.
        // Return the amount of money you will have leftover after buying the two chocolates.
        // If you cannot afford to buy any two chocolates, return money.
        
        // greedy implementation
        int firstMinCost = Integer.MAX_VALUE;
        int secondMinCost = Integer.MAX_VALUE;
        for (int p : prices) {
            if (p < firstMinCost) {
                secondMinCost = firstMinCost;
                firstMinCost = p;
            } else {
                secondMinCost = Math.min(secondMinCost, p);
            }
        }
        int leftover = money - (firstMinCost + secondMinCost);
        return leftover >= 0 ? leftover : money;   
    }
}
