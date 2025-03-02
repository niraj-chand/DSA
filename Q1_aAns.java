/*
 Problem: Find the minimum number of measurements (moves) to test n temperature levels using k samples.

Base Case:

If k == 1, return n (each level must be tested individually).

DP Table:

dp[i][j] = max temperature levels testable with i samples and j measurements.

Recurrence Relation:

dp[i][j] = 1 + dp[i-1][j-1] + dp[i][j-1]:

1: Current measurement.

dp[i-1][j-1]: Levels below threshold.

dp[i][j-1]: Levels above threshold.

Iterate:

Increment moves until dp[k][moves] >= n.

Result:

Return the smallest moves satisfying dp[k][moves] >= n.

Complexity:
Time: O(k * moves)

Space: O(k * n)
 */
public class Q1_aAns { // Define a public class named Q1_aAns

    // Method to find the minimum number of measurements required
    public static int minMeasurements(int k, int n) {
        // If we have only one sample (k = 1), we need to check every temperature one by one.
        if (k == 1) return n;

        // Create a DP table where dp[i][j] represents the maximum number of temperature
        // levels that can be tested with 'i' samples and 'j' measurements.
        int[][] dp = new int[k + 1][n + 1];

        // Variable to count the number of measurements needed
        int moves = 0;

        // Keep increasing the number of measurements until we can test at least 'n' levels
        while (dp[k][moves] < n) {
            moves++; // Increment the measurement count

            // Loop through each sample count from 1 to k
            for (int i = 1; i <= k; i++) {
                // DP recurrence relation:
                // The maximum number of temperature levels that can be tested is determined by:
                // - One additional test (the current measurement)
                // - The number of levels we can test with one fewer sample (dp[i - 1][moves - 1])
                // - The number of levels we can test with the same number of samples but one fewer measurement (dp[i][moves - 1])
                dp[i][moves] = 1 + dp[i - 1][moves - 1] + dp[i][moves - 1];
            }
        }

        // Return the minimum number of measurements required
        return moves;
    }

    // Main method to test the function with sample cases
    public static void main(String[] args) {
        System.out.println(minMeasurements(1, 2));  // Expected output: 2
        System.out.println(minMeasurements(2, 6));  // Expected output: 3
        System.out.println(minMeasurements(3, 14)); // Expected output: 4
    }
}


//Output
// 2
// 3
// 4