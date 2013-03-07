package capps.interpreter.expr;

import capps.interpreter.LittleLangComponent;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Expr extends LittleLangComponent {

    public abstract int evaluate();
    public String description() {
        return "An Expression";
    }

}
