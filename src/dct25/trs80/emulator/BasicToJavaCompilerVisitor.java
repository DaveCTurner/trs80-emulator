package dct25.trs80.emulator;

import java.io.StringWriter;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.StringLiteral;

import dct25.trs80.syntaxTree.*;
import dct25.trs80.syntaxTree.Statement;

public class BasicToJavaCompilerVisitor extends AbstractVisitor {
    private AST _ast;
    private TypeDeclaration _tyProgram;
    private NumberedStatementsFinder _nsf;
    
    public BasicToJavaCompilerVisitor(AST ast, TypeDeclaration tyProgram, NumberedStatementsFinder nsf) {
        _ast = ast;
        _tyProgram = tyProgram;
        _nsf = nsf;
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

        setFallThroughToNextStatement(ps, medExecuteBody);
    }
    
    /** Builds a method for the given statement, and returns its body for population. */
    @SuppressWarnings("unchecked")
    private Block buildMethodForStatement(Statement s) throws Exception {
        MethodDeclaration medExecute = _ast.newMethodDeclaration();
        _tyProgram.bodyDeclarations().add(medExecute);
        medExecute.setName(_ast.newSimpleName(s.getName()));
        medExecute.setReturnType2(_ast.newPrimitiveType(PrimitiveType.VOID));
        medExecute.modifiers().add(_ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        
        Javadoc jdMedExecute = _ast.newJavadoc();
        medExecute.setJavadoc(jdMedExecute);
        TagElement jdMedExecuteTextTag = _ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteTextTag);
        TextElement jdMedExecuteText = _ast.newTextElement();
        jdMedExecuteTextTag.fragments().add(jdMedExecuteText);
        
        StringWriter w = new StringWriter();
        AsBasicVisitor asBasic = new AsBasicVisitor(w);
        s.visit(asBasic);
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
    public void leaveProgram(Program p) {
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
