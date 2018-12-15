package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSONParser implements Parser {

    private File dataFile;

    public JSONParser(File dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public List<String> parseExpressions() {
        List<String> res = new ArrayList<>();
        res.add("args[0] + args[1]");
        res.add("args[0] * args[1]");
        res.add("args[0] - args[1]");
        res.add("(args[0] - args[1])/2");

        return res;//todo need to realise
    }
}
