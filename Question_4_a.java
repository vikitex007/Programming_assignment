import java.util.*;

class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int targetKeys = 0;
        int[] dir = {-1, 0, 1, 0, -1}; // Direction array for moving up, right, down, left
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        int steps = 0;

        // Find the starting point 'S' and calculate the target keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == 'S') {
                    queue.offer(new int[]{i, j, 0});
                    visited.add(i + "-" + j + "-0");
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    targetKeys |= 1 << (grid[i].charAt(j) - 'a');
                }
            }
        }

        // Perform BFS to find the shortest path to collect all keys
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int keys = cur[2];
                
                // If all target keys are collected, return the number of steps
                if (keys == targetKeys) return steps;

                // Explore neighboring cells
                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k];
                    int ny = y + dir[k + 1];
                    int newKeys = keys;
                    // Check if the neighbor is within bounds and not a wall
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                        char c = grid[nx].charAt(ny);
                        // If the neighbor is an uppercase letter and the corresponding key is not collected, skip
                        if (Character.isUpperCase(c) && ((keys >> (c - 'A')) & 1) == 0) continue;
                        // If the neighbor is a lowercase letter, collect the key and update target keys
                        if (Character.isLowerCase(c)) {
                            newKeys |= 1 << (c - 'a');
                        }
                        // Add the new state to the queue if not visited before
                        if (!visited.contains(nx + "-" + ny + "-" + newKeys)) {
                            visited.add(nx + "-" + ny + "-" + newKeys);
                            queue.offer(new int[]{nx, ny, newKeys});
                        }
                    }
                }
            }
            // Increment the number of steps
            steps++;
        }
        // If it's not possible to collect all keys, return -1
        return -1;
    }
}

public class Question_4_a {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] grid = {
            "SPqPP",
            "WWWPW",
            "rPQPR"
        };
        System.out.println(solution.shortestPathAllKeys(grid)); // Output: 8
    }
}
