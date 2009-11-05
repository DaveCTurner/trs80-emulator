package dct25.trs80.programs;

public class AmazingReimplementation extends amazing_test_bas {
    protected void line1200statement0() {
        J = (1);
        do {
            _env.print("I", false);
            I = (1);
            do {
                if (((Vs[I-1][J-1]) < (2))) {
                    _env.print("  I", false);
                } else {
                    _env.print("   ", false);
                }
                I += 1;
            } while (I <= H);
            _env.print(" ", true);
            I = (1);
            do {
                if (((Vs[I-1][J-1]) == (0))) {
                    _env.print(":--", false);
                } else {
                    if (((Vs[I-1][J-1]) == (2))) {
                        _env.print(":--", false);
                    } else {
                        _env.print(":  ", false);
                    }
                }
                I += 1;
            } while (I <= (H));
            _env.print(":", true);
            J += 1;
        } while (J <= V);
    }
}
