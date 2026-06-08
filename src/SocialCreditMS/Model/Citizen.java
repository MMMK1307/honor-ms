package SocialCreditMS.Model;

import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Citizen extends BaseModel {

    public Citizen() {}

    public Citizen(UUID id, String name) {
        super(id, "citizen");
        this.name = name;
    }

    private String name;

    public static Citizen create(String name) {
        return new Citizen(UUID.randomUUID(), name);
    }

    public static Citizen createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), Citizen.class);
    }

    public String getName() {
        return name;
    }
}
