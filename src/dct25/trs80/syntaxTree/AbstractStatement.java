package dct25.trs80.syntaxTree;

import java.util.LinkedList;

import beaver.Symbol;

public abstract class AbstractStatement extends Symbol implements Statement {

    private String _name;

    public abstract void visit(SyntaxTreeVisitor v) throws Exception;

    private Statement _nextStatement;

    public String getName() { return _name; }

    public void setName(String name) { _name = name; }

    private LinkedList<Statement> _nextStatements = new LinkedList<Statement>();

    public Statement getNextStatement() { return _nextStatement; }

    public void setNextStatement(Statement next) { _nextStatement = next; }

    private LinkedList<Statement> _precedingStatements = new LinkedList<Statement>();

    public void addNextStatement(Statement next) { _nextStatements.add(next); }

    private static Statement[] arrayFromList(LinkedList<Statement> linkedList) {
        Statement [] rv = new Statement[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++) {
            rv[i] = linkedList.get(i);
        }
        return rv;
    }

    public void addPrecedingStatement(Statement preceding) { _precedingStatements.add(preceding); }

    public Statement[] getNextStatements() { return arrayFromList(_nextStatements); }

    public Statement[] getPrecedingStatements() { return arrayFromList(_precedingStatements); }

    public AbstractStatement() {
        super();
    }

}