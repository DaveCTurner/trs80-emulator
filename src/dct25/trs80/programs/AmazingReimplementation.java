package dct25.trs80.programs;

import java.util.ArrayList;
import java.util.List;

import dct25.trs80.emulator.Environment;

public class AmazingReimplementation extends amazing_test_bas {
    public void execute(Environment env) {
        _env = env;
        _env.clearScreen();
        _env.print("AMAZING", true);
        _env.print("COPYRIGHT BY", true);
        _env.print("CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY", true);

        boolean valid = false;
        int h, v;
        h = v = 0;
        while (!valid) {
            _env.clearScreen();
            _env.print("WHAT ARE YOUR WIDTH AND LENGTH", true);
            h = _env.getInput();
            v = _env.getInput();
            valid = !((h == 1) || (v == 1));
            if (!valid) {
                _env.print("MEANINGLESS DIMENSIONS. TRY AGAIN", true);
            }
        }
        _env.print("PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT", true);

        entryPosition = _env.getNextRandomNumber(h);

        Ws = new int[h][v];
        Vs = new int[h][v];
        Ws[entryPosition - 1][0] = 1;

        II = 1501; // replaced FOR II = (1) TO (1500) : NEXT
        A = 501; // replaced FOR A=1 TO 500: NEXT A
        H = h;
        V = v;
        Q = 0;
        Z = 0;
        X = entryPosition;
        I = h + 1;
        C = 2;
        R = entryPosition;
        S = 1;

        line270statement0();
    }

    protected void line210statement0() {
        do {
            moveToNextSquare();
        } while (Ws[R - 1][S - 1] == 0);
        line270statement0();
    }

    private void moveToNextSquare() {
        if (R != H) {
            R += 1;
        } else {
            if (S != V) {
                R = 1;
                S += 1;
            } else {
                R = 1;
                S = 1;
            }
        }
    }

    protected void line260statement0() {
        while (Ws[R - 1][S - 1] == 0) {
            moveToNextSquare();
        }
        line270statement0();
    }

    private void randomJump(List<Integer> targets) {
        int[] targetsArray = new int[targets.size()];
        for (int i = 0; i < targets.size(); i++) {
            targetsArray[i] = targets.get(i);
        }
        randomJump(targetsArray);
    }

    private void randomJump(int[] lineNumbers) {
        X = (_env.getNextRandomNumber(lineNumbers.length));
        int jumpTarget = lineNumbers[X - 1];
        switch (jumpTarget) {
        case 940:
            line940statement0();
            break;
        case 980:
            line980statement0();
            break;
        case 1020:
            line1020statement0();
            break;
        case 1090:
            line1090statement0();
            break;
        }
    }

    protected void line590statement0() {
        randomJump(new int[] { 940 });
    }

    protected void line570statement0() {
        randomJump(new int[] { 940, 1090 });
    }

    protected void line510statement0() {
        randomJump(new int[] { 940, 1020 });
    }

    protected void line490statement0() {
        randomJump(new int[] { 940, 1020, 1090 });
    }

    protected void line410statement0() {
        randomJump(new int[] { 940, 980 });
    }

    protected void line390statement0() {
        randomJump(new int[] { 940, 980, 1090 });
    }

    protected void line270statement0() {
        if ((((R) - (1)) == (0)) || ((Ws[(((R) - (1)) - 1)][((S) - 1)]) != (0))) {
            line600statement0();
        } else {
            if ((((S) - (1)) == (0))
                    || ((Ws[((R) - 1)][(((S) - (1)) - 1)]) != (0))) {
                if (((R) == (H)) || ((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
                    setQFlagIfAtSouthernEdgeAndZUnset();

                    if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                        randomJump(new int[] { 940 });
                    } else {
                        randomJump(new int[] { 940, 1090 });
                    }
                } else {
                    setQFlagIfAtSouthernEdgeAndZUnset();

                    if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                        randomJump(new int[] { 940, 1020 });
                    } else {
                        randomJump(new int[] { 940, 1020, 1090 });
                    }
                }
            } else {
                if (((R) == (H)) || ((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
                    setQFlagIfAtSouthernEdgeAndZUnset();

                    if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                        randomJump(new int[] { 940, 980 });
                    } else {
                        randomJump(new int[] { 940, 980, 1090 });
                    }
                } else {
                    randomJump(new int[] { 940, 980, 1020 });
                }
            }
        }
    }

    protected void line600statement0() {
        List<Integer> jumpTargets = new ArrayList<Integer>();

        if ((((S) - (1)) == (0)) || ((Ws[((R) - 1)][(((S) - (1)) - 1)]) != (0))) {
            if (((R) == (H)) || ((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
                setQFlagIfAtSouthernEdgeAndZUnset();

                if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                    line210statement0();
                } else {
                    jumpTargets.add(1090);
                    randomJump(jumpTargets);
                }
            } else {
                line810statement0();
            }
        } else {
            if (((R) == (H)) || ((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
                setQFlagIfAtSouthernEdgeAndZUnset();

                if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                    jumpTargets.add(980);
                    randomJump(jumpTargets);
                } else {
                    jumpTargets.add(980);
                    jumpTargets.add(1090);
                    randomJump(jumpTargets);
                }
            } else {
                setQFlagIfAtSouthernEdgeAndZUnset();

                if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                    jumpTargets.add(980);
                    jumpTargets.add(1020);
                    randomJump(jumpTargets);
                } else {
                    jumpTargets.add(980);
                    jumpTargets.add(1020);
                    jumpTargets.add(1090);
                    randomJump(jumpTargets);
                }
            }
        }
    }

    private boolean atSouthernEdgeAndZSet() {
        return ((S == V) && ((Z) == (1)));
    }

    private boolean haveVisitedCellToSouth() {
        return ((S) != (V)) && ((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0));
    }

    private void setQFlagIfAtSouthernEdgeAndZUnset() {
        if ((S == V) && (Z != 1)) {
            Q = 1;
        }
    }

    protected void line940statement0() {
        R -= 1;
        Ws[R - 1][S - 1] = C;
        C += 1;
        Vs[R - 1][S - 1] = 2;
        if (C == (H * V + 1)) {
            printMaze(V, H, Vs);
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void line980statement0() {
        S -= 1;
        Ws[R - 1][S - 1] = C;
        C += 1;
        Vs[R - 1][S - 1] = 1;
        if (C == (H * V + 1)) {
            printMaze(V, H, Vs);
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void line1020statement0() {
        if (Vs[R - 1][S - 1] == 0) {
            Vs[R - 1][S - 1] = 2;
        } else {
            Vs[R - 1][S - 1] = 3;
        }
        R += 1;
        Ws[R - 1][S - 1] = C;
        C += 1;
        if (C == (H * V + 1)) {
            printMaze(V, H, Vs);
            return;
        }
        line600statement0();
    }

    protected void line1090statement0() {
        if (Q == 1) {
            Z = 1;
            if (Vs[R - 1][S - 1] == 0) {
                Vs[R - 1][S - 1] = 1;
                R = 1;
                S = 1;
            } else {
                Vs[R - 1][S - 1] = 3;
                moveToNextSquare();
            }
            Q = 0;
            while (Ws[R - 1][S - 1] == 0) {
                moveToNextSquare();
            }
        } else {

            if (Vs[R - 1][S - 1] == 0) {
                Vs[R - 1][S - 1] = 1;
            } else {
                Vs[R - 1][S - 1] = 3;
            }
            S += 1;
            Ws[R - 1][S - 1] = C;
            C = C + 1;
            if (C == (H * V + 1)) {
                printMaze(V, H, Vs);
                return;
            }
        }
        line270statement0();
    }

    protected void line1200statement0() {
        printMaze(V, H, Vs);
    }

    private int entryPosition;

    private void printMaze(int v, int h, int[][] wallFlags) {
        for (int i = 1; i <= h; i++) {
            if (i == entryPosition) {
                _env.print(":  ", false);
            } else {
                _env.print(":--", false);
            }
        }
        _env.print(":", true);

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
