package capps.interpreter.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/27/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Identifier implements Token {
    private String name;
    private static final Pattern varPattern = Pattern.compile("([a-zA-Z]\\w*)");


    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public Pair<Token, String> matchNext(String tokenString) {
        Matcher matcher = varPattern.matcher(tokenString);
        if (matcher.lookingAt()) {
            String id = matcher.group(1);
            return new Pair(new Identifier(id),
                            tokenString.substring(id.length()));
        }
        return new Pair(null, tokenString);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "an identifier(" + name + ")";
    }
}
