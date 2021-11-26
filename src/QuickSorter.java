import java.time.Duration;
import java.util.*;

/**
 * Quick sorter uses several variations of the in-place QuickSort
 * algorithms, each with a different choice of pivot
 */
public class QuickSorter {

    // private to ensure objects can't be instantiated
    private QuickSorter() {}

    /**
     * The enum Pivot strategy.
     */
    public static enum PivotStrategy {
        /**
         * First element pivot strategy.
         */
        FIRST_ELEMENT,

        /**
         * Random element pivot strategy.
         */
        RANDOM_ELEMENT,

        /**
         * Median of three random elements pivot strategy.
         */
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,

        /**
         * Median of first, center and last element.
         */
        MEDIAN_OF_THREE,
    }

    /**
     * Timed quick sort that returns duration of the
     * sorted algorithm time in nanoseconds.
     *
     * @param <E>           the type parameter
     * @param list          the list
     * @param pivotStrategy the pivot strategy
     * @return the duration
     * @throws NullPointerException the null pointer exception
     */
    public static <E extends Comparable<E>> Duration
    timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy)
            throws NullPointerException {

        //verifies list != empty
        if (list.isEmpty()) {
            throw new NullPointerException("The list is empty");
        }

        ////verifies pivot strategy != null
        if (pivotStrategy == null ) {
            throw new NullPointerException("The pivot strategy is invalid");
        }

        //sort time when the algorithm starts
        long sortTime1 = System.nanoTime();

        //sorts list based off of selected pivot strategy
        switch (pivotStrategy) {
            case FIRST_ELEMENT -> quickSortFirst(list,0, list.size()-1);
            case RANDOM_ELEMENT -> quickSortRandom(list, 0, list.size() - 1);
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS -> quickSortMedOfThreeRan(list, 0, list.size() - 1);
            case MEDIAN_OF_THREE -> quickSortMedOfThree(list, 0, list.size() - 1);
            default -> System.out.println("Invalid pivot strategy");
        }

        // sort time when algorithm ends
        long sortTime2 = System.nanoTime();

        // calculates the actual sort time and return value
        return Duration.ofNanos(sortTime2 - sortTime1);
    }

    private static <E extends Comparable<E>> void quickSortMedOfThree(ArrayList<E> list, int left, int right) {
    }

    private static <E extends Comparable<E>> void quickSortMedOfThreeRan(ArrayList<E> list, int left, int right) {
    }

    private static <E extends Comparable<E>> void quickSortRandom(ArrayList<E> list, int left, int right) {
    }

    private static <E extends Comparable<E>> void quickSortFirst(ArrayList<E> list, int left, int right) {
            int pivotIndex = left;

            int count = 0;

            if (count == 1) {
                return;
            }


            quickSort(list, 0, list.size()-1, pivotIndex);
            count++;
    }

    /**
     * Quick sort algorithm to sort a list using recursion. IF
     * elements are <20, the sort will be done using insertion sort.
     *
     * @param list          Array list
     * @param left          leftist element -> 0
     * @param right         The rightist element
     * @param pivotIndex    The pivot index
     * @param <E>
     */
    private static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int left, int right, int pivotIndex) {
        int left1, right1;
        E pivot;
        // variable for cutoff set to 20
        final int CUTOFF = 20;

        // end method when list is done
        if (left >= right) return;

        left1 = left;
        right1 = right;

        // if number of elements are >20, use recursive strategy
        if ((left + CUTOFF) <= right) {
            // assign the pivot
            pivot = list.get(pivotIndex);

            // partition
            while (left1 <= right1) {

                while (pivot.compareTo(list.get(left1)) == 1) left1++;

                while (pivot.compareTo(list.get(right1)) == -1) right1 --;

                if (left1 <= right1) {
                    // used as a holder to swap elements
                    E holder = list.get(left1);
                    //modify the left element
                    list.remove(left1);
                    list.add(left1, list.get(right1));

                    // modify the right element
                    list.remove(right1);
                    list.add(right1, holder);
                    left1++;
                    right1--;
                }
            }
            // recursion
            quickSort(list, left, right1, pivotIndex);
            quickSort(list, left1, right, pivotIndex);

        } else {
            //call the insertionSort
            insertionSort(list, left, right);
        }
    }

    /**
     * Insertion sort Algorithm to sort elements <20
     *
     * @param list      the list
     * @param left      the leftest element in the list
     * @param right     the rightest element in the list
     * @param <E>       the type parameter
     */
    private static <E extends Comparable<E>> void insertionSort(ArrayList<E> list, int left, int right) {

        // E type to compare with element in a list
        E value;

        // loop through the list
        for (int i = left+1; i < right+1; i++) {
            //assign value to current index of list
            value = list.get(i);
            int x = i-1;

            //modify the element at x+1 with list.get(j)
            while ((x >= 0) && ((list.get(x).compareTo(value)) == 1)) {
                list.remove(x+1);
                list.add(x+1, list.get(x));
                x -= 1;
            }

            //modify the element at x+1 with value
            list.remove(x+1);
            list.add(x+1, value);
        }
        //print this to test out the insertion sort
        System.out.println("You have used insertion sort");
    }

    /**
     * Generate random array list.
     *
     * @param size the size
     * @return the array list
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static ArrayList<Integer> generateRandomList(int size)
            throws IllegalArgumentException {

        if (size < 0) throw new IllegalArgumentException("The size cannot be negative");

        ArrayList<Integer> list = new ArrayList<>(size);

        // object to generate a random number
        Random random = new Random();

        // loop to create the random generated list
        for (int i = 0; i < size; i++) {
            int number = random.nextInt();
            list.add(number);
        }

        return list;
    }
}
