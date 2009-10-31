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
    
    public PrintStatement(StringLiteral text, beaver.Symbol position) {
        super();
        _text = text;
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
               
        return true;
    }

    public void visit(Visitor v) throws Exception {
        v.visitPrintStatement(this);
    }

    public StringLiteral getText() { return _text; }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
