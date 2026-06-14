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

    public static void listAll() {
        var citizens = citizenRepo.getAll();

        if(citizens == null) {
            System.out.println("No citizens found.");
            return;
        }

        CitizenView.getBy(citizens);
    }

    public static void createCitizen() {
        Citizen citizen = CitizenView.create();
        boolean success = citizenRepo.save(citizen);

        if(success) {
            System.out.println("Citizen created successfully!");
        } else {
            System.out.println("Failed to create citizen.");
        }
    }

    public static void menu() {
        CitizenView.menu();
    }
}