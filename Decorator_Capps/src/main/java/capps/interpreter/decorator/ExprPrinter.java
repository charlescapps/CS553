package capps.interpreter.decorator;

import capps.interpreter.expr.Expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/19/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExprPrinter extends AbstractExprWrapper {

    public ExprPrinter(Expr wrapped) {
        super(wrapped);
    }

    @Override
    public int evaluate() {
        int result = super.evaluate();
        System.out.println("Result = "  + result);
        return result;
    }
}
