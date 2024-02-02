package beyonnex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static beyonnex.AnagramFixture.*;
import static org.junit.jupiter.api.Assertions.*;

class AnagramCheckerIntegrationTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream systemOutContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    private static void callIsAnagramOfA(StringBuilder builder, String candidate) {
        builder
                .append(1)
                .append(System.lineSeparator())
                .append(A)
                .append(System.lineSeparator())
                .append(candidate)
                .append(System.lineSeparator());
    }

    private static void callGetAnagrams(StringBuilder builder, String subject) {
        builder
                .append(2)
                .append(System.lineSeparator())
                .append(subject)
                .append(System.lineSeparator());
    }

    private static void callExit(StringBuilder builder) {
        builder
                .append(3)
                .append(System.lineSeparator());
    }

    private static StringBuilder setUpAnagrams() {
        StringBuilder inputBuilder = new StringBuilder();
        callIsAnagramOfA(inputBuilder, B);
        callIsAnagramOfA(inputBuilder, C);
        callIsAnagramOfA(inputBuilder, D);
        return inputBuilder;
    }

    @Test
    void GIVEN_anagramsAandBandD_WHEN_callingAnagramsForA_THEN_returnsBandD () {
        StringBuilder inputBuilder = setUpAnagrams();
        callGetAnagrams(inputBuilder, A);
        callExit(inputBuilder);

        ByteArrayInputStream input = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        System.setIn(input);

        Application.main(null);

        String[] lines = systemOutContent.toString().split(System.lineSeparator());
        assertEquals(String.format("Anagrams for %s: [%s, %s]", A, B, D), lines[26]);
    }

    @Test
    void GIVEN_anagramsAandBandD_WHEN_callingAnagramsForB_THEN_returnsAandD () {
        StringBuilder inputBuilder = setUpAnagrams();
        callGetAnagrams(inputBuilder, B);
        callExit(inputBuilder);

        ByteArrayInputStream input = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        System.setIn(input);

        Application.main(null);

        String[] lines = systemOutContent.toString().split(System.lineSeparator());
        assertEquals(String.format("Anagrams for %s: [%s, %s]", B, D, A), lines[26]);
    }

    @Test
    void GIVEN_anagramsAandBandD_WHEN_callingAnagramsForC_THEN_returnsEmptyArray () {
        StringBuilder inputBuilder = setUpAnagrams();
        callGetAnagrams(inputBuilder, C);
        callExit(inputBuilder);

        ByteArrayInputStream input = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        System.setIn(input);

        Application.main(null);

        String[] lines = systemOutContent.toString().split(System.lineSeparator());
        assertEquals(String.format("Anagrams for %s: []", C), lines[26]);
    }
}