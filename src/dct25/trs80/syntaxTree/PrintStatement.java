/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class PrintStatement extends AbstractStatement {
    StringLiteral _text;
    IntegerLiteral _position;
    boolean _newLine;
    
    public PrintStatement(StringLiteral text, IntegerLiteral position, boolean newLine) {
        super();
        _text = text;
        _position = position;
        _newLine = newLine;
    }
    
    public PrintStatement(StringLiteral text, IntegerLiteral position) {
        this(text, position, true);
    }

    public PrintStatement(StringLiteral text, boolean newLine) {
        this(text, null, newLine);
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
        if (this._newLine != other._newLine) { return false; }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitPrintStatement(this);
    }

    public StringLiteral getText() { return _text; }
    public IntegerLiteral getPosition() { return _position; }
    public boolean getNewLine() { return _newLine; }
}
