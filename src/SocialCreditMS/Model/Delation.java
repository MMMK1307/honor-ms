package SocialCreditMS.Model;

import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Delation extends BaseModel {

    public Delation() {}

    public Delation(UUID id, UUID citizenId, String motivo) {
        super(id, "delation");
        this.citizenId = citizenId;
        this.motivo = motivo;
    }

    private UUID citizenId;
    private String motivo;

    public static Delation create(UUID citizenId, String motivo) {
        return new Delation(UUID.randomUUID(), citizenId, motivo);
    }

    public static Delation createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), Delation.class);
    }

    public UUID getCitizenId() {
        return citizenId;
    }

    public String getMotivo() {
        return motivo;
    }
}