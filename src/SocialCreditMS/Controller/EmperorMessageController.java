package SocialCreditMS.Controller;

import SocialCreditMS.Model.EmperorMessage;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.Util.ViewMessage;
import SocialCreditMS.View.EmperorMessageView;
import SocialCreditMS.db.EmperorMessageRepository;

import java.util.UUID;

public class EmperorMessageController {

    private final static EmperorMessageRepository empMessageRepo = new EmperorMessageRepository();

    public static void menu() {
        EmperorMessageView.menu();
    }

    public static ViewMessage create(String title, String body, UserAccess targetGroup) {
        var message = EmperorMessage.create(title, body, targetGroup);
        boolean saveSuccess = empMessageRepo.save(message);
        if(saveSuccess) {
           return ViewMessage.Success("Message was Posted");
        }
        return ViewMessage.Fail("Error in creating message");
    }

    public static ViewMessage edit(EmperorMessage msg, String title, String body, boolean active, UserAccess targetGroup) {
        if(!title.isEmpty())
            msg.setTitle(title);
        if(!body.isEmpty())
            msg.setBody(body);
        msg.setTargetGroup(targetGroup);
        msg.setActive(active);

        boolean saveSuccess = empMessageRepo.save(msg);
        if(saveSuccess) {
            return ViewMessage.Success("Message was edited");
        }
        return ViewMessage.Fail("Error in editing the message");
    }

    public static void list() {
        var messages = empMessageRepo.getAll();
        EmperorMessageView.list(messages);
    }

    public static ViewMessage delete(UUID msgId) {
        var msg = empMessageRepo.getById(msgId);
        if(msg == null) {
            return ViewMessage.Fail("Message not Found");
        }
        boolean deleteSuccess = empMessageRepo.delete(msg);
        if(deleteSuccess) {
            return ViewMessage.Success("Message was Deleted");
        }
        return ViewMessage.Fail("Error in Deleting Message");
    }
}
