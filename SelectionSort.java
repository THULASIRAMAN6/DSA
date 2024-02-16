import java.util.Scanner;

public class SelectionSort<T extends Comparable<T>> {

    public void selectionSortAscending(T[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the index of the minimum element in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public void selectionSortDescending(T[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            // Find the index of the maximum element in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            // Swap the found maximum element with the first element
            T temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }
    }

    // Method to print an array
    public void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the size of the array from the user
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        // Declare an array of Comparable elements with the given size
        SelectionSort<Integer> sorter = new SelectionSort<>();
        Integer[] intArray = new Integer[size];

        // Get array elements from the user
        System.out.println("Enter the elements of the array:");

        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            intArray[i] = scanner.nextInt();
        }

        // Sort and print the array in ascending order
        System.out.println("\nSorted Array (Ascending Order):");
        sorter.selectionSortAscending(intArray);
        sorter.printArray(intArray);

        // Sort and print the array in descending order
        System.out.println("\nSorted Array (Descending Order):");
        sorter.selectionSortDescending(intArray);
        sorter.printArray(intArray);

        // Close the scanner
        scanner.close();
    }
}
