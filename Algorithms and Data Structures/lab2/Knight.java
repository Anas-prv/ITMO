import java.io.*;
public class Knight {
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("knight.in"))) {
            String line = reader.readLine();
            String [] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int [][] board = new int[n+1][m+1];
            board[1][1] = 1;
            for (int i = 2; i < n + 1; i ++) {
                for (int j = 2; j < m + 1; j ++) {
                    board[i][j] = board[i-2][j-1] + board[i-1][j-2];   
                }
            }
            PrintWriter writer = new PrintWriter("knight.out");
            writer.print(board[n][m]);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}