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
}
