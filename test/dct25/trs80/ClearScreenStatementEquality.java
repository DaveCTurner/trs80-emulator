package dct25.trs80;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.ClearScreenStatement;

public class ClearScreenStatementEquality {

    @Test
    public void testEquals() {
        ClearScreenStatement cls1 = new ClearScreenStatement();
        ClearScreenStatement cls2 = new ClearScreenStatement();
        assertEquals(cls1, cls2);
    }

}
