package beyonnex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static beyonnex.AnagramFixture.*;
import static org.junit.jupiter.api.Assertions.*;

class AnagramCheckerTest {

    @Test
    void isAnagram_returnsTrue_forValidAnagrams() {
        assertTrue(new AnagramChecker().isAnagram("coronavirus", "carnivorous"));
    }

    @Test
    void isAnagram_returnsTrue_forAnagramsIncludingSpaces() {
        assertTrue(new AnagramChecker().isAnagram("a gentleman", "elegant man"));
    }

    @Test
    void isAnagram_returnsTrue_forAnagramsThatSwitchLetterCase() {
        assertTrue(new AnagramChecker().isAnagram("Rot", "Tor"));
    }

    @Test
    void isAnagram_returnsTrue_forAnagramsIncludingSpecialSigns() {
        assertTrue(new AnagramChecker().isAnagram("McDonald's restaurants", "Uncle Sam's standard rot"));
    }

    @ParameterizedTest
    @CsvSource({"sample,lamps", "sample,a lamp"})
    void isAnagram_returnsFalse_forInvalidAnagrams(String subject, String candidate) {
        assertFalse(new AnagramChecker().isAnagram(subject, candidate));
    }

    @Test
    void getAnagrams_doesNotReturnSelf() {
        AnagramChecker anagramChecker = new AnagramChecker();
        anagramChecker.isAnagram(A, C);

        assertEquals(Collections.emptyList(), anagramChecker.getAnagrams(A));
    }

    @Test
    void getAnagrams_doesNotReturnDuplicates() {
        AnagramChecker anagramChecker = new AnagramChecker();
        anagramChecker.isAnagram(A, B);
        anagramChecker.isAnagram(A, D);

        assertEquals(List.of(D, A), anagramChecker.getAnagrams(B));
    }
}
