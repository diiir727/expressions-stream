package expressions.generator;

public interface ExpressionGenerator {

    /**
     *
     * @param expression math expression
     * @return implementation of interface with custom expression
     * @throws Exception when can't create object
     */
    Expression generate(String expression) throws Exception;

}
