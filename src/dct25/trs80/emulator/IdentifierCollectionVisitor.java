package dct25.trs80.emulator;

import java.util.HashSet;

import dct25.trs80.syntaxTree.SyntaxTreeVisitor;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.InputStatement;
import dct25.trs80.syntaxTree.Program;

public class IdentifierCollectionVisitor extends SyntaxTreeVisitor {
    private HashSet<Identifier> _ids;
    
    public void enterProgram(Program p) {
        _ids = new HashSet<Identifier>();
    }
    
    public void enterForStatement(ForStatement fs) {
        _ids.add(fs.getLoopVariableIdentifier());
    }
    
    public void visitInputStatement(InputStatement is) {
        _ids.add(is.getIdentifier1());
        _ids.add(is.getIdentifier2());
    }
    
    public Identifier[] getIdentifiers() {
        return _ids.toArray(new Identifier[0]);
    }
}
