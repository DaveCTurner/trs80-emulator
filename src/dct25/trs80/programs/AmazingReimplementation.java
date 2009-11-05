package dct25.trs80.programs;

import dct25.trs80.emulator.Environment;

public class AmazingReimplementation extends amazing_test_bas {
    public void execute(Environment env) {
        _env = env;
        _env.clearScreen();
        _env.print("AMAZING", true);
        _env.print("COPYRIGHT BY", true);
        _env.print("CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY", true);
        II = 1501; // replaced FOR II = (1) TO (1500) : NEXT
        while (true) {
            _env.clearScreen();
            _env.print("WHAT ARE YOUR WIDTH AND LENGTH", true);
            H = _env.getInput();
            V = _env.getInput();
            if ((((H) != (1)) && ((V) != (1)))) {
                break;
            }
            _env.print("MEANINGLESS DIMENSIONS. TRY AGAIN", true);
            A = 501; // replaced FOR A=1 TO 500: NEXT A
        }
        _env.print("PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT", true);
        line100statement0();
    }

    
    protected void line1200statement0() {
        printMaze(V, H, Vs);
    }

    private void printMaze(int v, int h, int[][] wallFlags) {
        for (int j = 0; j < v; j++) {
            _env.print("I", false);
            for (int i = 0; i < h; i++) {
                if ((wallFlags[i][j] & 0x02) == 0) {
                    _env.print("  I", false);
                } else {
                    _env.print("   ", false);
                }
            }
            _env.print(" ", true);
            for (int i = 0; i < h; i++) {
                if ((wallFlags[i][j] & 0x01) == 0) {
                    _env.print(":--", false);
                } else {
                    _env.print(":  ", false);
                }
            }
            _env.print(":", true);
        }
    }
}
