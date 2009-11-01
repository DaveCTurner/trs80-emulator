package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.IfStatement;
import dct25.trs80.syntaxTree.NextStatement;
import dct25.trs80.syntaxTree.OnGotoStatement;
import dct25.trs80.syntaxTree.SyntaxTreeVisitor;
import dct25.trs80.syntaxTree.GotoStatement;

public class SetStatementLinksVisitor extends SyntaxTreeVisitor {
    private NumberedStatementsFinder _nsf;
    public SetStatementLinksVisitor(NumberedStatementsFinder nsf) {
        _nsf = nsf;
    }
    
    public void visitGotoStatement(GotoStatement s) {
        s.addNextStatement(_nsf.getNumberedStatement(s.getTarget()));
    }
    
    public void enterIfStatement(IfStatement s) {
        s.addNextStatement(_nsf.getNumberedStatement(s.getTarget()));
    }
    
    public void visitNextStatement(NextStatement s) {
        s.addNextStatement(s.getLoopStartStatement().getNextStatement());
    }
    
    public void enterOnGotoStatement(OnGotoStatement s) {
        for (int i = 1; i <= s.getTargetCount(); i++) {
            s.addNextStatement(_nsf.getNumberedStatement(s.getTarget(i)));
        }
    }
}
