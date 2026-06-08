package SocialCreditMS.db;

import SocialCreditMS.Model.EmperorRequisition;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class EmperorRequisitionRepository implements BaseRepository<EmperorRequisition> {

    @Override
    public EmperorRequisition getById(UUID id) {
        var tableData = getRaw("emperor_requisition");
        JSONObject userData = tableData.getJSONObject(id.toString());
        if(userData == null) {
            return null;
        }
        return EmperorRequisition.createFromJson(userData);
    }

    @Override
    public ArrayList<EmperorRequisition> getAll() {
        ArrayList<EmperorRequisition> requisitions = new ArrayList<>();
        var rawData = getRaw("emperor_requisition");
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            requisitions.add(EmperorRequisition.createFromJson(rawData.getJSONObject(key)));
        }
        return requisitions;
    }

    @Override
    public ArrayList<EmperorRequisition> getBy(Function<EmperorRequisition, Boolean> predicate) {
        ArrayList<EmperorRequisition> requisitions = new ArrayList<>();
        var rawData = getRaw("emperor_requisition");
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            var tempRequisition = EmperorRequisition.createFromJson(rawData.getJSONObject(key));
            if(predicate.apply(tempRequisition)) {
                requisitions.add(tempRequisition);
            }
        }
        return requisitions;
    }
}
