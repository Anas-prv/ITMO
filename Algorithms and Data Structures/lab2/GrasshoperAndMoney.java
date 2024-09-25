import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Math.max;
public class GrasshoperAndMoney {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] array = new int[n];
            array[0] = 0;
            array[n - 1] = 0;
            for (int i = 1; i < n - 1; i++) {
                array[i] = sc.nextInt();
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            int[] remember = new int[n + 1];
            for (int i = 2; i < n + 1; i ++) {
                int last = i - 1;
                int jump = max(i - k, 1);
                for (int j = jump; j < i; j ++) {
                    if (dp[j] > dp[last]) {
                        last = j;
                    }
                }
                remember[i] = last;
                dp[i] = dp[last] + array[i - 1];
            }
            int i = n;
            int count = 0;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(i);
            while(i > 1) {
                i = remember[i];
                list.add(i);
                count ++;
            }
            sb.append(dp[n] + "\n").append(count + "\n");
            Collections.reverse(list);
            for(int o = 0; o < list.size(); o ++) {
                
                    sb.append(list.get(o) + " ");
                
            }
            PrintWriter writer = new PrintWriter("output.txt");
            writer.print(sb);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}