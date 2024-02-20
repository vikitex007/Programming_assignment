public class Question_2_a{

    // Method to calculate the minimum moves required to equalize the number of dresses in each machine
    public static int calculateMinimumMoves(int[] machines) {
        int totalDresses = 0;
        int totalMachines = machines.length;

        // Calculate the total number of dresses across all machines
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        // If the total number of dresses cannot be evenly distributed, return -1
        if (totalDresses % totalMachines != 0) {
            return -1;
        }

        // Calculate the target number of dresses per machine
        int targetDresses = totalDresses / totalMachines;
        int moves = 0;
        int cumulativeSum = 0;

        // Iterate through the machines to calculate the moves needed to equalize dresses
        for (int dresses : machines) {
            // Update the cumulative sum of dresses
            cumulativeSum += dresses - targetDresses;
            // Calculate the maximum moves needed based on the current cumulative sum and individual machine dresses
            moves = Math.max(moves, Math.max(Math.abs(cumulativeSum), dresses - targetDresses));
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] machines = {1, 0, 5};
        int minMoves = calculateMinimumMoves(machines);
        System.out.println("Minimum moves required: " + minMoves); // Output should be 2
    }
}
