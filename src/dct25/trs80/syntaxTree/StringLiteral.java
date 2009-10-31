/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class StringLiteral extends beaver.Symbol {
    String _text;
    
    public StringLiteral(String text) {
        _text = text;
    }
    
    public StringLiteral(beaver.Symbol n) {
        super();
        _text = new String(n.value.toString());
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof StringLiteral)) { return false; }
        
        StringLiteral other = (StringLiteral) o;
        if (!_text.equals(other._text)) { return false; }
               
        return true;
    }
    
    public int hashCode() {
        return _text.hashCode();
    }
    
    public String toString() {
        return _text;
    }
}
