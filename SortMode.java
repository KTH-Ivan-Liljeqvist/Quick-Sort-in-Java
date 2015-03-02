/**
 * Enumeration to hold the different sorting modes that can be used to initiate the QuickSort Class.
 * @author IvanLiljeqvist
 *
 * Description of the different values:
 *
 * FIXED - the pivot index will be set to 0 by default.
 * RANDOM - the pivot index will be randomly generated.
 * FIXED_AND_INSERTION - the pivot index will be set to 0 by default AND insertion sort will be used at the end.
 * RANDOM_AND_INSERTION - the pivot index will be randomly generated AND insertion sort will be used at the end.
 */
public enum SortMode {
	 FIXED, RANDOM, FIXED_AND_INSERTION, RANDOM_AND_INSERTION
}
