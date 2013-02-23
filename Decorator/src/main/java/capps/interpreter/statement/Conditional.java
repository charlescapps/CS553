package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Expr;
import capps.interpreter.expr.Var;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Conditional extends Statement {

    private Expr expr;
    private Statement ifTrue;
    private Statement ifFalse;

    public Conditional(Expr expr, Statement ifTrue, Statement ifFalse) {
        this.expr = expr;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public String toString() {
        return "if ( " + expr + " ) then\n" +
               ifTrue.toString(1) +
               "else" + "\n" +
               ifFalse.toString(1);
    }

    @Override
    public LittleLangComponent[] components() {
        return new LittleLangComponent[] {
                expr, ifTrue, ifFalse
        };
    }

    @Override
    public void interpret(Set<Var> vars) {
        if (expr.evaluate() == 1) {
            ifTrue.interpret(vars);
        }
        else {
            ifFalse.interpret(vars);
        }
    }
}
