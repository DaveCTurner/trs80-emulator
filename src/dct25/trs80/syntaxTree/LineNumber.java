/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class LineNumber extends beaver.Symbol {
    IntegerLiteral _number;
    
    public LineNumber(int n) {
        _number = new IntegerLiteral(n);
    }
    
    public LineNumber(IntegerLiteral n) {
        super();
        _number = n;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof LineNumber)) { return false; }
        
        LineNumber other = (LineNumber) o;
        if (null == _number) {
            if (null != other._number) { return false; }
        } else {
            if (!_number.equals(other._number)) { return false; }
        }
               
        return true;
    }
    
    public int hashCode() {
        return _number.hashCode();
    }
    
    public String toString() {
        return _number.toString();
    }
}
