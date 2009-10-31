package dct25.trs80.emulator;

import org.eclipse.jdt.core.dom.*;

import dct25.trs80.syntaxTree.*;

public class BasicToJavaCompilerVisitor extends AbstractVisitor {
    private AST _ast;
    private TypeDeclaration _tyProgram;
    
    public BasicToJavaCompilerVisitor(AST ast, TypeDeclaration tyProgram) {
        _ast = ast;
        _tyProgram = tyProgram;
    }
    
    @SuppressWarnings("unchecked")
    public void leaveProgram(Program p) {
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
        jdMedExecuteText.setText("Execute the program");

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
        
        MethodInvocation clsInvocation = _ast.newMethodInvocation();
        org.eclipse.jdt.core.dom.Statement clsStatement = _ast.newExpressionStatement(clsInvocation);
        medExecuteBody.statements().add(clsStatement);
        clsInvocation.setExpression(_ast.newSimpleName("env"));
        clsInvocation.setName(_ast.newSimpleName("clearScreen"));
    }
}
