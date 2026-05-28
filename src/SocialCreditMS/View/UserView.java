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

    public static void menu(ViewMessage message) {
        message.printMessage();
        menu();
    }


    public static void menu() {
        Print.nl(Colors.Blue, "-- User Menu -- ");
        Print.nl(Colors.Default, "1: Create User | 2: List Users | 0: Exit");
        int option = -1;
        while (option != 0) {
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
        Print.nl(Colors.Blue, "-- Create User --");
        String name = sc.String(" -- Name: ");
        String login = sc.String(" -- Login: ");
        String password = sc.String(" -- Password: ");
        int ac = sc.UntilInt("[1] Admin [0] Basic: ", "Invalid Access: ", (v) -> v == 0 || v == 1);
        UserController.createUser(name, login, password, UserAccess.values()[ac]);
    }

    public static void listUsers(List<User> users) {
        for(int i = 0 ; i < users.size(); i++) {
            var user = users.get(i);
            Print.sl("\nid: ", i, "| Name: ", user.getName(), "| Login: ", user.getLogin(), "| Access: ", user.getAccess());
        }
        Print.n("\n[1] Edit [2] Delete [0] Exit");
        var option = sc.UntilInt("Option: ", "Invalid Option. Try Again: ", (x) -> x >= 0 && x < 3);

        switch(option) {
            case 1:


        }
    }
}