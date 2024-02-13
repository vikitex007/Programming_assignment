
public class Question_1_a {

    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length;
        int k = costs[0].length;

        // Initialize dp array to store minimum costs
        int[][] dp = new int[n][k];

        // Initialize the first row with the costs of decorating the first venue
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

        // Iterate over each venue starting from the second one
        for (int i = 1; i < n; i++) {
            // Iterate over each theme for the current venue
            for (int j = 0; j < k; j++) {
                // Calculate the minimum cost of decorating the current venue with the current
                // theme
                // based on the costs of decorating the previous venue with other themes
                dp[i][j] = costs[i][j] + minExcept(dp[i - 1], j);
            }
        }

        // Return the minimum cost of decorating the last venue
        return min(dp[n - 1]);
    }

    // Utility function to find the minimum value in an array except for a given
    // index
    private static int minExcept(int[] arr, int index) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                min = Math.min(min, arr[i]);
            }
        }
        return min;
    }

    // Utility function to find the minimum value in an array
    private static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] costs = { { 1, 3, 2 }, { 4, 6, 8 }, { 3, 1, 5 } };
        System.out.println(minCost(costs)); // Output: 7
    }
}
