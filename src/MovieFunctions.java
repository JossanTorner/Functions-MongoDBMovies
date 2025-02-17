import java.util.*;
import java.util.stream.Collectors;

public class MovieFunctions {

    public long getNumberOfMovies(List<Movie> movies){
        return movies.stream().filter(movie -> movie.getYear() == 1975).count();
    }


    public int getLongestMovieRuntime(List<Movie> movies){
        return movies.stream().mapToInt(Movie::getRuntime).max().getAsInt();
    }

    public long countDistinctAttributes(List<Movie> movies, MovieAttributeSearch movieAttributeSearch) {
        return movies.stream().map(movieAttributeSearch::getAttribute).flatMap(List::stream).distinct().count();
    }

    public List<String> getHighestRatingMovieCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getImdbRating);
        double maxRating = movies.stream().max(movieComparator).get().getImdbRating();
        return movies.stream().filter(e -> e.getImdbRating() == maxRating).map(Movie::getCast)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
        //return movies.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());
    }

    public String getMovieWithSmallestCast(List<Movie> movies){
        int smallestCastSize = movies.stream().mapToInt(e -> e.getCast().size()).min().orElse(0);
        return movies.stream().filter(e->e.getCast().size() == smallestCastSize).map(Movie::getTitle).collect(Collectors.joining(", "));
    }

    public long getNumberOfActorsInSeveralMovies(List<Movie> movies){
        return getActorFrequencyMap(movies).entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    public List<String> getActorsStarringInMostMovies(List<Movie> movies){
        Long highestAmountOfMovies = getActorFrequencyMap(movies).values().stream().max(Comparator.naturalOrder()).orElse(null);
        return getActorFrequencyMap(movies).entrySet().stream().filter(e->e.getValue() == highestAmountOfMovies).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

    public Map<String, Long> getActorFrequencyMap(List<Movie> movies){
        //Tar fram en hashmap med skådis som key, hur många filmer de varit med i som value
        return movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));
    }

}
