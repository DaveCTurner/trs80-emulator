/**
 * 
 */
package dct25.trs80.emulator;

/**
 * @author dct25
 *
 * Program's interface to the TRS-80 BASIC environment.
 */
public interface Environment {
    public void clearScreen();
    public void print(String s, boolean newLine);
    public int getInput();
    public int getNextRandomNumber(int maximum);
}
