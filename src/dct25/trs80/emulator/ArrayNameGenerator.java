package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.Identifier;

public class ArrayNameGenerator {

    String buildArrayName(Identifier arrayId) {
        return arrayId.toString() + "s";
    }

}
