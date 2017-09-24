package dynamicProgramming;

/**
 * at http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
 */
public class OptimalStrategyForGameofCoins {
    static int total_profit (int coins[], int i, int j, int dp[][]) {
        if (dp[i][j] != -1)
            dp[i][j] = dp[i][j];
        else if (j-i == 1)
            dp[i][j] = Math.max(coins[i], coins[j]);
        else {
            // opponent always leaves whatever gives u minimum
            // choose ith coin
            int value1 = coins[i] + Math.min(total_profit(coins, i+2, j, dp), total_profit(coins, i+1, j-1, dp));
            // choose jth coin
            int value2 = coins[j] + Math.min (total_profit(coins, i+1, j-1, dp), total_profit(coins, i, j-2, dp));
            dp[i][j] = Math.max (value1, value2);
        }

        return dp[i][j];
    }

    static int total_profit (int coins[], int n) {
        int dp[][] = new int[n][n];
        for (int i = 0; i<n; i++)
            for (int j = 0; j<n; j++) {
                if (i == j)
                    dp[i][j] = coins[i];
                else
                    dp[i][j] = -1;
            }
        return total_profit(coins, 0, n-1, dp);
    }

    public static void main(String args[] ) throws Exception {
        int coins[] = {20, 30, 2, 2, 2, 10};//{8, 15, 3, 7};//{2, 2, 2, 2};
        int n = coins.length;

        System.out.println ("total profit = " + total_profit(coins, n));
    }
}
