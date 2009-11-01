package dct25.trs80.syntaxTree;

public abstract class IntegerExpression extends beaver.Symbol {
    public static IntegerExpression fromIdentifier(Identifier i) {
        return new IntegerIdentifierExpression(i);
    }

    public static IntegerExpression fromIntegerLiteral(IntegerLiteral i) {
        return new IntegerLiteralExpression(i);
    }
    
    public abstract void visit(SyntaxTreeVisitor v) throws Exception;

    public static IntegerExpression randomNumber(IntegerExpression i) {
        return new RandomNumberExpression(i);
    }
    
    public static IntegerExpression sum(IntegerExpression i1, IntegerExpression i2) {
        return new IntegerSumExpression(i1, i2);
    }
    
    public static IntegerExpression fromArrayElement(ArrayElement ae) {
        return new ArrayElementExpression(ae);
    }

    public static IntegerExpression difference(IntegerExpression i1, IntegerExpression i2) {
        return new IntegerDifferenceExpression(i1, i2);
    }

    public static IntegerExpression product(IntegerExpression i1, IntegerExpression i2) {
        return new IntegerProductExpression(i1, i2);
    }
}
