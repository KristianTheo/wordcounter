package wordcounter.service.implementation;

import wordcounter.service.IFileReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileReaderService implements IFileReaderService {
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("\\W+");

    @Override
    public Stream<String> read(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        return reader.lines()
                .flatMap(line -> NON_WORD_PATTERN.splitAsStream(line.toLowerCase()))
                .onClose(() -> {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
