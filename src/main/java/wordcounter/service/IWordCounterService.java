package wordcounter.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface IWordCounterService {
    Map<String, Long> countWords(Path inputDirectory, IFileReaderService fileReaderService) throws IOException;
}
