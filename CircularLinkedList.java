import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class CircularLinkedList<T extends Comparable<T>> implements Iterable<T> {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    Node<T> head;

    // Method to display the circular linked list
    void display() {
        if (head == null) {
            System.out.println("Circular Linked List is empty.");
            return;
        }

        Node<T> current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);

        System.out.println("(head)");
    }

    // Method to create a node at a given position
    void createNodeAtPosition(T data, int position) {
        Node<T> newNode = new Node<>(data);

        if (position == 0) {
            if (head == null) {
                head = newNode;
                head.next = head;
            } else {
                Node<T> last = getLastNode();
                newNode.next = head;
                head = newNode;
                last.next = head;
            }
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < position - 1 && current.next != head; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Method to delete a node at a given position
    void deleteNodeAtPosition(int position) {
        if (head == null) {
            System.out.println("Circular Linked List is empty. Nothing to delete.");
            return;
        }

        if (position == 0) {
            deleteAtBeginning();
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < position && current.next != head; i++) {
            previous = current;
            current = current.next;
        }

        if (current == head) {
            System.out.println("Position out of bounds");
            return;
        }

        previous.next = current.next;
    }

    // Method to get the last node in the circular linked list
    private Node<T> getLastNode() {
        Node<T> last = head;
        while (last.next != head) {
            last = last.next;
        }
        return last;
    }

    // Method to reverse the circular linked list
    void reverse() {
        if (head == null) {
            System.out.println("Circular Linked List is empty. Nothing to reverse.");
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;
        Node<T> next;

        do {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } while (current != head);

        head = prev;
    }

    // Method to sort the circular linked list in ascending order
    void sortAscending() {
        T[] values = toArray();
        Arrays.sort(values);
        fromArray(values);
    }

    // Method to sort the circular linked list in descending order
    void sortDescending() {
        T[] values = toArray();
        Arrays.sort(values);
        reverseArray(values);
        fromArray(values);
    }

    // Convert the circular linked list to an array
    private T[] toArray() {
        if (head == null) {
            return (T[]) new Object[0];
        }

        Node<T> current = head;
        int size = 0;
        do {
            size++;
            current = current.next;
        } while (current != head);

        T[] values = (T[]) new Object[size];
        current = head;
        int index = 0;
        do {
            values[index++] = current.data;
            current = current.next;
        } while (current != head);

        return values;
    }

    // Populate the circular linked list from an array
    private void fromArray(T[] values) {
        head = null;
        for (T value : values) {
            createNodeAtPosition(value, 0);
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

    // Iterator method to iterate through the circular linked list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private boolean firstNodeVisited = false;

            @Override
            public boolean hasNext() {
                return current != null && (!firstNodeVisited || current != head);
            }

            @Override
            public T next() {
                if (!firstNodeVisited) {
                    firstNodeVisited = true;
                } else {
                    current = current.next;
                }
                return current.data;
            }
        };
    }

    // Method to insert a node at the beginning of the circular linked list
    void insertAtBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> last = getLastNode();
            newNode.next = head;
            head = newNode;
            last.next = head;
        }
    }

    // Method to insert a node at the end of the circular linked list
    void insertAtLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> last = getLastNode();
            newNode.next = head;
            last.next = newNode;
        }
    }

    // Method to delete a node at the beginning of the circular linked list
    void deleteAtBeginning() {
        if (head == null) {
            System.out.println("Circular Linked List is empty. Nothing to delete.");
            return;
        }

        if (head.next == head) {
            head = null;
        } else {
            Node<T> last = getLastNode();
            head = head.next;
            last.next = head;
        }
    }

    // Method to delete a node at the end of the circular linked list
    void deleteAtLast() {
        if (head == null) {
            System.out.println("Circular Linked List is empty. Nothing to delete.");
            return;
        }

        if (head.next == head) {
            head = null;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            do {
                previous = current;
                current = current.next;
            } while (current.next != head);

            previous.next = head;
        }
    }

    // Main method with switch case for user interaction
    public static void main(String[] args) {
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n----- Circular Linked List Operations -----");
            System.out.println("1. Display");
            System.out.println("2. Create Node at Position");
            System.out.println("3. Delete Node at Position");
            System.out.println("4. Reverse");
            System.out.println("5. Sort Ascending");
            System.out.println("6. Sort Descending");
            System.out.println("7. Iterator");
            System.out.println("8. Insert at Beginning");
            System.out.println("9. Insert at Last");
            System.out.println("10. Delete at Beginning");
            System.out.println("11. Delete at Last");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    circularLinkedList.display();
                    break;

                case 2:
                    System.out.print("Enter data: ");
                    int data = scanner.nextInt();
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    circularLinkedList.createNodeAtPosition(data, position);
                    break;

                case 3:
                    System.out.print("Enter position: ");
                    int deletePosition = scanner.nextInt();
                    circularLinkedList.deleteNodeAtPosition(deletePosition);
                    break;

                case 4:
                    circularLinkedList.reverse();
                    break;

                case 5:
                    circularLinkedList.sortAscending();
                    break;

                case 6:
                    circularLinkedList.sortDescending();
                    break;

                case 7:
                    System.out.println("Iterator:");
                    for (Integer value : circularLinkedList) {
                        System.out.print(value + " -> ");
                    }
                    System.out.println("(head)");
                    break;

                case 8:
                    System.out.print("Enter data to insert at the beginning: ");
                    int insertAtBeginningData = scanner.nextInt();
                    circularLinkedList.insertAtBeginning(insertAtBeginningData);
                    break;

                case 9:
                    System.out.print("Enter data to insert at the last: ");
                    int insertAtLastData = scanner.nextInt();
                    circularLinkedList.insertAtLast(insertAtLastData);
                    break;

                case 10:
                    circularLinkedList.deleteAtBeginning();
                    break;

                case 11:
                    circularLinkedList.deleteAtLast();
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
