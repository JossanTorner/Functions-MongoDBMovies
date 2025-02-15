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
        double maxRating = movies.stream().max(movieComparator).get().getImdbRating();
        return movies.stream().filter(e -> e.getImdbRating() == maxRating).map(Movie::getCast).flatMap(List::stream).distinct().collect(Collectors.toList());
        //return movies.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());
    }

    public String getMovieWithSmallestCast(List<Movie> movies){
        Comparator<Movie> movieComparator = Comparator.comparing(e->e.getCast().size());
        return movies.stream().min(movieComparator).map(Movie::getTitle).orElse("");
    }

    public long getAmountOfActorsStarringInSeveralMovies(List<Movie> movies){
        return movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    public List<String> getMostPopularActors(List<Movie> movies){
        //Tar fram en map med alla skådisar, namn som key, value antal filmer de varit med i: Collectors.counting räknar antalet gånger namnet uppkommer
        Map<String, Long> frequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        //Tar fram en lista med nyckel-värdepar, får ut namn och antal filmer de varit med i
        List<Map.Entry<String, Long>> entrySetOfFoundActors = frequencyMap.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getKey)).map(Map.Entry::getValue).orElse(List.of());
        //Returnerar till sist en lista med bara namnen från entry-sets
        return entrySetOfFoundActors.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public long getNumberOfLanguages(List<Movie> movies){
        return movies.stream().map(Movie::getLanguages).flatMap(List::stream).distinct().count();
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting())).entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
