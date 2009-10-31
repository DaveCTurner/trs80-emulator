/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class ForStatement extends beaver.Symbol implements Statement {
   
    public ForStatement(Identifier loopVariable, IntegerExpression lowerBound, IntegerExpression upperBound) {
        super();
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

    public void visit(Visitor v) throws Exception {
        v.visitForStatement(this);
    }
    
    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
