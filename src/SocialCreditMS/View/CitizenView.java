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
}