package SocialCreditMS.Controller;

import SocialCreditMS.Model.User;
import SocialCreditMS.Util.AppState;
import SocialCreditMS.View.MainView;
import SocialCreditMS.db.UserRepository;

public class MainController {
    public static void menu() {
        MainView.menu();
    }

    public static boolean login(String login, String password) {
        UserRepository userRepo = new UserRepository();
        User user;
        try {
            user = userRepo.getBy(u -> u.getLogin().equals(login)).get(0);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        boolean passwordMatch = user.checkPassword(password);
        if(!passwordMatch) {
            return false;
        }
        AppState.setState(user.getLogin(), user.getAccess());
        return true;
    }
}
