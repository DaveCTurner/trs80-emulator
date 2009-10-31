/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class Identifier extends beaver.Symbol {
    String _name;
    
    public Identifier(String text) {
        _name = text;
    }
    
    public Identifier(beaver.Symbol n) {
        super();
        _name = new String(n.value.toString());
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof Identifier)) { return false; }
        
        Identifier other = (Identifier) o;
        if (!_name.equals(other._name)) { return false; }
               
        return true;
    }
    
    public int hashCode() {
        return _name.hashCode();
    }
    
    public String toString() {
        return _name;
    }
}
