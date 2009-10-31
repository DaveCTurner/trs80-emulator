/**
 * 
 */
package dct25.trs80.syntaxTree;

import java.io.IOException;
import java.io.Writer;

/**
 * @author dct25
 *
 */
public class GotoStatement extends beaver.Symbol implements Statement {
    LineNumber m_target;
    
    public GotoStatement(LineNumber target) {
        super();
        m_target = target;
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof GotoStatement)) { return false; }
        
        GotoStatement other = (GotoStatement)o; 
        if (!other.m_target.equals(this.m_target)) { return false; }
               
        return true;
    }

    public void writeAsBasic(Writer out) throws IOException {
        out.write("GOTO ");
        m_target.writeAsBasic(out);
    }
}
