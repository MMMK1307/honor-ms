package SocialCreditMS.db;

import SocialCreditMS.Model.Citizen;
import SocialCreditMS.Model.EmperorRequisition;
import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class EmperorRequisitionRepository implements BaseRepository<EmperorRequisition> {

    private void setCitizen(JSONObject citizenTableData, EmperorRequisition requisition) {
        var requester_id = requisition.getRequesterId();
        if(requester_id == null) {
            return;
        }
        var citizenData = citizenTableData.getJSONObject(requester_id.toString());
        if(citizenData == null) {
            requisition.setRequester(null);
            return;
        }
        requisition.setRequester(Citizen.createFromJson(citizenData));
    }

    @Override
    public EmperorRequisition getById(UUID id) {
        var tableData = getRaw(TableNames.EmperorMessage);
        var citizenTableData = getRaw("citizen");
        JSONObject userData = tableData.getJSONObject(id.toString());
        if(userData == null) {
            return null;
        }
        var requisition =  EmperorRequisition.createFromJson(userData);
        setCitizen(citizenTableData, requisition);
        return requisition;
    }

    @Override
    public ArrayList<EmperorRequisition> getAll() {
        ArrayList<EmperorRequisition> requisitions = new ArrayList<>();
        var rawData = getRaw(TableNames.EmperorMessage);
        var citizenTableData = getRaw("citizen");
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            var requisition = EmperorRequisition.createFromJson(rawData.getJSONObject(key));
            setCitizen(citizenTableData, requisition);
            requisitions.add(requisition);
        }
        return requisitions;
    }

    @Override
    public ArrayList<EmperorRequisition> getBy(Function<EmperorRequisition, Boolean> predicate) {
        ArrayList<EmperorRequisition> requisitions = new ArrayList<>();
        var rawData = getRaw(TableNames.EmperorMessage);
        var citizenTableData = getRaw("citizen");
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            var tempRequisition = EmperorRequisition.createFromJson(rawData.getJSONObject(key));
            if(predicate.apply(tempRequisition)) {
                requisitions.add(tempRequisition);
                setCitizen(citizenTableData, tempRequisition);
            }
        }
        return requisitions;
    }
}
