package wordcounter.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import wordcounter.filter.IWordFilter;
import wordcounter.service.IFileReaderService;

@ExtendWith(MockitoExtension.class)
public class WordCounterServiceTest {

    @Mock
    private IFileReaderService fileReaderService;

    @Mock
    private IWordFilter wordFilter;

    @InjectMocks
    private WordCounterService wordCounterService;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        when(fileReaderService.read(any(Path.class))).thenReturn(Stream.of("lorem", "ipsum", "dolor", "Lorem").map(String::toLowerCase));
        when(wordFilter.filterExcludedWords(anyMap())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testCountWords() throws IOException {
        Path testFile = tempDir.resolve("testFile.txt");
        Files.createFile(testFile);

        Map<String, Long> result = wordCounterService.countWords(tempDir, fileReaderService);

        assertEquals(3, result.size());
        assertEquals(Long.valueOf(2), result.get("lorem"));
        assertEquals(Long.valueOf(1), result.get("ipsum"));
        assertEquals(Long.valueOf(1), result.get("dolor"));
    }
}
