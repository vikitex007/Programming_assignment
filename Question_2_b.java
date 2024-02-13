

import java.util.*;

public class Question_2_b {

    public static List<Integer> whoKnowsSecret(int n, int[][] intervals, int firstPerson) {
        // Set to keep track of individuals who know the secret
        Set<Integer> known = new HashSet<>();
        // Queue to keep track of individuals who will know the secret during each interval
        Queue<Integer> queue = new LinkedList<>();
        
        // Initially, only the first person knows the secret
        queue.offer(firstPerson);

        // Iterate through each interval
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // Process each interval
            while (!queue.isEmpty() && start <= end) {
                // Get the current person
                int currentPerson = queue.poll();
                // Add the current person to the known set
                known.add(currentPerson);

                // Share the secret with other individuals
                for (int i = 0; i < n; i++) {
                    // Exclude the current person and those already known
                    if (i != currentPerson && !known.contains(i)) {
                        queue.offer(i);
                    }
                }

                // Move to the next time step
                start++;
            }
        }

        // Convert the known set to a list and return
        return new ArrayList<>(known);
    }

    public static void main(String[] args) {
        // Example input
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;

        // Call the method to find out who eventually knows the secret
        List<Integer> result = whoKnowsSecret(n, intervals, firstPerson);
        
        // Output the result
        System.out.println("Individuals who eventually know the secret: " + result);
    }
}
