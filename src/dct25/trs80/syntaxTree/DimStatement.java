/**
 * 
 */
package dct25.trs80.syntaxTree;

/**
 * @author dct25
 *
 */
public class DimStatement extends AbstractStatement {
    ArrayElement[] _arrays;
    
    public DimStatement(ArrayElement ar1, ArrayElement ar2) {
        super();
        _arrays = new ArrayElement[] { ar1, ar2 };
        for (int i = 0; i < _arrays.length; i++) { _arrays[i].setDeclaration(); }
    }
    
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (!(o instanceof DimStatement)) { return false; }
        
        DimStatement other = (DimStatement)o;
        for (int i = 0; i < _arrays.length; i++) {
            if (null == this._arrays[i]) {
                if (null != other._arrays[i]) { return false; }
            } else {
                if (!this._arrays[i].equals(other._arrays[i])) { return false; }
            }
        }
               
        return true;
    }

    public void visit(SyntaxTreeVisitor v) throws Exception {
        v.enterDimStatement(this);
        for (int i = 0; i < _arrays.length; i++) {
            v.visitingDimStatementArray(this, i);
            _arrays[i].visit(v);
            v.visitedDimStatementArray(this, i);
        }
        v.leaveDimStatement(this);
    }

    public ArrayElement getArray(int i) { return _arrays[i]; }
}
