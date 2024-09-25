import java.util.Scanner;
import java.util.Stack;

public class K {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] array = new int [n];
        for (int i = 0; i < n; i ++) {
            array[i] = sc.nextInt();
        }
        int [] first = new int [n];
        int [] dp = new int [n];
        for (int i = 0; i < n; i ++) {
            dp[i] = 1;
            first[i] = -1;
            for (int j = 0; j < i; j ++) {
                if (array[j] < array[i]) {
                    if(dp[j] > dp[i] - 1) {
                        dp[i] = dp[j] + 1;
                        first[i] = j;
                    }
                }
            }
        }
        int position = 0;
        int start = dp[0];
        for (int i = 0; i < n; i ++) {
            if (dp[i] > start) {
                position = i;
                start = dp[i];
            }
        }
        Stack <Integer> stack = new Stack<>();
        while (position != -1) {
            stack.push(array[position]);
            position = first[position];
        }
        System.out.println(stack.size());
        String s = "";
        while (!stack.isEmpty()) {
            s += stack.pop() + " ";
        }
        System.out.println(s);
    }
}