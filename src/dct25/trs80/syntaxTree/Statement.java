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
    
    public void setName(String name);
    public String getName();
    
    public void setNextStatement(Statement next);
    public Statement getNextStatement();
}
