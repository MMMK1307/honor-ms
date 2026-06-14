package SocialCreditMS.db;

import SocialCreditMS.Model.Rule;
import SocialCreditMS.Util.TableNames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class RuleRepository implements BaseRepository<Rule> {

    @Override
    public Rule getById(UUID id) {

        var tableData = getRaw(TableNames.Rule);

        var ruleData =
                tableData.getJSONObject(id.toString());

        return Rule.createFromJson(ruleData);
    }

    @Override
    public ArrayList<Rule> getAll() {

        ArrayList<Rule> rules = new ArrayList<>();

        var tableData =
                getRaw(TableNames.Rule);

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var rule =
                    Rule.createFromJson(
                            tableData.getJSONObject(key)
                    );

            rules.add(rule);
        }

        return rules;
    }

    @Override
    public ArrayList<Rule> getBy(Function<Rule, Boolean> predicate) {

        ArrayList<Rule> rules = new ArrayList<>();

        var tableData =
                getRaw(TableNames.Rule);

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var rule =
                    Rule.createFromJson(
                            tableData.getJSONObject(key)
                    );

            if(predicate.apply(rule)) {
                rules.add(rule);
            }
        }

        return rules;
    }
}