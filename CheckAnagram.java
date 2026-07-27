import java.util.Scanner;

public class CheckAnagram {
    public static void main(String[] args) {
        Scanner consoleInputReader = new Scanner(System.in);

        if (!consoleInputReader.hasNext()) {
            return;
        }

        String firstWord = consoleInputReader.next();
        String secondWord = consoleInputReader.next();

        if (checkIfAnagrams(firstWord, secondWord)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        consoleInputReader.close();
    }

    public static boolean checkIfAnagrams(String wordOne, String wordTwo) {
        if (wordOne.length() != wordTwo.length()) {
            return false;
        }

        char[] firstCharacterArray = wordOne.toCharArray();
        char[] secondCharacterArray = wordTwo.toCharArray();

        // Custom manual sorting (QuickSort)
        sortCharacterArray(firstCharacterArray, 0, firstCharacterArray.length - 1);
        sortCharacterArray(secondCharacterArray, 0, secondCharacterArray.length - 1);

        for (int index = 0; index < firstCharacterArray.length; index++) {
            if (firstCharacterArray[index] != secondCharacterArray[index]) {
                return false;
            }
        }

        return true;
    }

    private static void sortCharacterArray(char[] characterArray, int leftBoundIndex, int rightBoundIndex) {
        if (leftBoundIndex < rightBoundIndex) {
            int partitionIndex = partitionCharacters(characterArray, leftBoundIndex, rightBoundIndex);
            sortCharacterArray(characterArray, leftBoundIndex, partitionIndex - 1);
            sortCharacterArray(characterArray, partitionIndex + 1, rightBoundIndex);
        }
    }

    private static int partitionCharacters(char[] characterArray, int leftBoundIndex, int rightBoundIndex) {
        char pivotValue = characterArray[rightBoundIndex];
        int smallerElementIndex = leftBoundIndex - 1;

        for (int currentIndex = leftBoundIndex; currentIndex < rightBoundIndex; currentIndex++) {
            if (characterArray[currentIndex] <= pivotValue) {
                smallerElementIndex++;
                char temporaryValue = characterArray[smallerElementIndex];
                characterArray[smallerElementIndex] = characterArray[currentIndex];
                characterArray[currentIndex] = temporaryValue;
            }
        }

        char temporaryValue = characterArray[smallerElementIndex + 1];
        characterArray[smallerElementIndex + 1] = characterArray[rightBoundIndex];
        characterArray[rightBoundIndex] = temporaryValue;

        return smallerElementIndex + 1;
    }
}
