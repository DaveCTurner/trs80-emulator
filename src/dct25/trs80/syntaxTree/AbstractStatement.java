package dct25.trs80.syntaxTree;

import java.util.LinkedList;

import dct25.trs80.emulator.StatementNameGenerator;

import beaver.Symbol;

public abstract class AbstractStatement extends Symbol implements Statement {

    public abstract void visit(SyntaxTreeVisitor v) throws Exception;

    private Statement _nextStatement;

    public String getName() { return _ng.getStatementName(_lineNumber, _statementIndex); }
    public String getShortName() { return _lineNumber.toString() + "." + _statementIndex; }

    private LinkedList<Statement> _nextStatements = new LinkedList<Statement>();

    public Statement getNextStatement() { return _nextStatement; }

    public void setNextStatement(Statement next) {
        _nextStatement = next;
        addNextStatement(next);
    }

    private LinkedList<Statement> _precedingStatements = new LinkedList<Statement>();

    public void addNextStatement(Statement next) {
        _nextStatements.add(next);
        next.addPrecedingStatement(this);
    }

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

    private LineNumber _lineNumber;
    private int _statementIndex;
    public void setPosition(LineNumber lineNumber, int statementIndex) {
        _lineNumber = lineNumber;
        _statementIndex = statementIndex;
    }
    
    private StatementNameGenerator _ng;
    public void setNameGenerator(StatementNameGenerator ng) {
        _ng = ng;
    }

    public AbstractStatement() {
        super();
    }

}