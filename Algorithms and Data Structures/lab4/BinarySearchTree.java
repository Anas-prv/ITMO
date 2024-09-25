import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class BinarySearchTree {
    public static class TreeNode {
        long key;
        TreeNode left;
        TreeNode right;

        public TreeNode(long key) {
            this.key = key;
        }
    }

    public static TreeNode add(long k, TreeNode element) {
        if (element != null) {
            if (element.key > k) {
                element.left = add(k, element.left);
            } else if (element.key < k) {
                element.right = add(k, element.right);
            }
        } else {
            return new TreeNode(k);
        }
        return element;
    }

    public static boolean find(long k, TreeNode element) {
        if (element != null) {
            if (element.key > k) {
                return find(k, element.left);
            } else if (element.key < k) {
                return find(k, element.right);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static TreeNode findMin(TreeNode element) {
        if (element.left == null) {
            return element;
        } else {
            return findMin(element.left);
        }
    }

    public static TreeNode remove(long k, TreeNode element) {
        if (element != null) {
            if (element.key > k) {
                element.left = remove(k, element.left);
            } else if (element.key < k) {
                element.right = remove(k, element.right);
            } else {
                if (element.left != null && element.right != null) {
                    TreeNode minInRight = findMin(element.right);
                    element.key = minInRight.key;
                    element.right = remove(minInRight.key, element.right);
                } else if (element.right != null) {
                    element = element.right;
                } else if (element.left != null) {
                    element = element.left;
                } else {
                    element = null;
                }
            }
        }
        return element;
    }

    public static Long nextMin(long k, TreeNode element) {
        TreeNode next = null;
        while (element != null) {
            if (element.key <= k) {
                element = element.right;
            } else {
                next = element;
                element = next.left;
            }
        }
        if (next == null) {
            return 0L;
        } else {
            return next.key;
        }
    }

    public static Long previousMax(long k, TreeNode element) {
        TreeNode prev = null;
        while (element != null) {
            if (element.key >= k) {
                element = element.left;
            } else {
                prev = element;
                element = prev.right;
            }
        }
        if (prev == null) {
            return 0L;
        } else {
            return prev.key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        TreeNode element = null;
        Long k;
        while ((line = in.readLine()) != null) {
            String[] items = line.split(" ");
            if (Objects.equals(items[0], "insert")) {
                element = add(Long.parseLong(items[1]), element);
            } else if (Objects.equals(items[0], "delete")) {
                element = remove(Long.parseLong(items[1]), element);
            } else if (Objects.equals(items[0], "exists")) {
                System.out.println(find(Long.parseLong(items[1]), element));
            } else if (Objects.equals(items[0], "next")) {
                k = nextMin(Long.parseLong(items[1]), element);
                if (k == 0L) {
                    System.out.println("none");
                } else {
                    System.out.println(k);
                }
            } else if (Objects.equals(items[0], "prev")) {
                k = previousMax(Long.parseLong(items[1]), element);
                if (k == 0L) {
                    System.out.println("none");
                } else {
                    System.out.println(k);
                }
            }
        }
    }
}


