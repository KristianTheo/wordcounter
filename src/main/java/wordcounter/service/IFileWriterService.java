package wordcounter.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface IFileWriterService {
    void writeToFile(Path path, Map<String, Long> wordCount) throws IOException;
}
