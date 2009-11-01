package dct25.trs80.emulator;

import org.eclipse.jdt.core.dom.*;

import dct25.trs80.inMemoryCompiler.InMemorySourceCompiler;
import dct25.trs80.syntaxTree.Identifier;
import dct25.trs80.syntaxTree.Program;

public class BasicToJavaCompiler {

    private String _className;
    private String _packageName;
    
    public BasicToJavaCompiler(String className, String packageName) {
        _className = className;
        _packageName = packageName;
    }
    
    public Executable compile(Program program) throws Exception {
        String code = generateCode(program);
        System.out.println(code);
        InMemorySourceCompiler compiler = new InMemorySourceCompiler(_className, code);
        return compiler.instantiate();
    }

    @SuppressWarnings("unchecked")
    public String generateCode(Program program) throws Exception {
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

        program.visit(new SetStatementNameVisitor(new StatementNameGenerator()));
        program.visit(new MatchLoopsVisitor());
        program.visit(new SetNextStatementVisitor());

        NumberedStatementsFinder nsf = new NumberedStatementsFinder();
        program.visit(nsf);
        program.visit(new SetStatementLinksVisitor(nsf));
        
        IdentifierCollectionVisitor icv = new IdentifierCollectionVisitor();
        program.visit(icv);
        Identifier[] ids = icv.getIdentifiers();
        for(int i = 0; i < ids.length; i++) {
            VariableDeclarationFragment vdfEnv = ast.newVariableDeclarationFragment();
            FieldDeclaration fidEnv = ast.newFieldDeclaration(vdfEnv);
            tyProgram.bodyDeclarations().add(fidEnv);
            vdfEnv.setName(ast.newSimpleName(ids[i].toString()));
            fidEnv.setType(ast.newPrimitiveType(PrimitiveType.INT));
        }

        ArrayNameGenerator arrayNameGenerator = new ArrayNameGenerator();

        ArrayIdentifier[] aids = icv.getArrayIdentifiers();
        for(int i = 0; i < aids.length; i++) {
            VariableDeclarationFragment vdfEnv = ast.newVariableDeclarationFragment();
            FieldDeclaration fidEnv = ast.newFieldDeclaration(vdfEnv);
            tyProgram.bodyDeclarations().add(fidEnv);
            vdfEnv.setName(ast.newSimpleName(arrayNameGenerator.buildArrayName(aids[i].id)));
            fidEnv.setType(ast.newArrayType(ast.newPrimitiveType(PrimitiveType.INT), aids[i].dim));
        }

        BasicToJavaCompilerVisitor v = new BasicToJavaCompilerVisitor(ast, tyProgram, nsf, arrayNameGenerator);
        program.visit(v);
        return cu.toString();
    }
}
