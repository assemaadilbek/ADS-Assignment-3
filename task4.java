import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt(); // Number of packages
        
        int[] weights = new int[n];
        int maxWeight = 0;
        int sumWeights = 0;

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
            maxWeight = Math.max(maxWeight, weights[i]);
            sumWeights += weights[i];
        }

        int days = scanner.nextInt(); // Target days to ship

        System.out.println(findMinCapacity(weights, days, maxWeight, sumWeights));

        scanner.close();
    }

    public static int findMinCapacity(int[] weights, int days, int low, int high) {
        int ans = high;

        // Binary Search for minimum capacity
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canShipWithinDays(weights, days, mid)) {
                ans = mid;
                high = mid - 1; // Search for a smaller valid capacity
            } else {
                low = mid + 1;  // Increase capacity
            }
        }

        return ans;
    }

    private static boolean canShipWithinDays(int[] weights, int days, int capacity) {
        int requiredDays = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }

        return requiredDays <= days;
    }
}
