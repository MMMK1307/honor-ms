package SocialCreditMS.db;

import SocialCreditMS.Model.PrisonOrder;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class PrisonOrderRepository implements BaseRepository<PrisonOrder> {

    @Override
    public PrisonOrder getById(UUID id) {
        var tableData = getRaw("prisonOrder");

        JSONObject prisonOrderData = tableData.getJSONObject(id.toString());

        if (prisonOrderData == null) {
            return null;
        }

        return PrisonOrder.createFromJson(prisonOrderData);
    }

    @Override
    public ArrayList<PrisonOrder> getAll() {
        return null;
    }

    @Override
    public ArrayList<PrisonOrder> getBy(Function<PrisonOrder, Boolean> predicate) {

        var tableData = getRaw("prisonOrder");

        ArrayList<PrisonOrder> prisonOrders = new ArrayList<>();

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var tempPrisonOrder =
                    PrisonOrder.createFromJson(tableData.getJSONObject(key));

            if (predicate.apply(tempPrisonOrder)) {
                prisonOrders.add(tempPrisonOrder);
            }
        }

        return prisonOrders;
    }
}