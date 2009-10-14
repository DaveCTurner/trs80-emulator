/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class Program extends beaver.Symbol {
    public Program (LineNumber lineNumber, Statement statement) {
        super();
        /* Do nothing */
    }
    
    public String toString() {
        return "10 CLX";
    }
}
