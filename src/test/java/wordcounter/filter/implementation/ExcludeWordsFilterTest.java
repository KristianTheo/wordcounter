package wordcounter.filter.implementation;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExcludeWordsFilterTest {
    @Test
    public void testFilterExcludedWords() {
        ExcludeWordsFilter filter = new ExcludeWordsFilter(Set.of("dolor", "amet"));
        Map<String, Long> words = Map.of("lorem", 2L, "ipsum", 2L, "dolor", 1L, "sit", 1L, "amet", 1L);
        Map<String, Long> filtered = filter.filterExcludedWords(words);

        assertEquals(3, filtered.size());
        assertFalse(filtered.containsKey("dolor"));
        assertFalse(filtered.containsKey("amet"));
    }
}
