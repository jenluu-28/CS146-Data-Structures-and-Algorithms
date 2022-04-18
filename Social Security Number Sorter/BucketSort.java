package SSN;

import java.util.*;

//A class that implements Bucketsort
public class BucketSort 
{
	//Argument: an unsorted ArrayList A 
	//Outcome: sorted ArrayList A in ascending order
	public static void insertionSort(ArrayList<Integer> A)
	{
		//Start with the second element and continue until the entire array is reached
		for (int j = 1; j < A.size(); j++)
		{
			int key = A.get(j);
			
			//Insert A[j] into the sorted sequence A[1...j-1]
			int i = j - 1;
			while (i >= 0 && A.get(i) > key)
			{
			
				A.set(i+1, A.get(i));
				i--;
 			}
			A.set(i+1, key);
		
		}
	}
	
	
	//Argument: an unsorted int[] A
	//Outcome: int[] A will be sorted in ascending order
	public static void bucketSort(int[] A)
	{	
		//Create a bucket that contains 10 ArrayLists
		ArrayList<Integer>[] bucket = new ArrayList[10];
		
		
		//Initialize each ArrayList in the bucket
		for (int i = 0; i < 10; i++)
		{
		
			bucket[i] = new ArrayList<Integer>();
		}
		
		//Create 10 subintervals as conditions for the buckets
		for (int i = 0; i < A.length; i++)
		{
			//Divide the ssn by 10^8 to obtain a single digit and put the original number in the appropriate bucket
			int digit  = A[i] /  100000000;
			
			if (digit >= 0 && digit < 1 )
			{
				bucket[0].add(A[i]);
			}
			
			else if (digit >= 1 && digit < 2)
			{
				bucket[1].add(A[i]);
			}
			
			else if (digit >= 2 && digit < 3)
			{
				bucket[2].add(A[i]);
			}
			else if (digit >= 3 && digit < 4)
			{
				bucket[3].add(A[i]);
			}
			else if (digit >= 4  && digit < 5)
			{
				bucket[4].add(A[i]);
			}
			else if (digit >= 5 && digit < 6)
			{
				bucket[5].add(A[i]);
			}
			else if (digit >= 6 && digit< 7)
			{
				bucket[6].add(A[i]);
			}
			else if (digit >= 7 && digit < 8)
			{
				bucket[7].add(A[i]);
			}
			else if (digit >= 8 && digit < 9)
			{
				bucket[8].add(A[i]);
			}
			else if (digit >= 9 && digit < 10)
			{
				bucket[9].add(A[i]);
			}
			
		}
		
		//Sort the 10 lists with insertion sort
		for (int i = 0; i < 10; i++)
			
		{
			insertionSort(bucket[i]);
		}
		
	
		//Concatenate the lists together and update the original int[] A
		int index = 0;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < bucket[i].size(); j++)
			{
				A[index] = bucket[i].get(j);
				index++;
			}
		}
		
	}
	
	//Testing case
	public static void main(String[] args)
	{
		int[] test = new int[] {999999999, 111111111, 222222222};
		System.out.println("Before: "  + Arrays.toString(test));
		bucketSort(test);
		System.out.println("Results: " + Arrays.toString(test));
		System.out.println("Expected: [111111111, 222222222, 999999999]");
	}

}
