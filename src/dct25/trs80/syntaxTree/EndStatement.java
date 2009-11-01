/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class EndStatement extends AbstractStatement {
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

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitEndStatement(this);
    }
}
