package capps.interpreter.statement;

import capps.interpreter.LittleLangComponent;
import capps.interpreter.expr.Var;

import java.util.Set;

/**
 * A Statement is a Composite
 */
public abstract class Statement extends LittleLangComponent {
    public abstract LittleLangComponent[] components();
    public abstract void interpret(Set<Var> vars);
}
