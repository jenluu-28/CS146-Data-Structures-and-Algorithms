package SSN;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.IOException;

public class Sorter 
{

	//Holds the randomly generated ssn
	private static String[] ssNumbers = new String[300];
	
	//Holds the sorted ssn after quicksort is called
	private static int[] quicksortSSN = new int[300];
	
	//Holds the sorted ssn after radix sort is called
	private static int[] radixsortSSN = new int[300];
	
	//Holds the sorted ssn after bucket sort is called
	private static int[] bucketsortSSN = new int[300];

	//Temporarily holds the string conversion of the ssn 
	private static String[] placeholderS = new String[300];
	
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("");
		System.out.println("Welcome to the social security number sorter.");
		
		
		
		System.out.println("Please enter '1' to generate 300 random social security numbers and write them into a text file.");
		int response = scan.nextInt();
		
		if (response == 1)
		{
			//Create 300 random social security numbers
			generateSSN();
			//Write them into a text file 
			writeSSN(ssNumbers, "Random_SSN.txt");
			
			System.out.println("The process has been completed. Please search your device with the label 'Random_SNN.txt' to see the file.");
			System.out.println("");
		}
		
		
		int response2 = 0;
		while (response != 5)
		{
			System.out.println("______________________________________________________________________________________________________________________");
			System.out.println("Please enter '2' to use Quicksort to sort the social security numbers, find the population for each region, and write them into a text file.");
			System.out.println("Please enter '3' to use Radix sort to sort the social security numbers, find the population for each region, and write them into a text file.");
			System.out.println("Please enter '4' to use Bucket sort to sort the social security numbers, find the population for each region, and write them into a text file.");
			System.out.println("Please enter '5' if all options have been completed.");
			System.out.println("______________________________________________________________________________________________________________________");
			
			Scanner scan2 = new Scanner(System.in);
			response2 = scan2.nextInt();
			
			switch (response2)
			{
			
			case 2:
				{
					//Prepare for Quicksort: convert the string[] of ssn to int[]
					convertToInt(ssNumbers, quicksortSSN);
					//Call Quicksort to sort the SSN
					useQuicksort(quicksortSSN);
					//Convert int[] to string[]
					convertToString(quicksortSSN);
					//Separate the ssn according to specified regions and write the results into a text file
					sortRegions(placeholderS, "Quick_SSN.txt");
					
					System.out.println("");
					System.out.println("The process has been completed. Please search your device with the label 'Quick_SSN.txt' to see the file.");
					
					break;
				}
				
			case 3:
				{
					//Preapre for RadixSort: convert the string[] of ssn to int[]
					convertToInt(ssNumbers, radixsortSSN);
					//Call RadixSort to sort the SSN and state the highest-order digit
					useRadixSort(radixsortSSN, 9);
					//Convert int[] to string[]
					convertToString(radixsortSSN);
					//Separate the ssn according to specified regions and write the results into a text file 
					sortRegions(placeholderS, "Radix_SSN.txt");
					
					System.out.println("");
					System.out.println("The process has been completed. Please search your device with the label 'Radix_SSN.txt' to see the file.");
					
					break;
				}
				
			case 4:
				{
					//Preapre for Bucketsort: convert the string[] of ssn to int[]
					convertToInt(ssNumbers, bucketsortSSN);
					//Call BucketSort
					useBucketSort(bucketsortSSN);
					//Convert in[] to stringp[
					convertToString(bucketsortSSN);
					//Separate the ssn according to specified regions and write the results into a text file 
					sortRegions(placeholderS, "Bucket_SSN.txt");
					
					System.out.println("");
					System.out.println("The process has been completed. Please search your device with the label 'Bucket_SSN.txt' to see the file.");
					
					break;
					
				}
				
			case 5:
			{
				
				System.out.println("All functions have been completed. Thank you for using the social security number sorter.");
				break;
				
			}
			default:
			{
				System.out.println("______________________________________________________________________________________________________________________");
				System.out.println("Please enter '2' to use Quicksort to sort the social security numbers, find the population for each region, and write them into a text file.");
				System.out.println("Please enter '3' to use Radix sort to sort the social security numbers, find the population for each region, and write them into a text file.");
				System.out.println("Please enter '4' to use Bucket sort to sort the social security numbers, find the population for each region, and write them into a text file.");
				System.out.println("Please enter '5' if all options have been completed.");
				System.out.println("______________________________________________________________________________________________________________________");
			}
			
			
		  }
				
		}	
		
	}
	
	
	//Argument: int[] A 
	//Converts the int[] of ssn into string[]
	public static void convertToString(int[] A)
	{
		for (int i = 0; i < 300; i++)
		{
			int intSSN = A[i];
			String stringSSN = Integer.toString(intSSN);
			placeholderS[i] = stringSSN;
		}
	}
	
	//Argument: string[] A, the title of the text file after sorting 
	public static void sortRegions(String[] A, String title)
	{
		int northEastCoast = 0;
		int southCoast = 0;
		int middleStates = 0;
		int northWestCoast = 0;
		int westCoastStates = 0;
		
		//Goes through the 300 ssn
		for (int i = 0; i < 300; i++)
		{
			//Obtains the area number
			String threeDigits = A[i].substring(0, 3);
			//Converts the area number into an int
			int firstThreeDigits = Integer.parseInt(threeDigits);
			
			//Increments appropriate counter based on the area number
			if (firstThreeDigits >= 000 && firstThreeDigits <= 199)
			{
				northEastCoast++;
			}
			else if (firstThreeDigits >= 200 && firstThreeDigits <= 399)
			{
				southCoast++;
			}
			else if (firstThreeDigits >= 400 && firstThreeDigits <= 599)
			{
				middleStates++;
			}
			else if (firstThreeDigits >= 600 && firstThreeDigits <= 799)
			{
				northWestCoast++;
			}
			else if (firstThreeDigits >= 800 && firstThreeDigits <= 999)
			{
				westCoastStates++;
			}
		}
		
		
		//Creates the text for the different area counters
		String north = "Northeast Coast States: " + northEastCoast + " people";
		String south = "South Coast States: " + southCoast + " people";
		String middle = "Middle States: " + middleStates + " people";
		String northwest = "Northwest Coast States: " + northWestCoast + " people";
		String west = "West Coast States: " + westCoastStates + " people";
		
		try
		{
			FileWriter fw = new FileWriter(title);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//Writes the sorted array 
			for (int i = 0; i < 300; i++)
			{
				String ssN = i+1 + ". " + A[i].substring(0,3) + "-" +A[i].substring(3,5) + "-" + A[i].substring(5,9);
				bw.write(ssN);
				bw.newLine();
			}
			
			bw.newLine();
			
			//Writes the different area counters
			bw.write(north);
			bw.newLine();
			
			bw.write(south);
			bw.newLine();
			
			bw.write(middle);
			bw.newLine();
			
			bw.write(northwest);
			bw.newLine();
			
			bw.write(west);
			bw.newLine();
			
			bw.close();
			
		}
		catch (IOException e)
		{
			System.out.println("The array is empty.");
		}
		
		
	}
	
	//Argument: int[] A of ssn 
	//Calls quicksort to sort the int[]
	public static void useQuicksort(int[] A)
	{
		Quicksort q = new Quicksort();
		q.quicksort(A, 0, 299);
		
	}
	
	//Argument: int[] A, highest-order digit d
	//Calls radix sort to sort the int[]
	public static void useRadixSort(int[] A, int d)
	{
		RadixSort r = new RadixSort();
		r.radixSort(A, d);
	}
	
	//Argument: int[] A
	//Calls bucketsort to sort the int[]
	public static void useBucketSort(int[] A)
	{
		BucketSort b = new BucketSort();
		b.bucketSort(A);
	}
	
	//Arguments: string[] A, int[] B
	//Converts the string[] to int[] and stores the result into B
	public static void convertToInt(String[] A, int[] B)
	{
		for (int i = 0; i < 300; i++)
		{
			//Obtains the integer portion of the ssn only 
			String digitString = A[i].substring(0,3) + A[i].substring(4, 6) + A[i].substring(7, 11);
			int digits = Integer.parseInt(digitString);
			B[i] = digits;
		}
	}
	
	//Randomly generates 300 ssn
	public static void generateSSN()
	{
		for (int i = 0; i < 300; i++)
		{
			Number newSSN = new Number();
			ssNumbers[i] = newSSN.getSSN();
		}
	}
	
	//Argument: string[], the title of the text file 
	//Writes the string[] of ssn and names the file based on the given title 
	public static void writeSSN(String[] A, String title)
	{
		try
		{
			FileWriter fw = new FileWriter(title);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < 300; i++)
			{
				String ssN = i+1 + ". " + ssNumbers[i];
				bw.write(ssN);
				bw.newLine();
			}
			bw.close();
			
		}
		catch (IOException e)
		{
			System.out.println("The array is empty.");
		}
	}

}
