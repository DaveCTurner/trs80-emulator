package dct25.trs80.emulator;

import java.io.StringWriter;
import java.util.Stack;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.StringLiteral;

import dct25.trs80.syntaxTree.*;
import dct25.trs80.syntaxTree.ForStatement;
import dct25.trs80.syntaxTree.IfStatement;
import dct25.trs80.syntaxTree.Statement;

public class BasicToJavaCompilerVisitor extends SyntaxTreeVisitor {
    private AST _ast;
    private TypeDeclaration _tyProgram;
    private NumberedStatementsFinder _nsf;
    private ArrayNameGenerator _ang;
    
    public BasicToJavaCompilerVisitor(AST ast, TypeDeclaration tyProgram, NumberedStatementsFinder nsf,
            ArrayNameGenerator ang) {
        _ast = ast;
        _tyProgram = tyProgram;
        _nsf = nsf;
        _ang = ang;
    }
    
    @SuppressWarnings("unchecked")
    public void visitClearScreenStatement(ClearScreenStatement cls) throws Exception {
        Block medExecuteBody = buildMethodForStatement(cls);
        
        MethodInvocation clsInvocation = _ast.newMethodInvocation();
        medExecuteBody.statements().add(_ast.newExpressionStatement(clsInvocation));
        clsInvocation.setExpression(_ast.newSimpleName("_env"));
        clsInvocation.setName(_ast.newSimpleName("clearScreen"));

        setFallThroughToNextStatement(cls, medExecuteBody);
    }
  
    @SuppressWarnings("unchecked")
    public void visitGotoStatement(GotoStatement gs) throws Exception {
        Block medExecuteBody = buildMethodForStatement(gs);
        
        MethodInvocation gotoStatementInvocation = _ast.newMethodInvocation();
        medExecuteBody.statements().add(_ast.newExpressionStatement(gotoStatementInvocation));
        gotoStatementInvocation.setName(_ast.newSimpleName(_nsf.getNumberedStatement(gs.getTarget()).getName()));

        setFallThroughToNextStatement(gs, medExecuteBody);
    }

    
    @SuppressWarnings("unchecked")
    public void visitEndStatement(EndStatement es) throws Exception {
        Block medExecuteBody = buildMethodForStatement(es);

        setFallThroughToNextStatement(es, medExecuteBody);
    }
    
    @SuppressWarnings("unchecked")
    public void visitPrintStatement(PrintStatement ps) throws Exception {
        Block medExecuteBody = buildMethodForStatement(ps);
        
        MethodInvocation printInvocation = _ast.newMethodInvocation();
        medExecuteBody.statements().add(_ast.newExpressionStatement(printInvocation));
        printInvocation.setExpression(_ast.newSimpleName("_env"));
        printInvocation.setName(_ast.newSimpleName("print"));
        
        StringLiteral sl = _ast.newStringLiteral();
        printInvocation.arguments().add(sl);
        sl.setLiteralValue(ps.getText().toString());

        printInvocation.arguments().add(_ast.newBooleanLiteral(ps.getNewLine()));

        setFallThroughToNextStatement(ps, medExecuteBody);
    }
    
    @SuppressWarnings("unchecked")
    public void visitNextStatement(NextStatement ns) throws Exception {
        Block medExecuteBody = buildMethodForStatement(ns);
        
        Assignment incStatement = _ast.newAssignment();
        medExecuteBody.statements().add(_ast.newExpressionStatement(incStatement));
        incStatement.setLeftHandSide(_ast.newSimpleName(ns.getLoopStartStatement().getLoopVariableIdentifier().toString()));
        incStatement.setRightHandSide(_ast.newNumberLiteral("1"));
        incStatement.setOperator(Assignment.Operator.PLUS_ASSIGN);
        
        org.eclipse.jdt.core.dom.IfStatement ifStatement = _ast.newIfStatement();
        medExecuteBody.statements().add(ifStatement);
        
        Block elseBlock = _ast.newBlock();
        ifStatement.setElseStatement(elseBlock);
        MethodInvocation startAgainInvocation = _ast.newMethodInvocation();
        startAgainInvocation.setName(_ast.newSimpleName(ns.getLoopStartStatement().getNextStatement().getName()));
        elseBlock.statements().add(_ast.newExpressionStatement(startAgainInvocation));
        
        InfixExpression condition = _ast.newInfixExpression();
        ifStatement.setExpression(condition);
        condition.setLeftOperand(_ast.newSimpleName(ns.getLoopStartStatement().getLoopVariableIdentifier().toString()));
        condition.setRightOperand(parenthesize(_expressionStack.pop()));
        condition.setOperator(InfixExpression.Operator.GREATER);

        Block thenBlock = _ast.newBlock();
        ifStatement.setThenStatement(thenBlock);
        setFallThroughToNextStatement(ns, thenBlock);
    }

    private Assignment _currentForStatementLowerBoundAssignment;
    private Stack<Expression> _expressionStack = new Stack<Expression>();
    
    @SuppressWarnings("unchecked")
    public void enterForStatement(ForStatement fs) throws Exception {
        Block medExecuteBody = buildMethodForStatement(fs);

        Assignment envAssign = _ast.newAssignment();
        medExecuteBody.statements().add(_ast.newExpressionStatement(envAssign));
        envAssign.setLeftHandSide(_ast.newSimpleName(fs.getLoopVariableIdentifier().toString()));
        _currentForStatementLowerBoundAssignment = envAssign;
        
        setFallThroughToNextStatement(fs, medExecuteBody);
    }

    public void visitedLowerBoundInForStatement(ForStatement fs) throws Exception {
        _currentForStatementLowerBoundAssignment.setRightHandSide(parenthesize(_currentExpression));
    }

    public void visitedUpperBoundInForStatement(ForStatement fs) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    
    @SuppressWarnings("unchecked")
    public void leaveOnGotoStatement(OnGotoStatement ogs) throws Exception {
        Block medExecuteBody = buildMethodForStatement(ogs);
        
        SwitchStatement ss = _ast.newSwitchStatement();
        medExecuteBody.statements().add(ss);
        ss.setExpression(_currentExpression);
        
        for (int i = 1; i <= ogs.getTargetCount(); i++) {
            SwitchCase sc = _ast.newSwitchCase();
            sc.setExpression(_ast.newNumberLiteral(Integer.toString(i)));
            ss.statements().add(sc);

            MethodInvocation targetInvocation = _ast.newMethodInvocation();
            targetInvocation.setName(_ast.newSimpleName(_nsf.getNumberedStatement(ogs.getTarget(i)).getName()));
            ss.statements().add(_ast.newExpressionStatement(targetInvocation));
            ss.statements().add(_ast.newBreakStatement());
        }

        setFallThroughToNextStatement(ogs, medExecuteBody);
    }
    
    @SuppressWarnings("unchecked")
    public void visitInputStatement(InputStatement is) throws Exception {
        Block medExecuteBody = buildMethodForStatement(is);
        
        MethodInvocation printInvocation = _ast.newMethodInvocation();
        medExecuteBody.statements().add(_ast.newExpressionStatement(printInvocation));
        printInvocation.setExpression(_ast.newSimpleName("_env"));
        printInvocation.setName(_ast.newSimpleName("print"));
        
        StringLiteral sl = _ast.newStringLiteral();
        printInvocation.arguments().add(sl);
        sl.setLiteralValue(is.getPrompt().toString());
        
        printInvocation.arguments().add(_ast.newBooleanLiteral(true));
        
        Identifier[] identifiers = new Identifier[] { is.getIdentifier1(), is.getIdentifier2() };
        
        for (int i = 0; i < identifiers.length; i++) {
            Assignment idAssign = _ast.newAssignment();
            medExecuteBody.statements().add(_ast.newExpressionStatement(idAssign));
            idAssign.setLeftHandSide(_ast.newSimpleName(identifiers[i].toString()));
            MethodInvocation getInvocation = _ast.newMethodInvocation();
            idAssign.setRightHandSide(getInvocation);
            getInvocation.setExpression(_ast.newSimpleName("_env"));
            getInvocation.setName(_ast.newSimpleName("getInput"));
        }

        setFallThroughToNextStatement(is, medExecuteBody);
    }
    
    
    private Expression _currentExpression;
    
    private Expression parenthesize(Expression e) {
        ParenthesizedExpression pe = _ast.newParenthesizedExpression();
        pe.setExpression(e);
        return pe;
    }
    
    @SuppressWarnings("unchecked")
    public void leaveRandomNumberExpression(RandomNumberExpression e) {
        MethodInvocation randomInvocation = _ast.newMethodInvocation();
        randomInvocation.setExpression(_ast.newSimpleName("_env"));
        randomInvocation.setName(_ast.newSimpleName("getNextRandomNumber"));
        randomInvocation.arguments().add(_currentExpression);
        _currentExpression = randomInvocation;
    }
    
    public void visitIntegerLiteralExpression(IntegerLiteralExpression i) {
        _currentExpression = _ast.newNumberLiteral(i.toString());
    }

    public void visitIntegerIdentifierExpression(IntegerIdentifierExpression ii) {
        _currentExpression = _ast.newName(ii.toString());
    }

    private org.eclipse.jdt.core.dom.IfStatement _currentIfStatement;

    @SuppressWarnings("unchecked")
    public void enterIfStatement(IfStatement is) throws Exception {
        Block medExecuteBody = buildMethodForStatement(is);
        
        org.eclipse.jdt.core.dom.IfStatement ifStatement = _ast.newIfStatement();
        medExecuteBody.statements().add(ifStatement);
        _currentIfStatement = ifStatement;
        
        Block thenBlock = _ast.newBlock();
        ifStatement.setThenStatement(thenBlock);
        MethodInvocation gotoStatementInvocation = _ast.newMethodInvocation();
        gotoStatementInvocation.setName(_ast.newSimpleName(_nsf.getNumberedStatement(is.getTarget()).getName()));
        thenBlock.statements().add(_ast.newExpressionStatement(gotoStatementInvocation));
        
        Block elseBlock = _ast.newBlock();
        ifStatement.setElseStatement(elseBlock);

        setFallThroughToNextStatement(is, elseBlock);
    }
    
    public void visitedIfStatementCondition(IfStatement is) throws Exception {
        _currentIfStatement.setExpression(parenthesize(_currentExpression));
    }
    
    public void visitedLeftOperandOfNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfNotEqualsExpression(NotEqualsExpression ne) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.NOT_EQUALS);
        _currentExpression = e;
    }
    
    public void visitedLeftOperandOfLessThanExpression(LessThanExpression lte) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfLessThanExpression(LessThanExpression lte) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.LESS);
        _currentExpression = e;
    }
    
    public void visitedLeftOperandOfIntegerSum(IntegerSumExpression ise) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfIntegerSum(IntegerSumExpression ise) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.PLUS);
        _currentExpression = e;
    }
    
    public void visitedLeftOperandOfIntegerDifference(IntegerDifferenceExpression ide) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfIntegerDifference(IntegerDifferenceExpression ide) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.MINUS);
        _currentExpression = e;
    }
    
    public void visitedLeftOperandOfIntegerProduct(IntegerProductExpression ipe) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfIntegerProduct(IntegerProductExpression ipe) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.TIMES);
        _currentExpression = e;
    }
    
    
    public void visitedLeftOperandOfEqualsExpression(EqualsExpression ee) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfEqualsExpression(EqualsExpression ee) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.EQUALS);
        _currentExpression = e;
    }
    
    public void visitedLeftOperandOfConjunction(Conjunction c) throws Exception {
        _expressionStack.push(_currentExpression);
    }

    public void visitedRightOperandOfConjunction(Conjunction c) throws Exception {
        InfixExpression e = _ast.newInfixExpression();
        e.setLeftOperand(parenthesize(_expressionStack.pop()));
        e.setRightOperand(parenthesize(_currentExpression));
        e.setOperator(InfixExpression.Operator.CONDITIONAL_AND);
        _currentExpression = e;
    }
    
    @SuppressWarnings("unchecked")
    public void leaveScalarAssignmentStatement(ScalarAssignmentStatement as) throws Exception {
        Block medExecuteBody = buildMethodForStatement(as);

        Assignment currentAssignmentStatement = _ast.newAssignment();
        medExecuteBody.statements().add(_ast.newExpressionStatement(currentAssignmentStatement));
        currentAssignmentStatement.setLeftHandSide(_ast.newSimpleName(as.getAssignee().toString()));
        currentAssignmentStatement.setRightHandSide(parenthesize(_currentExpression));

        setFallThroughToNextStatement(as, medExecuteBody);
    }
    
    public void visitedArrayElementSubscript(ArrayElement ae, int dimensionIndex) throws Exception {
        _expressionStack.push(_currentExpression);
    }
    
    public void leaveArrayElement(ArrayElement ae) throws Exception {
        Expression e = _ast.newSimpleName(_ang.buildArrayName(ae.getIdentifier()));
        Expression[] subscripts = new Expression[ae.getDimensionCount()];
        
        for (int i = ae.getDimensionCount() - 1; i >= 0; i--) {
            subscripts[i] = _expressionStack.pop();
        }
        for (int i = 0; i < ae.getDimensionCount(); i++) {
            ArrayAccess e2 = _ast.newArrayAccess();
            e2.setArray(e);
            e2.setIndex(parenthesize(subscripts[i]));
            e = e2;
        }
        _currentExpression = e;
    }
    
    public void visitedArrayAssignee(ArrayAssignmentStatement aas) throws Exception {
        _expressionStack.push(_currentExpression);
    }
    
    @SuppressWarnings("unchecked")
    public void leaveArrayAssignmentStatement(ArrayAssignmentStatement aas) throws Exception {
        Block blk = buildMethodForStatement(aas);
        
        Assignment assign = _ast.newAssignment();
        blk.statements().add(_ast.newExpressionStatement(assign));
        assign.setLeftHandSide(_expressionStack.pop());
        assign.setRightHandSide(parenthesize(_currentExpression));
        
        setFallThroughToNextStatement(aas, blk);
    }

    public void enterDimStatement(DimStatement ds) throws Exception {
        _currentDimStatementBlock = buildMethodForStatement(ds);
    }
    
    private Block _currentArrayDeclarationBlock;
    @SuppressWarnings("unchecked")
    public void leaveArrayDeclaration(ArrayElement ae) throws Exception {
        _currentArrayDeclarationBlock = _ast.newBlock();
        
        Assignment assign = _ast.newAssignment();
        _currentArrayDeclarationBlock.statements().add(_ast.newExpressionStatement(assign));
        assign.setLeftHandSide(_ast.newSimpleName(_ang.buildArrayName(ae.getIdentifier())));
        
        ArrayCreation creation = _ast.newArrayCreation();
        assign.setRightHandSide(creation);
        
        Expression[] subscripts = new Expression[ae.getDimensionCount()];
        for (int i = ae.getDimensionCount() - 1; i >= 0; i--) {
            subscripts[i] = _expressionStack.pop();
        }
        for (int i = 0; i < ae.getDimensionCount(); i++) {
            creation.dimensions().add(subscripts[i]);
        }
        creation.setType(_ast.newArrayType(_ast.newPrimitiveType(PrimitiveType.INT), ae.getDimensionCount()));
    }
    
    private Block _currentDimStatementBlock;
    @SuppressWarnings("unchecked")
    public void visitedDimStatementArray(DimStatement ds, int arrayNumber) throws Exception {
        _currentDimStatementBlock.statements().add(_currentArrayDeclarationBlock);
    }
    
    public void leaveDimStatement(DimStatement ds) throws Exception {
        setFallThroughToNextStatement(ds, _currentDimStatementBlock);
    }
    
    /** Builds a method for the given statement, and returns its body for population. */
    @SuppressWarnings("unchecked")
    private Block buildMethodForStatement(Statement s) throws Exception {
        MethodDeclaration medExecute = _ast.newMethodDeclaration();
        _tyProgram.bodyDeclarations().add(medExecute);
        medExecute.setName(_ast.newSimpleName(s.getName()));
        medExecute.setReturnType2(_ast.newPrimitiveType(PrimitiveType.VOID));
        medExecute.modifiers().add(_ast.newModifier(Modifier.ModifierKeyword.PRIVATE_KEYWORD));
        
        Javadoc jdMedExecute = _ast.newJavadoc();
        medExecute.setJavadoc(jdMedExecute);
        TagElement jdMedExecuteTextTag = _ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteTextTag);
        TextElement jdMedExecuteText = _ast.newTextElement();
        jdMedExecuteTextTag.fragments().add(jdMedExecuteText);
        
        StringWriter w = new StringWriter();
        Statement [] precedingStatements = s.getPrecedingStatements();
        for (int i = 0; i < precedingStatements.length; i++) {
            w.write(" ");
            w.write(precedingStatements[i].getName());
        }
        w.write(" -> ");
        AsBasicVisitor asBasic = new AsBasicVisitor(w);
        s.visit(asBasic);
        w.write(" -> ");
        Statement [] nextStatements = s.getNextStatements();
        for (int i = 0; i < nextStatements.length; i++) {
            w.write(" ");
            w.write(nextStatements[i].getName());
        }
        jdMedExecuteText.setText(w.toString());

        Block medExecuteBody = _ast.newBlock();
        medExecute.setBody(medExecuteBody);
        return medExecuteBody;
    }

    /** If execution after statement 's' falls through, then set up the fallthrough call here. */
    @SuppressWarnings("unchecked")
    private void setFallThroughToNextStatement(Statement s, Block medExecuteBody) {
        Statement nextStatement = s.getNextStatement();
        if (null != nextStatement) {
            MethodInvocation nextStatementInvocation = _ast.newMethodInvocation();
            medExecuteBody.statements().add(_ast.newExpressionStatement(nextStatementInvocation));
            nextStatementInvocation.setName(_ast.newSimpleName(nextStatement.getName()));
        }
    }
  
    @SuppressWarnings("unchecked")
    public void enterProgram(Program p) {
        VariableDeclarationFragment vdfEnv = _ast.newVariableDeclarationFragment();
        FieldDeclaration fidEnv = _ast.newFieldDeclaration(vdfEnv);
        _tyProgram.bodyDeclarations().add(fidEnv);
        vdfEnv.setName(_ast.newSimpleName("_env"));
        fidEnv.setType(_ast.newSimpleType(_ast.newSimpleName("Environment")));
        
        MethodDeclaration medExecute = _ast.newMethodDeclaration();
        _tyProgram.bodyDeclarations().add(medExecute);
        medExecute.setName(_ast.newSimpleName("execute"));
        medExecute.setReturnType2(_ast.newPrimitiveType(PrimitiveType.VOID));
        medExecute.modifiers().add(_ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        
        Javadoc jdMedExecute = _ast.newJavadoc();
        medExecute.setJavadoc(jdMedExecute);
        TagElement jdMedExecuteTextTag = _ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteTextTag);
        TextElement jdMedExecuteText = _ast.newTextElement();
        jdMedExecuteTextTag.fragments().add(jdMedExecuteText);
        jdMedExecuteText.setText("Execute the program (call " 
                + _nsf.getNumberedStatement(_nsf.getEntryLine()).getName() + ")");

        TagElement jdMedExecuteParamTag = _ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteParamTag);
        jdMedExecuteParamTag.setTagName(TagElement.TAG_PARAM);
        TextElement jdMedExecuteParam = _ast.newTextElement();
        jdMedExecuteParamTag.fragments().add(_ast.newSimpleName("env"));
        jdMedExecuteParamTag.fragments().add(jdMedExecuteParam);
        jdMedExecuteParam.setText(" The environment in which to execute the program.");
        
        SingleVariableDeclaration medExecuteParam = _ast.newSingleVariableDeclaration();
        medExecute.parameters().add(medExecuteParam);
        medExecuteParam.setName(_ast.newSimpleName("env"));
        medExecuteParam.setType(_ast.newSimpleType(_ast.newSimpleName("Environment")));
        
        Block medExecuteBody = _ast.newBlock();
        medExecute.setBody(medExecuteBody);

        Assignment envAssign = _ast.newAssignment();
        medExecuteBody.statements().add(_ast.newExpressionStatement(envAssign));
        envAssign.setLeftHandSide(_ast.newSimpleName("_env"));
        envAssign.setRightHandSide(_ast.newSimpleName("env"));
        
        MethodInvocation entryPointInvocation = _ast.newMethodInvocation();
        medExecuteBody.statements().add(_ast.newExpressionStatement(entryPointInvocation));
        entryPointInvocation.setName(_ast.newSimpleName(_nsf.getNumberedStatement(_nsf.getEntryLine()).getName()));
    }
}
