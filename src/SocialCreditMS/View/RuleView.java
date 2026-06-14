package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import SocialCreditMS.Controller.RuleController;
import SocialCreditMS.Model.Rule;

import java.util.ArrayList;

public class RuleView {

    private static Reader sc = new Reader();

    public static Rule getByDescription() {

        String description =
                sc.String("Rule Description: ");

        var rules =
                RuleController.getByDescription(
                        description
                );

        return getBy(rules);
    }

    public static Rule getBy(
            ArrayList<Rule> rules
    ) {

        Print.nl("\n-- Rules --");
        Print.nl("ID || DESCRIPTION");

        for(int i = 0; i < rules.size(); i++) {

            var rule = rules.get(i);

            Print.fl(
                    "%3d || %s",
                    i + 1,
                    rule.getDescription()
            );
        }

        int position =
                sc.UntilInt(
                        "\nId/Position: ",
                        "Invalid Id/Position. Try again: ",
                        (i) -> i >= 0 && i <= rules.size()
                );

        position--;

        if(position == -1) {
            return null;
        }

        return rules.get(position);
    }

    public static Rule create() {

        String description =
                sc.String(
                        "Rule Description: "
                );

        return Rule.create(
                description
        );
    }

    public static void menu() {

        int option = -1;

        while(option != 0) {

            Print.nl(
                    "[1] Create Rule"
            );

            Print.nl(
                    "[2] Search Rule"
            );

            Print.nl(
                    "[3] List All Rules"
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
                    RuleController.createRule();
                    break;

                case 2:
                    getByDescription();
                    break;

                case 3:
                    RuleController.listAll();
                    break;
            }
        }
    }
}