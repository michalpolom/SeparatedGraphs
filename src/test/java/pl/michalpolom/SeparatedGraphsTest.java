package pl.michalpolom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeparatedGraphsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    void shouldReturnNumberOfSeparatedGraphs_simpleTest() {
        provideInput("""
                3
                1 2
                2 3
                4 5
                """);
        Integer output = 2;

        SeparatedGraphs.main(new String[]{});

        assertEquals(output, Integer.valueOf(outputStreamCaptor.toString().trim()));
    }

    @Test
    void shouldReturnNumberOfSeparatedGraphs_complexTest() {
        provideInput("""
                7
                12 2147483647
                2147483647 9999999
                11111 5555
                11111 235245
                44444 9453
                1 99922
                75643 523
                """);
        Integer output = 5;

        SeparatedGraphs.main(new String[]{});

        assertEquals(output, Integer.valueOf(outputStreamCaptor.toString().trim()));
    }

    @Test
    void shouldReturnTooManyValuesInLineException() {
        provideInput("""
                1
                1 2 3
                """);
        String output = "Too many values in line";

        assertThrows(IndexOutOfBoundsException.class,
                () -> SeparatedGraphs.main(new String[]{}),
                output
        );
    }

    @Test
    void shouldReturnWrongIntegerValueException_whenPairIsWrong() {
        provideInput("""
                1
                -1 2
                """);
        String output = "Arguments should be between 1 and 2147483647";

        assertThrows(NumberFormatException.class,
                () -> SeparatedGraphs.main(new String[]{}),
                output
        );
    }

    @Test
    void shouldReturnWrongIntegerValueException_whenNumberOfPairsIsWrong() {
        provideInput("""
                -1
                1 2
                """);
        String output = "Arguments should be between 1 and 2147483647";

        assertThrows(NumberFormatException.class,
                () -> SeparatedGraphs.main(new String[]{}),
                output
        );
    }

    @Test
    void shouldReturnWrongIntegerValueException_whenInputIsNotANumber() {
        provideInput("""
                A
                1 2
                """);
        String output = "Arguments should be between 1 and 2147483647";

        assertThrows(NumberFormatException.class,
                () -> SeparatedGraphs.main(new String[]{}),
                output
        );
    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}