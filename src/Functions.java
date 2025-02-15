import java.util.*;
import java.util.stream.Collectors;

public class Functions {

    public long countDistinctAttributes(List<Movie> movies, MovieAttributeSearch movieAttributeSearch) {
        return movies.stream().map(movieAttributeSearch::mapper).flatMap(List::stream).distinct().count();
    }

    public long getNumberOfMovies(List<Movie> movies){
        return movies.stream().filter(movie -> movie.getYear() == 1075).count();
    }

    public long getNumberOfActorsInSeveralMovies(List<Movie> movies){
        return movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    public int getLongestMovieRuntime(List<Movie> movies){
        return movies.stream().mapToInt(Movie::getRuntime).max().getAsInt();
    }

    public List<String> getHighestRatingMovieCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getImdbRating);
        double maxRating = movies.stream().max(movieComparator).get().getImdbRating();
        return movies.stream().filter(e -> e.getImdbRating() == maxRating).map(Movie::getCast)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
        //return movies.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());
    }

    public String getMovieWithSmallestCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(e->e.getCast().size());
        return movies.stream().min(movieComparator).map(Movie::getTitle).orElse("");
    }

    public List<String> getActorsStarringInSeveralMovies(List<Movie> movies){
        //Tar fram en map med alla skådisar, namn som key, value antal filmer de varit med i: Collectors.counting räknar antalet gånger namnet uppkommer
        Map<String, Long> frequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        //Tar fram en lista med nyckel-värdepar, får ut namn och antal filmer de varit med i
        List<Map.Entry<String, Long>> entrySetOfFoundActors = frequencyMap.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getKey)).map(Map.Entry::getValue).orElse(List.of());
        //Returnerar till sist en lista med bara namnen från entry-sets
        return entrySetOfFoundActors.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
