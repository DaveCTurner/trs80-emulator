package dct25.trs80.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;

public class AmazingReimplementation implements Executable {
    int V;

    int S;

    int R;

    int C;

    int H;

    int[][] Ws;

    int[][] Vs;

    Environment _env;

    private boolean _haveMadeExit;

    private boolean _inExitMode;

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

        H = h;
        V = v;
        _inExitMode = false;
        _haveMadeExit = false;
        C = 2;
        R = entryPosition;
        goNorthToEdge();

        _skipToLine600 = false;
        line270or600();
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

    private int POSSIBLY_WEST = 940;

    private int POSSIBLY_NORTH = 980;

    private int POSSIBLY_EAST = 1020;

    private int POSSIBLY_SOUTH = 1090;

    boolean _skipToLine600;

    private void line270or600() {
        while (true) {
            List<Integer> jumpTargets = new ArrayList<Integer>();
            boolean performRandomJump = false;

            if (unvisitedCellToEast()) {
                jumpTargets.add(POSSIBLY_EAST);
                performRandomJump = true;
            }

            if (unvisitedCellToNorth()) {
                jumpTargets.add(POSSIBLY_NORTH);
                performRandomJump = true;
            }

            if (!_skipToLine600 && unvisitedCellToWest()) {
                jumpTargets.add(POSSIBLY_WEST);
                performRandomJump = true;
            }

            if (unvisitedCellToSouth() && jumpTargets.size() < 3) {
                jumpTargets.add(POSSIBLY_SOUTH);
                performRandomJump = true;
            }

            if (!_skipToLine600 && unvisitedCellToWest()) {
                if (atSouthernEdge() && !_haveMadeExit
                        && jumpTargets.size() < 3) {
                    jumpTargets.add(POSSIBLY_SOUTH);
                    _inExitMode = true;
                }
            } else {

                if ((atSouthernEdge() && !_haveMadeExit && jumpTargets.size() < 3)) {
                    if ((!unvisitedCellToNorth()) && unvisitedCellToEast()) {
                        goNorthWithoutMark();
                        if (finished()) {
                            printMaze();
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        jumpTargets.add(POSSIBLY_SOUTH);
                        _inExitMode = true;
                        performRandomJump = true;
                    }
                } else {
                    if (!performRandomJump) {
                        do {
                            moveToNextSquare();
                        } while (currentCellIsUnvisited());
                        _skipToLine600 = false;
                        continue;
                    }
                }
            }
            boolean didSomethingWeird = false;
            int[] targetsArray = new int[jumpTargets.size()];
            for (int i = 0; i < jumpTargets.size(); i++) {
                targetsArray[i] = jumpTargets.get(i);
            }
            Arrays.sort(targetsArray);
            switch (targetsArray[(_env.getNextRandomNumber(targetsArray.length)) - 1]) {
            case 940:
                goWest();
                break;
            case 980:
                goNorth();
                break;
            case 1020:
                goEast();
                break;
            case 1090:
                if (_inExitMode) {
                    doSomethingWeird();
                    didSomethingWeird = true;
                } else {
                    goSouth();
                }
                break;
            default:
                throw new Error("Unknown target in randomJump()");
            }
            if (!didSomethingWeird && finished()) {
                printMaze();
                break;
            } else {
                continue;
            }
        }
    }

    private void goNorthWithoutMark() {
        moveNorth();
        advanceCounter();
        setWallToEastButNotToSouth();
        _inExitMode = false;
        _skipToLine600 = false;
    }

    private void goWest() {
        moveWest();
        markCurrentCellAndAdvanceCounter();
        setWallToSouthButNotToEast();
        _inExitMode = false;
        _skipToLine600 = false;
    }

    private void goNorth() {
        moveNorth();
        markCurrentCellAndAdvanceCounter();
        setWallToEastButNotToSouth();
        _inExitMode = false;
        _skipToLine600 = false;
    }

    private void goEast() {
        if (wallsToEastAndSouth()) {
            setWallToSouthButNotToEast();
        } else {
            setNoWallsToEastOrSouth();
        }
        moveEast();
        markCurrentCellAndAdvanceCounter();
        _skipToLine600 = true;
    }

    private void doSomethingWeird() {
        _haveMadeExit = true;
        if (wallsToEastAndSouth()) {
            setWallToEastButNotToSouth();
            goToNorthWestCorner();
        } else {
            setNoWallsToEastOrSouth();
            moveToNextSquare();
        }
        _inExitMode = false;
        while (currentCellIsUnvisited()) {
            moveToNextSquare();
        }
        _skipToLine600 = false;
    }

    private void goSouth() {
        if (wallsToEastAndSouth()) {
            setWallToEastButNotToSouth();
        } else {
            setNoWallsToEastOrSouth();
        }
        moveSouth();
        markCurrentCellAndAdvanceCounter();
        _skipToLine600 = false;
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

    private boolean wallsToEastAndSouth() {
        return Vs[R - 1][S - 1] == 0;
    }

    private void setWallToEastButNotToSouth() {
        Vs[R - 1][S - 1] = 1;
    }

    private void setWallToSouthButNotToEast() {
        Vs[R - 1][S - 1] = 2;
    }

    private void setNoWallsToEastOrSouth() {
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

    private boolean unvisitedCellToSouth() {
        return (!(atSouthernEdge() || haveVisitedCellToSouth()));
    }

    private boolean atSouthernEdge() {
        return (S == V);
    }

    private boolean haveVisitedCellToSouth() {
        return (cellToSouth()) != (0);
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
