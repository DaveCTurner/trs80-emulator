/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class PrintStatement extends beaver.Symbol implements Statement {
    StringLiteral _text;
    IntegerLiteral _position;
    
    public PrintStatement(StringLiteral text, IntegerLiteral position) {
        super();
        _text = text;
        _position = position;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof PrintStatement)) { return false; }
        
        PrintStatement other = (PrintStatement)o;
        if (null == this._text) {
            if (null != other._text) { return false; }
        } else {
            if (!this._text.equals(other._text)) { return false; }
        }
        if (null == this._position) {
            if (null != other._position) { return false; }
        } else {
            if (!this._position.equals(other._position)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitPrintStatement(this);
    }

    public StringLiteral getText() { return _text; }
    public IntegerLiteral getPosition() { return _position; }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
