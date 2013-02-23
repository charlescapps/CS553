package capps.interpreter;

import capps.interpreter.expr.Expr;
import capps.interpreter.parser.LittleLangParser;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/29/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseSimpleMain {
    public static void main(String[] args) throws Exception {
        LittleLangParser parser = LittleLangParser.getInstance();
        String exprStr = "5 + x - 2*2*5-z";
        Expr expr = parser.parse(exprStr);
        System.out.println(expr);
    }
}
