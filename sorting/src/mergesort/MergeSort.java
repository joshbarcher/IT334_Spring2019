package mergesort;

import java.util.Arrays;

public class MergeSort
{
    private static int[] aux;

    public static void main(String[] args)
    {
        int[] testArray = {15, 6, 4, 12, 5, 1, 2, 3};
        mergesort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    public static void mergesort(int[] array)
    {
        //short-circuit
        if (array.length <= 1)
        {
            return;
        }

        //prepare my extra space for the merge
        aux = new int[array.length];

        //start the sort
        mergesort(array, 0, array.length - 1);
    }

    private static void mergesort(int[] array, int low, int high)
    {
        //base case
        if (high == low)
        {
            return; //return because one element is sorted
        }

        //recursive calls (divide)
        int mid = (high + low) / 2;
        mergesort(array, low, mid); //left
        mergesort(array, mid + 1, high); //right

        //merge our sorted sub-arrays together
        merge(array, low, high);
    }

    private static void merge(int[] array, int low, int high)
    {
        //create some housekeeping variables
        int mid = (high + low) / 2;
        int left = low;
        int right = mid + 1;
        int numElementsToSort = high - low + 1;

        //perform the merge...
        for (int i = 0; i < numElementsToSort; i++)
        {
            //if we have exhausted a sub-array
            if (left > mid)
            {
                aux[low + i] = array[right++];
            }
            else if (right > high)
            {
                aux[low + i] = array[left++];
            }
            //otherwise add from the appropriate sub-array
            else if (array[left] < array[right])
            {
                aux[low + i] = array[left++];
            }
            else
            {
                aux[low + i] = array[right++];
            }
        }

        //move from the auxiliary array to the input array
        for (int i = 0; i < numElementsToSort; i++)
        {
            array[low + i] = aux[low + i];
        }
    }
}
