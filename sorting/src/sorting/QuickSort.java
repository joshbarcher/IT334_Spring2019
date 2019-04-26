package sorting;

import java.util.Random;

public class QuickSort
{
    public static void main(String[] args)
    {

    }

    private static void quickSort(int[] array)
    {

    }

    private static void swap(int[] array, int first, int second)
    {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static int[] generateArray(int size)
    {
        Random random = new Random();
        int[] results = new int[size];

        for (int i = 0; i < results.length; i++)
        {
            results[i] = random.nextInt(size);
        }

        return results;
    }

    public static boolean containsInversions(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
            {
                return true;
            }
        }

        return false;
    }
}
