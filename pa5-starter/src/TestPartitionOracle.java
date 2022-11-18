import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * This is an example of how to implement the Partitioner interface to implement
 * a concrete Partitioner. You can use this bad implementation to test your
 * PartitionOracle, to ensure that it works in detecting a bad Partitioner. You
 * should add a correct implementation of a Partitioner here, maybe one from
 * class, to verify that your PartitionOracle also works correctly on good
 * implementations. Once you implement part 2, you can also test those
 * Partitioner implementations here as well.
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

class PartitionFromClass implements Partitioner {

	PartitionFromClass() {
	}

	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	public int partitionFromClass(String[] strs, int low, int high) {
		int pivotStartIndex = high - 1;
		String pivot = strs[pivotStartIndex];
		int smallerBefore = low, largerAfter = high - 2;

		while (smallerBefore <= largerAfter) {
			if (strs[smallerBefore].compareTo(pivot) < 0) {
				smallerBefore += 1;
			} else {
				swap(strs, smallerBefore, largerAfter);
				// if (largerAfter + 1 < strs.length)
				{
					largerAfter += 1;
				}
			}
		}

		swap(strs, smallerBefore, pivotStartIndex);
		return smallerBefore; // this is now the pivot value/ index
	}
	/**
	 * Partitioner gotten from github below:
	 * https://github.com/pedrovgs/Algorithms/blob/master/src/main/java/com/github/pedrovgs/problem80/QuickSort.java
	 * Modified for strings
	 */
	public int partition(String[] str, int left, int right) {
		String pivot = str[right];
		int i = left - 1;
		for (int j = left; j < right; ++j) {
			if (str[j].compareTo(pivot) <= 0) {
				swap(str, ++i, j);
			}
		}
		swap(str, ++i, right);
		return i;
	}

}

public class TestPartitionOracle {
	@Test
	public void testCopyFirstElementPartition() {
		System.out.println("CopyFirstElementTest:");
		CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
		
		System.out.println(ce);
		assertNotNull(ce);
		System.out.println("Done!\n");
	}

	@Test
	public void testValidPartitionResult() {
		System.out.println("Testing ValidPartitionResult:");
		String[] before = { "d", "b", "c", "a", "e", "d", "b", "c", "a", "e" };
		String[] after = { "d", "b", "c", "a", "e", "c", "d", "d", "e", "e" };
		int pivot = 4;
		int low = 0;
		int high = 4;
		assertNull(PartitionOracle.isValidPartitionResult(before, low, high, pivot, after));
		System.out.println("Done!\n");
	}

	@Test
	public void testInternetPartition() {
		System.out.println("Testing testInternetPartition:");
		Partitioner p = new PartitionFromClass();
		CounterExample ce = PartitionOracle.findCounterExample(p);
		System.out.println(ce);
		assertNull(ce);
		System.out.println("Done!\n");
	}
	@Test
	public void testCenterPartition() {
		System.out.println("Testing CenterPartition:");
		Partitioner p = new CentralPivotPartitioner();
		CounterExample ce = PartitionOracle.findCounterExample(p);
		System.out.println(ce);
		assertNull(ce);
		System.out.println("Done!\n");
	}

	@Test
	public void testFirstPartition() {
		System.out.println("Testing FirstPivotPartition:");
		Partitioner p = new FirstElePivotPartitioner();
		CounterExample ce = PartitionOracle.findCounterExample(p);
		System.out.println(ce);
		assertNull(ce);
		System.out.println("Done!\n");
	}
}
