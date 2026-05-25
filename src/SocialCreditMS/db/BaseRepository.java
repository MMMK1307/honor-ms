package SocialCreditMS.db;

import SocialCreditMS.Model.BaseModel;
import SocialCreditMS.Util.JsonHelper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public interface BaseRepository<M extends BaseModel> {
    String dbBasePath = "./src/SocialCreditMS/db/data/";

    default boolean save(M model) {
        JSONObject tableData = getRaw(model.getTableName());
        tableData.put(model.getId().toString(), model.toJson());
        String path = dbBasePath + model.getTableName();
        JsonHelper.writeJsonFile(path, tableData);
        return true;
    }
    default JSONObject getRaw(String tableName) {
        String path = dbBasePath + tableName;
        JSONObject tableData = JsonHelper.readJsonFileOrEmpty(path);
        return tableData;
    }

    M getById(UUID id);
    ArrayList<M> getAll();
    ArrayList<M> getBy(Function<M, Boolean> predicate);
}
