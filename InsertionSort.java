/**
 * @version 22-02-2015
 * @author IvanLiljeqvist
 * 
 * Class representing insertion sort - an algorithm for used for sorting.
 *
 */

public class InsertionSort{

	/**
	 * Sorts an array of integers.
	 * @params v - the element to be sorted.
	 */
	
	public void sort(int[] v,int low,int high) {
		

	    for (int j=low+1;j<=high;j++){
	      //get value at index
	      int value=v[j];
	      //duplicate index
	      int k=j;

	      //go from index backwards and move all elements forward 
	      while(k>low&&v[k-1]>=value){
	        v[k]=v[k-1];
	        k--;
	      }
	 
	      v[k]=value;
	      
	    }
		
	}

}
