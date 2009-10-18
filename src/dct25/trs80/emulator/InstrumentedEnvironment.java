package dct25.trs80.emulator;

public class InstrumentedEnvironment implements Environment {

    public InstrumentedEnvironment() {
        screenCleared = false;
    }
    
    private boolean screenCleared;
    
    public boolean getScreenCleared() { return screenCleared; }
    
    public void clearScreen() {
        screenCleared = true;
    }

}
