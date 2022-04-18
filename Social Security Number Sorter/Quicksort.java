package SSN;

import java.util.Arrays;

//A class that implements Quicksort 
public class Quicksort 
{
	
	//Arguments: an unsorted int[], the first index p, and the index of the pivot
	//Outcome: will sort the same array in ascending order
	public static void quicksort(int A[], int p, int r)
	{
		//Checking conditions: the pivot index must not be less than the first index
		if ( p < r)
		{
			
			//Split up the array 
			int q = partition(A, p, r);
			
			//Sort left subarray
			//Elements are less than or equal to A[r]
			quicksort(A, p, q-1);
			
			//Sort right subarray
			//Elements are greater than A[r]
			quicksort(A, q+1, r);
		}
	}
	
	//Arguments: int[], the first index, and the index of the pivot
	//Outcome: will split the array into two subarrays where the sorting will take place
	public static int partition(int[] A, int p, int r)
	{
		//Obtain the pivot
		int x = A[r];
		int i = p - 1;
		
		//Go through the array
		for (int j = p; j < r; j++)
		{
			if (A[j] <= x)
					{
						i++;
						
						//exchange A[i] and A[j]
						int placeholder = A[i];
						A[i] = A[j];
						A[j] = placeholder;
					}
		}
		
		//exchange A[i+1] and A[r]
		 int placeholder2 = A[i+1];
		 A[i+1] = A[r];
		 A[r] = placeholder2;
		 
		 //Return q = i + 1
		 return i+1;
	}
	
	//Testing case 
	public static void main(String[] args)
	{
		int[] test = new int[] {2, 9, 10, 10, 0, 4, 18};
		System.out.println("Before: " + Arrays.toString(test));
		
		Quicksort q = new Quicksort();
		
		q.quicksort(test, 0, 6);
		System.out.println("Results: " + Arrays.toString(test));
		System.out.println("Expected: [0, 2, 4, 9, 10, 10, 18]");
		
		int x = (24/10) % 10;
		System.out.println(x);
		
				
	}
	
	
}
