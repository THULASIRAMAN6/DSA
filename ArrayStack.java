import java.util.Scanner;
import java.util.*;

public class ArrayStack <T>{
	static final int MAX_SIZE = 30;
	T arr[] = (T[])new Object[MAX_SIZE];
	int top;
	
	ArrayStack() {
		top = -1;
	}
	
	void push(T val){
		if(top==MAX_SIZE-1)
			throw new IndexOutOfBoundsException("Stack overflow");
		arr[++top] = val;
	}
	
	T pop(){
		if(top==-1) {
			throw new IndexOutOfBoundsException("Stack underflow");
		}
		return arr[top--];
	}
	
	T peek() {
		return arr[top];
	}
	
	boolean isEmpty(){
		return top==-1;
	}

    private static void printStack(ArrayStack<Integer> stack) {
        ArrayStack<Integer> tempStack = new ArrayStack<>();
        while (!stack.isEmpty()) {
            int element = stack.pop();
            System.out.print(element + " ");
            tempStack.push(element);
        }
        // Restoring the stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n----- Stack Operations -----");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check if Stack is Empty");
            System.out.println("5. Print Stack");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter value to push: ");
                int pushValue = scanner.nextInt();
                stack.push(pushValue);
                break;

            case 2:
                try {
                    int poppedValue = stack.pop();
                    System.out.println("Popped value: " + poppedValue);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                try {
                    int peekedValue = stack.peek();
                    System.out.println("Peeked value: " + peekedValue);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                boolean isEmpty = stack.isEmpty();
                System.out.println("Is Stack Empty: " + isEmpty);
                break;

            case 5:
                System.out.println("Stack Elements:");
                printStack(stack);
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