package utilities;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailUtils {

    public static boolean isValidEmailAddress(String emailId) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(emailId);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}