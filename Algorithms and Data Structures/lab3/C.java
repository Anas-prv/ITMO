import java.util.*;

public class C {

    public static long find(long[][] tree, int placeInTree) {
        return tree[placeInTree][3] > 0 ? tree[placeInTree][3] : 0;
    }

    public static void set(long position, long valueToInsert, int placeInTree,
                           long currentLeft, long currentRight, long[][] myTree) {
        if (currentRight == currentLeft) {
            myTree[placeInTree][0] = valueToInsert;
            myTree[placeInTree][1] = valueToInsert;
            myTree[placeInTree][2] = valueToInsert;
            myTree[placeInTree][3] = valueToInsert;
        } else {
            long middle = (currentLeft + currentRight + 1) / 2;
            if (position >= middle) {
                set(position, valueToInsert, 2 * placeInTree + 2, middle, currentRight, myTree);
            } else {
                set(position, valueToInsert, 2 * placeInTree + 1, currentLeft, middle - 1, myTree);
            }
            myTree[placeInTree][0] = myTree[2 * placeInTree + 1][0] + myTree[2 * placeInTree + 2][0];
            myTree[placeInTree][3] = Math.max(Math.max(myTree[2 * placeInTree + 1][3],
                            myTree[2 * placeInTree + 2][1] + myTree[2 * placeInTree + 1][2]),
                    myTree[2 * placeInTree + 2][3]);
            if (myTree[2 * placeInTree + 1][1] > myTree[2 * placeInTree + 1][0] +
                    myTree[(2 * placeInTree + 2)][1]) {
                myTree[placeInTree][1] = myTree[2 * placeInTree + 1][1];
            } else {
                myTree[placeInTree][1] = myTree[2 * placeInTree + 1][0] +
                        myTree[(2 * placeInTree + 2)][1];
            }
            if (myTree[2 * placeInTree + 1][2] +
                    myTree[2 * placeInTree + 2][0] > myTree[2 * placeInTree + 2][2]) {
                myTree[placeInTree][2] = myTree[2 * placeInTree + 1][2] +
                        myTree[2 * placeInTree + 2][0];
            } else {
                myTree[placeInTree][2] = myTree[2 * placeInTree + 2][2];
            }
        }
    }

    public static long[][] treeToBuild(int currentLeft, int currentRight,
                                       int placeInTree, long[] array, long[][] myTree) {
        if (currentRight == currentLeft) {
            myTree[placeInTree][0] = array[currentRight];
            myTree[placeInTree][1] = array[currentRight];
            myTree[placeInTree][2] = array[currentRight];
            myTree[placeInTree][3] = array[currentRight];
        } else {
            int middle = (currentLeft + currentRight + 1) / 2;
            treeToBuild(currentLeft, middle - 1, 2 * placeInTree + 1, array, myTree);
            treeToBuild(middle, currentRight, 2 * placeInTree + 2, array, myTree);
            myTree[placeInTree][0] = myTree[2 * placeInTree + 1][0] + myTree[2 * placeInTree + 2][0];
            myTree[placeInTree][3] = Math.max(Math.max(myTree[2 * placeInTree + 1][3],
                            myTree[2 * placeInTree + 1][2] + myTree[2 * placeInTree + 2][1]),
                    myTree[2 * placeInTree + 2][3]);
            if (myTree[2 * placeInTree + 1][1] > myTree[2 * placeInTree + 1][0] +
                    myTree[(2 * placeInTree + 2)][1]) {
                myTree[placeInTree][1] = myTree[2 * placeInTree + 1][1];  
            } else {
                myTree[placeInTree][1] = myTree[2 * placeInTree + 1][0] +
                        myTree[(2 * placeInTree + 2)][1];
            }
            if (myTree[2 * placeInTree + 1][2] +
                    myTree[2 * placeInTree + 2][0] > myTree[2 * placeInTree + 2][2]) {
                myTree[placeInTree][2] = myTree[2 * placeInTree + 1][2] +
                        myTree[2 * placeInTree + 2][0];
            } else {
                myTree[placeInTree][2] = myTree[2 * placeInTree + 2][2];
            }
        }
        return myTree;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nn = sc.nextInt();
        int m = sc.nextInt();
        int n;
        StringBuilder sb = new StringBuilder();
        int t = (int) (Math.log(nn) / Math.log(2));
        if (nn > Math.pow(2, t)) {
            n = (int) Math.pow(2, (t + 1));
        } else {
            n = (int) Math.pow(2, (t));
        }
        long[] array = new long[n];
        long v, k;
        for (int i = 0; i < nn; i++) {
            array[i] = sc.nextLong();
        }
        for (int i = nn; i < n; i++) {
            array[i] = 0;
        }
        long[][] time = new long[2 * n - 1][4];
        long[][] myTree = treeToBuild(0, n - 1, 0, array, time);
        long first = find(myTree, 0);
        sb.append(first).append('\n');
        for (int i = 0; i < m; i++) {
            k = sc.nextLong();
            v = sc.nextLong();
            set(k, v, 0, 0, n - 1, myTree);
            long all = find(myTree, 0);
            sb.append(all).append('\n');
        }
        System.out.println(sb);
    }
}