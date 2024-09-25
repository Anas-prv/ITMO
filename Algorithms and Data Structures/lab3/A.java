import java.util.Scanner;

public class A {
    public static long sumOfElements(long left, long right, long currentLeft, 
                                     long currentRight, long[] tree, long placeInTree) {
        if (currentRight <= right && left <= currentLeft) {
            return tree[(int) placeInTree];
        }
        if (currentRight < left || right < currentLeft) {
            return 0;
        }
        long middle = (1 + currentLeft + currentRight) / 2;
        long leftSum = sumOfElements(left, right, currentLeft,
                middle - 1, tree, 2 * placeInTree + 1);
        long rightSum = sumOfElements(left, right, middle, currentRight, tree, 2 * placeInTree + 2);
        long sum = rightSum + leftSum;
        return sum;
    }

    public static long[] set(long position, long valueToInsert, long PlaceInTree,
                             long currentLeft, long currentRight, long[] tree) {
        if (currentRight == currentLeft) {
            tree[(int) PlaceInTree] = valueToInsert;
        } else {
            long middle = (1 + currentLeft + currentRight) / 2;
            if (position >= middle) {
                set(position, valueToInsert, 2 * PlaceInTree + 2, middle, currentRight, tree);
            } else {
                set(position, valueToInsert, 2 * PlaceInTree + 1, currentLeft, middle - 1, tree);
            }
            tree[(int) PlaceInTree] = tree[(int) (2 * PlaceInTree + 1)] + tree[(int) (2 * PlaceInTree + 2)];
        }
        return tree;
    }

    public static long[] treeToBuild(int currentLeft, int currentRight, int placeInTree, long[] array, long[] tree) {
        if (currentRight == currentLeft) {
            tree[placeInTree] = array[currentLeft];
        } else {
            int middle = (1 + currentLeft + currentRight) / 2;
            treeToBuild(currentLeft, middle - 1, 2 * placeInTree + 1, array, tree);
            treeToBuild(middle, currentRight, 2 * placeInTree + 2, array, tree);
            tree[placeInTree] = tree[2 * placeInTree + 1] + tree[2 * placeInTree + 2];
        }
        return tree;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nn = sc.nextInt();
        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        int t = (int) (Math.log(nn) / Math.log(2));
        int n = (int) Math.pow(2, (t + 1));
        long[] array = new long[n];
        int k;
        long v, y;
        for (int i = 0; i < nn; i++) {
            array[i] = sc.nextLong();
        }
        for (int i = nn; i < n; i++) {
            array[i] = 0;
        }
        long[] st = new long[2 * n - 1];
        long res;
        long[] stt = treeToBuild(0, n - 1, 0, array, st);
        for (int i = 0; i < m; i++) {
            k = sc.nextInt();
            v = sc.nextLong();
            y = sc.nextLong();
            if (k == 2) {
                res = sumOfElements(v, y - 1, 0, n - 1, stt, 0);
                sb.append(res).append('\n');
            } else {
                set(v, y, 0, 0, n - 1, stt);
            }
        }
        System.out.println(sb);
    }
}
