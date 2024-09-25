import java.util.Scanner;
public class NumberOfInversions {
    public static long myMergeSort(long [] array){
        int len = array.length;
        int middle = len/2;
        long h = 0;
        long[] leftPart = new long[middle];
        long[] rightPart = new long[len - middle];
        for (int i = 0; i < middle; i++) {
            leftPart[i] = array[i];
        }
        for (int i = middle; i < len; i++) {
            rightPart[i - middle] = array[i];
        }
        if (array.length <= 1) {
            return 0;
        }
        h += myMergeSort(leftPart);
        h += myMergeSort(rightPart);
        h += mergeMyArrays(array, leftPart, rightPart);
        return h;
    }
    public static long  mergeMyArrays(long[] array, long [] leftPart, long[] rightPart) {
        long indexFirst = 0;
        long indexSecond = 0;
        long k = 0;
        while (indexFirst < leftPart.length || indexSecond < rightPart.length) {
            if (indexSecond == rightPart.length) {
                array[(int) (indexFirst + indexSecond)] = leftPart[(int) indexFirst];
                indexFirst ++;
            }else if (indexFirst == leftPart.length) {
                array[(int) (indexFirst + indexSecond)] = rightPart[(int) indexSecond];
                indexSecond ++;
            } else if (leftPart[(int) indexFirst] <= rightPart[(int) indexSecond]) {
                array[(int) (indexFirst + indexSecond)] = leftPart[(int) indexFirst];
                indexFirst ++;
            } else {
                array[(int) (indexFirst + indexSecond)] = rightPart[(int) indexSecond];
                k += leftPart.length - indexFirst;
                indexSecond ++;
            }
        }
        return k;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long [] array = new long[(int) n];
        for (long i = 0; i < n; i++) {
            array[(int) i] += sc.nextInt();
        }
        System.out.println(myMergeSort(array));
    }
}