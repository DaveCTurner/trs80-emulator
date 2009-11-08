package dct25.trs80.programs;

import java.util.ArrayList;
import java.util.Arrays;
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
        goNorthToEdge();

        line270statement0();
    }

    protected void line210statement0() {
        do {
            moveToNextSquare();
        } while (currentCellIsUnvisited());
        line270statement0();
    }

    private void moveToNextSquare() {
        if (atEasternEdge()) {
            if (atSouthernEdge()) {
                goToNorthWestCorner();
            } else {
                goWestToEdge();
                moveSouth();
            }
        } else {
            moveEast();
        }
    }

    protected void line260statement0() {
        while (currentCellIsUnvisited()) {
            moveToNextSquare();
        }
        line270statement0();
    }

    private void randomJump(List<Integer> targets) {
        int[] targetsArray = new int[targets.size()];
        for (int i = 0; i < targets.size(); i++) {
            targetsArray[i] = targets.get(i);
        }
        Arrays.sort(targetsArray);
        X = (_env.getNextRandomNumber(targetsArray.length));
        int jumpTarget = targetsArray[X - 1];
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

    protected void line270statement0() {
        List<Integer> jumpTargets = new ArrayList<Integer>();

        if (unvisitedCellToWest()) {
            jumpTargets.add(940);
            if (unvisitedCellToNorth()) {
                jumpTargets.add(980);
                if (unvisitedCellToEast()) {
                    jumpTargets.add(1020);
                } else {
                    setQFlagIfAtSouthernEdgeAndZUnset();
                    if (!((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet())) {
                        jumpTargets.add(1090);
                    }
                }
            } else {
                setQFlagIfAtSouthernEdgeAndZUnset();
                if (unvisitedCellToEast()) {
                    jumpTargets.add(1020);
                }
                if (!((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet())) {
                    jumpTargets.add(1090);
                }
            }
            randomJump(jumpTargets);
        } else {
            line600statement0();
        }
    }

    protected void line600statement0() {
        List<Integer> jumpTargets = new ArrayList<Integer>();

        if (unvisitedCellToNorth()) {
            setQFlagIfAtSouthernEdgeAndZUnset();
            jumpTargets.add(980);

            if (unvisitedCellToEast()) {
                jumpTargets.add(1020);
            }

            if (!((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet())) {
                jumpTargets.add(1090);
            }
            randomJump(jumpTargets);
        } else {
            if (unvisitedCellToEast()) {
                if (atSouthernEdge()) {
                    if (zSet()) {
                        jumpTargets.add(1020);
                        randomJump(jumpTargets);
                    } else {
                        line830statement0();
                    }
                } else {
                    jumpTargets.add(1020);
                    if (!(((cellToSouth()) != (0)))) {
                        jumpTargets.add(1090);
                    }
                    randomJump(jumpTargets);
                }
            } else {
                setQFlagIfAtSouthernEdgeAndZUnset();

                if ((haveVisitedCellToSouth()) || atSouthernEdgeAndZSet()) {
                    line210statement0();
                } else {
                    jumpTargets.add(1090);
                    randomJump(jumpTargets);
                }
            }
        }
    }

    private boolean zSet() {
        return ((Z) == (1));
    }

    private void setQFlagIfAtSouthernEdgeAndZUnset() {
        if (atSouthernEdge() && (Z != 1)) {
            Q = 1;
        }
    }

    protected void line830statement0() {
        moveNorth();
        advanceCounter();
        setCurrentCellWallsToOne();
        if (finished()) {
            printMaze();
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void line940statement0() {
        moveWest();
        markCurrentCellAndAdvanceCounter();
        setCurrentCellWallsToTwo();
        if (finished()) {
            printMaze();
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void line980statement0() {
        moveNorth();
        markCurrentCellAndAdvanceCounter();
        setCurrentCellWallsToOne();
        if (finished()) {
            printMaze();
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void line1020statement0() {
        if (currentCellWallsAreZero()) {
            setCurrentCellWallsToTwo();
        } else {
            setCurrentCellWallsToThree();
        }
        moveEast();
        markCurrentCellAndAdvanceCounter();
        if (finished()) {
            printMaze();
            return;
        }
        line600statement0();
    }

    protected void line1090statement0() {
        if (Q == 1) {
            Z = 1;
            if (currentCellWallsAreZero()) {
                setCurrentCellWallsToOne();
                goToNorthWestCorner();
            } else {
                setCurrentCellWallsToThree();
                moveToNextSquare();
            }
            Q = 0;
            while (currentCellIsUnvisited()) {
                moveToNextSquare();
            }
        } else {

            if (currentCellWallsAreZero()) {
                setCurrentCellWallsToOne();
            } else {
                setCurrentCellWallsToThree();
            }
            moveSouth();
            markCurrentCellAndAdvanceCounter();
            if (finished()) {
                printMaze();
                return;
            }
        }
        line270statement0();
    }

    protected void line1200statement0() {
        printMaze();
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

    private void goToNorthWestCorner() {
        goWestToEdge();
        goNorthToEdge();
    }

    private void goWestToEdge() {
        R = 1;
    }

    private void goNorthToEdge() {
        S = 1;
    }

    private void moveWest() {
        R -= 1;
    }

    private void moveEast() {
        R += 1;
    }

    private void moveNorth() {
        S -= 1;
    }

    private void moveSouth() {
        S += 1;
    }

    private void markCurrentCellAndAdvanceCounter() {
        Ws[R - 1][S - 1] = C;
        advanceCounter();
    }

    private void advanceCounter() {
        C += 1;
    }

    private boolean finished() {
        return C == (H * V + 1);
    }

    private boolean currentCellWallsAreZero() {
        return Vs[R - 1][S - 1] == 0;
    }

    private void setCurrentCellWallsToOne() {
        Vs[R - 1][S - 1] = 1;
    }

    private void setCurrentCellWallsToTwo() {
        Vs[R - 1][S - 1] = 2;
    }

    private void setCurrentCellWallsToThree() {
        Vs[R - 1][S - 1] = 3;
    }

    private void printMaze() {
        printMaze(V, H, Vs);
    }

    private boolean unvisitedCellToWest() {
        return (!(atWesternEdge() || haveVisitedCellToWest()));
    }

    private boolean atWesternEdge() {
        return (((R) - (1)) == (0));
    }

    private boolean haveVisitedCellToWest() {
        return (cellToWest() != (0));
    }

    private int cellToWest() {
        return (Ws[(((R) - (1)) - 1)][((S) - 1)]);
    }

    private boolean unvisitedCellToNorth() {
        return (!(atNorthernEdge() || haveVisitedCellToNorth()));
    }

    private boolean atNorthernEdge() {
        return (((S) - (1)) == (0));
    }

    private boolean haveVisitedCellToNorth() {
        return (cellToNorth() != (0));
    }

    private int cellToNorth() {
        return (Ws[((R) - 1)][(((S) - (1)) - 1)]);
    }

    private boolean unvisitedCellToEast() {
        return (!(atEasternEdge() || haveVisitedCellToEast()));
    }

    private boolean atEasternEdge() {
        return ((R) == (H));
    }

    private boolean haveVisitedCellToEast() {
        return ((cellToEast()) != (0));
    }

    private int cellToEast() {
        return Ws[(((R) + (1)) - 1)][((S) - 1)];
    }

    private boolean atSouthernEdgeAndZSet() {
        return (atSouthernEdge() && zSet());
    }

    private boolean atSouthernEdge() {
        return (S == V);
    }

    private boolean haveVisitedCellToSouth() {
        return (!atSouthernEdge()) && ((cellToSouth()) != (0));
    }

    private int cellToSouth() {
        return Ws[((R) - 1)][(((S) + (1)) - 1)];
    }

    private boolean currentCellIsUnvisited() {
        return currentCell() == 0;
    }

    private int currentCell() {
        return Ws[R - 1][S - 1];
    }

}
