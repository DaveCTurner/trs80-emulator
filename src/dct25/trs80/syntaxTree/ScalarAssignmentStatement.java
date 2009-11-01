/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class ScalarAssignmentStatement extends AbstractStatement {
    Identifier _assignee;
    IntegerExpression _value;
    
    public ScalarAssignmentStatement(Identifier assignee, IntegerExpression value) {
        super();
        _assignee = assignee;
        _value = value;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ScalarAssignmentStatement)) { return false; }
        
        ScalarAssignmentStatement other = (ScalarAssignmentStatement)o;
        if (null == this._assignee) {
            if (null != other._assignee) { return false; }
        } else {
            if (!this._assignee.equals(other._assignee)) { return false; }
        }
        if (null == this._value) {
            if (null != other._value) { return false; }
        } else {
            if (!this._value.equals(other._value)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterScalarAssignmentStatement(this);
        _value.visit(v);
        v.leaveScalarAssignmentStatement(this);
    }

    public Identifier getAssignee() { return _assignee; }
    public IntegerExpression getValue() { return _value; }
}
