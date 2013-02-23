package capps.interpreter.expr;

import capps.interpreter.expr.Expr;
import capps.interpreter.expr.OP;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class OperationExpr extends Expr {

    private OP op;
    private Expr operand1;
    private Expr operand2;

    public OperationExpr(OP op, Expr operand1, Expr operand2) {
        this.op = op;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + op.toString() + operand2.toString() + ")";
    }

    @Override
    public int evaluate() {
        switch (op) {
            case ADD: return operand1.evaluate() + operand2.evaluate();
            case SUB: return operand1.evaluate() - operand2.evaluate();
            case MULT: return operand1.evaluate() * operand2.evaluate();
            case DIVIDE:return operand1.evaluate() / operand2.evaluate();
        }
        throw new RuntimeException("Invalid OP enum found in OperationExpr.evaluate: " + op);
    }

}
