package SocialCreditMS.Model;

import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Rule extends BaseModel {

    private Rule() {}

    private Rule(
            UUID id,
            String description
    ) {
        super(id, TableNames.Rule);
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Rule create(String description) {
        return new Rule(
                UUID.randomUUID(),
                description
        );
    }

    public static Rule createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(
                jsonData.toString(),
                Rule.class
        );
    }
}