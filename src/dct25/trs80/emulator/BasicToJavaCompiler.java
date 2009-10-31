package dct25.trs80.emulator;

import org.eclipse.jdt.core.dom.*;

import dct25.trs80.inMemoryCompiler.InMemorySourceCompiler;
import dct25.trs80.syntaxTree.Program;

public class BasicToJavaCompiler {

    private String _className;
    private String _packageName;
    
    public BasicToJavaCompiler(String className, String packageName) {
        _className = className;
        _packageName = packageName;
    }
    
    public Executable compile(Program program) throws Exception {
        InMemorySourceCompiler compiler = new InMemorySourceCompiler(_className, generateCode(program));
        return compiler.instantiate();
    }

    @SuppressWarnings("unchecked")
    public String generateCode(Program program) {
        AST ast = AST.newAST(AST.JLS3);
        CompilationUnit cu = ast.newCompilationUnit();
        
        PackageDeclaration pkDec = ast.newPackageDeclaration();
        cu.setPackage(pkDec);
        pkDec.setName(ast.newName(_packageName));
        
        ImportDeclaration imEnvironment = ast.newImportDeclaration();
        cu.imports().add(imEnvironment);
        imEnvironment.setName(ast.newName("dct25.trs80.emulator.Environment"));

        ImportDeclaration imExecutable = ast.newImportDeclaration();
        cu.imports().add(imExecutable);
        imExecutable.setName(ast.newName("dct25.trs80.emulator.Executable"));
        
        TypeDeclaration tyProgram = ast.newTypeDeclaration();
        cu.types().add(tyProgram);
        tyProgram.setName(ast.newSimpleName(_className));
        tyProgram.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        tyProgram.superInterfaceTypes().add(ast.newSimpleType(ast.newSimpleName("Executable")));
        
        Javadoc jdProgram = ast.newJavadoc();
        tyProgram.setJavadoc(jdProgram);
        TagElement jdProgramTextTag = ast.newTagElement();
        jdProgram.tags().add(jdProgramTextTag);
        TextElement jdProgramText = ast.newTextElement();
        jdProgramTextTag.fragments().add(jdProgramText);
        jdProgramText.setText("Translation of the following TRS-80 Program into Java:\n---\n"
                + program.asBasic() + "---");
        
        MethodDeclaration medExecute = ast.newMethodDeclaration();
        tyProgram.bodyDeclarations().add(medExecute);
        medExecute.setName(ast.newSimpleName("execute"));
        medExecute.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        medExecute.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        
        Javadoc jdMedExecute = ast.newJavadoc();
        medExecute.setJavadoc(jdMedExecute);
        TagElement jdMedExecuteTextTag = ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteTextTag);
        TextElement jdMedExecuteText = ast.newTextElement();
        jdMedExecuteTextTag.fragments().add(jdMedExecuteText);
        jdMedExecuteText.setText("Execute the program");

        TagElement jdMedExecuteParamTag = ast.newTagElement();
        jdMedExecute.tags().add(jdMedExecuteParamTag);
        jdMedExecuteParamTag.setTagName(TagElement.TAG_PARAM);
        TextElement jdMedExecuteParam = ast.newTextElement();
        jdMedExecuteParamTag.fragments().add(ast.newSimpleName("env"));
        jdMedExecuteParamTag.fragments().add(jdMedExecuteParam);
        jdMedExecuteParam.setText(" The environment in which to execute the program.");
        
        SingleVariableDeclaration medExecuteParam = ast.newSingleVariableDeclaration();
        medExecute.parameters().add(medExecuteParam);
        medExecuteParam.setName(ast.newSimpleName("env"));
        medExecuteParam.setType(ast.newSimpleType(ast.newSimpleName("Environment")));
        
        Block medExecuteBody = ast.newBlock();
        medExecute.setBody(medExecuteBody);
        
        MethodInvocation clsInvocation = ast.newMethodInvocation();
        Statement clsStatement = ast.newExpressionStatement(clsInvocation);
        medExecuteBody.statements().add(clsStatement);
        clsInvocation.setExpression(ast.newSimpleName("env"));
        clsInvocation.setName(ast.newSimpleName("clearScreen"));

        return cu.toString();
    }

}
