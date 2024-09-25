import java.util.Scanner;
public class PrefixSum {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long [] array = new long[n];
        int m = sc.nextInt();
        long sum;
        long [] ints = new long [n];
        for (int i = 0; i < n; i ++) {
            array[i] = sc.nextLong();
            if (i == 0) {
                ints[i] = array[0];
            } else {
                ints[i] = ints[i - 1] + array[i];
            }
        }
        for (int j = 0; j < m; j ++) {
            int l = sc.nextInt();
            int r  = sc.nextInt();
            if (l > 1) {
                sum = ints[r - 1] - ints[l - 2];
            } else {
                sum = ints[r - 1];
            }
            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }
}