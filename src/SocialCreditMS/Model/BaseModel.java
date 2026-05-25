package SocialCreditMS.Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class BaseModel {
    private UUID id;
    private String tableName;

    BaseModel() {};

    public BaseModel(UUID id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }

    public JSONObject toJson() {
        var d = new ObjectMapper().writeValueAsString(this);
        return new JSONObject(d);
    };

    public UUID getId() {
        return id;
    };

    public String getTableName() {
        return tableName;
    };
}
