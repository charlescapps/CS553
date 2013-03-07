package capps.interpreter.decorator;

import capps.interpreter.expr.Expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/19/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExprTimer extends AbstractExprWrapper implements TimerIF {

    private long startTimeNs;
    private long endTimeNs;

    private long startTimeMs;
    private long endTimeMs;

    public ExprTimer(Expr delegate) {
        super(delegate);
    }

    @Override
    public int evaluate() {
        startTimeMs = System.currentTimeMillis();
        startTimeNs = System.nanoTime();
        int result = super.evaluate();
        endTimeNs = System.nanoTime();
        endTimeMs = System.currentTimeMillis();
        return result;
    }

    @Override
    public long getPreviousOpDurationNs() {
        return endTimeNs - startTimeNs;
    }

    @Override
    public long getPreviousOpDurationMs() {
        return endTimeMs - startTimeMs;
    }

}
