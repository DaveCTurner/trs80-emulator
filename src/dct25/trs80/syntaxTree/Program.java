/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class Program extends beaver.Symbol {
    private LineNumber _lineNumber;
    private Statement _statement;

    public Program (LineNumber lineNumber, Statement statement) {
        super();
        _lineNumber = lineNumber;
        _statement = statement;
    }
    
    public String toString() {
        return "TRS-80 Program";
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

        if (this._statement == null) {
            if (other._statement != null) { return false; }
        } else {
            if (!(this._statement.equals(other._statement))) { return false; }
        }
        
        return true;
    }
}
