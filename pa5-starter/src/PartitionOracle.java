
// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    /*
     * Return null if the pivot and after array reflect a correct partitioning of
     * the before array between low and high.
     *
     * Return a non-null String (your choice) describing why it isn't a valid
     * partition if it is not a valid result. You might choose Strings like these,
     * though there may be more you want to report:
     * 
     * 
     * 
     * all elements in original are present after the run
     * 
     * only the values in the range low to high changed
     * 
     * values in range low to high have been correctly partitioned
     * 
     */
    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        System.out.println("Pivot: " + pivot);
        if (before.length != after.length) {
            return "LENGTH CHECK: Before and After lengths are different!";
        }

        // check elements are retained
        boolean altered = false;
        String missing = "";
        ArrayList<String> temp = new ArrayList<String>(Arrays.asList(after));
        for (String s : before) {
            if (!temp.contains(s)) {
                missing = s;
                altered = true;
            }
        }
        if (altered) {
            return "ALTERED CHECK: elements in After do not match elements in Before! Missing: " + missing;
        }

        // check outside bounds are intact
        if (before.length >= 2) {
            for (int i = 0; i < low; i++) {
                if (!before[i].equals(after[i])) {
                    return "EXTERIOR CHECK: outside of lower bound does not match!";
                }
            }

            for (int i = high; i < before.length; i++) {
                if (!before[i].equals(after[i])) {
                    return "EXTERIOR CHECK: outside of lower bound does not match!";
                }
            }
        }

        // check this is partitioned
        // this is the string at the pivot!
        String pStr = after[pivot];

        for (int i = low; i < pivot; i++) {
            if (after[i].compareTo(pStr) > 0) {
                return "LOW CHECK: Element at index [" + i + "] is " + after[i] + " and greater than "
                        + pStr;
            }
        }
        for (int i = high - 1; i >= pivot; i--) {
            if (after[i].compareTo(pStr) < 0) {
                return "HIGH CHECK: Element at index [" + i + "] is " + after[i] + " and less than "
                        + pStr;
            }
        }

        return null;
    }

    public static String getRandLetter() {
        Random r = new Random();
        int asciiForACapLetter = r.nextInt(26) + 65; // Generates a random letter from A - Z
        return Character.toString((char) (asciiForACapLetter));

    }

    public static String[] generateInput(int n) {
        ArrayList<String> output = new ArrayList<>();
        for (; n > 0; n--) {
            output.add(getRandLetter());
        }

        return output.toArray(new String[] {});
    }

    public static CounterExample findCounterExample(Partitioner p) {
        // create a random String array between size 1 and 100
        String[] before = generateInput((int) (Math.random() * 10) + 1);
        String[] after = Arrays.copyOf(before, before.length);
        System.out.println("Before: " + Arrays.toString(before) + before.length);

        int low = 0;
        int high = after.length - 1;
        int pivot = p.partition(after, low, high);
        System.out.println("After: " + Arrays.toString(after));

        String output = isValidPartitionResult(before, low, high, pivot, after);
        if (output == null)
            return null;
        else {
            return new CounterExample(before, low, high, pivot, after, output);
        }
    }

}
