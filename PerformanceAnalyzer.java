import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class PerformanceAnalyzer {
	
	public static void analyzeJavaSortVector(Data.Order vectorOrder){
		Stopwatch c = new Stopwatch();
		int[] data=new Data(1000,10000,vectorOrder).get();
		
		c.reset();
		c.start();
		Arrays.sort(data);
		c.stop();
		
		System.out.println("Sorted 1000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(10000,10000,vectorOrder).get();
		c.reset();
		c.start();
		Arrays.sort(data);
		c.stop();
		
		System.out.println("Sorted 10000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(100000,10000,vectorOrder).get();
		c.reset();
		c.start();
		Arrays.sort(data);
		c.stop();
		
		System.out.println("Sorted 100000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(1000000,10000,vectorOrder).get();
		c.reset();
		c.start();
		Arrays.sort(data);
		c.stop();
		
		System.out.println("Sorted 1000000 elements in "+c.milliseconds()+" nanoseconds");
	}
	
	public static void analyzeQuickSortVector(SortMode mode,Data.Order vectorOrder){
		
		QuickSort qs=new QuickSort(mode);
		final int OPTIMAL_K=46;
		
		if(mode==SortMode.FIXED_AND_INSERTION||mode==SortMode.RANDOM_AND_INSERTION){
			qs=new QuickSort(mode,OPTIMAL_K);
		}
		
		
		System.out.println(mode.toString()+" PIVOT, "+vectorOrder.toString()+" DATA");
		int[] data=new Data(1000,1000,vectorOrder).get();
		
		Stopwatch c = new Stopwatch();
		
		c.reset();
		c.start();
		qs.sort(data);
		c.stop();
		
		System.out.println("Sorted 1000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(10000,1000,vectorOrder).get();
		c.reset();
		c.start();
		qs.sort(data);
		c.stop();
		
		System.out.println("Sorted 10000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(100000,1000,vectorOrder).get();
		c.reset();
		c.start();
		qs.sort(data);
		c.stop();
		
		System.out.println("Sorted 100000 elements in "+c.milliseconds()+" nanoseconds");
		
		data=new Data(1000000,1000,vectorOrder).get();
		c.reset();
		c.start();
		qs.sort(data);
		c.stop();
		
		System.out.println("Sorted 1000000 elements in "+c.milliseconds()+" nanoseconds");
		
	}

	public static void analyzeWhenToSwitchToInsertion(SortMode mode){

		ArrayList<Integer> thresholds=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			thresholds.add(findThresholdForInsertion(mode,1000));
		}

		System.out.println("Threshold max 1000 found: "+calculateAverage(thresholds));


		thresholds=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			thresholds.add(findThresholdForInsertion(mode,1000000));
		}

		System.out.println("Threshold max 1000000 found: "+calculateAverage(thresholds));
		
		thresholds=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			thresholds.add(findThresholdForInsertion(mode,10000));
		}

		System.out.println("Threshold max 10000 found: "+calculateAverage(thresholds));
		
		thresholds=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			thresholds.add(findThresholdForInsertion(mode,100000));
		}

		System.out.println("Threshold max 100000 found: "+calculateAverage(thresholds));
		
	}

	private static int findThresholdForInsertion(SortMode mode,int max){
		int incrementThreshold=1;

		Stopwatch c = new Stopwatch();
		c.start();
		c.reset();

		//create a table containing QuickSort instances with different thresholds
		ArrayList<QuickSort> list=new ArrayList<QuickSort>();
		for(int i=0;i<100;i+=incrementThreshold){
			list.add(new QuickSort(mode,i));
		}



		QuickSort standard=new QuickSort(mode);
		ArrayList<Long>results=new ArrayList<Long>();

		for(QuickSort qs:list){

			int[] data=new Data(max,1000,Data.Order.RANDOM).get();
			int[] data2=Arrays.copyOf(data,data.length);


			c.reset();
			c.start();
			qs.sort(data);
			c.stop();
			long mill1=c.nanoseconds();


			c.reset();
			c.start();
			standard.sort(data2);
			c.stop();
			long mill2=c.nanoseconds();

			long diff=mill2-mill1;
			results.add(diff);


		}

		return getMaxIndex(results)*incrementThreshold;

	}

	private static int getMaxIndex(ArrayList<Long> list){
		long max = Long.MIN_VALUE;

		int i=0;
		int toReturn=0;

		for(i=0; i<list.size(); i++){
			if((long)list.get(i) > max){
				max = (long)list.get(i);
				toReturn=i;
			}
		}

		return toReturn;
	}

	private static int calculateAverage(ArrayList<Integer> list){
		int sum=0;
		for(int i:list){
			sum+=i;
		}

		return sum/list.size();
	}

}
