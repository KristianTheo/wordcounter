# Word Counter Application

This is a Java application that reads text files from a specified directory, counts the occurrences of words, excludes certain words based on a provided list, and then writes the word counts to separate files for each letter of the alphabet.

## Prerequisites

- Gradle (for running with Gradle).

## Running the Application

### Using Gradle

1. Clone or download this repository to your local machine.

2. Open a terminal and navigate to the project directory.

3. Run the following command to execute the application using Gradle:

   ```
   ./gradlew run -Pargs="<input_directory> <exclude_file> <output_directory>"
   ```

   Replace `<input_directory>`, `<exclude_file>`, and `<output_directory>` with the paths to the input directory containing text files, the file containing words to exclude, and the output directory where result files will be saved, respectively.

   Example:

   ```
   ./gradlew run -Pargs="/path/to/input /path/to/exclude.txt /path/to/output"
   ```

### Using Java

1. Clone or download this repository to your local machine.

2. Open a terminal and navigate to the project directory.

3. Build the project using Gradle (if not already built):

   ```
   ./gradlew build
   ```

4. Navigate to the build/libs directory:

   ```
   cd build/libs
   ```

5. Run the JAR file using the following command:

   ```
   java -jar wordcounter-<version>.jar <input_directory> <exclude_file> <output_directory>
   ```

   Replace `<input_directory>`, `<exclude_file>`, and `<output_directory>` with the paths to the input directory containing text files, the file containing words to exclude, and the output directory where result files will be saved, respectively.

   Example:

   ```
   java -jar WordCounter-1.0.jar /path/to/input /path/to/exclude.txt /path/to/output
   ```

## Sample Output

The application will create output files for each letter of the alphabet in the specified output directory. Each file will contain words starting with the corresponding letter along with their counts.

Example output files:

- FILE_A.txt
- FILE_B.txt
- ...
- FILE_Z.txt
