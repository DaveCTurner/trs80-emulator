package dct25.trs80.emulator;

import dct25.trs80.inMemoryCompiler.InMemorySourceCompiler;
import dct25.trs80.syntaxTree.Program;

public class BasicToJavaCompiler {

    public BasicToJavaCompiler(String className, String packageName) {
        // Do nothing
    }
    
    public Executable compile(Program program) throws Exception {
        InMemorySourceCompiler compiler = new InMemorySourceCompiler("OnTheFlyProgram", generateCode(program));
        return compiler.instantiate();
    }

    public String generateCode(Program program) {
        StringBuilder sb = new StringBuilder();
        sb.append("package dct25.trs80.examplePrograms.onTheFly;\n");
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
        sb.append("public class OnTheFlyProgram implements Executable {\n");
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
