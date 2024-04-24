package wordcounter;

import wordcounter.filter.implementation.ExcludeWordsFilter;
import wordcounter.service.*;
import wordcounter.service.implementation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordCounterApplication {
    private static final String EXCLUDED_WORDS_OUTPUT_FILE = "excluded_word_count.txt";

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: ./gradlew --args=\"<input_directory> <exclude_file> <output_directory>\"");
            System.exit(1);
        }
        Path inputDirectory = Path.of(args[0]);
        Path excludeFilePath = Path.of(args[1]);
        Path outputDirectory = Path.of(args[2]);

        IFileReaderService fileReaderService = new FileReaderService();
        IFileWriterService fileWriterService = new FileWriterService();

        try {
            Set<String> excludedWords = fileReaderService
                    .read(excludeFilePath)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            ExcludeWordsFilter wordFilter = new ExcludeWordsFilter(excludedWords);

            IWordCounterService wordCounterService = new WordCounterService(wordFilter);
            Map<String, Long> wordCounts = wordCounterService.countWords(inputDirectory, fileReaderService);

            writeWordCountFiles(outputDirectory, fileWriterService, wordCounts);
            writeExcludedWords(outputDirectory, fileWriterService, wordFilter.getExcludedWordCounts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeExcludedWords(Path outputDirectory, IFileWriterService fileWriterService, Map<String, Long> excludedWordCounts) throws IOException {
        if (!excludedWordCounts.isEmpty()) {
            Path filePath = outputDirectory.resolve(EXCLUDED_WORDS_OUTPUT_FILE);
            fileWriterService.writeToFile(filePath, excludedWordCounts);
        }
    }

    private static void writeWordCountFiles(Path outputDirectory, IFileWriterService fileWriterService, Map<String, Long> wordCounts) throws IOException {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            String fileName = "FILE_" + Character.toUpperCase(letter) + ".txt";
            char finalLetter = letter;
            Map<String, Long> letterWordCounts = wordCounts.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith(String.valueOf(finalLetter)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            fileWriterService.writeToFile(outputDirectory.resolve(fileName), letterWordCounts);
        }
    }
}
