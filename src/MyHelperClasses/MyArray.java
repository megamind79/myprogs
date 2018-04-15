package MyHelperClasses;

import java.util.Arrays;

class MyArrayOverFlowException extends Exception {
    /**
     * Exception to handle MyArray overflow
     * @param N sze of MyArray
     */
    public MyArrayOverFlowException (int N) {
        super(String.format("Trying to enter more than %d elements in array", N));
    }
}

public class MyArray {
    private int array[], index = 0, N;

    /**
     * Create an array of size N
     * @param N array size
     */
    public MyArray (int N) {
        array = new int[N];
    }

    /**
     * Push an element onto MyArray
     * @param element element t be pushed
     * @throws MyArrayOverFlowException Throws MyArrayOverFlowException in case of overflow
     */
    public void addElement (int element) throws MyArrayOverFlowException {
        if (index == N)
            throw new MyArrayOverFlowException(N);
        array[index] = element;
        index++;
    }

    private int binarySearch (int element, int lo, int hi) {
        if (lo >= hi) {
            if (array[lo] == element)
                return lo;
            if (array[lo] > element)
                return (0-lo-1);
            return (0-lo-2);
        }

        int mid = (lo+hi)/2;

        if (array[mid] == element)
            return mid;

        if (array[mid] > element)
            return binarySearch(element, lo, mid-1);

        return binarySearch(element, mid+1, hi);
    }

    /**
     * Search for an element in the sorted MyArray
     * @param element element to be searched
     * @return index of the element if present or
     * -inclusion_point-1 if not present where inclusion
     * point is the place where this element can be inserted
     */
    public int binarySearch (int element) {
        return binarySearch(element, 0, N-1);
    }

    /**
     * sort MyArray
     */
    public void sort() {
        Arrays.sort(array);
    }

    public String toString () {
        return Arrays.toString(array);
    }

    /**
     * Get the size of MyArray
     * @return MyArray size
     */
    public int getSize () {
        return N;
    }
}
