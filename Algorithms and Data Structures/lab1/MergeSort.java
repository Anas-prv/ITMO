import java.util.Scanner;

public class MergeSort {

    public static void myMergeSort(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }
        int middle = len/2;
        int[] leftPart = new int[middle];
        int[] rightPart = new int[len - middle];

        for (int i = 0; i < middle; i++) {
            leftPart[i] = array[i];
        }
        for (int i = middle; i < len; i++) {
            rightPart[i - middle] = array[i];
        }
        myMergeSort(leftPart);
        myMergeSort(rightPart);

        mergeMyArrays(array, leftPart, rightPart);
    }
    public static void mergeMyArrays(int[] array, int[] leftPart, int[] rightPart) {
        int indexFirst = 0;
        int indexSecond = 0;
        while (indexFirst < leftPart.length || indexSecond < rightPart.length) {
            if (indexSecond == rightPart.length || indexFirst < leftPart.length && leftPart[indexFirst] < rightPart[indexSecond]) {
                array[indexFirst + indexSecond] = leftPart[indexFirst];
                indexFirst++;
            }
            else {
                array[indexFirst + indexSecond] = rightPart[indexSecond];
                indexSecond++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        while (sc.hasNextInt()) {
            for (int i = 0; i < n; i++) {
                array[i] += sc.nextInt();
            }
        }
        myMergeSort(array);
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
