package wordcounter.filter.implementation;

import wordcounter.filter.IWordFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExcludeWordsFilter implements IWordFilter {
    private Set<String> excludedWords;
    private Map<String, Long> excludedWordCounts = new HashMap<>();

    public ExcludeWordsFilter(Set<String> excludedWords) {
        this.excludedWords = excludedWords;
    }

    @Override
    public Map<String, Long> filterExcludedWords(Map<String, Long> wordCounts) {
        excludedWordCounts = wordCounts.entrySet().stream()
                .filter(entry -> excludedWords.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return wordCounts.entrySet().stream()
                .filter(entry -> !excludedWords.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Long> getExcludedWordCounts() {
        return excludedWordCounts;
    }
}
