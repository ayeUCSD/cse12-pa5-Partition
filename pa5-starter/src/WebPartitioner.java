// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class WebPartitioner {
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
	
	
	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
}
