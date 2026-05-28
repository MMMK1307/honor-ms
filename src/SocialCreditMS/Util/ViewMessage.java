package SocialCreditMS.Util;

import AltStd.Altio.Print;
import AltStd.Colors;

public record ViewMessage(boolean success, String message) {
    public void printMessage() {
        var messageColor = success ? Colors.Green : Colors.Red;
        Print.sl(messageColor, "\n", message, "\n");
    }
}
