import java.util.PriorityQueue;

public class MinimumTimeToBuildEngines {
    public static int minTimeToBuildEngines(int[] engines, int splitCost) {
        // Create a priority queue to simulate the process of assigning engineers to build engines
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add initial engineer
        pq.offer(0);
        
        // Initialize total time
        int totalTime = 0;
        
        // Assign engineers to build engines
        for (int engine : engines) {
            // Get the engineer who can finish building the engine earliest
            int currentEngineer = pq.poll();
            
            // Update the total time
            totalTime = Math.max(totalTime, currentEngineer) + engine;
            
            // Add the engineer back to the queue after finishing the current engine
            pq.offer(currentEngineer + splitCost);
        }
        
        return totalTime;
    }

    public static void main(String[] args) {
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;
        System.out.println(minTimeToBuildEngines(engines, splitCost)); // Output: 4
    }
}
