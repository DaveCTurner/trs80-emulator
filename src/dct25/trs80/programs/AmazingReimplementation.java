package dct25.trs80.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;

public class AmazingReimplementation implements Executable {
    int Q;

    int C;

    int Z;

    Environment _env;

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

        _layout = new AmazingLayout(h, v, entryPosition);
        
        Q = 0;
        Z = 0;
        C = 2;

        line270statement0();
    }

    private void moveToNextSquare() {
        if (_layout.atEasternEdge()) {
            if (_layout.atSouthernEdge()) {
                _layout.goToNorthWestCorner();
            } else {
                _layout.goWestToEdge();
                _layout.moveSouth();
            }
        } else {
            _layout.moveEast();
        }
    }

    private int POSSIBLY_WEST = 940;

    private int POSSIBLY_NORTH = 980;

    private int POSSIBLY_EAST = 1020;

    private int POSSIBLY_SOUTH = 1090;

    private void randomJump(List<Integer> targets) {
        int[] targetsArray = new int[targets.size()];
        for (int i = 0; i < targets.size(); i++) {
            targetsArray[i] = targets.get(i);
        }
        Arrays.sort(targetsArray);
        switch (targetsArray[(_env.getNextRandomNumber(targetsArray.length)) - 1]) {
        case 940:
            goWestAndRestart();
            break;
        case 980:
            goNorthAndRestart();
            break;
        case 1020:
            goEastAndRestart();
            break;
        case 1090:
            if (Q == 1) {
                doSomethingWeirdAndRestart();
            } else {
                goSouthAndRestart();
            }
            break;
        }
    }

    protected void line270statement0() {
        List<Integer> jumpTargets = new ArrayList<Integer>();

        if (_layout.unvisitedCellToWest()) {
            jumpTargets.add(POSSIBLY_WEST);
            if (_layout.unvisitedCellToNorth()) {
                jumpTargets.add(POSSIBLY_NORTH);
                if (_layout.unvisitedCellToEast()) {
                    jumpTargets.add(POSSIBLY_EAST);
                } else {
                    setQFlagIfAtSouthernEdgeAndZUnset();
                    if ((_layout.unvisitedCellToSouth() || couldExitHere())) {
                        jumpTargets.add(POSSIBLY_SOUTH);
                    }
                }
            } else {
                if (_layout.unvisitedCellToEast()) {
                    jumpTargets.add(POSSIBLY_EAST);
                }
                setQFlagIfAtSouthernEdgeAndZUnset();
                if ((_layout.unvisitedCellToSouth() || couldExitHere())) {
                    jumpTargets.add(POSSIBLY_SOUTH);
                }
            }
            randomJump(jumpTargets);
        } else {
            line600statement0();
        }
    }

    protected void line600statement0() {
        List<Integer> jumpTargets = new ArrayList<Integer>();

        if (_layout.unvisitedCellToNorth()) {
            jumpTargets.add(POSSIBLY_NORTH);

            if (_layout.unvisitedCellToEast()) {
                jumpTargets.add(POSSIBLY_EAST);
            }

            setQFlagIfAtSouthernEdgeAndZUnset();
            if ((_layout.unvisitedCellToSouth() || couldExitHere())) {
                jumpTargets.add(POSSIBLY_SOUTH);
            }
            randomJump(jumpTargets);
        } else {
            if (_layout.unvisitedCellToEast()) {
                if (_layout.atSouthernEdge()) {
                    if (zSet()) {
                        jumpTargets.add(POSSIBLY_EAST);
                        randomJump(jumpTargets);
                    } else {
                        goNorthAndRestartWithoutMark();
                    }
                } else {
                    jumpTargets.add(POSSIBLY_EAST);
                    if (!((_layout.haveVisitedCellToSouth()))) {
                        jumpTargets.add(POSSIBLY_SOUTH);
                    }
                    randomJump(jumpTargets);
                }
            } else {
                setQFlagIfAtSouthernEdgeAndZUnset();

                if ((_layout.unvisitedCellToSouth() || couldExitHere())) {
                    jumpTargets.add(POSSIBLY_SOUTH);
                    randomJump(jumpTargets);
                } else {
                    do {
                        moveToNextSquare();
                    } while (_layout.currentCellIsUnvisited());
                    line270statement0();
                }
            }
        }
    }

    private boolean zSet() {
        return ((Z) == (1));
    }

    private void setQFlagIfAtSouthernEdgeAndZUnset() {
        if (_layout.atSouthernEdge() && (Z != 1)) {
            Q = 1;
        }
    }

    protected void goNorthAndRestartWithoutMark() {
        _layout.moveNorth();
        advanceCounter();
        _layout.setWallToEastButNotToSouth();
        if (finished()) {
            _layout.printMaze(this);
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void goWestAndRestart() {
        _layout.moveWest();
        markCurrentCellAndAdvanceCounter();
        _layout.setWallToSouthButNotToEast();
        if (finished()) {
            _layout.printMaze(this);
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void goNorthAndRestart() {
        _layout.moveNorth();
        markCurrentCellAndAdvanceCounter();
        _layout.setWallToEastButNotToSouth();
        if (finished()) {
            _layout.printMaze(this);
            return;
        }
        Q = 0;
        line270statement0();
    }

    protected void goEastAndRestart() {
        if (_layout.wallsToEastAndSouth()) {
            _layout.setWallToSouthButNotToEast();
        } else {
            _layout.setNoWallsToEastOrSouth();
        }
        _layout.moveEast();
        markCurrentCellAndAdvanceCounter();
        if (finished()) {
            _layout.printMaze(this);
            return;
        }
        line600statement0();
    }

    private void doSomethingWeirdAndRestart() {
        Z = 1;
        if (_layout.wallsToEastAndSouth()) {
            _layout.setWallToEastButNotToSouth();
            _layout.goToNorthWestCorner();
        } else {
            _layout.setNoWallsToEastOrSouth();
            moveToNextSquare();
        }
        Q = 0;
        while (_layout.currentCellIsUnvisited()) {
            moveToNextSquare();
        }
        line270statement0();
    }

    private void goSouthAndRestart() {
        if (_layout.wallsToEastAndSouth()) {
            _layout.setWallToEastButNotToSouth();
        } else {
            _layout.setNoWallsToEastOrSouth();
        }
        _layout.moveSouth();
        markCurrentCellAndAdvanceCounter();
        if (finished()) {
            _layout.printMaze(this);
        } else {
            line270statement0();
        }
    }

    int entryPosition;

    AmazingLayout _layout;
    
    private void markCurrentCellAndAdvanceCounter() {
        _layout.markCurrentPosition(C);
        advanceCounter();
    }

    private void advanceCounter() {
        C += 1;
    }

    private boolean finished() {
        return C == (_layout.numberOfCells() + 1);
    }

    boolean couldExitHere() {
        return (_layout.atSouthernEdge() && !zSet());
    }

}
