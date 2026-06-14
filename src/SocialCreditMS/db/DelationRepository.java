package SocialCreditMS.db;

import SocialCreditMS.Model.Delation;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class DelationRepository implements BaseRepository<Delation> {

    @Override
    public Delation getById(UUID id) {
        var tableData = getRaw("delation");

        JSONObject delationData = tableData.getJSONObject(id.toString());

        if (delationData == null) {
            return null;
        }

        return Delation.createFromJson(delationData);
    }

    @Override
    public ArrayList<Delation> getAll() {
        return null;
    }

    @Override
    public ArrayList<Delation> getBy(Function<Delation, Boolean> predicate) {

        var tableData = getRaw("delation");

        ArrayList<Delation> delations = new ArrayList<>();

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var tempDelation =
                    Delation.createFromJson(tableData.getJSONObject(key));

            if (predicate.apply(tempDelation)) {
                delations.add(tempDelation);
            }
        }

        return delations;
    }
}