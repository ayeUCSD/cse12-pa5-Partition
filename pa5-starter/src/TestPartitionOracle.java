import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is an example of how to implement the Partitioner interface to implement
 * a concrete Partitioner. You can use this bad implementation to test your
 * PartitionOracle,
 * to ensure that it works in detecting a bad Partitioner. You should add a
 * correct implementation
 * of a Partitioner here, maybe one from class, to verify that your
 * PartitionOracle also works
 * correctly on good implementations. Once you implement part 2, you can also
 * test those Partitioner
 * implementations here as well.
 * 
 */
class CopyFirstElementPartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {
        if (high - low < 1)
            return 0;
        for (int i = 0; i < strs.length; i += 1) {
            strs[i] = strs[0];
        }
        return 0;
    }
}

public class TestPartitionOracle {
    @Test
    public void testCopyFirstElementPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
        System.out.println(ce);
        assertNotNull(ce);
    }

    @Test
    public void testValidPartitionResult() {
        String[] before = { "d", "b", "c", "a", "e", "d", "b", "c", "a", "e" };
        String[] after =  { "d", "b", "c", "a", "e", "c", "d", "d", "e", "e"  };
        int pivot = 4;
        int low = 0;
        int high = 4;
        assertNull(PartitionOracle.isValidPartitionResult(before, low, high, pivot, after));
    }

}
