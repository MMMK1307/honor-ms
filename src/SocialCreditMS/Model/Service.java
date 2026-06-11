package SocialCreditMS.Model;

import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Service extends BaseModel {

    public Service() {}

    public Service(
            UUID id,
            String name,
            int requiredCredit,
            double cost,
            boolean active
    ) {
        super(id, "service");

        this.name = name;
        this.requiredCredit = requiredCredit;
        this.cost = cost;
        this.active = active;
    }

    private String name;
    private int requiredCredit;
    private double cost;
    private boolean active;

    public static Service create(
            String name,
            int requiredCredit,
            double cost,
            boolean active
    ) {
        return new Service(
                UUID.randomUUID(),
                name,
                requiredCredit,
                cost,
                active
        );
    }

    public static Service createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(
                jsonData.toString(),
                Service.class
        );
    }

    public String getName() {
        return name;
    }

    public int getRequiredCredit() {
        return requiredCredit;
    }

    public double getCost() {
        return cost;
    }

    public boolean isActive() {
        return active;
    }
}