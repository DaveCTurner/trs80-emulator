/**
 * 
 */
package dct25.trs80.syntaxTree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import dct25.trs80.emulator.Executable;
import dct25.trs80.inMemoryCompiler.InMemorySourceCompiler;

/**
 * @author dct25
 *
 */
public class Program extends beaver.Symbol {
    private ProgramLine[] _lines;

    public Program (ProgramLine[] lines) {
        super();
        _lines = lines;
        if (null == _lines) { throw new NullPointerException("Lines parameter cannot be null"); }
    }
    
    public String toString() {
        return "TRS-80 Program";
    }

    public String asBasic() {
        Writer out = new StringWriter();
        try {
            writeAsBasic(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    
    public void writeAsBasic(Writer out) throws IOException {
        for (int i = 0; i < _lines.length; i++) {
            _lines[i].writeAsBasic(out);
        }
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
        
        if (this._lines.length != other._lines.length) { return false; }
        for (int i = 0; i < this._lines.length; i++) {
            if (!(this._lines[i].equals(other._lines[i]))) { return false; }
        }
        
        return true;
    }

    public Executable compile() throws Exception {
        InMemorySourceCompiler compiler = new InMemorySourceCompiler("OnTheFlyProgram", asJava());
        return (Executable) compiler.instantiate();
    }
}
