/**
 * 
 */
package dct25.trs80.syntaxTree;

import beaver.Symbol;
import dct25.trs80.emulator.Executable;
import dct25.trs80.inMemoryCompiler.InMemorySourceCompiler;

/**
 * @author dct25
 *
 */
public class Program extends beaver.Symbol {
    private LineNumber _lineNumber;
    private Statement[] _statements;

    public Program (LineNumber lineNumber, Statement[] statements) {
        super();
        _lineNumber = lineNumber;
        _statements = statements;
        if (null == _statements) { throw new NullPointerException("Statements parameter cannot be null"); }
    }
    
    public String toString() {
        return "TRS-80 Program";
    }
    
    public String asBasic() {
        StringBuilder sb = new StringBuilder();
        sb.append("10 CLS\n");
        return sb.toString();
    }
    
    public String asJava() {
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
        sb.append(asBasic());
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
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof Program)) { return false; }
        Program other = (Program) o;
        
        if (this._lineNumber == null) {
            if (other._lineNumber != null) { return false; }
        } else {
            if (!(this._lineNumber.equals(other._lineNumber))) { return false; }
        }

        if (this._statements.length != other._statements.length) { return false; }
        for (int i = 0; i < this._statements.length; i++) {
            if (!(this._statements[i].equals(other._statements[i]))) { return false; }
        }
        
        return true;
    }

    public Executable compile() throws Exception {
        InMemorySourceCompiler compiler = new InMemorySourceCompiler("OnTheFlyProgram", asJava());
        return (Executable) compiler.instantiate();
    }
}
