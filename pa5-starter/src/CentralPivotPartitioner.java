import java.util.Arrays;

// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner {

	@Override
	public int partition(String[] strs, int low, int high) {
		int pIndex = high/2;
		String p = strs[pIndex];
		
		while(high > low) {
			//if high is less than pVal
			while(strs[high].compareTo(p) > 0 && high != low) {
				high--;
			}
			while(strs[low].compareTo(p) <= 0 && high != low) {
				low++;
			}
			swap (strs, low, high);
			}
		
		System.out.println("Low " +low+ strs[low] + " | High " +high+ strs[high]);
		System.out.println(Arrays.toString(strs));
		//if(strs[high].compareTo(p) > 0)
			swap(strs,high,pIndex);
		return low;
	}

	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
}
