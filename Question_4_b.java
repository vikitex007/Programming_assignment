import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Question_4_b {
    // Method to find x closest values to the target k in the binary search tree
    public List<Integer> closestKValues(TreeNode root, double target, int x) {
        List<Integer> closestValues = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        // Perform inorder traversal of the BST
        while (root != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // Process the leftmost node
            root = stack.pop();
            // Add the value of the current node to the priority queue
            addValueToQueue(closestValues, root.val, target, x);

            // Move to the right subtree
            root = root.right;
        }

        return closestValues;
    }

    // Method to add a value to the priority queue if it is closer to the target than existing values
    private void addValueToQueue(List<Integer> closestValues, int val, double target, int x) {
        if (closestValues.size() < x) {
            closestValues.add(val);
        } else {
            // If the priority queue is full, compare the current value with the target
            double diff1 = Math.abs(closestValues.get(0) - target);
            double diff2 = Math.abs(val - target);
            // If the current value is closer to the target, remove the farthest value from the priority queue
            if (diff2 < diff1) {
                closestValues.remove(0);
                closestValues.add(val);
            }
        }
        // Sort the priority queue to maintain the closest values in ascending order
        Collections.sort(closestValues);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example usage
        Question_4_b solution = new Question_4_b();

        // Create the binary search tree
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        // Define the target and the number of closest values required
        double target = 3.8;
        int x = 2;

        // Find and print the x closest values to the target
        List<Integer> closestValues = solution.closestKValues(root, target, x);
        System.out.println("Closest values to " + target + " are: " + closestValues);
    }
}
