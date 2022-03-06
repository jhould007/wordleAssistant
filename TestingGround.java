import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//A file for testing various methods and playing around. 

public class TestingGround {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("testwords.txt");
        findWords(f, "tears", "gyyxg");
    }

    // Checks if a word contains a letter but not in a given position
    public static boolean yellowLetter(String word, char letter, int position) {
        // If the word has the letter in that position, the condition is false
        if (word.charAt(position) == letter) {
            return false;
        }
        // If the letter is not in that position, search for it in the rest of the word
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }

    // Checks if a word does not contain a letter (returns true if it does not)
    public static boolean grayLetter(String word, char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return false;
            }
        }
        return true;
    }

    // Checks if a word contains a letter in a certain position
    public static boolean greenLetter(String word, char letter, int position) {
        if (word.charAt(position) == letter) {
            return true;
        }
        return false;
    }

    // Finds all words matching the criteria outlined by the color sequence given
    // tears yxxyx means "find all words containing t but not in that position, not
    // containing e and a, containing r but not in that position, and not containing
    // s."
    public static void findWords(File f, String word, String colorSequence) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        boolean[] conditions = new boolean[5];

        while (sc.hasNextLine()) {
            String currentWord = sc.nextLine();
            System.out.println("Checking the word \"" + currentWord + "\".");
            // Populate the conditions array with conditions from the color sequence
            for (int i = 0; i < 5; i++) {
                conditions[i] = checkCondition(currentWord, word, colorSequence.charAt(i), i);
            }

            // Check the boolean array to check if all conditions are true for the word
            // being analyzed. If yes, then print it out.
            if (!checkBooleanArray(conditions)) {
                System.out.println(currentWord);
            }
        }
        sc.close();
    }

    // Apply the color condition from the first word to the second
    public static boolean checkCondition(String currentWord, String word, char color, int index) {
        if (color == 'x') {
            return (grayLetter(currentWord, word.charAt(index)));
        } else if (color == 'y') {
            return (yellowLetter(currentWord, word.charAt(index), index));
        } else if (color == 'g') {
            return (greenLetter(currentWord, word.charAt(index), index));
        }
        return false;
    }

    // Check if a boolean array contains any false values
    public static boolean checkBooleanArray(boolean[] b) {
        for (int i = 0; i < b.length; i++) {
            if (b[i] == false) {
                return true;
            }
        }
        return false;
    }

    // Testing reading through file and printing contents
    public static void readFile(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }
}
