package dct25.trs80.syntaxTree;

public abstract class BooleanExpression extends beaver.Symbol {
    public static BooleanExpression conjunction(BooleanExpression b1, BooleanExpression b2) {
        return new Conjunction(b1, b2);
    }

    public static BooleanExpression notEquals(IntegerExpression i1, IntegerExpression i2) {
        return new NotEqualsExpression(i1, i2);
    }
    
    public abstract void visit(SyntaxTreeVisitor v) throws Exception;

    public static BooleanExpression equals(IntegerExpression i1, IntegerExpression i2) {
        return new EqualsExpression(i1, i2);
    }
}
