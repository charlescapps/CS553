package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Var;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Compound extends Statement {
    private Statement[] statements;
    public Compound(Statement ... statements) {
        this.statements = new Statement[statements.length];
        System.arraycopy(statements, 0, this.statements, 0, statements.length);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("begin\n");
        for (Statement s: statements) {
            sb.append(s.toString(1));
        }
        return sb.append("end\n").toString();
    }

    @Override
    public LittleLangComponent[] components() {
        return statements;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void interpret(Set<Var> vars) {
        for (Statement s: statements) {
            s.interpret(vars);
        }
    }
}
