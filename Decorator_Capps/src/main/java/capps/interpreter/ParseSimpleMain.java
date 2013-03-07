package capps.interpreter;

import capps.interpreter.expr.Expr;
import capps.interpreter.lexer.TokenException;
import capps.interpreter.parser.LittleLangParser;
import capps.interpreter.parser.ParseException;
import jline.console.ConsoleReader;

import java.io.Console;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/29/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseSimpleMain {
    private static ConsoleReader r;
    private static final LittleLangParser parser = LittleLangParser.getInstance();
    public static void main(String[] args) throws Exception {
        r = new ConsoleReader();
        menu();
    }


    private static void menu() throws IOException {
        r.clearScreen();
        while (true) {
            r.println("Enter an arithmetic expression:"); r.flush();
            String input = r.readLine();
            r.clearScreen();
            Expr expr;
            try {
                expr = parser.parse(input);
            } catch (Exception e) {
                r.println("Error Parsing expression! " + e.getMessage());
                continue;
            }
            r.println("Result:");
            r.println(expr.toString());
            r.flush();
        }

    }

}
