package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.LineNumber;

public class StatementNameGenerator {
    /**
     * Calculate the name of a statement based on its location in the source.
     * 
     * @param lineNumber The line number of the statement
     * @param i The (0-based) index of the statement in its line.
     * @return The name of the statement at the given position.
     */
    public String getStatementName(LineNumber lineNumber, int i) {
        return "line" + lineNumber.toString() + "statement" + i;
    }
}
