package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import AltStd.Colors;
import SocialCreditMS.Controller.DelationController;

import java.util.UUID;

public class DelationView {

    private static Reader sc = new Reader();

    public static void menu() {

        int option = -1;

        while(option != 0) {

            Print.nl(Colors.Blue, "\n-- Delation Menu --");
            Print.n(Colors.Default);

            Print.nl("[1] Register Delation");
            Print.nl("[0] Exit");

            option = sc.UntilInt(
                    "Option: ",
                    "Invalid Option. Try again: ",
                    (a) -> a >= 0 && a <= 1
            );

            switch(option) {

                case 1:
                    try {

                        String citizenId = sc.String("Citizen UUID: ");

                        String motivo = sc.String("Reason: ");

                        boolean success =
                                DelationController.registrarDenuncia(
                                        UUID.fromString(citizenId),
                                        motivo
                                );

                        if(success) {
                            Print.nl(Colors.Green,
                                    "Delation registered successfully!");
                        } else {
                            Print.nl(Colors.Red,
                                    "Failed to register delation.");
                        }

                    } catch (IllegalArgumentException e) {
                        Print.nl(Colors.Red, "Invalid UUID.");
                    }

                    break;
            }
        }
    }
}