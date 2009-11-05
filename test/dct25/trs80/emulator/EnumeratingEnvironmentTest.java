package dct25.trs80.emulator;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnumeratingEnvironmentTest {
    @Test
    public void ShouldConstruct() {
        @SuppressWarnings("unused")
        Environment e = new EnumeratingEnvironment(System.out);
    }

    @Test
    public void ShouldEnumerateSizes() {
        Environment e = new EnumeratingEnvironment(System.out);

        int[][] expectedSizes = { { 1, 2 }, { 2, 1 }, { 1, 3 }, { 2, 2 },
                { 3, 1 }, { 1, 4 }, { 2, 3 }, { 3, 2 }, { 4, 1 }, { 1, 5 }, };

        for (int sizeIndex = 0; sizeIndex < expectedSizes.length; sizeIndex++) {
            int expectedWidth = expectedSizes[sizeIndex][0];
            int expectedHeight = expectedSizes[sizeIndex][1];
            assertEquals("Check width of " + expectedWidth + "x"
                    + expectedHeight, expectedWidth, e.getInput());
            assertEquals("Check height of " + expectedWidth + "x"
                    + expectedHeight, expectedHeight, e.getInput());
        }
    }

    @Test
    public void ShouldEnumerateRandomNumbers() {
        int expectedWidth = 1, expectedHeight = 2;
        Environment e = new EnumeratingEnvironment(System.out);

        for (int i = 1; i <= 14; i++) {
            assertEquals("Check width of " + expectedWidth + "x"
                    + expectedHeight + " (" + i + ")", expectedWidth, e
                    .getInput());
            assertEquals("Check height of " + expectedWidth + "x"
                    + expectedHeight + " (" + i + ")", expectedHeight, e
                    .getInput());
            if (2 == i) {
                break; // TODO REMOVE THIS.
            }
            assertEquals("Check random number sequence", i,
                    randomNumberSequenceIndex(e));
        }

        expectedWidth = 2;
        expectedHeight = 1;
        assertEquals("Check width of " + expectedWidth + "x" + expectedHeight,
                expectedWidth, e.getInput());
        assertEquals("Check height of " + expectedWidth + "x" + expectedHeight,
                expectedHeight, e.getInput());
    }

    private int randomNumberSequenceIndex(Environment e) {
        switch (e.getNextRandomNumber(3)) {
        case 1:
            switch (e.getNextRandomNumber(2)) {
            case 1:
                switch (e.getNextRandomNumber(4)) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                default:
                    return 0;
                }
            case 2:
                return 5;
            default:
                return 0;
            }
        case 2:
            switch (e.getNextRandomNumber(3)) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                switch (e.getNextRandomNumber(2)) {
                case 1:
                    return 8;
                case 2:
                    return 9;
                default:
                    return 0;
                }
            default:
                return 0;
            }
        case 3:
            switch (e.getNextRandomNumber(3)) {
            case 1:
                return 10;
            case 2:
                switch (e.getNextRandomNumber(5)) {
                case 1:
                    return 11;
                case 2:
                    return 12;
                case 3:
                    return 13;
                default:
                    return 0;
                }
            case 3:
                return 14;
            default:
                return 0;
            }
        default:
            return 0;
        }
    }
}
