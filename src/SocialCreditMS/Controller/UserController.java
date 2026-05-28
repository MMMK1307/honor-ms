package SocialCreditMS.Controller;
import SocialCreditMS.Model.User;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.Util.ViewMessage;
import SocialCreditMS.View.UserView;
import SocialCreditMS.db.UserRepository;

public class UserController {
    private static UserRepository userRepo = new UserRepository();

    public static void menu() {
        UserView.menu();
    }

    public static void createUser(String name, String login, String password, UserAccess access) {
        User user = User.create(name, login, password, access);
        boolean success = userRepo.save(user);
        if (success) {
            UserView.menu(new ViewMessage(true, "Successfully created user"));
        } else {
            UserView.menu(new ViewMessage(true, "Error in user creation"));
        }
    }

    public static void listUsers() {
        var users =  userRepo.getAll();
        UserView.listUsers(users);
    }

    public static void editUser(User user) {
    }
}
