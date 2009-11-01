package dct25.trs80.emulator;

import java.util.Stack;

import dct25.trs80.syntaxTree.SyntaxTreeVisitor;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.NextStatement;
import dct25.trs80.syntaxTree.Program;

public class MatchLoopsVisitor extends SyntaxTreeVisitor {
    private Stack<ForStatement> _loopStarts = new Stack<ForStatement>();

    public void leaveProgram(Program p) throws Exception {
        if (!_loopStarts.isEmpty()) {
            throw new Exception("Mismatched FOR loops");
        }
    }
    
    public void visitNextStatement(NextStatement ns) {
        ns.setLoopStartStatement(_loopStarts.pop());
    }

    public void enterForStatement(ForStatement fs) {
        _loopStarts.push(fs);
    }
}
