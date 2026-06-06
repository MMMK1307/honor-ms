package SocialCreditMS.Util;

import AltStd.Altio.Print;
import AltStd.Colors;

public record ViewMessage(boolean success, String message) {
    public void printMessage() {
        var messageColor = success ? Colors.Green : Colors.Red;
        Print.sl(messageColor, "\n\n", message, "\n");
    }
    public static ViewMessage Success(String msg) {
        return new ViewMessage(true, msg);
    }
    public static ViewMessage Fail(String msg) {
        return new ViewMessage(false, msg);
    }
}
