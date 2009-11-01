package dct25.trs80.syntaxTree;

public class AbstractBooleanExpressionVisitor {

    public void enterConjunction(Conjunction c) throws Exception { }
    public void visitConjunction(Conjunction c) throws Exception { }
    public void leaveConjunction(Conjunction c) throws Exception { }

    public void visitNotEqualsExpression(NotEqualsExpression ne) throws Exception { }
}