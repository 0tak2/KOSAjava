package springioc.anno.calc;

public class AddOperation implements Operation {

    @Override
    public double execute(double a, double b) {
        return a + b;
    }
}
