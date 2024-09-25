import java.util.Scanner;
public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] array = new int[n+1];
        boolean [][] dp = new boolean [n+1][m+1];
        for (int i = 1; i < n + 1; i ++) {
            array[i] = sc.nextInt();
        }
        dp[0][0] = true;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (j - array[i] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - array[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        if (dp[n][m]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}