package wordcounter.service.implementation;
import wordcounter.service.IFileWriterService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileWriterService implements IFileWriterService {
    @Override
    public void writeToFile(Path path, Map<String, Long> wordCount) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
