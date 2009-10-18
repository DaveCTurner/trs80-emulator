package dct25.trs80.emulator;

public class InstrumentedEnvironment implements Environment {

    public InstrumentedEnvironment() {
        screenClearedCount = 0;
    }
    
    private int screenClearedCount;
    
    public int getScreenClearedCount() { return screenClearedCount; }

    public void clearScreen() {
        screenClearedCount += 1;
    }

}
