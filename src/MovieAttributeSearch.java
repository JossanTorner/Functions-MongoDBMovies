import java.util.List;

@FunctionalInterface
public interface MovieAttributeSearch {
    List <String> mapper(Movie movie);
}
