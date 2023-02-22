package springioc.anno.calc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml", Operation.class);

        Calc calc = context.getBean("calc", Calc.class);
        double result = calc.cal(3.2, 12.1, Calc.add);

        System.out.println(result);

        ((ClassPathXmlApplicationContext)context).close();
    }
}
