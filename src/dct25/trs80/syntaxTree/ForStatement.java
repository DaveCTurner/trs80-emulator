/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class ForStatement extends AbstractStatement {
   
    private Identifier _loopVariable;
    private IntegerExpression _lowerBound, _upperBound;
    public ForStatement(Identifier loopVariable, IntegerExpression lowerBound, IntegerExpression upperBound) {
        super();
        _loopVariable = loopVariable;
        _lowerBound = lowerBound;
        _upperBound = upperBound;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ForStatement)) { return false; }
        
//        ForStatement other = (ForStatement)o;
//        if (null == this._text) {
//            if (null != other._text) { return false; }
//        } else {
//            if (!this._text.equals(other._text)) { return false; }
//        }
                      
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterForStatement(this);
        _lowerBound.visit(v);
        v.visitedLowerBoundInForStatement(this);
        _upperBound.visit(v);
        v.visitedUpperBoundInForStatement(this);
        v.leaveForStatement(this);
    }
    
    public Identifier getLoopVariableIdentifier() { return _loopVariable; }
    public IntegerExpression getLowerBound() { return _lowerBound; }
    public IntegerExpression getUpperBound() { return _upperBound; }
}
