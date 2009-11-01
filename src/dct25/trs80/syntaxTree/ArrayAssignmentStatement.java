/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class ArrayAssignmentStatement extends beaver.Symbol implements Statement {
    ArrayElement _assignee;
    IntegerExpression _value;
    
    public ArrayAssignmentStatement(ArrayElement assignee, IntegerExpression value) {
        super();
        _assignee = assignee;
        _value = value;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ArrayAssignmentStatement)) { return false; }
        
        ArrayAssignmentStatement other = (ArrayAssignmentStatement)o;
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
        v.enterArrayAssignmentStatement(this);
        _assignee.visit(v);
        v.visitedArrayAssignee(this);
        _value.visit(v);
        v.leaveArrayAssignmentStatement(this);
    }

    public ArrayElement getAssignee() { return _assignee; }
    public IntegerExpression getValue() { return _value; }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
