/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class EndStatement extends beaver.Symbol implements Statement {
    public EndStatement() {
        super();
        /* Do nothing */
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof EndStatement)) { return false; }
               
        return true;
    }

    public void visit(Visitor v) throws Exception {
        v.visitEndStatement(this);
    }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }
    
    private Statement _nextStatement;
    public Statement getNextStatement() { return _nextStatement; }
    public void setNextStatement(Statement next) { _nextStatement = next; }
}
