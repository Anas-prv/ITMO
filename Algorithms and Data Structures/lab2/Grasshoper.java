import java.io.*;

public class Grasshoper {
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] found = new int[n + 1];
            for (int cell = 1; cell < n + 1; cell++) {
                if (cell <= 2) {
                    found[cell] = 1;
                } else {
                    found[cell] = found[cell - 1] + found[cell - 2];
                }
            }
            PrintWriter writer = new PrintWriter("output.txt");
            writer.print(found[n]);
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}