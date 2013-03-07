package capps.interpreter.expr;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class NumberFactory {
    private static final NumberFactory instance = new NumberFactory();

    private NumberFactory(){

    }

    public static NumberFactory getInstance() {
        return instance;
    }

    private Map<Integer, NumberExpr> pool = new HashMap<Integer, NumberExpr>();

    public NumberExpr getNumberExpr(String valueStr) {
        Integer val = Integer.parseInt(valueStr);
        if (pool.get(val) == null) {
            pool.put(val, new NumberExpr(valueStr));
        }
        return pool.get(val);
    }

    public NumberExpr getNumberExpr(Integer val) {
        NumberExpr num = pool.get(val);
        if (num == null) {
            num = new NumberExpr(val);
            pool.put(val, num);
        }
        return num;
    }
}
