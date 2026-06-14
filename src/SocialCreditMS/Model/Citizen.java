package SocialCreditMS.Model;

import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Citizen extends BaseModel {

    public Citizen() {}

    public Citizen(UUID id, String name, Profession profession) {
        super(id, TableNames.Citizen);
        this.name = name;
        this.profession = profession;
    }

    private String name;
    private Profession profession;

    public static Citizen create(String name, Profession profession) {

        return new Citizen(UUID.randomUUID(), name, profession);
    }

    public static Citizen createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), Citizen.class);
    }

    public String getName() {
        return name;
    }

    public Profession getProfession() {
        return profession;
    }
}