import java.util.*;
import java.util.stream.Collectors;

public class MovieFunctions {

    public static FindExtreme findMax = Integer::max;
    public static FindExtreme findMin = Integer::min;
    public static MovieAttributeMapper countLanguages = Movie::getLanguages;
    public static MovieAttributeMapper countGenres = Movie::getGenres;

    public long getNumberOfMovies(List<Movie> movies){
        return movies.stream().filter(movie -> movie.getYear() == 1975).count();
    }

    public int getRuntime(List<Movie> movies, FindExtreme findExtreme){
        return movies.stream().mapToInt(Movie::getRuntime).reduce(findExtreme::maxOrMin).orElse(0);
    }

    public long countDistinctAttributes(List<Movie> movies, MovieAttributeMapper movieAttributeMapper) {
        return movies.stream().map(movieAttributeMapper::getAttribute).flatMap(List::stream).distinct().count();
    }

    public List<String> getHighestRatingMovieCast(List<Movie> movies){
        double max = movies.stream().map(Movie::getImdbRating).mapToDouble(rating -> rating).max().orElse(0);
        return movies.stream().filter(e -> e.getImdbRating() == max).map(Movie::getCast)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
    }

    public String getMoviesWithSmallestOrLargestCast(List<Movie> movies, FindExtreme findExtreme){
        int castSize = movies.stream().mapToInt(e -> e.getCast().size()).reduce(findExtreme::maxOrMin).orElse(0);
        return movies.stream().filter(e->e.getCast().size() == castSize).map(Movie::getTitle).collect(Collectors.joining(", "));
    }

    public long getNumberOfActorsInSeveralMovies(List<Movie> movies){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    public List<String> getActorsStarringInMostMovies(List<Movie> movies){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        Long maxAmountOfMovies = actorFrequencyMap.values().stream().max(Comparator.naturalOrder()).orElse(null);
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() == maxAmountOfMovies)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

}
