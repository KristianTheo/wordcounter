package wordcounter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileWriterServiceTest {
    private FileWriterService fileWriterService;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        fileWriterService = new FileWriterService();
    }

    @Test
    public void testWriteToFile() throws IOException {
        Path testOutput = tempDir.resolve("output.txt");

        Map<String, Long> wordCount = Map.of("lorem", 5L, "ipsum", 5L, "dolor", 3L, "sit", 3L, "amet", 2L);

        fileWriterService.writeToFile(testOutput, wordCount);

        assertTrue(Files.exists(testOutput));
        assertTrue(Files.lines(testOutput).count() == 5);
    }
}
