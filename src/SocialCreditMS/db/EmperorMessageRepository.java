package SocialCreditMS.db;

import SocialCreditMS.Model.EmperorMessage;
import SocialCreditMS.Util.TableNames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class EmperorMessageRepository implements BaseRepository<EmperorMessage> {

    @Override
    public EmperorMessage getById(UUID id) {
        var tableData = getRaw(TableNames.EmperorMessage);
        var messageData = tableData.getJSONObject(id.toString());
        return EmperorMessage.createFromJson(messageData);
    }

    @Override
    public ArrayList<EmperorMessage> getAll() {
        ArrayList<EmperorMessage> empMessages = new ArrayList<>();
        var tableData = getRaw(TableNames.EmperorMessage);
        for (Iterator<String> it = tableData.keys(); it.hasNext(); ) {
            var key = it.next();
            var message = EmperorMessage.createFromJson(tableData.getJSONObject(key));
            empMessages.add(message);
        }
        return empMessages;
    }

    @Override
    public ArrayList<EmperorMessage> getBy(Function<EmperorMessage, Boolean> predicate) {
        ArrayList<EmperorMessage> empMessages = new ArrayList<>();
        var tableData = getRaw(TableNames.EmperorMessage);
        for (Iterator<String> it = tableData.keys(); it.hasNext(); ) {
            var key = it.next();
            var message = EmperorMessage.createFromJson(tableData.getJSONObject(key));
            if(predicate.apply(message)) {
                empMessages.add(message);
            }
        }
        return empMessages;
    }
}
