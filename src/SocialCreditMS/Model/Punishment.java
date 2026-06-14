package SocialCreditMS.Model;

import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Punishment extends BaseModel {

    private Punishment() {}

    private Punishment(
            UUID id,
            String description,
            int honorPenalty
    ) {
        super(id, TableNames.Punishment);

        this.description = description;
        this.honorPenalty = honorPenalty;
    }

    private String description;
    private int honorPenalty;

    public String getDescription() {
        return description;
    }

    public int getHonorPenalty() {
        return honorPenalty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHonorPenalty(int honorPenalty) {
        this.honorPenalty = honorPenalty;
    }

    public static Punishment create(
            String description,
            int honorPenalty
    ) {
        return new Punishment(
                UUID.randomUUID(),
                description,
                honorPenalty
        );
    }

    public static Punishment createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(
                jsonData.toString(),
                Punishment.class
        );
    }
}