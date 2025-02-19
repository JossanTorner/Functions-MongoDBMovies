import java.util.stream.DoubleStream;

@FunctionalInterface
public interface MovieCalculator {
    double calculate(DoubleStream stream);
}
