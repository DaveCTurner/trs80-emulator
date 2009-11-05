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
        
    }
}
