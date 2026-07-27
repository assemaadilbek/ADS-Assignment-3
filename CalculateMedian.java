import java.util.Scanner;

public class CalculateMedian {
    public static void main(String[] args) {
        Scanner consoleInputReader = new Scanner(System.in);

        if (!consoleInputReader.hasNextInt()) {
            return;
        }

        int totalElementCount = consoleInputReader.nextInt();
        int[] numbersArray = new int[totalElementCount];

        for (int index = 0; index < totalElementCount; index++) {
            numbersArray[index] = consoleInputReader.nextInt();
        }

        int medianValue = calculateMedianValue(numbersArray);
        System.out.println(medianValue);

        consoleInputReader.close();
    }

    public static int calculateMedianValue(int[] integerArray) {
        // Custom manual sorting (QuickSort)
        sortIntegerArray(integerArray, 0, integerArray.length - 1);
        return integerArray[integerArray.length / 2];
    }

    private static void sortIntegerArray(int[] integerArray, int leftBoundIndex, int rightBoundIndex) {
        if (leftBoundIndex < rightBoundIndex) {
            int partitionIndex = partitionIntegers(integerArray, leftBoundIndex, rightBoundIndex);
            sortIntegerArray(integerArray, leftBoundIndex, partitionIndex - 1);
            sortIntegerArray(integerArray, partitionIndex + 1, rightBoundIndex);
        }
    }

    private static int partitionIntegers(int[] integerArray, int leftBoundIndex, int rightBoundIndex) {
        int pivotValue = integerArray[rightBoundIndex];
        int smallerElementIndex = leftBoundIndex - 1;

        for (int currentIndex = leftBoundIndex; currentIndex < rightBoundIndex; currentIndex++) {
            if (integerArray[currentIndex] <= pivotValue) {
                smallerElementIndex++;
                int temporaryValue = integerArray[smallerElementIndex];
                integerArray[smallerElementIndex] = integerArray[currentIndex];
                integerArray[currentIndex] = temporaryValue;
            }
        }

        int temporaryValue = integerArray[smallerElementIndex + 1];
        integerArray[smallerElementIndex + 1] = integerArray[rightBoundIndex];
        integerArray[rightBoundIndex] = temporaryValue;

        return smallerElementIndex + 1;
    }
}
