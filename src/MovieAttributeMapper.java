import java.util.List;

@FunctionalInterface
public interface MovieAttributeMapper {
    List <String> getAttribute(Movie movie);
}
