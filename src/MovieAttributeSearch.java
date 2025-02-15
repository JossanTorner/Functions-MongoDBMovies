import java.util.List;

@FunctionalInterface
public interface MovieAttributeSearch<T> {
    List<T> mapper(Movie movie);
}
