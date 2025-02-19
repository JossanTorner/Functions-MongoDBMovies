import java.util.stream.DoubleStream;

@FunctionalInterface
public interface Calculator {
    double calculate(DoubleStream stream);
}
