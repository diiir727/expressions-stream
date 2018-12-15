package expressions.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse math expressions from .json file
 */
public class JSONParser implements Parser {

    private File dataFile;

    public JSONParser(File dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public List<String> parseExpressions() throws Exception{
        List<String> res = new ArrayList<>();
        String content = new String(Files.readAllBytes(dataFile.toPath()));
        JSONObject obj = new JSONObject(content);
        JSONArray array = obj.getJSONArray("expressions");
        for(int i =0; i< array.length(); i++) {
            res.add(array.getString(i));
        }
        return res;
    }
}
