package expressions.generator;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionExpressionGeneratorTest {

    @Test
    public void testCorrectCreate() throws Exception {
        String function = "args[0] * args[1]";
        ReflectionExpressionGenerator generator = new ReflectionExpressionGenerator(
                "expressions.generator.Expression",
                "double",
                "double... args"
        );
        Expression exp = generator.generate(function);
        Assert.assertEquals(exp.calc(2, 3), 6d, 0);
    }

    @Test(expected = Exception.class)
    public void testNotCorrectCreate() throws Exception {
        String function = "args[0 - args[1]";
        ReflectionExpressionGenerator generator = new ReflectionExpressionGenerator(
                "expressions.generator.Expression",
                "double",
                "double... args"
        );
        generator.generate(function);
    }

}
