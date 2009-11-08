package dct25.trs80.programs;

import dct25.trs80.emulator.Environment;

public class AmazingLayout {

    private int _entryPosition;
    
    public AmazingLayout(int horizontalSize, int verticalSize, int entryPosition) {
        H = horizontalSize;
        V = verticalSize;
        _entryPosition = entryPosition;
        
        Ws = new int[H][V];
        Vs = new int[H][V];
        Ws[_entryPosition - 1][0] = 1;
        R = entryPosition;
        S = 1;
    }

    void goToNorthWestCorner() {
        goWestToEdge();
        goNorthToEdge();
    }

    void goWestToEdge() {
        R = 1;
    }

    void goNorthToEdge() {
        S = 1;
    }

    void moveWest() {
        R -= 1;
    }

    void moveEast() {
        R += 1;
    }

    void moveNorth() {
        S -= 1;
    }

    void moveSouth() {
        S += 1;
    }

    void markCurrentPosition(int c) {
        Ws[R - 1][S - 1] = c;
    }

    int numberOfCells() {
        return H * V;
    }

    boolean wallsToEastAndSouth() {
        return Vs[R - 1][S - 1] == 0;
    }

    void setWallToEastButNotToSouth() {
        Vs[R - 1][S - 1] = 1;
    }

    void setWallToSouthButNotToEast() {
        Vs[R - 1][S - 1] = 2;
    }

    void setNoWallsToEastOrSouth() {
        Vs[R - 1][S - 1] = 3;
    }

    void printMaze(AmazingReimplementation reimplementation) {
        Environment env = reimplementation._env;
        printMaze(env, H, V, Vs);
    }

    boolean unvisitedCellToWest() {
        return (!(atWesternEdge() || haveVisitedCellToWest()));
    }

    boolean atWesternEdge() {
        return R == 1;
    }

    boolean haveVisitedCellToWest() {
        return cellToWest() != (0);
    }

    int cellToWest() {
        return (Ws[R - 2][S - 1]);
    }

    boolean unvisitedCellToNorth() {
        return (!(atNorthernEdge() || haveVisitedCellToNorth()));
    }

    boolean atNorthernEdge() {
        return S == 1;
    }

    boolean haveVisitedCellToNorth() {
        return cellToNorth() != 0;
    }

    int cellToNorth() {
        return Ws[R - 1][S - 2];
    }

    boolean unvisitedCellToEast() {
        return (!(atEasternEdge() || haveVisitedCellToEast()));
    }

    boolean atEasternEdge() {
        return R == H;
    }

    boolean haveVisitedCellToEast() {
        return cellToEast() != 0;
    }

    int cellToEast() {
        return Ws[R][S - 1];
    }

    boolean unvisitedCellToSouth() {
        return (!(atSouthernEdge() || haveVisitedCellToSouth()));
    }

    boolean atSouthernEdge() {
        return S == V;
    }

    boolean haveVisitedCellToSouth() {
        return cellToSouth() != 0;
    }

    int cellToSouth() {
        return Ws[R - 1][S];
    }

    boolean currentCellIsUnvisited() {
        return currentCell() == 0;
    }

    int currentCell() {
        return Ws[R - 1][S - 1];
    }

    private void printMaze(Environment env, int h, int v, int[][] wallFlags) {
        for (int i = 1; i <= h; i++) {
            if (i == _entryPosition) {
                env.print(":  ", false);
            } else {
                env.print(":--", false);
            }
        }
        env.print(":", true);

        for (int j = 0; j < v; j++) {
            env.print("I", false);
            for (int i = 0; i < h; i++) {
                if ((wallFlags[i][j] & 0x02) == 0) {
                    env.print("  I", false);
                } else {
                    env.print("   ", false);
                }
            }
            env.print(" ", true);
            for (int i = 0; i < h; i++) {
                if ((wallFlags[i][j] & 0x01) == 0) {
                    env.print(":--", false);
                } else {
                    env.print(":  ", false);
                }
            }
            env.print(":", true);
        }
    }

    private int R;

    private int H;

    private int V;

    private int S;

    private int[][] Ws;

    private int[][] Vs;
}
