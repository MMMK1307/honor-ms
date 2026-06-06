package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.UserController;

public class MainView {
    public static void menu() {
        int option = -1;
        Reader sc = new Reader();

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
}
