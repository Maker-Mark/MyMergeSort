import java.util.*;
import java.io.*;

public class MergeSortMain{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Hi");
		int[] arr = new int[10];
		Random rd = new Random();
		for(int i=0;i<9; i++){
			arr[i] = rd.nextInt(10);
			System.out.println(arr[i]);
		}
		mergeSort(arr, 0 ,10);
		double time1= System.currentTimeMillis();
		System.out.printf( "%s %.2f \n", "Current Time in Millisec",  time1);
		System.out.println("Is it sorted;)?");
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] +" , ");
		double time2= System.currentTimeMillis();
		System.out.printf("%n%s %.2f%n%s %.2f %s" , "Now it's:", time2 , "that took about about " , ( time2- time1) , " MilliSeconds");
	}

	// We have "two arrays" but they are really be in one array. They must be sorted
	public static void merge(int[] arr, int start, int mid, int end)
	{
		// So the precondition: arr[start mid-1]
		// and arr[mmid... end -1] are already sorted
		int[] temp = new int[end-start];// the number of elements in total.
		// new array to not over write the stuff as we are looking at it.
		// we need three indexes, one into temp one into left and one into right(only need 2 and use the sum, but three is more clear)
		int index = 0; //temp[goes from zero to end - start]
		int indexL = start; // arr[start until mid-1]
		int indexR = mid;// goes from arr[mid to end -1]
		while(indexL < mid && indexR < end){
			// while i have stuff in both arrays to look at:check which one is smaller 
			if(arr[indexL] < arr[indexR]){ // pick the left one
				temp[index] = arr[indexL];
				indexL++;
			}else{
				temp[index] = indexR; 
				indexR++;
			}
			index++;// increment the index no matter what    
		}// ends the while loop

		// if the left hand side still has elements sort them, meaning the right hand side is finished.
		while(indexL < mid){
			temp[index]= arr[indexL];
			indexL++;
			index++;
		}
		while(indexR<end){
			temp[index] = arr[indexR];
			indexR++;
			index++;
		}

		for(int i =0; i<end-start; i++){
			// go through  original  array and copy the stuff in temp
			// we might be doing this from other position than 0;
			// are start and end are passed in as parameter
			arr[start+i]= temp[i];
		}
	}

	/* Actual merge-sort that does the actual sorting. 
	 * Logic: Makes sure the array is not already sorted
	 * Finds the middle of the array
	 * Calls itself on the "right" half, and "left" half
	 * Works recursively, almost tail-recursive, however needs to call merge,
	 * which does depend on previous data.
	 * On each meregeSort call, the array is sub-divided until you really have two single arrays,
	 * then the merge method puts it all together.  
	 */
	public static void mergeSort(int[] arr, int start, int end){
		if((end - start) <= 1) // meaning I have one or no elements
			return;
		//  In regards to space complexity, we can identify that we
		// are doing a lot of division so we know there is O(logn) in here
		// However, it's actually O(nlogn), which is provable via mathematical induction and the base case
		// that mergeSort with an array of size one is O(1). 
		// if it's not a tiny array, lets find the midpoint
		int  mid = (start + end)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid, end);
		merge(arr, start, mid, end);


	}
}



