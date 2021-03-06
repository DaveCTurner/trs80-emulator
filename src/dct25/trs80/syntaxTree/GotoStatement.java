/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class GotoStatement extends AbstractStatement {
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
        if (null == this.m_target) {
            if (null != other.m_target) { return false; }
        } else {
            if (!this.m_target.equals(other.m_target)) { return false; }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.visitGotoStatement(this);
    }

    public LineNumber getTarget() { return m_target; }
}
