package wordcounter.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileReaderService {
    Stream<String> read(Path path) throws IOException;
}
