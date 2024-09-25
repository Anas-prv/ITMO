import java.util.Scanner;
import java.util.Stack;
public class Brackets {
    public static boolean check(String str) {
        Stack <Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch== '[' || ch == '{') {
                stack.push(str.charAt(i));
            } else if (!stack.empty() && (ch == ']' || ch == '}' || ch == ')')) {
                if (ch == ')' && stack.lastElement() == '(') {
                    stack.pop();
                } else if (ch == ']' && stack.lastElement() == '[') {
                    stack.pop();
                } else if (ch == '}' && stack.lastElement() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String line = str.replace(" ", "");
        if (check(line)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}