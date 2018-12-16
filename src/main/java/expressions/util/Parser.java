package expressions.util;

import java.util.List;

public interface Parser {

    /**
     * @return list of expression from data source
     * @throws Exception
     */
    List<String> parseExpressions() throws Exception;
}
