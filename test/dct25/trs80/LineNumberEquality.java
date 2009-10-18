package dct25.trs80;

import static org.junit.Assert.*;

import org.junit.Test;

import dct25.trs80.syntaxTree.LineNumber;

public class LineNumberEquality {

    @Test
    public void testEqualsBoth10() throws Exception {
        LineNumber ln1 = new LineNumber(new beaver.Symbol(new java.lang.Integer(10)));
        LineNumber ln2 = new LineNumber(new beaver.Symbol(new java.lang.Integer(10)));
        assertEquals(ln1, ln2);
    }

    @Test
    public void testEqualsBoth10Int() throws Exception {
        LineNumber ln1 = new LineNumber(10);
        LineNumber ln2 = new LineNumber(10);
        assertEquals(ln1, ln2);
    }
    
    @Test
    public void testEqualBoth10DifferentConstructors() throws Exception {
        LineNumber ln1 = new LineNumber(new beaver.Symbol(new java.lang.Integer(10)));
        LineNumber ln2 = new LineNumber(10);
        assertEquals(ln1, ln2);
    }

    @Test
    public void testInequal() throws Exception {
        LineNumber ln1 = new LineNumber(new beaver.Symbol(new java.lang.Integer(10)));
        LineNumber ln2 = new LineNumber(new beaver.Symbol(new java.lang.Integer(20)));
        assertFalse(ln1.equals(ln2));
    }

    @Test
    public void testInequalBothInt() throws Exception {
        LineNumber ln1 = new LineNumber(10);
        LineNumber ln2 = new LineNumber(20);
        assertFalse(ln1.equals(ln2));
    }
    
    @Test
    public void testInequalDifferentConstructors() throws Exception {
        LineNumber ln1 = new LineNumber(new beaver.Symbol(new java.lang.Integer(10)));
        LineNumber ln2 = new LineNumber(20);
        assertFalse(ln1.equals(ln2));
    }

}
