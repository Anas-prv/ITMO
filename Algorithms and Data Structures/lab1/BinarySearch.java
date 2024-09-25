import java.util.Scanner;
public class BinarySearch {
    public static long binarySearch(long[] array, long l) {
        int left = 0;
        int right = array.length - 1;
        int middle = 0;
        while (right - left > 1) {
            if (array[middle] < l) {
                left = middle;
            } else {
                right = middle;
            }
            middle = (right + left) / 2;
        }
        if (Math.abs(array[right] - l) < Math.abs(array[left] - l)) {
            return array[right];
        } else {
            return array[left];
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long [] array = new long [n];
        long[] ints = new long [k];
        for (int i = 0; i < n; i ++) {
            array[i] = sc.nextLong();
        }
        for (int i = 0; i < k; i ++) {
            ints[i] = sc.nextLong();
            System.out.println(binarySearch(array,ints[i]));
        }
    }
}