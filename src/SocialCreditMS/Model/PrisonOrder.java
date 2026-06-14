package SocialCreditMS.Model;

import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class PrisonOrder extends BaseModel {

    public PrisonOrder() {}

    public PrisonOrder(UUID id, UUID citizenId, String reason) {
        super(id, "prisonOrder");
        this.citizenId = citizenId;
        this.reason = reason;
    }

    private UUID citizenId;
    private String reason;

    public static PrisonOrder create(UUID citizenId, String reason) {
        return new PrisonOrder(UUID.randomUUID(), citizenId, reason);
    }

    public static PrisonOrder createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), PrisonOrder.class);
    }

    public UUID getCitizenId() {
        return citizenId;
    }

    public String getReason() {
        return reason;
    }
}