package SocialCreditMS.Controller;

import SocialCreditMS.Model.Punishment;
import SocialCreditMS.View.PunishmentView;
import SocialCreditMS.db.PunishmentRepository;

import java.util.ArrayList;

public class PunishmentController {

    private static PunishmentRepository punishmentRepo =
            new PunishmentRepository();

    public static Punishment getSingleByDescription() {
        return PunishmentView.getByDescription();
    }

    public static ArrayList<Punishment>
    getByDescription(String description) {

        return punishmentRepo.getBy(
                (p) -> p.getDescription()
                        .contains(description)
        );
    }

    public static void listAll() {

        var punishments =
                punishmentRepo.getAll();

        PunishmentView.getBy(
                punishments
        );
    }

    public static void createPunishment() {

        Punishment punishment =
                PunishmentView.create();

        boolean success =
                punishmentRepo.save(
                        punishment
                );

        if(success) {

            System.out.println(
                    "Punishment created successfully!"
            );

        } else {

            System.out.println(
                    "Failed to create punishment."
            );
        }
    }

    public static void menu() {
        PunishmentView.menu();
    }
}