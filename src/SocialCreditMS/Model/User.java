package SocialCreditMS.Model;

import SocialCreditMS.Util.Hashing;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;


public class User extends BaseModel {

    private User() {}

    private User(UUID id, String login, String name, String password, UserAccess access) {
        super(id, "users");
        this.login = login;
        this.name = name;
        this.password = password;
        this.access = access;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserAccess getAccess() {
        return access;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccess(UserAccess access) {
        this.access = access;
    }

    private String name;
    private String login;
    private String password;
    private UserAccess access;

    public static User createFromJson(JSONObject jsonData) {
        return new ObjectMapper().readValue(jsonData.toString(), User.class);
    }
    public static User create(String name, String login, String password, UserAccess access) {
        var id = UUID.randomUUID();
        return new User(id, login, name, Hashing.createHash(password, id.toString()), access);
    }
}
