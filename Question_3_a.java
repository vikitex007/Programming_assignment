import java.util.PriorityQueue;

// Class to track scores and calculate median
class ScoreTracker {
    private PriorityQueue<Double> minHeap; // Heap for the higher half of the scores
    private PriorityQueue<Double> maxHeap; // Heap for the lower half of the scores

    // Constructor to initialize the score tracker
    public ScoreTracker() {
        // Initialize the min and max heaps
        minHeap = new PriorityQueue<>();
        // We use a custom comparator to create a max heap
        maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b, a)); // Max heap comparator
    }

    // Method to add a new score to the data stream
    public void addScore(double score) {
        // If the max heap is empty or the score is less than or equal to the maximum score in the max heap
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            // Add the score to the max heap
            maxHeap.offer(score);
        } else {
            // Add the score to the min heap
            minHeap.offer(score);
        }

        // Balance the heaps
        // If the size of the max heap is more than one greater than the size of the min heap
        if (maxHeap.size() > minHeap.size() + 1) {
            // Move the maximum score from the max heap to the min heap
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            // If the size of the min heap is greater than the size of the max heap
            // Move the minimum score from the min heap to the max heap
            maxHeap.offer(minHeap.poll());
        }
    }

    // Method to calculate and return the median score
    public double getMedianScore() {
        // If the max heap is empty, there are no scores added yet
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("No scores added yet");
        }

        // If the sizes of both heaps are equal, return the average of the top elements
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Otherwise, return the top element of the max heap
            return maxHeap.peek();
        }
    }
}

public class Question_3_a {
    public static void main(String[] args) {
        // Create a new instance of ScoreTracker
        ScoreTracker scoreTracker = new ScoreTracker();
        
        // Add scores to the tracker
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        
        // Get the median score and print it
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1); // Output: 87.8
        
        // Add more scores
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        
        // Get the new median score and print it
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2); // Output: 87.1
    }
}
