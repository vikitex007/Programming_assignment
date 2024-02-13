public class Question_2_a {
    public int minMoves(int[] machines) {
        // Calculate the total number of dresses in all machines
        int totalDresses = 0;
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        int n = machines.length;
        // Check if the total number of dresses is divisible by the number of machines
        if (totalDresses % n != 0) {
            return -1; // If not divisible, it's impossible to equalize
        }

        int target = totalDresses / n; // Target number of dresses in each machine
        int moves = 0;
        int balance = 0;

        // Iterate through each machine to balance the dresses
        for (int dresses : machines) {
            // Calculate the imbalance of dresses in the current machine
            balance += dresses - target;
            // Update the moves required to balance the dresses
            // Max of current imbalance and the absolute difference between current imbalance and dresses required
            moves = Math.max(moves, Math.max(balance, Math.abs(balance)));
        }

        return moves;
    }

    public static void main(String[] args) {
        Question_2_a machine = new Question_2_a();

        // Example usage
        int[] machines = {1, 0, 5};
        System.out.println(machine.minMoves(machines)); // Output: 2
    }
}
