package SocialCreditMS.Model;

import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;


public class User extends BaseModel {

    private User() {}

    private User(UUID id, String login, String name, String password) {
        super(id, "users");
        this.login = login;
        this.name = name;
        this.password = password;
    }

    private String login;
    private String name;
    private String password;

    public static User createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), User.class);
    }
    public static User create(String login, String name, String password) {
        return new User(UUID.randomUUID(), login, name, password);
    }
}
