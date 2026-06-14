package SocialCreditMS.db;

import SocialCreditMS.Model.User;
import SocialCreditMS.Util.TableNames;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class UserRepository implements BaseRepository<User> {

    @Override
    public User getById(UUID id) {
        var rawUsers = getRaw(TableNames.User);
        JSONObject userData = rawUsers.getJSONObject(id.toString());
        if(userData == null) {
            return null;
        }
        return User.createFromJson(userData);
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        var rawData = getRaw(TableNames.User);
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            users.add(User.createFromJson(rawData.getJSONObject(key)));
        }
        return users;
    }

    @Override
    public ArrayList<User> getBy(Function<User, Boolean> predicate) {
        ArrayList<User> users = new ArrayList<>();
        var rawData = getRaw(TableNames.User);
        for (Iterator<String> it = rawData.keys(); it.hasNext(); ) {
            var key = it.next();
            var tempUser = User.createFromJson(rawData.getJSONObject(key));
            if(predicate.apply(tempUser)) {
                users.add(tempUser);
            }
        }
        return users;
    }
}
