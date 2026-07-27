import java.util.Scanner;

public class OptimizeShippingCapacity {
    public static void main(String[] args) {
        Scanner consoleInputReader = new Scanner(System.in);

        if (!consoleInputReader.hasNextInt()) {
            return;
        }

        int totalPackageCount = consoleInputReader.nextInt();
        int[] packageWeights = new int[totalPackageCount];

        int heaviestSinglePackage = 0;
        int totalWeightSum = 0;

        for (int index = 0; index < totalPackageCount; index++) {
            packageWeights[index] = consoleInputReader.nextInt();
            heaviestSinglePackage = Math.max(heaviestSinglePackage, packageWeights[index]);
            totalWeightSum += packageWeights[index];
        }

        int availableShippingDays = consoleInputReader.nextInt();

        int optimalTruckCapacity = findMinimumCapacity(
            packageWeights, 
            availableShippingDays, 
            heaviestSinglePackage, 
            totalWeightSum
        );

        System.out.println(optimalTruckCapacity);

        consoleInputReader.close();
    }

    public static int findMinimumCapacity(int[] weights, int availableDays, int lowerCapacityLimit, int upperCapacityLimit) {
        int optimalCapacity = upperCapacityLimit;

        while (lowerCapacityLimit <= upperCapacityLimit) {
            int candidateCapacity = lowerCapacityLimit + (upperCapacityLimit - lowerCapacityLimit) / 2;

            if (canShipWithinDays(weights, availableDays, candidateCapacity)) {
                optimalCapacity = candidateCapacity;
                upperCapacityLimit = candidateCapacity - 1;
            } else {
                lowerCapacityLimit = candidateCapacity + 1;
            }
        }

        return optimalCapacity;
    }

    private static boolean canShipWithinDays(int[] weights, int targetDays, int currentTruckCapacity) {
        int requiredDaysCount = 1;
        int currentDailyLoad = 0;

        for (int packageWeight : weights) {
            if (currentDailyLoad + packageWeight > currentTruckCapacity) {
                requiredDaysCount++;
                currentDailyLoad = packageWeight;
            } else {
                currentDailyLoad += packageWeight;
            }
        }

        return requiredDaysCount <= targetDays;
    }
}
