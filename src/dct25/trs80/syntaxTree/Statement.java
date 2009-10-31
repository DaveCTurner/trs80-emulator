/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public interface Statement {
    public void visit(Visitor v) throws Exception;
}
