package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.MainController;
import SocialCreditMS.Controller.UserController;
import SocialCreditMS.Util.AppState;

public class MainView {
    private static Reader sc = new Reader();
    private static AppState state = AppState.getInstance();

    public static void menu() {
        int option = -1;

        while(option != 0) {
            Print.nl(Colors.Blue, "\n-- Main Menu --");
            Print.n(Colors.Default);
            Print.nl("[1] Users [0] Exit ");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (a) -> a >= 0 && a <= 5);

            switch(option) {
                case 1:
                    UserController.menu();
                    break;
            }
        }
    }

    public static void loginPage() {
        boolean loginSuccess = false;
        while(!loginSuccess) {
            Print.n(Colors.Default);
            String login = sc.String("Username/Login: ");
            String password = sc.String("Password: ");
            loginSuccess = MainController.login(login, password);
            if(loginSuccess) {
                break;
            }
            Print.nl(Colors.Red, "\nWrong login and/or password\n");
        }
        Print.nl(Colors.Green, "\nLogged in!");
        menu();
    }
}
