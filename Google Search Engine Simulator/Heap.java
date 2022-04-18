package simulator;
import java.util.*;

/**
 * Implements primary and helper methods to use for heap sort and heap priority queue
 */
public class Heap 
{
	//Used to initialize the heapsize of an array 
	//Note: heapsize =/= array length 
	private int heapsize;
	
	//Input: the index of some node in an array (heap)
	//Output: the index of the node's parent
	public int parent(int index)
	{
		return (index/2);
	}
	
	
	//Input: the index of some node in an array (heap)
	//Output: the index of the node's left child 
	public int left(int index)
	{
		return 2*index + 1;
	}
	
	//Input: the index of some node in an array (heap)
	//Output: the index of the node's right child 
	public int right(int index)
	{
		return 2*index + 2;
	}
	
	
	//Input: a reg. array of integers and the index of some node
	//Outcome: maintains the max heap property of a single node recursively 
	//Max heap property: A[Parent(i)] >= A[i]
	public void maxHeapify(int[] A, int index)
	{
		int leftChild = left(index);
		int rightChild = right(index);
		
		//A placeholder for comparison
		int largest;
		
		if (leftChild <= this.heapsize && A[leftChild] > A[index])
		{
			largest = leftChild;
		}
		else 
		{
			largest = index;
		}
		
		if (rightChild <= this.heapsize && A[rightChild] > A[largest])
		{
			largest = rightChild;
		}
		
		if (largest != index)
		{
			//Swap nodes to maintain max heap property 
			int placeholder = A[index];
			A[index] = A[largest];
			A[largest] = placeholder;
			
			
			//Recursive call to ensure the max heap property applies elsewhere
			maxHeapify(A, largest);
		}
	}
	
	
	//Input: an array of integers 
	//Outcome: creates a max heap where A[Parent(i)] >= A[i]
	public void buildMaxHeap(int[] A)
	{
		// -1 because using the full array size adds 1 extra slot since arrays start at 0
		this.heapsize = A.length - 1;
		
		
		for (int i = A.length/2; i >= 0; i--)
		{
			maxHeapify(A, i);
		}
	}
	
	
	//Input: an unsorted array of integers
	//Outcome: the same array is sorted from smallest to largest 
	public void heapsort(int[] A)
	{
		//create a max heap first
		buildMaxHeap(A);
		
		for (int i = A.length - 1; i >= 1; i--)
		{
			
			//Swap the root (currently biggest) node with the last node in the heap
			int placeholder = A[0];
			A[0] = A[i];
			A[i] = placeholder;
			
			//Decrease the heap size 
			this.heapsize = heapsize - 1;
			maxHeapify(A, 0);
		}
	}
	
	
	
	
	//Input: an array of integers (max heap)
	//Output: the max element (the root of the heap) of A 
	public int heapMaximum(int[] A)
	{
		return A[0];
	}
	
	
	//Input: an array of integers (max heap)
	//Output: the max element (the root of the heap) of A and A as a heap with it removed  
	public int heapExtractMax(int[] A) 
	{
		if (this.heapsize < 1)
		{
			throw new IllegalArgumentException("Heap underflow");
		}
		
		//Gets the root node of the max heap
		int max = A[0];
		
		
		A[0] = A[this.heapsize];
		this.heapsize = this.heapsize - 1;
		maxHeapify(A, 0);
		return max;
	}
	
	//Input: an array, an index of a node, and the amount (key) to increase the node to 
	//Output: the same array and target node is increased to a specified amount 
	public void heapIncreaseKey(int[] A, int index, int key)
	{
		if (key < A[index])
		{
			throw new IllegalArgumentException("The new amount is smaller than the current amount");
		}
		
		//Assigns the new value to the target node
		A[index] = key;
		while (index > 0 && A[parent(index)] < A[index])
		{
			int placeholder = A[index];
			A[index] = A[parent(index)];
			A[parent(index)] = placeholder;
			index = parent(index);
		}
	}
	
	
	//Input: an array (max heap) and a new element (key)
	//Output: an expanded max heap with the new element in its appropriate place
	public void maxHeapInsert(int[] A, int key)
	{
		//Make space for another element in the heap 
		this.heapsize = this.heapsize + 1;
		A[this.heapsize] = -1000000;
		
		heapIncreaseKey(A, this.heapsize, key);
		
	}
	
	public static void main(String[] args)
	{
		Heap h = new Heap();
		int[] tester = new int[] {6, 3, 2, 7, 8, 10};
		h.buildMaxHeap(tester);
		
		System.out.println("Before: " + Arrays.toString(tester));
		
		System.out.println("Expected: [8, 6, 7, 3, 2]");
		int max = h.heapExtractMax(tester);
		System.out.println("Max: " + max);
		System.out.println("After: " + Arrays.toString(tester)); 
		
	}
	
	
}
