import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;

        // dp[i][j] stores the max score from 'S' to (i, j)
        int[][] dp = new int[n][n];
        // count[i][j] stores the number of paths achieving that max score
        int[][] count = new int[n][n];

        // Initialize the starting position 'S' at the bottom-right
        count[n - 1][n - 1] = 1;

        // Iterate backwards from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip if this cell cannot be part of any valid path from 'S'
                if (count[i][j] == 0) continue;

                // Look at the 3 possible moves: Up, Left, and Up-Left (Diagonal)
                int[][] directions = {{-1, 0}, {0, -1}, {-1, -1}};

                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    // Check bounds and avoid obstacles
                    if (ni >= 0 && nj >= 0 && board.get(ni).charAt(nj) != 'X') {
                        char cellChar = board.get(ni).charAt(nj);
                        int cellValue = (cellChar == 'E') ? 0 : (cellChar - '0');
                        int nextScore = dp[i][j] + cellValue;

                        if (nextScore > dp[ni][nj]) {
                            // Found a strictly better path to (ni, nj)
                            dp[ni][nj] = nextScore;
                            count[ni][nj] = count[i][j];
                        } else if (nextScore == dp[ni][nj]) {
                            // Found another path with the same maximum score
                            count[ni][nj] = (count[ni][nj] + count[i][j]) % MOD;
                        }
                    }
                }
            }
        }

        // The answer is stored at the top-left corner 'E' (0, 0)
        return new int[]{dp[0][0], count[0][0]};
    }
}