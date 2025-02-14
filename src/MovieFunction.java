import java.util.*;
import java.util.stream.Collectors;

public class MovieFunction {

    public long amountOfMovies(List<Movie> movies){
        return movies.stream().count();
    }

    public int getLongestMovie(List<Movie> movies){
        return movies.stream().mapToInt(Movie::getRuntime).max().getAsInt();
    }

    public long getGenres(List<Movie> movies){
        return movies.stream().map(Movie::getGenres).flatMap(List::stream).distinct().count();
    }

    public List<String> getHighestRatingMovieCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getImdbRating);
        return movies.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());
    }

    public String getMovieWithSmallestCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(e->e.getCast().size());
        return movies.stream().min(movieComparator).map(Movie::getTitle).orElse("");
    }

    public long getAmountOfActorsStarringInSeveralMovies(List<Movie> movies){
        return movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    public String getMostPopularActor(List<Movie> movies){
        return movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey();
    }

    public long getNumberOfLanguages(List<Movie> movies){
        return movies.stream().map(Movie::getLanguages).flatMap(List::stream).distinct().count();
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting())).entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
