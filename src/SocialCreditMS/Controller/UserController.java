package SocialCreditMS.Controller;
import SocialCreditMS.Model.User;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.Util.ViewMessage;
import SocialCreditMS.View.UserView;
import SocialCreditMS.db.UserRepository;

import java.util.UUID;

public class UserController {
    private static UserRepository userRepo = new UserRepository();

    public static void menu() {
        UserView.menu();
    }

    public static ViewMessage createUser(String name, String login, String password, UserAccess access) {
        User user = User.create(name, login, password, access);
        boolean success = userRepo.save(user);
        if (success) {
            return ViewMessage.Success("Successfully created user");
        }
       return ViewMessage.Fail("Error in user creation");
    }

    public static void listUsers() {
        var users =  userRepo.getAll();
        UserView.listUsers(users);
    }

    public static ViewMessage editUser(User user, String name, String login, UserAccess access) {
        if(!name.isEmpty())
            user.setName(name);
        if(!login.isEmpty())
            user.setLogin(login);
        user.setAccess(access);
        boolean editSuccess = userRepo.save(user);
        if(editSuccess) {
            return ViewMessage.Success("User was edited");
        }
        return ViewMessage.Fail("Error in Editing User");
    }

    public static ViewMessage removeUser(UUID userId) {
        var user = userRepo.getById(userId);
        if(user == null) {
            return ViewMessage.Fail("User not Found");
        }
        boolean deleteSuccess = userRepo.delete(user);
        if(deleteSuccess) {
            return ViewMessage.Success("User was Deleted");
        }
        return ViewMessage.Fail("User not Found");
    }
}
