/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class IntegerLiteral extends beaver.Symbol {
    Integer _value;
    
    public IntegerLiteral(int value) {
        _value = value;
    }
    
    public IntegerLiteral(beaver.Symbol n) {
        super();
        if (n.value instanceof Integer) {
            _value = (Integer) n.value;
        } else  {
            try {
                _value = new Integer(n.value.toString());
            } catch (Exception e) {
                _value = null;
            }
        }
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof IntegerLiteral)) { return false; }
        
        IntegerLiteral other = (IntegerLiteral) o;
        if (!_value.equals(other._value)) { return false; }
               
        return true;
    }
    
    public int hashCode() {
        return _value.hashCode();
    }
    
    public String toString() {
        return _value.toString();
    }
}
