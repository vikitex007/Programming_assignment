import java.util.PriorityQueue;

public class Question_1_b {

    public int minBuildTime(int[] engines, int splitCost) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // Max heap

        for (int engine : engines) {
            pq.offer(engine);
        }

        int time = 0;

        while (pq.size() > 1) {
            int maxTime = pq.poll(); // Take the engineer with the maximum time to build an engine
            int secondMaxTime = pq.isEmpty() ? 0 : pq.peek(); // Get the time of the second engineer

            // Split the engineer and add the split cost
            time += splitCost;

            // Add the two new engineers after splitting
            pq.offer(maxTime / 2);
            pq.offer((maxTime + 1) / 2); // Handles odd times correctly

            // Adjust the second engineer's time if necessary
            if (secondMaxTime > maxTime / 2) {
                pq.remove(secondMaxTime);
                pq.offer(secondMaxTime / 2);
                pq.offer((secondMaxTime + 1) / 2); // Handles odd times correctly
            }
        }

        return time + pq.poll(); // Add the remaining time of the last engineer
    }

    public static void main(String[] args) {
        Question_1_b builder = new Question_1_b();
        int[] engines = {1, 2, 3};
        int splitCost = 1;
        System.out.println(builder.minBuildTime(engines, splitCost)); // Output: 4
    }
}
