package springioc.anno.calc;

public class MinusOperation implements Operation {
    @Override
    public double execute(double a, double b) {
        return a - b;
    }
}
