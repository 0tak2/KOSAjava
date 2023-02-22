package springioc.anno.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calc {

    static final int add = 0;
    static final int minus = 1;

    @Autowired
    private AddOperation addOperation;

    @Autowired
    private MinusOperation minusOperation;
    
    @Autowired(required = false)
    private DevideOperation devideOperation;
    
    @Autowired(required = false)
    private TimesOperation timesOperation;

    public double cal(double a, double b, int op)  {
        double result = 0.0;

        switch (op) {
            case add:
                result = addOperation.execute(a, b);
                break;
            case minus:
                result = minusOperation.execute(a, b);
                break;
        }

        return result;
    }
}
