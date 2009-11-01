/**
 * 
 */
package dct25.trs80.syntaxTree;

import java.io.StringWriter;
import java.io.Writer;

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
        return asBasic();
    }

    public String asBasic() {
        Writer out = new StringWriter();
        try {
            AsBasicVisitor v = new AsBasicVisitor(out);
            visit(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
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
    
    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterProgram(this);
        for (int i = 0; i < _lines.length; i++) {
            _lines[i].visit(v);
        }
        v.leaveProgram(this);
    }
}
