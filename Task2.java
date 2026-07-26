import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt(); // Array size
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int k = scanner.nextInt(); // 1-based index k

        // Sort array and pick element at index k-1
        Arrays.sort(arr);
        System.out.println(arr[k - 1]);

        scanner.close();
    }
}
