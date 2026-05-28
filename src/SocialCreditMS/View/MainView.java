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
            Print.nl(Colors.Default, "-- Main Menu --");
            Print.nl("1: Users");
            option = sc.UntilInt("Option: ", "Invalid Option. Try again: ", (a) -> a >= 0 && a <= 5);

            switch(option) {
                case 1:
                    UserController.menu();
            }
        }
        Print.nl("-- Main Menu --");
    }
}
