/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class ClearScreenStatement extends beaver.Symbol implements Statement {
    public ClearScreenStatement() {
        super();
        /* Do nothing */
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ClearScreenStatement)) { return false; }
               
        return true;
    }

    public void visit(Visitor v) throws Exception {
        v.visitClearScreenStatement(this);
    }

    private String _name;
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }
}
