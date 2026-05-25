package SocialCreditMS.Util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHelper {
    public static JSONObject readJsonFileOrEmpty(String filePath) {
        Path path = Path.of(filePath);
        String rawData;
        try {
            rawData = Files.readString(path);
        } catch (IOException e) {
            rawData = "{}";
        }
        return new JSONObject(rawData);
    }
    public static boolean writeJsonFile(String path, JSONObject jData) {
        try(var fileWriter = new FileWriter(path)) {
            fileWriter.write(jData.toString());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
