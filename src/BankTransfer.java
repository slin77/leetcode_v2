import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankTransfer {

    public List<String> simplify(List<String> inputs) {
        int[] toA = new int[255];
        int[] aTo = new int[255];

        // x y become
        // x to C
        // C to Y

        // if C already have c to x or x to c, this can merge.


        for (String txn : inputs) {
            char from = txn.charAt(0);
            char to  = txn.charAt(1);
            int amount = Integer.valueOf(txn.substring(2));

            if (from == 'a') {
                toA[to] -= amount;
            } else if (to == 'a') {
                toA[from] += amount;
            } else {
                // B to A
                // A to C
                toA[from] += amount;
                aTo[to] += amount;
            }
        }

        List<String> output = new ArrayList<>();
        for (int i = 0; i < 255; i++) {
            if (toA[i] > 0) {
                output.add((char) i + "a" + toA[i]);
            } else if (toA[i] < 0) {
                aTo[i] += -toA[i];
            }

            if (aTo[i] > 0) {
                output.add("a" + (char)i + aTo[i]);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList("ab1", "ba2", "bc1");

        List<String> test2 = Arrays.asList("ab1", "ba9", "bc1");// ba9, ac1

        List<String> test3 = Arrays.asList("ab9", "ba1");// ab8

        List<String> test4 = Arrays.asList("ab0", "ba0");

        List<String> test5 = Arrays.asList("ab0", "ba0", "ac10", "cd10"); // "ad10"

        List<String> test6 = Arrays.asList("ef1"); // "ea1", "af1"

        BankTransfer bt = new BankTransfer();

        System.out.println(bt.simplify(test));
        System.out.println(bt.simplify(test2));
        System.out.println(bt.simplify(test3));
        System.out.println(bt.simplify(test4));
        System.out.println(bt.simplify(test5));
        System.out.println(bt.simplify(test6));
    }
}
