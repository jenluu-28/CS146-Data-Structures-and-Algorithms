package SSN;

import java.util.*;

//A class that implements Radix sort, which uses Counting sort as an intermediate sorting step
public class RadixSort 
{
 
	//Arguments: an unsorted int[] A, an allocated int[] B for the output, and int d to specify the digit to be sorted on
	//Outcome: the array int[] A will be sorted and updated based on the digit d, the output will be stored in int[] B
	//Note: d is expected to be some power of 10 
	public static void countingSort(int[] A, int[] B, int d)
	{
		//Create int[] C to count the occurrences of the digits
		//The size is base 10 because the digits available are from 0-9
		int[] C = new int[10]; 
		
		//Initialize the elements of C[i] to 0
		for (int i = 0; i < C.length; i++)
		{
			C[i] = 0;
		}
		
		//Obtain the occurrences of each digit 
		for (int j = 0; j < A.length; j++)
		{
			
			// A[j]/d to specify the desired digit 
			// Ex. Want 2 from 244 --> 244/100 = 2
			// %10 to obtain the actual digit and add to the count 
			C[(A[j]/d) % 10] = C[(A[j]/d) %10] + 1;
			
			//C[i] now contains the number of elements equal to i
		}
		
		//Add up the cumulative occurrences 
		for (int i = 1; i < C.length; i++)
		{
			C[i] = C[i] + C[i-1];
			
			//C[i] now contains the number of elements less than or equal to i 
		}
		
		
		//Sort the elements based on the position of C[i] and store into B
		for (int j = A.length - 1; j >= 0; j--)
		{
			B[C[(A[j] / d) % 10] - 1] = A[j];
			
			//Decrement C[i] each time it is used to store an element
			C[(A[j] / d) % 10] = C[(A[j] / d) % 10] - 1;
		}
		
		//Update A to match the order of the elements in B
		for (int n = 0; n < A.length; n++)
		{
			A[n] = B[n];
		}
		
	}
	
	//Arguments: an unsorted int[] A, highest-order digit d 
	public static void radixSort(int[] A, int d)
	{
		//Create auxiliary array B 
		int[] B = new int[A.length];
		
		//Go through the iteration of each digit 
		//Math.pow(10, d) will start with the LSD and continue with the RSD
		for (int i = 1; i <= Math.pow(10, d); i = i * 10)
		{
			//Perform countingsort on the entire int[] A
			for (int j = 0; j < A.length; j++)
			{
				countingSort(A, B, i);
			}
			
		}
		
		
	}
	
	//Testing case
	public static void main(String[] args)
	{
		int[] tester = new int[] {120391, 2130, 213, 840, 100};
		System.out.println("Before: " + Arrays.toString(tester));
		RadixSort r = new RadixSort();
		r.radixSort(tester, 7 );
		System.out.println("Results: " + Arrays.toString(tester));
		System.out.println("Expected: [100, 213, 840, 2130, 120391]");
	}


}
