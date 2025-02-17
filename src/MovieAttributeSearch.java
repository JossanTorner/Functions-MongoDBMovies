import java.util.List;

@FunctionalInterface
public interface MovieAttributeSearch {
    List <String> getAttribute(Movie movie);
}
