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
public interface Statement {
    public void writeAsBasic(Writer out) throws IOException;
}
