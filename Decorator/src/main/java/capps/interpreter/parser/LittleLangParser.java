package capps.interpreter.parser;

import capps.interpreter.expr.*;
import capps.interpreter.lexer.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class LittleLangParser {
    private static final LittleLangParser instance = new LittleLangParser();
    private static final VarFactory vf = VarFactory.getInstance();
    private static final NumberFactory nf = NumberFactory.getInstance();

    public static LittleLangParser getInstance() {
        return instance;
    }

    public Expr parse(String code) throws ParseException, TokenException {
        Lexer lexer = Lexer.getInstance();
        List<Token> tokens = lexer.scan(code);
        return parseExpression(tokens);
    }

    public Expr parseExpression(List<Token> tokens) throws ParseException {
        Expr expr = expr(tokens);
        end(tokens);
        return expr;
    }

    //Recursive implementation
//    private Expr expr(List<Token> tokens) throws ParseException {
//
//        //try to parse as factor
//        Expr factor1 = factor(tokens);
//
//        //2 or more factors.
//        OpToken opToken = (OpToken)peek(tokens, OpToken.class);
//        if (opToken == null || opToken.getType() != OP.ADD && opToken.getType() != OP.SUB) {
//            return factor1;
//        }
//
//        tokens.remove(0);
//        return new OperationExpr(opToken.getType(), factor1, expr(tokens));
//
//    }

    private Expr expr(List<Token> tokens) throws ParseException {

        Expr factor1 = factor(tokens);

        while (true) {

            //2 or more factors.
            OpToken opToken = (OpToken)peek(tokens, OpToken.class);
            if (opToken == null || opToken.getType() != OP.ADD && opToken.getType() != OP.SUB) {
                return factor1;
            }

            tokens.remove(0);

            factor1 = new OperationExpr(opToken.getType(), factor1, factor(tokens));

        }
    }

    private Expr factor(List<Token> tokens) throws ParseException {

        Expr leftOperand = parseSimple(tokens);

        OpToken opToken = (OpToken)peek(tokens, OpToken.class);
        if (opToken == null || opToken.getType() != OP.MULT && opToken.getType() != OP.DIVIDE) {
            return leftOperand; //only 1 factor
        }
        //2 or more factors
        tokens.remove(0);
        return new OperationExpr(opToken.getType(), leftOperand, factor(tokens));
    }

    private Expr parseSimple(List<Token> tokens) throws ParseException {
        //try to parse as identifier
        Expr var = varExpr(tokens);
        if (var != null) {
            return var;
        }

        //try to parse as number
        Expr numberExpr = numberExpr(tokens);
        if (numberExpr != null) {
            return numberExpr;
        }

        //Finally, try to parse as ( expr )
        require(tokens, LParen.class);
        Expr expr = expr(tokens);
        require(tokens, RParen.class);
        return expr;

    }

    private NumberExpr numberExpr(List<Token> tokens) {
        NumberToken numberToken = numbertoken(tokens);
        if (numberToken == null) {
            return null;
        }
        return nf.getNumberExpr(numberToken.getValue());
    }

    private Var varExpr(List<Token> tokens)  {
        Identifier identifierToken = identifier(tokens);
        if (identifierToken == null) {
            return null;
        }
        return vf.getVar(identifierToken.getName());
    }

    private void end(List<Token> ts) throws ParseException{
        require(ts, EndToken.class);
    }

    private Identifier identifier(List<Token> ts)  {
        return (Identifier)expecting(ts, Identifier.class);
    }

    private NumberToken numbertoken(List<Token> ts)  {
        return (NumberToken)expecting(ts, NumberToken.class);
    }

    private Token peek(List<Token> tokens, Class tokenClass) {
        if (tokens.size() > 0 && tokens.get(0).getClass().equals(tokenClass)) {
            return tokens.get(0);
        }
        return null;
    }

    private Token expecting(List<Token> ts, Class tokenClass)  {
        if (!(ts.get(0).getClass().equals(tokenClass))) {
            return null;
        }
        Token t = ts.get(0);
        ts.remove(0);
        return t;
    }

    private Token require(List<Token> ts, Class tokenClass) throws ParseException {
        if (!(ts.get(0).getClass().equals(tokenClass))) {
            throw new ParseException("Required token class " + tokenClass.getName() + " at token " + ts.get(0));
        }
        Token t = ts.get(0);
        ts.remove(0);
        return t;
    }

}
