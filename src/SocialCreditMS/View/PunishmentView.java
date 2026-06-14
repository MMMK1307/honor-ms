package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import SocialCreditMS.Controller.PunishmentController;
import SocialCreditMS.Model.Punishment;

import java.util.ArrayList;

public class PunishmentView {

    private static Reader sc =
            new Reader();

    public static Punishment
    getByDescription() {

        String description =
                sc.String(
                        "Punishment Description: "
                );

        var punishments =
                PunishmentController
                        .getByDescription(
                                description
                        );

        return getBy(
                punishments
        );
    }

    public static Punishment getBy(
            ArrayList<Punishment> punishments
    ) {

        Print.nl(
                "\n-- Punishments --"
        );

        Print.nl(
                "ID || DESCRIPTION || PENALTY"
        );

        for(
                int i = 0;
                i < punishments.size();
                i++
        ) {

            var punishment =
                    punishments.get(i);

            Print.fl(
                    "%3d || %s || %d",
                    i + 1,
                    punishment.getDescription(),
                    punishment.getHonorPenalty()
            );
        }

        int position =
                sc.UntilInt(
                        "\nId/Position: ",
                        "Invalid Id/Position. Try again: ",
                        (i) ->
                                i >= 0 &&
                                        i <= punishments.size()
                );

        position--;

        if(position == -1) {
            return null;
        }

        return punishments.get(
                position
        );
    }

    public static Punishment create() {

        String description =
                sc.String(
                        "Punishment Description: "
                );

        int penalty =
                sc.UntilInt(
                        "Honor Penalty: ",
                        "Invalid Penalty. Try again: ",
                        (i) -> i >= 0
                );

        return Punishment.create(
                description,
                penalty
        );
    }

    public static void menu() {

        int option = -1;

        while(option != 0) {

            Print.nl(
                    "[1] Create Punishment"
            );

            Print.nl(
                    "[2] Search Punishment"
            );

            Print.nl(
                    "[3] List All Punishments"
            );

            Print.nl(
                    "[0] Exit"
            );

            option =
                    sc.UntilInt(
                            "Option: ",
                            "Invalid Option. Try again: ",
                            (i) -> i >= 0 && i <= 3
                    );

            switch(option) {

                case 1:
                    PunishmentController
                            .createPunishment();
                    break;

                case 2:
                    getByDescription();
                    break;

                case 3:
                    PunishmentController
                            .listAll();
                    break;
            }
        }
    }
}