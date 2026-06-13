package SocialCreditMS.db;

import SocialCreditMS.Model.Service;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class ServiceRepository implements BaseRepository<Service> {
    @Override
    public Service getById(UUID id) {
        var tableData = getRaw("service");
        JSONObject serviceData = tableData.getJSONObject(id.toString());

        if(serviceData == null) {
            return null;
        }

        return Service.createFromJson(serviceData);
    }

    @Override
    public ArrayList<Service> getAll() {
        var tableData = getRaw("service");

        ArrayList<Service> services = new ArrayList<>();

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {
            var key = it.next();

            services.add(
                    Service.createFromJson(
                            tableData.getJSONObject(key)
                    )
            );
        }

        return services;
    }

    @Override
    public ArrayList<Service> getBy(Function<Service, Boolean> predicate) {
        var tableData = getRaw("service");

        ArrayList<Service> services = new ArrayList();

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {
            var key = it.next();

            var tempService =
                    Service.createFromJson(
                            tableData.getJSONObject(key)
                    );

            if(predicate.apply(tempService)) {
                services.add(tempService);
            }
        }

        return services;
    }
}