package sorters;

public class Driver {
	
	public static void main(String[] args )
	{
		Driver d = new Driver();
		int[] data = {3,5,1,6,2,4,9,7,5,3,6};
		int[] tomerge1 = {5,6,7,8};
		int[] tomerge2 = {1,2,3,4};
		System.out.println("before");
		for ( int i : data)
		{
			System.out.print(i);
		}
		System.out.println();
//		int p = d.partition(data, 0, 4, 0);
//		System.out.println("partioned at: " + p);
		d.quicksort(data, 0, data.length-1);
		//data = d.arrayMerge(tomerge1, tomerge2, 4, 4);
		//data = d.mergeSort(data);
		for ( int i : data )
		System.out.print(i);
	}
	
	public int[] bubbleSort(int[] data)
	{
		return data;
	}
	
	public int[] insertionSort(int[] data)
	{
		return data;
	}
	
	public int[] mergeSort(int[] data)
	{
		if ( data.length == 1 )
		{
			return data;
		}
		else 
//		if ( data.length > 1 )
		{
			int[] left = new int[data.length/2];
			int[] right = new int[data.length - left.length];
			for ( int i = 0; i < data.length/2; i++)
			{
				left[i] = data[i];
			}
			int index = 0;
			for (int i = data.length/2; i < data.length; i++ )
			{
				right[index++] = data[i];
			}
			left = mergeSort(left);
			right = mergeSort(right);
			return this.arrayMerge(left, right, left.length, right.length);
		}
	}
	
	

	/* implement quick sort here */
	public int[] quicksort(int[] data, int startIndex, int endIndex)
	{
		if ( data.length == 1 )
		{
			return data;
		}
		else if ( data.length == 2 )
		{
			//rearrange them and return
			return rearrangeAndReturn(data);
		}
		else if ( startIndex > endIndex) 
		{
			return data;
		}
		else
		{
			//1 - pick a pivot
			//let's just pick last element of the array as the pivot
			
			//2 - rearrange the data around pivot
			int pivotIndex = endIndex;
			int partitionIndex = partition(data, startIndex, endIndex, pivotIndex);
			
			//3.1 - make recursive call on left part of the partioned list
			quicksort(data, startIndex, partitionIndex-1);
			
			//3.2 - make recursive call on right part of the partioned list
			quicksort(data, partitionIndex+1, endIndex);
		}
		return data;
	}
	
	private int partition(int[] data, int start, int end, int pivotIndex)
	{
		int pivotValue = data[pivotIndex];
		//let's move the value at pivot index to the end before we start the process
		swap(data, pivotIndex, end);

		int partitionIndex = start;
		for ( int i = start; i <= end; i++ )
		{
			if ( data[i] <= pivotValue)
			{
				swap(data, i, partitionIndex++);
			}
		}
		return partitionIndex-1;
	}
	
	private void swap(int[] data, int firstIndex, int secondIndex)
	{
		if ( firstIndex != secondIndex)
		{
			int temp = data[firstIndex];
			data[firstIndex] = data[secondIndex];
			data[secondIndex] = temp;
		}
	}
	
	private int[] rearrangeAndReturn(int[] data)
	{
		if ( data[0] < data[1])
		return data;
		else
		{
			int temp = data[0];
			data[0] = data[1];
			data[1] = temp;
			return data;
		}
	}
	
	private int[] arrayMerge(int[] array1, int[] array2, int array1Size, int array2Size)
	{
		int[] result = new int[array1Size + array2Size];
		
		// do merge
		int firstIndex=0, secondIndex = 0;
		for ( int i = 0; i < (array1Size+array2Size); i++)
		{
			if ( firstIndex == array1Size )
			{
				result[i] = array2[secondIndex++];
			}
			else if ( secondIndex == array2Size )
			{
				result[i] = array1[firstIndex++];
			}
			else if ( array1[firstIndex] < array2[secondIndex])
			{
				result[i] = array1[firstIndex++];
			}
			else
			{
				result[i] = array2[secondIndex++];
			}
		}
		
		//end do merge
		return result;
	}
}
