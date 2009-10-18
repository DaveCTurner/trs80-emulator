/**
 * 
 */
package dct25.trs80;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import dct25.trs80.syntax.TRS80Parser;
import dct25.trs80.syntax.TRS80Scanner;
import dct25.trs80.syntaxTree.ClearScreenStatement;
import dct25.trs80.syntaxTree.LineNumber;
import dct25.trs80.syntaxTree.Program;

/**
 * @author dct25
 * 
 */
public class SyntaxError {
    
    @org.junit.Test
    public void shouldHandleSyntaxError() throws beaver.Parser.Exception, IOException {
        Reader input = new StringReader("CLS");
        beaver.Scanner scanner = new TRS80Scanner(input);
        TRS80Parser parser = new TRS80Parser();
       
        Exception caughtException = null;
        try {
            Object o = parser.parse(scanner);
        }
        catch (Exception e) {
            caughtException = e;
        }
        
        if (null == caughtException) {
            fail("No exception caught");
        }
    }


}
