package SocialCreditMS.Controller;

import SocialCreditMS.Model.Rule;
import SocialCreditMS.View.RuleView;
import SocialCreditMS.db.RuleRepository;

import java.util.ArrayList;

public class RuleController {

    private static RuleRepository ruleRepo =
            new RuleRepository();

    public static Rule getSingleByDescription() {
        return RuleView.getByDescription();
    }

    public static ArrayList<Rule> getByDescription(
            String description
    ) {
        return ruleRepo.getBy(
                (r) -> r.getDescription().contains(description)
        );
    }

    public static void listAll() {

        var rules = ruleRepo.getAll();

        RuleView.getBy(rules);
    }

    public static void createRule() {

        Rule rule = RuleView.create();

        boolean success =
                ruleRepo.save(rule);

        if(success) {
            System.out.println(
                    "Rule created successfully!"
            );
        } else {
            System.out.println(
                    "Failed to create rule."
            );
        }
    }

    public static void menu() {
        RuleView.menu();
    }
}