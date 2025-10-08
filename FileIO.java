import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Programs that reads integers from a file.
 * For each line,
 * it sums up all the integers
 * and writes the sum to an output file.
 *
 * @author Atri Sarker
 * @version 1.0
 * @since 2025-10-07
 */
public final class FileIO {
  /**
   * Constant for the file path of the input file.
   */
  private static final String INPUT_FILE_PATH = "./input.txt";

  /**
   * Constant for the file path of the output file.
   */
  private static final String OUTPUT_FILE_PATH = "./output.txt";

  /**
   * Private constructor to satisfy style checker.
   *
   * @exception IllegalStateException for the utility class.
   * @see IllegalStateException
   */
  private FileIO() {
    // Prevents illegal states.
    throw new IllegalStateException("Utility class.");
  }

  /**
   * Entrypoint of the program.
   *
   * @param args UNUSED.
   */
  public static void main(final String[] args) {
    try {
      // Access the input file and create a File object.
      File inputFile = new File(INPUT_FILE_PATH);
      // Access the output file and create a FileWriter object.
      FileWriter outputFile = new FileWriter(OUTPUT_FILE_PATH);
      // Scanner that will read the File Object.
      Scanner fileReader = new Scanner(inputFile);
      // Create list to store all the lines
      ArrayList<String> listOfLines = new ArrayList<>();
      // Loop through all available lines
      while (fileReader.hasNextLine()) {
        // Add the line to the list
        listOfLines.add(fileReader.nextLine());
      }
      // Close the file reader
      fileReader.close();
      // Loop through all the lines in the list
      for (String line : listOfLines) {
        // Check if the line is empty
        if (line.equals("")) {
          // If so, write an error message and continue
          outputFile.write("Error: No integers were found on this line.\n");
          continue;
        }
        // Split the line into individual tokens
        String[] lineTokens = line.split(" ");
        // Variable to store the sum of the integers
        int sum = 0;
        // Initialize a flag variable
        // If it's "none",
        // it means that the line was processed without any errors.
        String errorFlagString = "none";
        // Loop through all the tokens in the line
        for (String token : lineTokens) {
          try {
            // Convert the token to an integer and add it to the sum
            sum += Integer.parseInt(token);
          } catch (NumberFormatException error) {
            // If the token is not an integer, break out of the loop
            // Activate the flag to write an error instead of the sum
            errorFlagString = "Error: " + token + " is not an integer.";
            break;
          }
        }

        // Write the result
        if (errorFlagString.equals("none")) {
          // Write the sum if there were no errors
          outputFile.write(Integer.toString(sum));
        } else {
          // Write the error message if there was an error
          outputFile.write(errorFlagString);
        }
        // Write a newline
        outputFile.write("\n");
      }
      // Close the FileWriter object
      outputFile.close();
    } catch (IOException error) {
      System.out.println(error);
    }
  }
}
