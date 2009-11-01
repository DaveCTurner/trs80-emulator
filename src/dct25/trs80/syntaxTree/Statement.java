/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public interface Statement {
    public void visit(SyntaxTreeVisitor v) throws Exception;
    
    public void setName(String name);
    public String getName();
    
    public void setNextStatement(Statement next);
    public Statement getNextStatement();
    
    public void addNextStatement(Statement next);
    public Statement[] getNextStatements();
    
    public void addPrecedingStatement(Statement preceding);
    public Statement[] getPrecedingStatements();
}
