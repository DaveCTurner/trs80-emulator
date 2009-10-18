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


    public String asBasic() {
        return "CLS";
    }
}
