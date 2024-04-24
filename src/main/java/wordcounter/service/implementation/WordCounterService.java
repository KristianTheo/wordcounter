package wordcounter.service.implementation;

import wordcounter.filter.IWordFilter;
import wordcounter.service.IFileReaderService;
import wordcounter.service.IWordCounterService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounterService implements IWordCounterService {
    private final IWordFilter wordFilter;

    public WordCounterService(IWordFilter wordFilter) {
        this.wordFilter = wordFilter;
    }

    @Override
    public Map<String, Long> countWords(Path inputDirectory, IFileReaderService fileReaderService) throws IOException {
        try (Stream<Path> files = Files.list(inputDirectory)) {
            Map<String, Long> wordCounts = files.parallel()
                    .flatMap(path -> {
                        try {
                            return fileReaderService.read(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            return wordFilter.filterExcludedWords(wordCounts);
        }
    }
}
