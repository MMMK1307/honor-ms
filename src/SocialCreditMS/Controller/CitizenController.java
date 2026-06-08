package SocialCreditMS.Controller;

import SocialCreditMS.Model.Citizen;
import SocialCreditMS.View.CitizenView;
import SocialCreditMS.db.CitizenRepository;

import java.util.ArrayList;

public class CitizenController {
    private static CitizenRepository citizenRepo = new CitizenRepository();

    public static Citizen getSingleByName() {
        return CitizenView.getByName();
    }

    public static ArrayList<Citizen> getByName(String name) {
        return citizenRepo.getBy((c) -> c.getName().contains(name));
    }
}
