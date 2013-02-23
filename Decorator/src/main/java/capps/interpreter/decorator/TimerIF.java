package capps.interpreter.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 2/19/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TimerIF {
    long getPreviousOpDurationNs();
    long getPreviousOpDurationMs();
}
