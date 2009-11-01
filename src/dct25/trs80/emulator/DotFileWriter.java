package dct25.trs80.emulator;

import java.io.PrintStream;

import dct25.trs80.syntaxTree.Statement;
import dct25.trs80.syntaxTree.SyntaxTreeVisitor;


public class DotFileWriter extends SyntaxTreeVisitor {

    private PrintStream _out;

    public DotFileWriter(PrintStream out) {
        _out = out;
    }

    public void visitStatement(Statement s) {
        Statement[] nextStatements = s.getNextStatements();
        _out.println(s.getName() + ";");
        for (int i = 0; i < nextStatements.length; i++) {
            _out.println(s.getName() + " -> " + nextStatements[i].getName() + ";");
        }
    }
}
