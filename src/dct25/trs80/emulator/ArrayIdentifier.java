/**
 * 
 */
package dct25.trs80.emulator;

import dct25.trs80.syntaxTree.Identifier;

public class ArrayIdentifier {
    public Identifier id;
    public int dim;
    public ArrayIdentifier(Identifier idIn, int dimIn) { id = idIn; dim = dimIn; }
    public int hashCode() { return id.hashCode(); }
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof ArrayIdentifier)) { return false; }

        ArrayIdentifier other = (ArrayIdentifier) o;
        if (null == this.id) {
            if (null != other.id) { return false; }
        } else {
            if (!this.id.equals(other.id)) { return false; }
        }
        if (dim != other.dim) { return false; }


        return true;        
    }
}