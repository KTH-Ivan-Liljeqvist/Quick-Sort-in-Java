import java.util.Arrays;


public class Main {
	
		public static void main(String []args){
			/*
			 * Analyze the performance sorting a vector.
			 */
			//for(int i=0;i<20;i++){
				/*PerformanceAnalyzer.analyzeJavaSortVector(Data.Order.RANDOM);
				PerformanceAnalyzer.analyzeJavaSortVector(Data.Order.ASCENDING);
				PerformanceAnalyzer.analyzeJavaSortVector(Data.Order.DESCENDING);
				PerformanceAnalyzer.analyzeJavaSortVector(Data.Order.ONLY_ONE_NUMBER);*/
				
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM_AND_INSERTION,Data.Order.RANDOM);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.RANDOM);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.RANDOM);
				/*PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM_AND_INSERTION,Data.Order.ASCENDING);
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM_AND_INSERTION,Data.Order.DESCENDING);
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM_AND_INSERTION,Data.Order.ONLY_ONE_NUMBER);*/
				
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED_AND_INSERTION,Data.Order.RANDOM);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED_AND_INSERTION,Data.Order.ASCENDING);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED_AND_INSERTION,Data.Order.DESCENDING);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED_AND_INSERTION,Data.Order.ONLY_ONE_NUMBER);
				
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM,Data.Order.RANDOM);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM,Data.Order.ASCENDING);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM,Data.Order.DESCENDING);
				//PerformanceAnalyzer.analyzeQuickSortVector(SortMode.RANDOM,Data.Order.ONLY_ONE_NUMBER);
				
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.RANDOM);
				/*PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.ASCENDING);
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.DESCENDING);
				PerformanceAnalyzer.analyzeQuickSortVector(SortMode.FIXED,Data.Order.ONLY_ONE_NUMBER);*/
			//}
			
			
			
			/*
			 * Analyze and find the threshold.
			 */
			//PerformanceAnalyzer.analyzeWhenToSwitchToInsertion(SortMode.FIXED_AND_INSERTION);
			//PerformanceAnalyzer.analyzeWhenToSwitchToInsertion(SortMode.RANDOM_AND_INSERTION);
		}

}
