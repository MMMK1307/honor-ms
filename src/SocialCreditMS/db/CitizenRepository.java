package SocialCreditMS.db;

import SocialCreditMS.Model.Citizen;
import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class CitizenRepository implements BaseRepository<Citizen> {

    @Override
    public Citizen getById(UUID id) {
        var tableData = getRaw(TableNames.Citizen);

        JSONObject citizenData =
                tableData.getJSONObject(id.toString());

        if(citizenData == null) {
            return null;
        }

        return Citizen.createFromJson(citizenData);
    }

    @Override
    public ArrayList<Citizen> getAll() {
        var tableData = getRaw(TableNames.Citizen);

        ArrayList<Citizen> citizens = new ArrayList<>();

        for (Iterator<String> it = tableData.keys(); it.hasNext(); ) {
            var key = it.next();

            var tempCitizen =
                    Citizen.createFromJson(
                            tableData.getJSONObject(key)
                    );

            citizens.add(tempCitizen);
        }

        return citizens;
    }

    @Override
    public ArrayList<Citizen> getBy(Function<Citizen, Boolean> predicate) {
        var tableData = getRaw(TableNames.Citizen);

        ArrayList<Citizen> citizens = new ArrayList<>();

        for (Iterator<String> it = tableData.keys(); it.hasNext(); ) {
            var key = it.next();

            var tempCitizen =
                    Citizen.createFromJson(
                            tableData.getJSONObject(key)
                    );

            if(predicate.apply(tempCitizen)) {
                citizens.add(tempCitizen);
            }
        }

        return citizens;
    }
}