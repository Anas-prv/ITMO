import java.util.*;

public class D {

    public static long[][] replaceTheNumber(long placeInTree, long currentLeft, long currentRight, long[][] tree, long position) {
        if (currentRight == currentLeft) {
            if (tree[(int) placeInTree][0] == 1) {
                tree[(int) placeInTree][0] = 0;
                tree[(int) placeInTree][1] = 0;
            } else {
                tree[(int) placeInTree][0] = 1;
                tree[(int) placeInTree][1] = 1;
            }
        } else {
            long middle = (currentLeft + currentRight + 1) / 2;
            if (position >= middle) {
                replaceTheNumber(2 * placeInTree + 2, middle, currentRight, tree, position);
            } else {
                replaceTheNumber(2 * placeInTree + 1, currentLeft, middle - 1, tree, position);
            }
            tree[(int) placeInTree][0] = Math.max(tree[(int) (2 * placeInTree + 1)][0],
                    tree[(int) (2 * placeInTree + 2)][0]);
            tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 1)][1] +
                    tree[(int) (2 * placeInTree + 2)][1];
        }
        return tree;
    }

    public static long indexOfTheNumber(long currentLeft, long currentRight,
                                        long[][] tree, long placeInTree, long index) {
        if (currentRight == currentLeft) {
            long indexToFind = currentRight;
            return indexToFind;
        } else {
            long middle = (currentLeft + currentRight + 1) / 2;
            if (index > tree[(int) (2 * placeInTree + 1)][1]) {
                index = index - tree[(int) (2 * placeInTree + 1)][1];
                return indexOfTheNumber(middle, currentRight, tree, 2 * placeInTree + 2, index);
            } else {
                return indexOfTheNumber(currentLeft, middle - 1, tree, 2 * placeInTree + 1, index);
            }
        }
    }

    public static long[][] treeToBuild(int currentLeft, int currentRight,
                                       int placeInTree, long[] array, long[][] tree) {
        if (currentRight == currentLeft) {
            tree[placeInTree][0] = array[currentLeft];
            tree[placeInTree][1] = array[currentLeft];
        } else {
            int middle = (currentLeft + currentRight + 1) / 2;
            treeToBuild(currentLeft, middle - 1, 2 * placeInTree + 1, array, tree);
            treeToBuild(middle, currentRight, 2 * placeInTree + 2, array, tree);
            tree[placeInTree][0] = Math.max(tree[2 * placeInTree + 1][0], tree[2 * placeInTree + 2][0]);
            tree[placeInTree][1] = tree[(2 * placeInTree + 1)][1] +
                    tree[(2 * placeInTree + 2)][1];
        }
        return tree;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int m = sc.nextInt();
        int n;
        StringBuilder sb = new StringBuilder();
        int t = (int) (Math.log(number) / Math.log(2));
        if (number > Math.pow(2, t)) {
            n = (int) Math.pow(2, (t + 1));
        } else {
            n = (int) Math.pow(2, (t));
        }
        long[] array = new long[n];
        int k;
        long v;
        for (int i = 0; i < number; i++) {
            array[i] = sc.nextLong();
        }
        for (int i = number; i < n; i++) {
            array[i] = 0;
        }
        long[][] time = new long[2 * n - 1][2];
        long num;
        long[][] tree = treeToBuild(0, n - 1, 0, array, time);
        for (int i = 0; i < m; i++) {
            k = sc.nextInt();
            v = sc.nextLong();
            if (k == 2) {
                num = indexOfTheNumber(0, n - 1, tree, 0, v + 1);
                sb.append(num).append(" ").append('\n');
            } else {
                replaceTheNumber(0, 0, n - 1, tree, v);
            }
        }
        System.out.println(sb);
    }
}