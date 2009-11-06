package dct25.trs80.programs;

import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;

public class AmazingReimplementation implements Executable {
    int II;

    int V;

    int Q;

    int A;

    int S;

    int R;

    int C;

    int H;

    int I;

    int X;

    int J;

    int Z;

    int[][] Ws;

    int[][] Vs;

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

    private void line1090withoutQ() {
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
        line270statement0();
    }

    private void line1090withQ() {
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
        line270statement0();
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

    protected void line270statement0() {
        if (atWesternEdge()) {
            line600statement0();
        } else {
            if (haveVisitedSquareToWest()) {
                line600statement0();
            } else {
                if (atNorthernEdge()) {
                    if (atEasternEdge()) {
                        if (!atSouthernEdge()) {
                            if (haveVisitedSquareToSouth()) {
                                line940statement0();
                            } else {
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line940statement0();
                                    break;
                                case 2:
                                    if (Q == 1) {
                                        line1090withQ();
                                    } else {
                                        line1090withoutQ();
                                    }
                                    break;
                                }
                            }
                        } else {
                            if (((Z) == (1))) {
                                line940statement0();
                            } else {
                                Q = (1);
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line940statement0();
                                    break;
                                case 2:
                                    line1090withQ();
                                    break;
                                }
                            }
                        }
                    } else {
                        if (haveVisitedSquareToEast()) {
                            if (!atSouthernEdge()) {
                                if (haveVisitedSquareToSouth()) {
                                    line940statement0();
                                } else {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        if (Q == 1) {
                                            line1090withQ();
                                        } else {
                                            line1090withoutQ();
                                        }
                                        break;
                                    }
                                }
                            } else {
                                if (((Z) == (1))) {
                                    line940statement0();
                                } else {
                                    Q = (1);
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1090withQ();
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (!atSouthernEdge()) {
                                if (haveVisitedSquareToSouth()) {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1020statement0();
                                        break;
                                    }
                                } else {
                                    X = (_env.getNextRandomNumber(3));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1020statement0();
                                        break;
                                    case 3:
                                        if (Q == 1) {
                                            line1090withQ();
                                        } else {
                                            line1090withoutQ();
                                        }
                                        break;
                                    }
                                }
                            } else {
                                if (((Z) == (1))) {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1020statement0();
                                        break;
                                    }
                                } else {
                                    Q = (1);
                                    X = (_env.getNextRandomNumber(3));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1020statement0();
                                        break;
                                    case 3:
                                        line1090withQ();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (haveVisitedSquareToNorth()) {
                        if (atEasternEdge()) {
                            if (!atSouthernEdge()) {
                                if (haveVisitedSquareToSouth()) {
                                    line940statement0();
                                } else {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        if (Q == 1) {
                                            line1090withQ();
                                        } else {
                                            line1090withoutQ();
                                        }
                                        break;
                                    }
                                }
                            } else {
                                if (((Z) == (1))) {
                                    line940statement0();
                                } else {
                                    Q = (1);
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line1090withQ();
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (haveVisitedSquareToEast()) {
                                if (!atSouthernEdge()) {
                                    if (haveVisitedSquareToSouth()) {
                                        line940statement0();
                                    } else {
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            if (Q == 1) {
                                                line1090withQ();
                                            } else {
                                                line1090withoutQ();
                                            }
                                            break;
                                        }
                                    }
                                } else {
                                    if (((Z) == (1))) {
                                        line940statement0();
                                    } else {
                                        Q = (1);
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line1090withQ();
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (!atSouthernEdge()) {
                                    if (haveVisitedSquareToSouth()) {
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line1020statement0();
                                            break;
                                        }
                                    } else {
                                        X = (_env.getNextRandomNumber(3));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line1020statement0();
                                            break;
                                        case 3:
                                            if (Q == 1) {
                                                line1090withQ();
                                            } else {
                                                line1090withoutQ();
                                            }
                                            break;
                                        }
                                    }
                                } else {
                                    if (((Z) == (1))) {
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line1020statement0();
                                            break;
                                        }
                                    } else {
                                        Q = (1);
                                        X = (_env.getNextRandomNumber(3));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line1020statement0();
                                            break;
                                        case 3:
                                            line1090withQ();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (atEasternEdge()) {
                            if (!atSouthernEdge()) {
                                if (haveVisitedSquareToSouth()) {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line980statement0();
                                        break;
                                    }
                                } else {
                                    X = (_env.getNextRandomNumber(3));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line980statement0();
                                        break;
                                    case 3:
                                        if (Q == 1) {
                                            line1090withQ();
                                        } else {
                                            line1090withoutQ();
                                        }
                                        break;
                                    }
                                }
                            } else {
                                if (((Z) == (1))) {
                                    X = (_env.getNextRandomNumber(2));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line980statement0();
                                        break;
                                    }
                                } else {
                                    Q = (1);
                                    X = (_env.getNextRandomNumber(3));
                                    switch (X) {
                                    case 1:
                                        line940statement0();
                                        break;
                                    case 2:
                                        line980statement0();
                                        break;
                                    case 3:
                                        line1090withQ();
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (haveVisitedSquareToEast()) {
                                if (!atSouthernEdge()) {
                                    if (haveVisitedSquareToSouth()) {
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line980statement0();
                                            break;
                                        }
                                    } else {
                                        X = (_env.getNextRandomNumber(3));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line980statement0();
                                            break;
                                        case 3:
                                            if (Q == 1) {
                                                line1090withQ();
                                            } else {
                                                line1090withoutQ();
                                            }
                                            break;
                                        }
                                    }
                                } else {
                                    if (((Z) == (1))) {
                                        X = (_env.getNextRandomNumber(2));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line980statement0();
                                            break;
                                        }
                                    } else {
                                        Q = (1);
                                        X = (_env.getNextRandomNumber(3));
                                        switch (X) {
                                        case 1:
                                            line940statement0();
                                            break;
                                        case 2:
                                            line980statement0();
                                            break;
                                        case 3:
                                            line1090withQ();
                                            break;
                                        }
                                    }
                                }
                            } else {
                                X = (_env.getNextRandomNumber(3));
                                switch (X) {
                                case 1:
                                    line940statement0();
                                    break;
                                case 2:
                                    line980statement0();
                                    break;
                                case 3:
                                    line1020statement0();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean haveVisitedSquareToNorth() {
        return ((Ws[((R) - 1)][(((S) - (1)) - 1)]) != (0));
    }

    private boolean haveVisitedSquareToEast() {
        return ((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0));
    }

    private boolean haveVisitedSquareToSouth() {
        return ((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0));
    }

    private boolean atSouthernEdge() {
        return ((S) == (V));
    }

    private boolean atEasternEdge() {
        return ((R) == (H));
    }

    private boolean atNorthernEdge() {
        return (((S) - (1)) == (0));
    }

    private boolean atWesternEdge() {
        return (((R) - (1)) == (0));
    }

    private boolean haveVisitedSquareToWest() {
        return ((Ws[(((R) - (1)) - 1)][((S) - 1)]) != (0));
    }

    protected void line600statement0() {
        if (atNorthernEdge()) {
            if (atEasternEdge()) {
                if (!atSouthernEdge()) {
                    if (haveVisitedSquareToSouth()) {
                        do {
                            moveToNextSquare();
                        } while (Ws[R - 1][S - 1] == 0);
                        line270statement0();
                    } else {
                        if (Q == 1) {
                            line1090withQ();
                        } else {
                            line1090withoutQ();
                        }
                    }
                } else {
                    if (((Z) == (1))) {
                        do {
                            moveToNextSquare();
                        } while (Ws[R - 1][S - 1] == 0);
                        line270statement0();
                    } else {
                        line1090withQ();
                    }
                }
            } else {
                if (haveVisitedSquareToEast()) {
                    if (!atSouthernEdge()) {
                        if (haveVisitedSquareToSouth()) {
                            do {
                                moveToNextSquare();
                            } while (Ws[R - 1][S - 1] == 0);
                            line270statement0();
                        } else {
                            if (Q == 1) {
                                line1090withQ();
                            } else {
                                line1090withoutQ();
                            }
                        }
                    } else {
                        if (((Z) == (1))) {
                            do {
                                moveToNextSquare();
                            } while (Ws[R - 1][S - 1] == 0);
                            line270statement0();
                        } else {
                            line1090withQ();
                        }
                    }
                } else {
                    if (!atSouthernEdge()) {
                        if (haveVisitedSquareToSouth()) {
                            line1020statement0();
                        } else {
                            X = (_env.getNextRandomNumber(2));
                            switch (X) {
                            case 1:
                                line1020statement0();
                                break;
                            case 2:
                                if (Q == 1) {
                                    line1090withQ();
                                } else {
                                    line1090withoutQ();
                                }
                                break;
                            }
                        }
                    } else {
                        if (((Z) == (1))) {
                            line1020statement0();
                        } else {
                            Q = (1);
                            C = ((C) + (1));
                            Vs[((R) - 1)][(((S) - (1)) - 1)] = (1);
                            S = ((S) - (1));
                            if (((C) == (((H) * (V)) + (1)))) {
                                printMaze(V, H, Vs);
                            } else {
                                Q = (0);
                                line270statement0();
                            }
                        }
                    }
                }
            }
        } else {
            if (haveVisitedSquareToNorth()) {
                if (atEasternEdge()) {
                    if (!atSouthernEdge()) {
                        if (haveVisitedSquareToSouth()) {
                            do {
                                moveToNextSquare();
                            } while (Ws[R - 1][S - 1] == 0);
                            line270statement0();
                        } else {
                            if (Q == 1) {
                                line1090withQ();
                            } else {
                                line1090withoutQ();
                            }
                        }
                    } else {
                        if (((Z) == (1))) {
                            do {
                                moveToNextSquare();
                            } while (Ws[R - 1][S - 1] == 0);
                            line270statement0();
                        } else {
                            line1090withQ();
                        }
                    }
                } else {
                    if (haveVisitedSquareToEast()) {
                        if (!atSouthernEdge()) {
                            if (haveVisitedSquareToSouth()) {
                                do {
                                    moveToNextSquare();
                                } while (Ws[R - 1][S - 1] == 0);
                                line270statement0();
                            } else {
                                if (Q == 1) {
                                    line1090withQ();
                                } else {
                                    line1090withoutQ();
                                }
                            }
                        } else {
                            if (((Z) == (1))) {
                                do {
                                    moveToNextSquare();
                                } while (Ws[R - 1][S - 1] == 0);
                                line270statement0();
                            } else {
                                line1090withQ();
                            }
                        }
                    } else {
                        if (!atSouthernEdge()) {
                            if (haveVisitedSquareToSouth()) {
                                line1020statement0();
                            } else {
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line1020statement0();
                                    break;
                                case 2:
                                    if (Q == 1) {
                                        line1090withQ();
                                    } else {
                                        line1090withoutQ();
                                    }
                                    break;
                                }
                            }
                        } else {
                            if (((Z) == (1))) {
                                line1020statement0();
                            } else {
                                Q = (1);
                                C = ((C) + (1));
                                Vs[((R) - 1)][(((S) - (1)) - 1)] = (1);
                                S = ((S) - (1));
                                if (((C) == (((H) * (V)) + (1)))) {
                                    printMaze(V, H, Vs);
                                } else {
                                    Q = (0);
                                    line270statement0();
                                }
                            }
                        }
                    }
                }
            } else {
                if (atEasternEdge()) {
                    if (!atSouthernEdge()) {
                        if (haveVisitedSquareToSouth()) {
                            line980statement0();
                        } else {
                            X = (_env.getNextRandomNumber(2));
                            switch (X) {
                            case 1:
                                line980statement0();
                                break;
                            case 2:
                                if (Q == 1) {
                                    line1090withQ();
                                } else {
                                    line1090withoutQ();
                                }
                                break;
                            }
                        }
                    } else {
                        if (((Z) == (1))) {
                            line980statement0();
                        } else {
                            X = (_env.getNextRandomNumber(2));
                            switch (X) {
                            case 1:
                                line980statement0();
                                break;
                            case 2:
                                line1090withQ();
                                break;
                            }
                        }
                    }
                } else {
                    if (haveVisitedSquareToEast()) {
                        if (!atSouthernEdge()) {
                            if (haveVisitedSquareToSouth()) {
                                line980statement0();
                            } else {
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    if (Q == 1) {
                                        line1090withQ();
                                    } else {
                                        line1090withoutQ();
                                    }
                                    break;
                                }
                            }
                        } else {
                            if (((Z) == (1))) {
                                line980statement0();
                            } else {
                                Q = (1);
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    line1090withQ();
                                    break;
                                }
                            }
                        }
                    } else {
                        if (!atSouthernEdge()) {
                            if (haveVisitedSquareToSouth()) {
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    line1020statement0();
                                    break;
                                }
                            } else {
                                X = (_env.getNextRandomNumber(3));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    line1020statement0();
                                    break;
                                case 3:
                                    if (Q == 1) {
                                        line1090withQ();
                                    } else {
                                        line1090withoutQ();
                                    }
                                    break;
                                }
                            }
                        } else {
                            if (((Z) == (1))) {
                                X = (_env.getNextRandomNumber(2));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    line1020statement0();
                                    break;
                                }
                            } else {
                                Q = (1);
                                X = (_env.getNextRandomNumber(3));
                                switch (X) {
                                case 1:
                                    line980statement0();
                                    break;
                                case 2:
                                    line1020statement0();
                                    break;
                                case 3:
                                    line1090withQ();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
