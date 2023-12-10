import java.util.*;

public class Pascals {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        
        // base case: numRows = 0
        if(numRows <= 0)
            return result;

        // create first row with number 1 only
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);            
        result.add(firstRow);

        for(int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = result.get(i - 1);

            // first element is always 1
            row.add(1);

            // calculate values basaed on values in row above
            for(int j = 1; j < i; j++)
                row.add(prevRow.get(j - 1) + prevRow.get(j));

            // last is always 1
            row.add(1);
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        Pascals pascal = new Pascals();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        List<List<Integer>> result = pascal.generate(n);
        for(List<Integer> row : result) {
            for(Integer num : row)
                System.out.print(num + " ");
            System.out.println();
        }
    }
}
