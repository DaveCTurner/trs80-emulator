/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class NextStatement extends beaver.Symbol implements Statement {
    public NextStatement() {
        super();
        /* Do nothing */
    }
    
    public NextStatement(Identifier i) {
        super();
        /* Do nothing */
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof NextStatement)) { return false; }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitNextStatement(this);
    }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private ForStatement _loopStart;
    public ForStatement getLoopStartStatement() { return _loopStart; }
    public void setLoopStartStatement(ForStatement loopStart) { _loopStart = loopStart; }
    
    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
    
    public Identifier getLoopIdentifier() { 
        if (_loopStart == null) { return null; }
        return _loopStart.getLoopVariableIdentifier();
    }
}
