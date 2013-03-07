package capps.interpreter;

import capps.interpreter.decorator.ExprCacher;
import capps.interpreter.decorator.ExprPrinter;
import capps.interpreter.decorator.ExprTimer;
import capps.interpreter.expr.Expr;
import capps.interpreter.expr.NumberFactory;
import capps.interpreter.expr.OP;
import capps.interpreter.expr.OperationExpr;
import capps.interpreter.parser.LittleLangParser;
import jline.console.ConsoleReader;

import java.io.*;
import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/29/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class DecoratorMain {
    private static NumberFactory nf = NumberFactory.getInstance();
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static ConsoleReader r;

    public static void main(String[] args) throws Exception {
        r = new ConsoleReader();
        mainMenu();
    }

    private static void mainMenu() throws IOException {

        r.clearScreen();
        while (true) {
            System.out.println("Enter an integer, or (q) to quit. ");
            String numString = r.readLine();
            if (numString.toLowerCase().equals("q")) {
                break;
            }
            try {
                fibMenu(Integer.parseInt(numString));
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer, try again.\n");
            }

        }

    }

    private static void fibMenu(int n) throws IOException {
        Expr fibExpr = buildFiboExpr(n);
        r.clearScreen();

        while (true) {
            printFibMenuOptions(n);
            String entry = r.readLine();
            r.clearScreen();
            if (entry.trim().equals("1")) {
                System.out.println("FIB( " + n + " ) = " + fibExpr.evaluate());
                if (fibExpr instanceof ExprTimer) {
                    System.out.println("Time for previous op (ms) : " + ((ExprTimer)fibExpr).getPreviousOpDurationMs());
                    System.out.println("Time for previous op (ns) : " + ((ExprTimer)fibExpr).getPreviousOpDurationNs());
                }
                System.out.println();
            }
            else if (entry.trim().equals("2")) {
                fibExpr = new ExprTimer(fibExpr);
            }
            else if (entry.trim().equals("3")) {
                fibExpr = new ExprPrinter(fibExpr);
            }
            else if (entry.trim().equals("4")) {
                fibExpr = new ExprCacher(fibExpr);
            }
            else if (entry.trim().equals("5")) {
                System.out.println(fibExpr.toString());
            }
            else if (entry.trim().equals("6")) {
                System.out.println(fibExpr.description());
            }
            else if (entry.trim().toLowerCase().equals("b")) {
                break;
            }
            else {
                System.out.println("Invalid input...\n");
            }

        }
    }

    private static void printFibMenuOptions(int n) throws IOException {
        System.out.println("Computing FIB( " + n + " )\n");
        r.drawLine();
        System.out.println("1) Evaluate\n");
        System.out.println("2) Wrap in ExprTimer\n");
        System.out.println("3) Wrap in ExprPrinter\n");
        System.out.println("4) Wrap in ExprCacher\n");
        System.out.println("5) Print expression\n");
        System.out.println("6) Print wrappers\n");
        System.out.println("b) Back");

    }

    private static Expr buildFiboExpr(int n) {
        if (n <= 0) {
            return nf.getNumberExpr(0);
        }
        if (n == 1) {
            return nf.getNumberExpr(1);
        }
        return new OperationExpr(OP.ADD, buildFiboExpr(n-1), buildFiboExpr(n-2));
    }

    private static String buildFiboExprString(int n) {
        if (n <= 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return buildFiboExprString(n - 1) + " + " + buildFiboExprString(n - 2);
    }
}
