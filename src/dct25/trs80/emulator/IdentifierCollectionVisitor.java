package dct25.trs80.emulator;

import java.util.HashSet;

import dct25.trs80.syntaxTree.ArrayElement;
import dct25.trs80.syntaxTree.ScalarAssignmentStatement;
import dct25.trs80.syntaxTree.SyntaxTreeVisitor;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.InputStatement;
import dct25.trs80.syntaxTree.Program;

public class IdentifierCollectionVisitor extends SyntaxTreeVisitor {
    private HashSet<Identifier> _ids;
    private HashSet<ArrayIdentifier> _arrayIds;
    
    public void enterProgram(Program p) {
        _ids = new HashSet<Identifier>();
        _arrayIds = new HashSet<ArrayIdentifier>();
    }
    
    public void enterForStatement(ForStatement fs) {
        _ids.add(fs.getLoopVariableIdentifier());
    }
    
    public void visitInputStatement(InputStatement is) {
        _ids.add(is.getIdentifier1());
        _ids.add(is.getIdentifier2());
    }
    
    public void leaveArrayDeclaration(ArrayElement ae) {
        _arrayIds.add(new ArrayIdentifier(ae.getIdentifier(), ae.getDimensionCount()));
    }
    
    public void enterScalarAssignmentStatement(ScalarAssignmentStatement as) {
        _ids.add(as.getAssignee());
    }
    
    public Identifier[] getIdentifiers() {
        return _ids.toArray(new Identifier[0]);
    }
    
    public ArrayIdentifier[] getArrayIdentifiers() {
        return _arrayIds.toArray(new ArrayIdentifier[0]);
    }
}
