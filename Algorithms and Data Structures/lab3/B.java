import java.util.*;

public class B {
    public static Long count = Long.valueOf(0);
    public static Long number = Long.MAX_VALUE;

    public static long minOfElements(long left, long right, long currentLeft,
                                     long currentRight, long[][] tree, long placeInTree) {
        if (currentRight <= right && left <= currentLeft) {
            if (number == tree[(int) placeInTree][0]) {
                count += tree[(int) placeInTree][1];
            } else if (number < tree[(int) placeInTree][0]) {
                count += 0;
            } else {
                number = tree[(int) placeInTree][0];
                count = tree[(int) placeInTree][1];
            }
            return tree[(int) placeInTree][0];
        }
        if (currentRight < left || right < currentLeft) {
            return Long.MAX_VALUE;
        }
        long middle = (currentLeft + currentRight + 1) / 2;
        long leftMin = minOfElements(left, right, currentLeft,
                middle - 1, tree, 2 * placeInTree + 1);
        long rightMin = minOfElements(left, right, middle, currentRight, tree, 2 * placeInTree + 2);
        long min = Math.min(leftMin, rightMin);
        return min;
    }

    public static long[][] set(long position, long valueToInsert, long placeInTree,
                               long currentLeft, long currentRight, long[][] tree) {
        if (currentRight == currentLeft) {
            tree[(int) placeInTree][0] = valueToInsert;
            tree[(int) placeInTree][1] = 1;
        } else {
            long middle = (currentLeft + currentRight + 1) / 2;
            if (position >= middle) {
                set(position, valueToInsert, 2 * placeInTree + 2, middle, currentRight, tree);
            } else {
                set(position, valueToInsert, 2 * placeInTree + 1, currentLeft, middle - 1, tree);
            }
            tree[(int) placeInTree][0] = Math.min(tree[(int) (2 * placeInTree + 1)][0],
                    tree[(int) (2 * placeInTree + 2)][0]);
            if (tree[(int) (2 * placeInTree + 1)][0] == tree[(int) (2 * placeInTree + 2)][0]) {
                tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 1)][1] +
                        tree[(int) (2 * placeInTree + 2)][1];
            } else if (tree[(int) placeInTree][0] == tree[(int) (2 * placeInTree + 1)][0]) {
                tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 1)][1];
            } else {
                tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 2)][1];
            }
        }
        return tree;
    }

    public static long[][] treeToBuild(int currentLeft, int currentRight,
                                       int placeInTree, long[] array, long[][] tree) {
        if (currentRight == currentLeft) {
            tree[placeInTree][0] = array[currentLeft];
            tree[placeInTree][1] = 1;
        } else {
            int middle = (currentLeft + currentRight + 1) / 2;
            treeToBuild(currentLeft, middle - 1, 2 * placeInTree + 1, array, tree);
            treeToBuild(middle, currentRight, 2 * placeInTree + 2, array, tree);
            tree[placeInTree][0] = Math.min(tree[2 * placeInTree + 1][0], tree[2 * placeInTree + 2][0]);
            if (tree[placeInTree][0] == tree[2 * placeInTree + 1][0]
                    && tree[placeInTree][0] == tree[2 * placeInTree + 2][0]) {
                tree[placeInTree][1] = tree[2 * placeInTree + 1][1] + tree[2 * placeInTree + 2][1];
            } else if (tree[(int) placeInTree][0] == tree[(int) (2 * placeInTree + 1)][0]) {
                tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 1)][1];
            } else {
                tree[(int) placeInTree][1] = tree[(int) (2 * placeInTree + 2)][1];
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nn = sc.nextInt();
        int m = sc.nextInt();
        int n = 0;
        StringBuilder sb = new StringBuilder();
        int t = (int) (Math.log(nn) / Math.log(2));
        if (nn > Math.pow(2,t)) {
            n = (int) Math.pow(2, (t + 1));
        } else {
            n = (int) Math.pow(2, (t));
        }
        long[] array = new long[n];
        int k;
        long v, y;
        for (int i = 0; i < nn; i++) {
            array[i] = sc.nextLong();
        }
        for (int i = nn; i < n; i++) {
            array[i] = Long.MAX_VALUE;
        }
        long[][] st = new long[2 * n - 1][2];
        long res;
        long[][] stt = treeToBuild(0, n - 1, 0, array, st);
        for (int i = 0; i < m; i++) {
            k = sc.nextInt();
            v = sc.nextLong();
            y = sc.nextLong();
            if (k == 2) {
                res = minOfElements(v, y - 1, 0, n - 1, stt, 0);
                sb.append(res).append(" ").append(count).append('\n');
                count = Long.valueOf(0);
                number = Long.MAX_VALUE;
            } else {
                set(v, y, 0, 0, n - 1, stt);
            }
        }
        System.out.println(sb);
    }
}