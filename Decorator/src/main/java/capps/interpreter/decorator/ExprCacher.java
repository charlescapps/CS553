package capps.interpreter.decorator;

import capps.interpreter.expr.Expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/19/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExprCacher extends AbstractExprWrapper {

    private Integer cache = null;

    public ExprCacher(Expr wrapped) {
        super(wrapped);
    }

    @Override
    public int evaluate() {
        if (cache == null) {
            cache = super.evaluate();
        }
        return cache;
    }
}
