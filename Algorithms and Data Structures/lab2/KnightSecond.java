import java.io.*;

public class KnightSecond {
    public static int count(int i, int j, int n, int m, int [][] dp) {
        if (i < 1 || j < 1) {
            return 0;
        }
        if (i > n || j > m) {
            return 0;
        }
        if (dp[i][j] == -1) {
            dp[i][j] = (int) ((count(i + 1,j - 2,n ,m, dp)
                    + count(i - 2,j + 1,n ,m, dp)
                    + count(i - 2,j - 1,n ,m, dp)
                    + count(i - 1,j - 2,n ,m, dp))
                    % (Math.pow(10,6) + 7));
        }
        return dp[i][j];
    }
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("knight.in"))) {
            String line = reader.readLine();
            String [] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int [][] dp = new int [n+1][m+1];
            for (int i = 0; i < n + 1; i ++) {
                for (int j = 0; j < m + 1; j ++) {
                     dp[i][j] = -1;
                }
            }
            dp[1][1] = 1;
            int cur = count(n,m,n,m,dp);
            PrintWriter writer = new PrintWriter("knight.out");
            writer.print(cur);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}