package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.EmperorMessageController;
import SocialCreditMS.Controller.EmperorRequisitionController;
import SocialCreditMS.Model.EmperorMessage;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.Util.ViewMessage;

import java.util.ArrayList;

public class EmperorMessageView {

    private final static Reader sc = new Reader();
    private static ViewMessage message = null;

    private static void printMessage() {
        if(message == null) {
            return;
        }
        message.printMessage();
        message = null;
    }

    public static void menu() {
        int option = -1;
        while(option != 0) {
            printMessage();
            Print.nl(Colors.Yellow, "\n-- Emperor Messages -- ");
            Print.nl(Colors.Default, "[1] New Message [2]: List All Messages [0]: Exit");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (x) -> x >= 0 && x <= 2);
            switch(option) {
                case 1:
                    create();
                    break;
                case 2:
                    EmperorMessageController.list();
                    break;
            }
        }
    }

    public static void create() {
        Print.nl(Colors.Yellow, "\n-- Creating Message --");
        String title = sc.String(" Title: ");
        String body = sc.String(" Body: ");
        int groupId = sc.UntilInt(" [0]All [1]Nornal/Basic [2]Admins: ", "Invalid Id. Try again: ");
        UserAccess access = UserAccess.All;

        if(groupId == 1)
            access = UserAccess.Basic;
        else if(groupId == 2)
            access = UserAccess.Admin;

        message = EmperorMessageController.create(title, body, access);
    }

    public static void edit(EmperorMessage msg) {
        Print.nl(Colors.Yellow, "\n-- Edit Message --");
        String title = sc.String(" Title [" + msg.getTitle() + "]: ");
        String body = sc.String(" Body: [" + msg.getBody() + "]: ");
        int activeNum = sc.UntilInt(" Active [0] NO [1] YES: ", "Invalid. Try again: ");

        boolean active = activeNum == 1;

        int groupId = sc.UntilInt(" [0]All [1]Nornal/Basic [2]Admins: ", "Invalid Id. Try again: ");
        UserAccess access = UserAccess.All;

        if(groupId == 1)
            access = UserAccess.Basic;
        else if(groupId == 2)
            access = UserAccess.Admin;

        message = EmperorMessageController.edit(msg, title, body, active, access);
    }

    public static void list(ArrayList<EmperorMessage> messages) {
        Print.nl(Colors.Yellow, "\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
        Print.fl("| %-3s | | %-20s | | %-40s | | %-20s | | %-20s || %-20s |", "ID", "TITLE", "BODY", "GROUP", "ACTIVE", "CREATION");
        Print.nl(Colors.Yellow, "------------------------------------------------------------------------------------------------------------------------------------------------------");

        for(int i = 0; i < messages.size(); i++) {
            var msg = messages.get(i);
            Print.fl("| %-3d | | %-20s | | %-40s | | %-20s | | %-20s || %-20s |",
                i+1, msg.getTitle(), msg.getBody(), msg.getTargetGroup().toString(),
                    msg.getActive() ? "YES" : "NO", msg.getFormattedCreatedAt()
            );
        }

        Print.n(Colors.Default);
        Print.nl("\n[1] Edit [2] Delete [0] Exit");
        var option = sc.UntilInt("Option: ", "Invalid Option. Try Again: ", (x) -> x >= 0 && x < 3);

        if(option == 0) {
            return;
        }
        var msgId = sc.UntilInt("Id/Position: ", "Invalid Id/Position. Try Again: ", (x) -> x >= 0 && x <= messages.size());
        msgId--;

        if(msgId < 0) {
            return;
        }

        var requisition = messages.get(msgId);

        switch(option) {
            case 1:
                edit(requisition);
                break;
            case 2:
                message = EmperorMessageController.delete(requisition.getId());
                break;
        }
    }
}
