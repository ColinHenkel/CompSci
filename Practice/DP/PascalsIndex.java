import java.util.*;

public class PascalsIndex {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        // init first row
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result = firstRow;

        // create each subsequent row replacing result with newRow each time until we reach given index
        for(int i = 1; i <= rowIndex; i++) {
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1);

            for(int j = 1; j < i; j++)
                newRow.add(result.get(j-1) + result.get(j));
            
            newRow.add(1);
            result = newRow;
        }
        return result;
    }

    public static void main(String[] args) {
        PascalsIndex pascal = new PascalsIndex();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        List<Integer> row = pascal.getRow(n);
        for(Integer num : row)
            System.out.print(num + " ");
        System.out.println();
    }
}
