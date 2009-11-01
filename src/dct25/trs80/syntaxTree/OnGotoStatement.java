/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class OnGotoStatement extends beaver.Symbol implements Statement {
    IntegerExpression _expression;
    LineNumber[] _targets;
    
    public OnGotoStatement(IntegerExpression e, LineNumber[] targets) {
        super();
        _targets = targets;
        _expression = e;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof OnGotoStatement)) { return false; }
        
        OnGotoStatement other = (OnGotoStatement)o;
        if (null == this._targets) {
            if (null != other._targets) { return false; }
        } else {
            if (_targets.length != other._targets.length) { return false; }
            for (int i = 0; i < _targets.length; i++) {
                if (null == this._targets[i]) {
                    if (null != other._targets[i]) { return false; }
                } else {
                    if (!this._targets[i].equals(other._targets[i])) { return false; }
                }
            }
        }
        if (null == this._expression) {
            if (null != other._expression) { return false; }
        } else {
            if (!this._expression.equals(other._expression)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterOnGotoStatement(this);
        _expression.visit(v);
        v.leaveOnGotoStatement(this);
    }

    public LineNumber getTarget(int i) { return _targets[i-1]; }
    public int getTargetCount() { return _targets.length; }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
