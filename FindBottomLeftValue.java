import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

class FindBottomLeftValueUsingQueue {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode current = null;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.right != null) {
                queue.add(current.right);
            }
            if (current.left != null) {
                queue.add(current.left);
            }
        }
        return current.val;
    }
}

class FindBottomLeftValueUsingStack {
    private int maxDepth = -1;
    private int bottomLeftValue;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return bottomLeftValue;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        // Check if this node is the deepest encountered so far
        if (depth > maxDepth) {
            maxDepth = depth;
            bottomLeftValue = node.val;
        }

        // First go to the left subtree, then to the right subtree
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}

public class FindBottomLeftValue {
    public static void main(String[] args) {
        // Create the binary tree from the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        // Create an instance of FindBottomLeftValueUsingStack and find the bottom left value
        FindBottomLeftValueUsingStack stackSolution = new FindBottomLeftValueUsingStack();
        int bottomLeftValueStack = stackSolution.findBottomLeftValue(root);
        System.out.println("The bottom left value using stack is: " + bottomLeftValueStack);

        // Create an instance of FindBottomLeftValueUsingQueue and find the bottom left value
        FindBottomLeftValueUsingQueue queueSolution = new FindBottomLeftValueUsingQueue();
        int bottomLeftValueQueue = queueSolution.findBottomLeftValue(root);
        System.out.println("The bottom left value using queue is: " + bottomLeftValueQueue);
    }
}
