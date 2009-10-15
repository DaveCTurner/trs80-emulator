package dct25.trs80;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.LineNumber;

public class LineNumberEquality {

    @Test
    public void testEqualsBothNull() {
        LineNumber ln1 = new LineNumber(null);
        LineNumber ln2 = new LineNumber(null);
        assertEquals(ln1, ln2);
    }

}
