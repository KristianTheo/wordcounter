package wordcounter.filter;

import java.util.Map;

public interface IWordFilter {
    Map<String, Long> filterExcludedWords(Map<String, Long> wordCounts);

    Map<String, Long> getExcludedWordCounts();
}
