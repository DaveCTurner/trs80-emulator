package dct25.trs80.programs;

public class AmazingReimplementation extends amazing_test_bas {
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
