package capps.interpreter;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: charles
 * Date: 1/24/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LittleLangComponent {
    public String toString(int numTabs) {
        String tabs = StringUtils.repeat("\t", numTabs);
        String me = toString();
        String[] lines = me.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String l: lines) {
            sb.append(tabs).append(l).append("\n");
        }
        return sb.toString();
    }
}
