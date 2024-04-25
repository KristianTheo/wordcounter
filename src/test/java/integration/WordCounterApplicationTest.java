package integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import wordcounter.WordCounterApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCounterApplicationTest {
    @TempDir
    private Path tempDirOutput;

    @Test
    public void testWordCounterApplication() throws IOException {
        Path inputFilesPath = Paths.get("src/test/resources/input/");
        Path excludedWordsFilePath = Paths.get("src/test/resources/exclude/exclude.txt");
        Path excludedWordsResultFilePath = Paths.get("src/test/resources/expected/excluded_word_count.txt");

        WordCounterApplication.main(new String[]{
                inputFilesPath.toString(),
                excludedWordsFilePath.toString(),
                tempDirOutput.toString()
        });

        for (char letter = 'a'; letter <= 'z'; letter++) {
            String fileName = "FILE_" + Character.toUpperCase(letter) + ".txt";
            Path pathToResult = Path.of(tempDirOutput + "/" + fileName);
            assertTrue(Files.exists(pathToResult));

            Path pathToExpectedResult = Paths.get("src/test/resources/expected/" + fileName);
            compareFileContents(pathToResult, pathToExpectedResult);
        }

        Path pathToExcludedWordsResult = Path.of(tempDirOutput + "/" + "excluded_word_count.txt");

        assertTrue(Files.exists(pathToExcludedWordsResult));
        compareFileContents(pathToExcludedWordsResult, excludedWordsResultFilePath);
    }

    public void compareFileContents(Path actual, Path expected) throws IOException {
        List<String> actualLines = Files.readAllLines(actual);
        List<String> expectedLines = Files.readAllLines(expected);

        Set<String> actualLineSet = new HashSet<>(actualLines);
        Set<String> expectedLineSet = new HashSet<>(expectedLines);

        assertEquals(actualLineSet, expectedLineSet);
    }
}
