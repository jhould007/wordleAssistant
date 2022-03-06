import java.io.File;
import java.util.Scanner; 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ParseFile {
    public static void main(String[] args) throws IOException {
            File originalWords = new File("words-alpha.txt");
         //   File fiveLetterWords = new File("5letterwords.txt"); 
            Scanner sc = new Scanner(originalWords); 
            while(sc.hasNextLine()) {
                String currentLine = sc.nextLine().trim(); 
                Writer output = new BufferedWriter(new FileWriter("5letterwords.txt", true));  
                if(currentLine.length() == 5) {
                    output.append(currentLine + "\n"); 
                    output.close(); 
                }
            }
            sc.close(); 
    }
}

/* 1) Create a scanner. 
2) Read the text file. 
3) 
*/ 

//I want to iterate through each line of the original file, checking if each string is length 5. If so, I want to add it to the 5-letter word file on a new line. 