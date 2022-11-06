// You can (and should) add "implements Partitioner" below once you have the implementation ready
import java.util.ArrayList;
import java.util.Arrays;
public class FirstElePivotPartitioner implements Partitioner {
        //testing
	public static void swap(String[] array, int i1, int i2) {
		String temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	@Override
	public int partition(String[] strs, int low, int high) {
		// TODO Auto-generated method stub
		String[] temp = Arrays.copyOfRange(strs, low, high);
		ArrayList<String> strList = new ArrayList<>(Arrays.asList(temp));
		ArrayList<String> greaterList = new ArrayList<>();
		String p = strList.remove(0);
		System.out.println("pivot value: " + p);
		
		//if a value is greater or equal to the pivot, add it to the right side
		for(String s : strList) {
			if(s.compareTo(p) >= 0) {
				greaterList.add(s);
			}
		}
		System.out.println("Final Right Side: " + greaterList);
		System.out.println("Before Left Side remove: " + strList);
		
		
		//remove everything from left that is in the right
		strList.removeAll(greaterList);
		System.out.println("Final Left Side: " + strList);
		
		//store the index
		int pIndex = strList.size();
		
		ArrayList<String> output = new ArrayList<>();
		
		//add left side
		for(String s : strList) {
			output.add(s);
		}
		
		//add the pivot value, and the right side to the left side
		output.add(p);
		
		//add right side
		for(String s : greaterList) {
			output.add(s);
		}
		
		System.out.println("Final: " + strList);
		strs =  output.toArray(new String[strs.length]);
		return pIndex;
	}
	
	
}
