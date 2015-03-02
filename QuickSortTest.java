import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * This class is testing the functionality of the QuickSort class.
 * All of the different modes of the class are tested.
 * 
 * This class is not testing the performance of the QuickSort class. It only tests the correctness of the calculations.
 * The results from this class are compared to Java's implementation of the sort function (Arrays.sort).
 * 
 * @author IvanLiljeqvist
 * @version 25-02-2015
 *
 */

public class QuickSortTest {
	
	/**
	 * Test the constructors of the QuickSort class.
	 * The constructors play important role in setting the different flags and initializing fields.
	 * 
	 * All of the different combinations of parameters will be tested.
	 */
	@Test
	public void constructorTest(){
		
		QuickSort qs1=new QuickSort();
		assertEquals(qs1.getSortMode(),SortMode.FIXED); // default mode if nothing is specified in the constructor
		assertEquals(qs1.getArraySizeAtWhichRunInsertionSort(),-1); //-1 is the code which means that insertion sort is turned off
		
		QuickSort qs2=new QuickSort(SortMode.RANDOM);
		assertEquals(qs2.getSortMode(),SortMode.RANDOM); 
		assertEquals(qs1.getArraySizeAtWhichRunInsertionSort(),-1);
		
		QuickSort qs3=new QuickSort(SortMode.FIXED_AND_INSERTION);
		assertEquals(qs3.getSortMode(),SortMode.FIXED_AND_INSERTION);
		//-1 is the code which means that insertion sort is turned off
		//although FIXED_AND_INSERTION is passed in, insertion sort will be turned off because no threshold specified
		assertEquals(qs3.getArraySizeAtWhichRunInsertionSort(),-1); 
		
		QuickSort qs4=new QuickSort(SortMode.RANDOM_AND_INSERTION);
		assertEquals(qs4.getSortMode(),SortMode.RANDOM_AND_INSERTION);
		//-1 is the code which means that insertion sort is turned off
		//although RANDOM_AND_INSERTION is passed in, insertion sort will be turned off because no threshold specified
		assertEquals(qs4.getArraySizeAtWhichRunInsertionSort(),-1); 
		
		QuickSort qs5=new QuickSort(SortMode.FIXED_AND_INSERTION,100);
		assertEquals(qs5.getSortMode(),SortMode.FIXED_AND_INSERTION);
		//now we've passed in the threshold which will set the arraySizeAtWhichRunInsertion
		//insertion sort is now turned on
		assertEquals(qs5.getArraySizeAtWhichRunInsertionSort(),100); 
		
		QuickSort qs6=new QuickSort(SortMode.RANDOM_AND_INSERTION,100);
		assertEquals(qs6.getSortMode(),SortMode.RANDOM_AND_INSERTION);
		//now we've passed in the threshold which will set the arraySizeAtWhichRunInsertion
		//insertion sort is now turned on
		assertEquals(qs6.getArraySizeAtWhichRunInsertionSort(),100); 
		
		QuickSort qs7=new QuickSort(SortMode.FIXED,100);
		assertEquals(qs7.getSortMode(),SortMode.FIXED);
		//we pass in the threshold, but the mode doesn't support insertion
		//insertion is therefore turned off
		assertEquals(qs7.getArraySizeAtWhichRunInsertionSort(),-1); 
		
		QuickSort qs8=new QuickSort(SortMode.RANDOM,100);
		assertEquals(qs8.getSortMode(),SortMode.RANDOM);
		//we pass in the threshold, but the mode doesn't support insertion
		//insertion is therefore turned off
		assertEquals(qs8.getArraySizeAtWhichRunInsertionSort(),-1); 
		
	}
	
	/**
	 * Tests the functionality of the QuickSort class initialized with RANDOM_AND_INSERTION mode with different insertion thresholds.
	 * Random, sorted and reverse sorted arrays are tested.
	 * Arrays containing numbers that are the same are tested.
	 */
	@Test
	public void randomInsertionTest() {
		//Test random order.
		quickSortVersusJava(new Data(10,3000,Data.Order.RANDOM),SortMode.RANDOM_AND_INSERTION,5);
	    quickSortVersusJava(new Data(10000,30030,Data.Order.RANDOM),SortMode.RANDOM_AND_INSERTION,5);
		quickSortVersusJava(new Data(10000,3000,Data.Order.RANDOM),SortMode.RANDOM_AND_INSERTION,5);
		
		//Sorted arrays.
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.RANDOM_AND_INSERTION,3);
		quickSortVersusJava(new Data(10000,30030,Data.Order.ASCENDING),SortMode.RANDOM_AND_INSERTION,2);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.RANDOM_AND_INSERTION,4);
		
		//Reverse sorted arrays.
		quickSortVersusJava(new Data(10000,1,Data.Order.DESCENDING),SortMode.RANDOM_AND_INSERTION,30);
		quickSortVersusJava(new Data(10000,3000,Data.Order.DESCENDING),SortMode.RANDOM_AND_INSERTION,20);
		quickSortVersusJava(new Data(100000,1,Data.Order.DESCENDING),SortMode.RANDOM_AND_INSERTION,0);
				
	    //Arrays containing elements that are the same.
		quickSortVersusJava(new Data(10000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM_AND_INSERTION,5);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM_AND_INSERTION,12);
	    quickSortVersusJava(new Data(500000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM_AND_INSERTION,7);
	}
	
	/**
	 * Tests the functionality of the QuickSort class initialized with RANDOM mode.
	 * Random, sorted and reverse sorted arrays are tested.
	 * Arrays containing numbers that are the same are tested.
	 */
	@Test
	public void randomTest() {

		//Test random order.
		quickSortVersusJava(new Data(10,3000,Data.Order.RANDOM),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,3000,Data.Order.RANDOM),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,1,Data.Order.RANDOM),SortMode.RANDOM);
		
		//Sorted arrays.
		quickSortVersusJava(new Data(10000,1,Data.Order.ASCENDING),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,1,Data.Order.ASCENDING),SortMode.RANDOM);
		
		//Reverse sorted arrays.
		quickSortVersusJava(new Data(10000,1,Data.Order.DESCENDING),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,3000,Data.Order.DESCENDING),SortMode.RANDOM);
		quickSortVersusJava(new Data(100000,1,Data.Order.DESCENDING),SortMode.RANDOM);
		
		//Arrays containing elements that are the same.
		quickSortVersusJava(new Data(10000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM);
		quickSortVersusJava(new Data(500000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.RANDOM);
		
	}
	
	/**
	 * Tests the functionality of the QuickSort class initialized with FIXED_AND_INSERTION mode with different insertion thresholds.
	 * Random, sorted and reverse sorted arrays are tested.
	 * Arrays containing numbers that are the same are tested.
	 */
	@Test
	public void fixedInsertionTest() {
		//Test random order.
		quickSortVersusJava(new Data(10,3000,Data.Order.RANDOM),SortMode.FIXED_AND_INSERTION,5);
	    quickSortVersusJava(new Data(10000,30030,Data.Order.RANDOM),SortMode.FIXED_AND_INSERTION,5);
		quickSortVersusJava(new Data(10000,3000,Data.Order.RANDOM),SortMode.FIXED_AND_INSERTION,5);
		
		//Sorted arrays.
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.FIXED_AND_INSERTION,3);
		quickSortVersusJava(new Data(10000,30030,Data.Order.ASCENDING),SortMode.FIXED_AND_INSERTION,2);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.FIXED_AND_INSERTION,4);
		
		//Reverse sorted arrays.
		quickSortVersusJava(new Data(10000,1,Data.Order.DESCENDING),SortMode.FIXED_AND_INSERTION,30);
		quickSortVersusJava(new Data(10000,3000,Data.Order.DESCENDING),SortMode.FIXED_AND_INSERTION,20);
		quickSortVersusJava(new Data(100000,1,Data.Order.DESCENDING),SortMode.FIXED_AND_INSERTION,0);
				
	    //Arrays containing elements that are the same.
		quickSortVersusJava(new Data(10000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED_AND_INSERTION,5);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED_AND_INSERTION,12);
	    quickSortVersusJava(new Data(500000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED_AND_INSERTION,7);
	}
	
	/**
	 * Tests the functionality of the QuickSort class initialized with FIXED mode.
	 * Random, sorted and reverse sorted arrays are tested.
	 * Arrays containing numbers that are the same are tested.
	 */
	@Test
	public void fixedTest() {

		//Test random order.
		quickSortVersusJava(new Data(10,3000,Data.Order.RANDOM),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,30030,Data.Order.RANDOM),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,3000,Data.Order.RANDOM),SortMode.FIXED);
		
		//Sorted arrays.
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ASCENDING),SortMode.FIXED);
		
		//Reverse sorted arrays.
		quickSortVersusJava(new Data(10000,1,Data.Order.DESCENDING),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,3000,Data.Order.DESCENDING),SortMode.FIXED);
		quickSortVersusJava(new Data(100000,1,Data.Order.DESCENDING),SortMode.FIXED);
		
		//Arrays containing elements that are the same.
		quickSortVersusJava(new Data(10000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED);
		quickSortVersusJava(new Data(10000,3000,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED);
		quickSortVersusJava(new Data(500000,1,Data.Order.ONLY_ONE_NUMBER),SortMode.FIXED);
		
	}
	
	/**
	 * Helper method that takes in a Data object containing the array you want to sort
	 * and a SortMode which will be used to sort.
	 * The method sorts the array with QuickSort class and Java's Arrays.sort and compares the results.
	 * The test passes if the results are the same and fails if the results are different.
	 */
	private void quickSortVersusJava(Data d,SortMode mode){
		int []quickSortArray=d.get();
		int []javaArray=d.get();
		
		QuickSort qs=new QuickSort(mode);
		qs.sort(quickSortArray);
		Arrays.sort(javaArray);
		
		assertTrue(Arrays.equals(quickSortArray, javaArray));
	}
	/**
	 * Helper method that takes in a Data object containing the array you want to sort,
	 * a SortMode which will be used to sort and a threshold for insertion sort.
	 * The method sorts the array with QuickSort class and Java's Arrays.sort and compares the results.
	 * The test passes if the results are the same and fails if the results are different.
	 */
	private void quickSortVersusJava(Data d,SortMode mode,int insertionThreshold){
		int []quickSortArray=d.get();
		int []javaArray=d.get();
		
		QuickSort qs=new QuickSort(mode,insertionThreshold);
		qs.sort(quickSortArray);
		Arrays.sort(javaArray);
		
		assertTrue(Arrays.equals(quickSortArray, javaArray));
	}

}
