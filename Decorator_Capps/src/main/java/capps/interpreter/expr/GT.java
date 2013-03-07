package capps.interpreter.expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class GT extends Expr {
    private Expr expr1;
    private Expr expr2;

    public GT(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public int evaluate() {
        return expr1.evaluate() > expr2.evaluate() ? 1: 0;
    }

    @Override
    public String toString() {
        return "( " + expr1.toString() + " > " + expr2.toString() + " )";
    }
}
