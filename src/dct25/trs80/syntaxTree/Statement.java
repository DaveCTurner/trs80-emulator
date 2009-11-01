/**
 * 
 */
package dct25.trs80.syntaxTree;

import dct25.trs80.emulator.StatementNameGenerator;

/**
 * @author dct25
 *
 */
public interface Statement {
    public void visit(SyntaxTreeVisitor v) throws Exception;
    
    public String getName();
    public String getShortName();
    
    public void setNextStatement(Statement next);
    public Statement getNextStatement();
    
    public void addNextStatement(Statement next);
    public Statement[] getNextStatements();
    
    public void addPrecedingStatement(Statement preceding);
    public Statement[] getPrecedingStatements();

    public void setPosition(LineNumber lineNumber, int statementIndex);
    public void setNameGenerator(StatementNameGenerator ng);
}
