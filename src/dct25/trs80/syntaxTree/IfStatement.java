/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class IfStatement extends AbstractStatement {
    BooleanExpression _condition;
    LineNumber _target;
    
    public IfStatement(BooleanExpression condition, LineNumber target) {
        super();
        _condition = condition;
        _target = target;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof IfStatement)) { return false; }
        
        IfStatement other = (IfStatement)o;
        if (null == this._condition) {
            if (null != other._condition) { return false; }
        } else {
            if (!this._condition.equals(other._condition)) { return false; }
        }
        if (null == this._target) {
            if (null != other._target) { return false; }
        } else {
            if (!this._target.equals(other._target)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterIfStatement(this);
        _condition.visit(v);
        v.visitedIfStatementCondition(this);
        v.leaveIfStatement(this);
    }

    public BooleanExpression getCondition() { return _condition; }
    public LineNumber getTarget() { return _target; }
}
