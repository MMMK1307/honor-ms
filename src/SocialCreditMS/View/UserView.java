package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.UserController;
import SocialCreditMS.Model.User;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.Util.ViewMessage;

import java.util.List;

public class UserView {
    private static Reader sc = new Reader();
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
        while (option != 0) {
            printMessage();
            Print.nl(Colors.BlueDark, "\n-- User Menu -- ");
            Print.nl(Colors.Default, "[1] Create User [2]: List Users [0]: Exit");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (x) -> x >= 0 && x <= 5);
            switch(option) {
                case 1:
                     createUser();
                    break;
                case 2:
                    UserController.listUsers();
                    break;
            }
        }
    }

    public static void createUser() {
        Print.nl(Colors.Blue, "\n-- Create User --");
        String name = sc.String(" -- Name: ");
        String login = sc.String(" -- Login: ");
        String password = sc.String(" -- Password: ");
        int ac = sc.UntilInt(" -- Access [1] Admin [2] Emperor [0] Basic: ", "Invalid Access: ", (v) -> v >= 0 && v <= 2 );
        message = UserController.createUser(name, login, password, UserAccess.values()[ac]);
    }

    public static void editUser(User user) {
        Print.nl(Colors.Blue, "\n-- Editing User --");
        String name = sc.String(" -- Name [" + user.getName() + "]: ");
        String login = sc.String(" -- Login [" + user.getLogin() + "]: ");
        int ac = sc.UntilInt(" -- Access [1] Admin [2] Emperor [0] Basic: ", "Invalid Access: ", (v) -> v >= 0 && v <= 2 );
        message = UserController.editUser(user, name, login, UserAccess.values()[ac]);
    }

    public static void listUsers(List<User> users) {
        if(users.isEmpty()) {
            Print.nl(Colors.Yellow, "\n--- NO USERS ---");
            return;
        }
        Print.nl(Colors.Blue, "\n----------------------------------------------------------------------");
        Print.fl("| %-3s | | %-20s | | %-20s | | %-8s |", "ID", "NAME", "LOGIN", "ACCESS");
        Print.nl("----------------------------------------------------------------------");
        for(int i = 0 ; i < users.size(); i++) {
            var user = users.get(i);
            Print.fl("| %03d | | %20s | | %20s | | %8s |", i + 1, user.getName(), user.getLogin(), user.getAccess());
        }
        Print.n(Colors.Default);

        Print.nl("\n[1] Edit [2] Delete [0] Exit");
        var option = sc.UntilInt("Option: ", "Invalid Option. Try Again: ", (x) -> x >= 0 && x < 3);

        if(option == 0) {
            return;
        }

        var userId = sc.UntilInt("Id/Position: ", "Invalid Option. Try Again: ", (x) -> x >= 0 && x <= users.size());
        userId--;

        if(userId < 0) {
            return;
        }

        switch(option) {
            case 1:
                editUser(users.get(userId));
                break;
            case 2:
                message = UserController.removeUser(users.get(userId).getId());
                break;
        }
    }
}