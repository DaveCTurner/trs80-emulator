/**
 * 
 */
package dct25.trs80.syntaxTree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author dct25
 *
 */
public class LineNumber extends beaver.Symbol {
    int _number;
    
    public LineNumber(int n) {
        _number = n;
    }
    
    public LineNumber(beaver.Symbol n) throws Exception {
        super();
        if (!(n.value instanceof java.lang.Integer)) {
            throw new Exception("Non-integer line number");
        }
        
        _number = ((java.lang.Integer)n.value).intValue();
    }
    
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof LineNumber)) { return false; }
        
        LineNumber other = (LineNumber) o;
        if (_number != other._number) { return false; }
               
        return true;
    }
    
    public void writeAsBasic(Writer out) throws IOException {
        out.write(Integer.toString(_number));
    }
    
    public String toString() {
        StringWriter out = new StringWriter();
        try {
            writeAsBasic(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return out.toString();
    }
}
