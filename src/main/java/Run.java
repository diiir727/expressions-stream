import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Run {

    public static void main(String[] args) {
//        AtomicInteger count = new AtomicInteger(0);
//
//        AtomicReference<Expression> expression = new AtomicReference<>();
//        expression.set(new Expression("a + b"));
//
//        AtomicReference<Expression> expression2 = new AtomicReference<>();
//        expression2.set(new Expression("b - a"));

        /*Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                expression.set(new Expression("a + b + " + count.getAndIncrement()));
                expression2.set(new Expression("b - a - " + count.getAndIncrement()));
            }
        }, 3000, 3000);*/

        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");

//        expressions.add(expression);
//        expressions.add(expression2);
        List<AtomicReference<Expression>> expressions = generateExpressions(20);

        long start = System.currentTimeMillis();
        int i = 0;
        while(i++ < 300000) {
            try {
                for (AtomicReference<Expression> ex : expressions) {
                    BigDecimal result = ex.get().with("a",a).and("b",b).eval();
                    System.out.println(result);
                }
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);

//        timer.cancel();

    }

    private static List<AtomicReference<Expression>> generateExpressions(int n){
        List<AtomicReference<Expression>> res = new ArrayList<>();
        for(int i=0 ; i < n; i++){
            res.add(new AtomicReference<>(new Expression("a + b + " + i)));
        }
        return res;
    }

}
