/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class InputStatement extends beaver.Symbol implements Statement {
    StringLiteral _prompt;
    Identifier _identifier1;
    Identifier _identifier2;
    
    public InputStatement(StringLiteral prompt, Identifier identifier1, Identifier identifier2) {
        super();
        _prompt = prompt;
        _identifier1 = identifier1;
        _identifier2 = identifier2;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof InputStatement)) { return false; }
        
        InputStatement other = (InputStatement)o;
        if (null == this._prompt) {
            if (null != other._prompt) { return false; }
        } else {
            if (!this._prompt.equals(other._prompt)) { return false; }
        }
        if (null == this._identifier1) {
            if (null != other._identifier1) { return false; }
        } else {
            if (!this._identifier1.equals(other._identifier1)) { return false; }
        }
        if (null == this._identifier2) {
            if (null != other._identifier2) { return false; }
        } else {
            if (!this._identifier2.equals(other._identifier2)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitInputStatement(this);
    }

    public StringLiteral getPrompt() { return _prompt; }
    public Identifier getIdentifier1() { return _identifier1; }
    public Identifier getIdentifier2() { return _identifier2; }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
