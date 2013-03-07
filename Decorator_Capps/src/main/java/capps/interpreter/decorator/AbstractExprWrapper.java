package capps.interpreter.decorator;

import capps.interpreter.expr.Expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/19/13
 * Time: 12:17 PM
 */
public abstract class AbstractExprWrapper extends Expr {
    private Expr wrapped;

    public AbstractExprWrapper(Expr wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public int evaluate() {
        return wrapped.evaluate();
    }

    public Expr getWrapped() {
        return wrapped;
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }

    public String description() {
        return getClass().getSimpleName() + ", " + wrapped.description();
    }

}
