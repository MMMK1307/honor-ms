package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import SocialCreditMS.Controller.CitizenController;
import SocialCreditMS.Model.Citizen;

import java.util.ArrayList;

public class CitizenView {
    private static Reader sc = new Reader();

    public static Citizen getByName() {
        String name = sc.String(" Citizen Name: ");
        var citizen = CitizenController.getByName(name);
        return getBy(citizen);
    }

    public static Citizen getBy(ArrayList<Citizen> citizens) {
        Print.nl("\n-- Citizens --");
        Print.nl("ID || NAME");
        for(int i = 0; i < citizens.size(); i++) {
            var citizen = citizens.get(i);
            Print.fl("%3d || %s", i + 1, citizen.getName());
        }
        int position = sc.UntilInt("\nId/Position: ", "Invalid Id/Position. Try again: ", (i) -> i >= 0 && i <= citizens.size());
        position--;
        if(position == -1) {
            return null;
        }
        return citizens.get(position);
    }
    public static Citizen create() {
        String name = sc.String("Citizen Name: ");

        String professionName = sc.String("Profession Name: ");

        return Citizen.create(
                name,
                new SocialCreditMS.Model.Profession(professionName)
        );
    }
    public static void menu() {
        int option = -1;

        while(option != 0) {
            Print.nl("[1] Create Citizen");
            Print.nl("[2] Search Citizen");
            Print.nl("[3] List All Citizens");
            Print.nl("[0] Exit");

            option = sc.UntilInt(
                    "Option: ",
                    "Invalid Option. Try again: ",
                    (i) -> i >= 0 && i <= 3
            );

            switch(option) {
                case 1:
                    CitizenController.createCitizen();
                    break;

                case 2:
                    getByName();
                    break;

                case 3:
                    CitizenController.listAll();
                    break;
            }
        }
    }
}