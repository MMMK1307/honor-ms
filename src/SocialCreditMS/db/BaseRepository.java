package SocialCreditMS.db;

import SocialCreditMS.Model.BaseModel;
import SocialCreditMS.Util.JsonHelper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public interface BaseRepository<M extends BaseModel> {
    String dbBasePath = "./src/SocialCreditMS/db/data/";

    private boolean saveTable(String tableName, JSONObject tableData) {
        String path = dbBasePath + tableName;
        return JsonHelper.writeJsonFile(path, tableData);
    }

    default boolean save(M model) {
        JSONObject tableData = getRaw(model.getTableName());
        tableData.put(model.getId().toString(), model.toJson());
        return saveTable(model.getTableName(), tableData);
    }

    default JSONObject getRaw(String tableName) {
        String path = dbBasePath + tableName;
        return JsonHelper.readJsonFileOrEmpty(path);
    }

    default boolean delete(M model) {
        JSONObject tableData = getRaw(model.getTableName());
        tableData.remove(model.getId().toString());
        return saveTable(model.getTableName(), tableData);
    }

    M getById(UUID id);
    ArrayList<M> getAll();
    ArrayList<M> getBy(Function<M, Boolean> predicate);
}
