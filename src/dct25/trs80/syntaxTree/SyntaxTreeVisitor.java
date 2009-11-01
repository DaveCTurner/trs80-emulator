package dct25.trs80.syntaxTree;

public class SyntaxTreeVisitor {

    public void enterProgram(Program p) throws Exception { }
    public void leaveProgram(Program p) throws Exception { }
    
    public void enterProgramLine(ProgramLine pl) throws Exception { }
    public void leaveProgramLine(ProgramLine pl) throws Exception { }

    /* Generic statement visitor */ 
    protected void visitStatement(Statement s) throws Exception { }

    /* CLS is terminal */
    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception {
        visitStatement(cls);
    }

    /* GOTO is terminal */
    public void visitGotoStatement(GotoStatement gs) throws Exception {
        visitStatement(gs);
    }
    
    /* ON ? GOTO ?, ? has one subcomponent: the expression */
    public void enterOnGotoStatement(OnGotoStatement ogs) throws Exception {
        visitStatement(ogs);
    }
    public void leaveOnGotoStatement(OnGotoStatement ogs) throws Exception { }
    
    /* END is terminal */
    public void visitEndStatement(EndStatement es) throws Exception { 
        visitStatement(es);
    }

    /* PRINT is terminal */
    public void visitPrintStatement(PrintStatement ps) throws Exception {
        visitStatement(ps);
    }
    
    /* FOR has two subcomponents: the expression for the lower bound, and the expression for the upper bound */
    public void enterForStatement(ForStatement statement) throws Exception { }
    public void visitedLowerBoundInForStatement(ForStatement statement) throws Exception { }
    public void visitedUpperBoundInForStatement(ForStatement statement) throws Exception { }
    public void leaveForStatement(ForStatement statement) throws Exception {
        visitStatement(statement);
    }
    
    /* NEXT is terminal */
    public void visitNextStatement(NextStatement ns) throws Exception {
        visitStatement(ns);
    }

    /* INPUT is terminal */
    public void visitInputStatement(InputStatement is) throws Exception {
        visitStatement(is);
    }

    /* IF has one subcomponent: the boolean expression */
    public void enterIfStatement(IfStatement is) throws Exception { }
    public void visitedIfStatementCondition(IfStatement is) throws Exception { }
    public void leaveIfStatement(IfStatement is) throws Exception {
        visitStatement(is);
    }
    
    /* Integer literals are terminal */
    public void visitIntegerLiteralExpression(IntegerLiteralExpression i) throws Exception { }

    /* Integer identifiers are terminal */
    public void visitIntegerIdentifierExpression(IntegerIdentifierExpression ii) throws Exception { }
    
    /* Conjunctions have two subcomponents: the two subsidiary boolean expressions */
    public void enterConjunction(Conjunction c) throws Exception { }
    public void visitedLeftOperandOfConjunction(Conjunction c) throws Exception { }
    public void visitedRightOperandOfConjunction(Conjunction c) throws Exception { }
    public void leaveConjunction(Conjunction c) throws Exception { }

    /* NotEqualsExpressions have two subcomponents: the two subsidiary integer expressions */
    public void enterNotEqualsExpression(NotEqualsExpression ne) throws Exception { }
    public void visitedLeftOperandOfNotEqualsExpression(NotEqualsExpression ne) throws Exception { }
    public void visitedRightOperandOfNotEqualsExpression(NotEqualsExpression ne) throws Exception { }
    public void leaveNotEqualsExpression(NotEqualsExpression ne) throws Exception { }

    /* EqualsExpressions have two subcomponents: the two subsidiary integer expressions */
    public void enterEqualsExpression(EqualsExpression ee) throws Exception { }
    public void visitedLeftOperandOfEqualsExpression(EqualsExpression ee) throws Exception { }
    public void visitedRightOperandOfEqualsExpression(EqualsExpression ee) throws Exception { }
    public void leaveEqualsExpression(EqualsExpression ee) throws Exception { }

    /* LessThanExpressions have two subcomponents: the two subsidiary integer expressions */
    public void enterLessThanExpression(LessThanExpression lte) throws Exception { }
    public void visitedLeftOperandOfLessThanExpression(LessThanExpression lte) throws Exception { }
    public void visitedRightOperandOfLessThanExpression(LessThanExpression lte) throws Exception { }
    public void leaveLessThanExpression(LessThanExpression lte) throws Exception { }

    /* IntegerSumExpressions have two subcomponents: the two subsidiary integer expressions */
    public void enterIntegerSumExpression(IntegerSumExpression ise) throws Exception { }
    public void visitedLeftOperandOfIntegerSum(IntegerSumExpression ise) throws Exception { }
    public void visitedRightOperandOfIntegerSum(IntegerSumExpression ise) throws Exception { }
    public void leaveIntegerSumExpression(IntegerSumExpression ise) throws Exception { }
    
    /* IntegerDifferenceExpression have two subcomponents: the two subsidiary integer expressions */
    public void enterIntegerDifferenceExpression(IntegerDifferenceExpression ide) throws Exception { }
    public void visitedLeftOperandOfIntegerDifference(IntegerDifferenceExpression ide) throws Exception { }
    public void visitedRightOperandOfIntegerDifference(IntegerDifferenceExpression ide) throws Exception { }
    public void leaveIntegerDifferenceExpression(IntegerDifferenceExpression ide) throws Exception { }
    
    /* IntegerProductExpression have two subcomponents: the two subsidiary integer expressions */
    public void enterIntegerProductExpression(IntegerProductExpression ipe) throws Exception { }
    public void visitedLeftOperandOfIntegerProduct(IntegerProductExpression ipe) throws Exception { }
    public void visitedRightOperandOfIntegerProduct(IntegerProductExpression ipe) throws Exception { }
    public void leaveIntegerProductExpression(IntegerProductExpression ipe) throws Exception { }
    
    public void enterArrayElement(ArrayElement element) throws Exception { }
    public void visitingArrayElementSubscript(ArrayElement element, int dimensionIndex) throws Exception { }
    public void visitedArrayElementSubscript(ArrayElement element, int dimensionIndex) throws Exception { }
    public void leaveArrayElement(ArrayElement element) throws Exception { }
    public void leaveArrayDeclaration(ArrayElement element) throws Exception { leaveArrayElement(element); }

    public void enterArrayElementExpression(ArrayElementExpression ae) throws Exception { }
    public void leaveArrayElementExpression(ArrayElementExpression ae) throws Exception { }
    
    /* Dim statements have one subcomponent for each array declaration */
    public void enterDimStatement(DimStatement statement) throws Exception { 
        visitStatement(statement);
    }
    public void visitingDimStatementArray(DimStatement statement, int dimensionIndex) throws Exception { }
    public void visitedDimStatementArray(DimStatement statement, int dimensionIndex) throws Exception { }
    public void leaveDimStatement(DimStatement statement) throws Exception { }
    
    /* Assignments have one subcomponent: the value expression */
    public void enterScalarAssignmentStatement(ScalarAssignmentStatement statement) throws Exception {
        visitStatement(statement);
    }
    public void leaveScalarAssignmentStatement(ScalarAssignmentStatement statement) throws Exception { }
    
    /* Array assignments have two subcomponent: the array element and the value expression */
    public void enterArrayAssignmentStatement(ArrayAssignmentStatement statement) throws Exception {
        visitStatement(statement);
    }
    public void visitedArrayAssignee(ArrayAssignmentStatement statement) throws Exception { }
    public void leaveArrayAssignmentStatement(ArrayAssignmentStatement statement) throws Exception { }
    
    /* Random number expressions have one subcomponent: the argument */
    public void enterRandomNumberExpression(RandomNumberExpression rne) throws Exception { }
    public void leaveRandomNumberExpression(RandomNumberExpression rne) throws Exception { }

}
