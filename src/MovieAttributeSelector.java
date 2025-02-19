import java.util.List;

@FunctionalInterface
public interface MovieAttributeSelector {
    List<String> getAttribute(Movie movie);
}
