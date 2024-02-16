import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class DoublyLinkedList<T> implements Iterable<T> {
    static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node<T> head;
    Node<T> tail;

    // Method to display the doubly linked list
    void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to create a node at a given position
    void createNodeAtPosition(T data, int position) {
        Node<T> newNode = new Node<>(data);

        if (position == 0) {
            newNode.next = head;
            newNode.prev = null;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
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
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    // Method to create a node at the beginning
    void createNodeAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        newNode.prev = null;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
    }

    // Method to create a node at the end
    void createNodeAtEnd(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        newNode.prev = tail;
        newNode.next = null;
        tail.next = newNode;
        tail = newNode;
    }

    // Method to delete a node at a given position
    void deleteNodeAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (position == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position out of bounds");
            return;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
    }

    // Method to delete a node at the beginning
    void deleteNodeAtBeginning() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
    }

    // Method to delete a node at the end
    void deleteNodeAtEnd() {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (head.next == null) {
            head = null;
            tail = null;
            return;
        }

        tail = tail.prev;
        tail.next = null;
    }

    // Method to reverse the doubly linked list
    void reverse() {
        Node<T> temp = null;
        Node<T> current = head;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
    }


    // Method to sort the doubly linked list in ascending order
    void sortAscending() {
        T[] values = toArray();
        Arrays.sort(values);
        fromArray(values);
    }

    // Method to sort the doubly linked list in descending order
    void sortDescending() {
        T[] values = toArray();
        Arrays.sort(values);
        reverseArray(values);
        fromArray(values);
    }

    // ... (remaining methods from the previous response)

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
        newHead.prev = null;
        newTail.next = null;

        tail.next = head;
        head.prev = tail;

        head = newHead;
    }

    // Convert the doubly linked list to an array
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

    // Populate the doubly linked list from an array
    private void fromArray(T[] values) {
        head = null;
        tail = null;
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

    // Iterator method to iterate through the doubly linked list
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
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n----- Doubly Linked List Operations -----");
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
                    doublyLinkedList.display();
                    break;

                case 2:
                    System.out.print("Enter data: ");
                    int data = scanner.nextInt();
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    doublyLinkedList.createNodeAtPosition(data, position);
                    break;

                case 3:
                    System.out.print("Enter data: ");
                    int newData = scanner.nextInt();
                    doublyLinkedList.createNodeAtBeginning(newData);
                    break;

                case 4:
                    System.out.print("Enter data: ");
                    int endData = scanner.nextInt();
                    doublyLinkedList.createNodeAtEnd(endData);
                    break;

                case 5:
                    System.out.print("Enter position: ");
                    int deletePosition = scanner.nextInt();
                    doublyLinkedList.deleteNodeAtPosition(deletePosition);
                    break;

                case 6:
                    doublyLinkedList.deleteNodeAtBeginning();
                    break;

                case 7:
                    doublyLinkedList.deleteNodeAtEnd();
                    break;

                case 8:
                    doublyLinkedList.reverse();
                    break;

                case 9:
                    doublyLinkedList.sortAscending();
                    break;

                case 10:
                    doublyLinkedList.sortDescending();
                    break;

                case 11:
                    System.out.print("Enter the number of positions to shift: ");
                    int shiftAmount = scanner.nextInt();
                    doublyLinkedList.shiftNodes(shiftAmount);
                    break;

                case 12:
                    System.out.println("Iterator:");
                    for (Integer value : doublyLinkedList) {
                        System.out.print(value + " <-> ");
                    }
                    System.out.println("null");
                    break;

                case 13:
                    System.out.print("Enter position: ");
                    int getPosition = scanner.nextInt();
                    Integer getValue = doublyLinkedList.get(getPosition);
                    System.out.println("Value at position " + getPosition + ": " + getValue);
                    break;

                case 14:
                    System.out.print("Enter position: ");
                    int updatePosition = scanner.nextInt();
                    System.out.print("Enter new value: ");
                    int updateValue = scanner.nextInt();
                    doublyLinkedList.update(updatePosition, updateValue);
                    System.out.println("Value updated at position " + updatePosition);
                    break;

                case 15:
                    System.out.print("Enter value to search: ");
                    int searchValue = scanner.nextInt();
                    int searchIndex = doublyLinkedList.search(searchValue);
                    System.out.println("Value " + searchValue + " found at index " + searchIndex);
                    break;

                case 16:
                    System.out.print("Enter value to check: ");
                    int containsValue = scanner.nextInt();
                    boolean contains = doublyLinkedList.contains(containsValue);
                    System.out.println("Linked list contains value " + containsValue + ": " + contains);
                    break;

                case 17:
                    int length = doublyLinkedList.length();
                    System.out.println("Length of the doubly linked list: " + length);
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