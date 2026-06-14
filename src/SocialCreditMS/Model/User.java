package SocialCreditMS.Model;

import SocialCreditMS.Util.Hashing;
import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;


public class User extends BaseModel {

    private User() {}

    private User(UUID id, String login, String name, String password, UserAccess access) {
        super(id, TableNames.User);
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
        User mappedUser = new ObjectMapper().readValue(jsonData.toString(), User.class);
        String access = jsonData.getString("access");
        mappedUser.setAccess(UserAccess.valueOf(access));
        return mappedUser;
    }

    public static User create(String name, String login, String password, UserAccess access) {
        UUID id = UUID.randomUUID();
        return new User(id, login, name, Hashing.createHash(password, id.toString()), access);
    }

    public boolean checkPassword(String rawPassword) {
        String passwordHash = Hashing.createHash(rawPassword, getId().toString());
        return password.equals(passwordHash);
    }
}
