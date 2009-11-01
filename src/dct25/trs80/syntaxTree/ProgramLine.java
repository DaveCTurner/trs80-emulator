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
public class ProgramLine extends beaver.Symbol {
    private LineNumber _lineNumber;
    private Statement[] _statements;

    public ProgramLine (LineNumber lineNumber, Statement[] statements) {
        super();
        _lineNumber = lineNumber;
        _statements = statements;
        if (null == _statements) { throw new NullPointerException("Statements parameter cannot be null"); }
    }
    
    public LineNumber getLineNumber() { return _lineNumber; }
    
    public String toString() {
        try {
            Writer out = new StringWriter();
            out.write("TRS-80 Program Line: '");
            AsBasicVisitor v = new AsBasicVisitor(out);
            visit(v);
            out.write("'");
            return out.toString();
        }
        catch (Exception e) {
            return "An exception occurred in ProgramLine.toString(): " + e.getMessage();
        }
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ProgramLine)) { return false; }
        ProgramLine other = (ProgramLine) o;
        
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

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterProgramLine(this);
        for (int i = 0; i < _statements.length; i++) {
            _statements[i].visit(v);
        }
        v.leaveProgramLine(this);
    }
}
