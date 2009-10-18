package dct25.trs80.emulator;

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

    public String generateCode(Program program) {
        StringBuilder sb = new StringBuilder();
        sb.append("package "); sb.append(_packageName); sb.append(";\n");
        sb.append("\n");
        sb.append("import dct25.trs80.emulator.Environment;\n");
        sb.append("import dct25.trs80.emulator.Executable;\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append(" * Translation of the following TRS-80 BASIC program into Java:\n");
        sb.append(" * \n");
        sb.append(" * ---\n");
        sb.append(program.asBasic());
        sb.append(" * ---\n");
        sb.append(" */\n");
        sb.append("\n");
        sb.append("public class " + _className + " implements Executable {\n");
        sb.append("\n");
        sb.append("    /**\n");
        sb.append("     * Execute the program.\n");
        sb.append("     * @param env The environment in which to execute the program.\n");
        sb.append("     */\n");
        sb.append("    public void execute(Environment env) {\n");
        sb.append("        env.clearScreen();\n");
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

}
