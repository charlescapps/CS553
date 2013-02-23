package capps.interpreter.expr;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Var extends LiteralExpr {

    private String name;
    private int value;

    public Var(String name) {
        this.name = name;
        this.value = 0;
    }

    public Var(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public String prettyPrint() {
        return name + " = " + value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (this == o) || (o instanceof Var) && o.toString().equals(this.toString());
    }

}
