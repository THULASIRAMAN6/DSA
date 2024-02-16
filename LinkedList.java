import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class LinkedList<T> implements Iterable<T> {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    Node<T> head;

    // Method to display the linked list
    void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to create a node at a given position
    void createNodeAtPosition(T data, int position) {
        Node<T> newNode = new Node<>(data);

        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Method to create a node at the beginning
    void createNodeAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    // Method to create a node at the end
    void createNodeAtEnd(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Method to delete a node at a given position
    void deleteNodeAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (position == 0) {
            head = head.next;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < position && current != null; i++) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }

        previous.next = current.next;
    }

    // Method to delete a node at the beginning
    void deleteNodeAtBeginning() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        head = head.next;
    }

    // Method to delete a node at the end
    void deleteNodeAtEnd() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        previous.next = null;
    }

    // Method to reverse the linked list
    void reverse() {
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head = previous;
    }

    // Method to sort the linked list in ascending order
    void sortAscending() {
        T[] values = toArray();
        Arrays.sort(values);
        fromArray(values);
    }

    // Method to sort the linked list in descending order
    void sortDescending() {
        T[] values = toArray();
        Arrays.sort(values);
        reverseArray(values);
        fromArray(values);
    }

    // Method to shift the nodes by k positions to the right
    void shiftNodes(int k) {
        if (head == null || k <= 0) {
            return;
        }

        int size = 1;
        Node<T> tail = head;

        while (tail.next != null) {
            size++;
            tail = tail.next;
        }

        k = k % size; // Adjust k in case it's larger than the size of the list

        if (k == 0) {
            return; // No change needed if k is a multiple of the list size
        }

        Node<T> newTail = head;
        for (int i = 0; i < size - k - 1; i++) {
            newTail = newTail.next;
        }

        Node<T> newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        head = newHead;
    }

    // Convert the linked list to an array
    private T[] toArray() {
        Node<T> current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        T[] values = (T[]) new Object[size];
        current = head;
        int index = 0;
        while (current != null) {
            values[index++] = current.data;
            current = current.next;
        }

        return values;
    }

    // Populate the linked list from an array
    private void fromArray(T[] values) {
        head = null;
        for (T value : values) {
            createNodeAtEnd(value);
        }
    }

    // Reverse the order of an array
    private void reverseArray(T[] values) {
        int start = 0;
        int end = values.length - 1;
        while (start < end) {
            T temp = values[start];
            values[start] = values[end];
            values[end] = temp;
            start++;
            end--;
        }
    }

    // Iterator method to iterate through the linked list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
     
    
    // Method to get data at a given position
    T get(int position) {
        if (head == null || position < 0) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }

        return (current != null) ? current.data : null;
    }

    // Method to update data at a given position
    void update(int position, T value) {
        if (head == null || position < 0) {
            System.out.println("List is empty or position is invalid.");
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }

        if (current != null) {
            current.data = value;
        } else {
            System.out.println("Position out of bounds");
        }
    }

    // Method to search for a value and return its index, -1 if not found
    int search(T value) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    // Method to check if the linked list contains a value
    boolean contains(T value) {
        return search(value) != -1;
    }

    // Method to get the length of the linked list
    int length() {
        int count = 0;
        Node<T> current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    // Main method with switch case for user interaction
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
             System.out.println("\n----- Linked List Operations -----");
            System.out.println("1. Display");
            System.out.println("2. Create Node at Position");
            System.out.println("3. Create Node at Beginning");
            System.out.println("4. Create Node at End");
            System.out.println("5. Delete Node at Position");
            System.out.println("6. Delete Node at Beginning");
            System.out.println("7. Delete Node at End");
            System.out.println("8. Reverse");
            System.out.println("9. Sort Ascending");
            System.out.println("10. Sort Descending");
            System.out.println("11. Shift Nodes");
            System.out.println("12. Iterator");
            System.out.println("13. Get at Position");
            System.out.println("14. Update at Position");
            System.out.println("15. Search Value");
            System.out.println("16. Contains Value");
            System.out.println("17. Length");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    linkedList.display();
                    break;
                    
                case 2:
                    System.out.print("Enter data: ");
                    int data = scanner.nextInt();
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    linkedList.createNodeAtPosition(data, position);
                    break;

                case 3:
                    System.out.print("Enter data: ");
                    int newData = scanner.nextInt();
                    linkedList.createNodeAtBeginning(newData);
                    break;

                case 4:
                    System.out.print("Enter data: ");
                    int endData = scanner.nextInt();
                    linkedList.createNodeAtEnd(endData);
                    break;

                case 5:
                    System.out.print("Enter position: ");
                    int deletePosition = scanner.nextInt();
                    linkedList.deleteNodeAtPosition(deletePosition);
                    break;

                case 6:
                    linkedList.deleteNodeAtBeginning();
                    break;

                case 7:
                    linkedList.deleteNodeAtEnd();
                    break;

                case 8:
                    linkedList.reverse();
                    break;

                case 9:
                    linkedList.sortAscending();
                    break;

                case 10:
                    linkedList.sortDescending();
                    break;

                case 11:
                    System.out.print("Enter the number of positions to shift: ");
                    int shiftAmount = scanner.nextInt();
                    linkedList.shiftNodes(shiftAmount);
                    break;

                case 12:
                    System.out.println("Iterator:");
                    for (Integer value : linkedList) {
                        System.out.print(value + " -> ");
                    }
                    System.out.println("null");
                    break;

                case 13:
                    System.out.print("Enter position: ");
                    int getPosition = scanner.nextInt();
                    Integer getValue = linkedList.get(getPosition);
                    System.out.println("Value at position " + getPosition + ": " + getValue);
                    break;

                case 14:
                    System.out.print("Enter position: ");
                    int updatePosition = scanner.nextInt();
                    System.out.print("Enter new value: ");
                    int updateValue = scanner.nextInt();
                    linkedList.update(updatePosition, updateValue);
                    System.out.println("Value updated at position " + updatePosition);
                    break;

                case 15:
                    System.out.print("Enter value to search: ");
                    int searchValue = scanner.nextInt();
                    int searchIndex = linkedList.search(searchValue);
                    System.out.println("Value " + searchValue + " found at index " + searchIndex);
                    break;

                case 16:
                    System.out.print("Enter value to check: ");
                    int containsValue = scanner.nextInt();
                    boolean contains = linkedList.contains(containsValue);
                    System.out.println("Linked list contains value " + containsValue + ": " + contains);
                    break;

                case 17:
                    int length = linkedList.length();
                    System.out.println("Length of the linked list: " + length);
                    break;

                    case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
