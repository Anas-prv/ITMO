import java.util.Scanner;
public class Heap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        int[] array = new int[n];
        while (sc.hasNextInt()) {
            for (int i = 0; i < n; i++) {
                array[i] += sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                if (2 * i + 1 < n) {
                    if (array[i] > array[2 * i + 1]) {
                        if (count == 0) {
                            System.out.println("NO");
                            count += 1;
                        }
                    }
                }
                if (2 * i + 2 <= n) {
                    if (array[i] > array[2 * i + 1]) {
                        if (count == 0) {
                            System.out.println("NO");
                            count += 1;
                        }
                    }
                }
            }
            if (count == 0) {
                System.out.println("YES");
            }
        }
    }
}
