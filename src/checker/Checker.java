package checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    public static boolean checkNumber(String number) {
        Pattern p = Pattern.compile("(([+]91)|0)?[6789][0-9]{9}");
        Matcher m = p.matcher(number);
        if (m.find()) {
            return number.equals(m.group());
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z]+([.][a-zA-z]+)+");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return email.equals(m.group());
        }
        return false;
    }

    public static boolean checkName(String name) {
        Pattern p = Pattern.compile("[a-zA-z]+");
        Matcher m = p.matcher(name);
        if (m.find()) {
            return name.equals(m.group());
        }
        return false;
    }
}
