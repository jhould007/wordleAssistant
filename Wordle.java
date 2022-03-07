import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {

    static ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {
        // Intro
        System.out.println();
        System.out.println(
                "Welcome! This tool will help you out in Wordle by taking your last guess along with information about what color each letter was, and giving you some suggestions!");

        // Read the file into an arraylist for processing
        File f = new File("5letterwords.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            words.add(sc.nextLine());
        }
        sc.close();

        // The moment of truth. Find words in the text file matching the criteria
        // outlined by the previous guess.
        Scanner sc2 = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {

            System.out.println();
            System.out.println(
                    "Enter your last guess below, and then the color of each letter. Enter x for gray, y for yellow, and g for green. EXAMPLE: tears yxxyx");
            System.out.println("If you got it, enter \"quit\" to quit. ");

            // Manage the user input
            String userInput = sc2.nextLine();
            // If the user gets the word, allow them to quit
            if (userInput.toLowerCase().equals("quit")) {
                System.out.println("Congratulations!");
                break;
            }
            String[] userInputArray = userInput.split(" ");
            System.out.println();
            System.out.println("Here are some guesses you could make.");
            findWords(words, userInputArray[0], userInputArray[1]);

            // Print out and format the list contents
            for (String s : words) {
                System.out.print(s + " | ");
            }
            System.out.println();
        }
        sc2.close();
    }

    // Checks if a word contains a letter in a certain position
    public static boolean greenLetter(String word, char letter, int position) {
        if (word.charAt(position) == letter) {
            return true;
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

    // Finds all words matching the criteria outlined by the color sequence given
    // tears yxxyx means "find all words containing t but not in that position, not
    // containing e and a, containing r but not in that position, and not containing
    // s."
    public static void findWords(ArrayList<String> s, String word, String colorSequence) throws FileNotFoundException {
        boolean[] conditions = new boolean[5];

        for (int i = 0; i < s.size(); i++) {
            String currentWord = s.get(i);
            // Populate the conditions array with conditions from the color sequence
            for (int j = 0; j < 5; j++) {
                conditions[j] = checkCondition(currentWord, word, colorSequence.charAt(j), j);
            }

            // Check the boolean array to check if any conditions for the current word are
            // false. If so, remove the word from consideration.
            if (checkBooleanArray(conditions)) {
                s.remove(i);
                i--;
            }
        }
    }

    // Apply the color condition from the first word to the second
    public static boolean checkCondition(String currentWord, String word, char color, int index) {
        if (color == 'x') {
            // Conditions are for repeated letters
            // If the word really does not contain a letter, proceed with that information
            // normally.
            if (grayLetter(currentWord, word.charAt(index))) {
                return (grayLetter(currentWord, word.charAt(index)));
                // If the word does contain a letter but at least one of them is gray, look for
                // words that don't have that letter in that position.
            } else {
                return (!greenLetter(currentWord, word.charAt(index), index));
            }
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

    // Check if a certain letter has occurred before in the word (sometimes a guess
    // will have a repeated letter, the first being yellow or green and the second
    // being gray)
    public static boolean letterOccurredAlready(String word, char letter, int secondIndex) {
        for (int i = 0; i < secondIndex; i++) {
            if (word.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }

}
