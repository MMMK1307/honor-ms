package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.*;
import SocialCreditMS.Model.EmperorRequisition;
import SocialCreditMS.Util.AppState;

public class MainView {
    private static Reader sc = new Reader();

    public static void menuAdmin() {
        int option = -1;
        while(option != 0) {
            Print.nl(Colors.BlueDark, "\n-- Admin Menu --");
            Print.n(Colors.Default);
            Print.nl("[1] Users");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (a) -> a >= 0 && a <= 5);

            switch(option) {
                case 1:
                    UserController.menu();
                    break;
            }
        }
    }

    public static void menuEmperor() {
        int option = -1;
        while(option != 0) {
            Print.nl(Colors.Yellow, "\n-- Emperor Menu --");
            Print.n(Colors.Default);
            Print.nl("[1] Requisitions [2] Messages [0] Exit");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (a) -> a >= 0 && a <= 5);
            switch(option) {
                case 1:
                    EmperorRequisitionController.listResponses();
                    break;
                case 2:
                    EmperorMessageController.menu();
                    break;
            }
        }
    }

    public static void menu() {
        int option = -1;

        while(option != 0) {
            Print.nl(Colors.Blue, "\n-- Main Menu --");
            Print.n(Colors.Default);

            // Basic Access Actions
            Print.n("[1] Citizens [2] Services [3] Delations [8] Requisitions for the Emperor ");

            // Admin Access Actions
            if(AppState.hasAdminAccess()) {
                Print.n("[9] Admin Menu ");
            }

            // Emperor Access Actions
            if(AppState.hasEmperorAccess()) {
                Print.n("[10] EmperorMenu ");
            }

            Print.nl("[0] Exit");

            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (a) -> a >= 0 && a <= 10);

            switch(option) {
                case 1:
                    break;
                case 2:
                    ServiceController.menu();
                    break;
                case 3:
                    DelationView.menu();
                case 8:
                    EmperorRequisitionController.menu();
                    break;
                case 9:
                    menuAdmin();
                    break;
                case 10:
                    menuEmperor();
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
            if(!loginSuccess) {
                Print.nl(Colors.Red, "\nWrong login and/or password\n");
            }
        }
        Print.nl(Colors.Green, "\nLogged in!");
        menu();
    }
}
