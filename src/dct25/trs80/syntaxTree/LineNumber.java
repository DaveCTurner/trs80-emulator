/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class LineNumber extends beaver.Symbol {
    public LineNumber(beaver.Symbol n) {
        super();
        System.out.println("Constructing line number:");
        System.out.println(n);
        if (null != n) {
            System.out.println(n.value);
            System.out.println(n.value.getClass().toString());
        }
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof LineNumber)) { return false; }
               
        return true;
    }
}
