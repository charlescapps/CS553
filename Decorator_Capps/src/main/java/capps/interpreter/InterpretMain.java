package capps.interpreter;

import capps.interpreter.expr.*;
import capps.interpreter.expr.NumberExpr;
import capps.interpreter.statement.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterpretMain {
    public static void main(String[] args) {
        VarFactory vf = VarFactory.getInstance();

        //initialize inputs.
        Set<Var> vars = new HashSet<Var>();
        Var n = vf.getVar("n");              //For convenience, give these names
        Var result = vf.getVar("result");

        Statement fact =
            new Compound(
                new Assignment(n, new NumberExpr("10")),
                new Assignment(result, new NumberExpr("1")),
                new While(new GT(n, new NumberExpr("0")),
                    new Compound(
                       new Assignment(result, new OperationExpr(OP.MULT, result, n)),
                       new Assignment(n, new OperationExpr(OP.SUB, n, new NumberExpr("1")))
                    ))
        );

        fact.interpret(vars);

        for (Var v: vars) {
            System.out.println(v.prettyPrint());
        }

    }
}
