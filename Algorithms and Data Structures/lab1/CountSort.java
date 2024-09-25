import java.util.Scanner;

public class CountSort {
    public static void myCountSort(int [] array, int count) {
        int n = array.length;
        int [] indexArray = new int [count + 1];
        for (int i = 0; i < count + 1; i ++) {
            indexArray[i] = 0;
        }
        for (int i = 0; i < n; i ++) {
            indexArray[array[i]] = indexArray[array[i]] + 1;
        }
        int index = 0;
        for (int i = 0; i < count + 1; i ++) {
            int t = indexArray[i];
            for (int j = 0; j < t; j ++) {
                array[index] = i;
                index ++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        int[] array = new int[n];
        while (sc.hasNextInt()) {
            for (int i = 0; i < n; i++) {
                array[i] += sc.nextInt();
                if (array[i] > count) {
                    count = array[i];
                }
            }
        }
        myCountSort(array,count);
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
