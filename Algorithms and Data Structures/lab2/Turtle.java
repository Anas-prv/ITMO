import java.io.*;
import java.util.Scanner;

import static java.lang.Math.max;
public class Turtle {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int [][] array = new int[n][m];
            for (int  i = 0; i < n; i ++) {
                for (int j = 0; j < m; j ++) {
                    array[i][j] = sc.nextInt();
                }
            }
            int [][] board = new int[n][m];
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < m; j ++) {
                    if (i == 0 && j == 0) {
                        board[i][j] = array[i][j];
                    } else if (i == 0) {
                        board[i][j] = board[i][j - 1] + array[i][j];
                    } else if (j == 0) {
                        board[i][j] = board[i-1][j] + array[i][j];
                    } else {
                        board[i][j] = max(board[i-1][j],board[i][j-1]) + array[i][j];
                    }
                }
            }
            int i = n -1;
            int j = m -1;
            while(i > 0 || j > 0) {
                if (i == 0) {
                    sb.append("R");
                    j--;
                } else if (j == 0) {
                    sb.append("D");
                    i --;
                } else if (board[i][j-1] > board[i-1][j]) {
                    sb.append("R");
                    j--;
                } else {
                    sb.append("D");
                    i --;
                }
            }
            sb.reverse();
            PrintWriter writer = new PrintWriter("output.txt");
            writer.println(board[n-1][m-1]);
            writer.print(sb);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }
    }    
}