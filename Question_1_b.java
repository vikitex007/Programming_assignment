public class Question_1_b {
    // Method to calculate the minimum time required to construct engines
    public static int findMinimumConstructionTime(int[] engineConstructionTimes, int splitCost) {
        int totalDuration = 0;
        // Iterate through each engine construction time
        for (int constructionTime : engineConstructionTimes) {
            // Check if splitting the construction time reduces the total duration
            if (splitCost + constructionTime / 2 < constructionTime) {
                // If splitting reduces time, add split cost to total duration
                totalDuration += splitCost;
            } else {
                // Otherwise, add full construction time to total duration
                totalDuration += constructionTime;
            }
        }
        return totalDuration;
    }

    public static void main(String[] args) {
        int[] engineConstructionTimes = {1, 2, 3}; // Array representing construction times of engines
        int splitCost = 1; // Cost to split construction time

        // Calculate and print the minimum time to construct engines
        System.out.println("Minimum time to construct engines: " + findMinimumConstructionTime(engineConstructionTimes, splitCost));
    }
}
