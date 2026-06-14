package SocialCreditMS.Model;

import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class EmperorMessage extends BaseModel {

    private EmperorMessage() {}

    private EmperorMessage(UUID id, String title, String body, UserAccess targetGroup, Boolean active, LocalDateTime createAt) {
        super(id, TableNames.EmperorMessage);
        this.title = title;
        this.body = body;
        this.targetGroup = targetGroup;
        this.active = active;
        this.createdAt = createAt;
    }

    String title;
    String body;
    UserAccess targetGroup;
    Boolean active;
    LocalDateTime createdAt;

    public static EmperorMessage create(String title, String body, UserAccess targetGroup) {
        return new EmperorMessage(UUID.randomUUID(), title, body, targetGroup, true, LocalDateTime.now());
    }

    public static EmperorMessage createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), EmperorMessage.class);
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public UserAccess getTargetGroup() {
        return targetGroup;
    }

    public Boolean getActive() {
        return active;
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTargetGroup(UserAccess targetGroup) {
        this.targetGroup = targetGroup;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
