package wordcounter.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderServiceTest {
    private final String TEST_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private FileReaderService fileReaderService;

    @TempDir
    private Path tempDir;

    @BeforeEach
    public void setUp() {
        fileReaderService = new FileReaderService();
    }

    @Test
    public void testRead() throws IOException {
        Path testFile = tempDir.resolve("sample.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(testFile)) {
            writer.write(TEST_STRING);
        }

        try (Stream<String> stream = fileReaderService.read(testFile)) {
            assertNotNull(stream);
            assertTrue(stream.count() == TEST_STRING.replaceAll("[^\\w\\s]", "").split("\\s+").length);
        }
    }
}
