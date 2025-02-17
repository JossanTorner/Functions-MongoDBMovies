import java.util.*;
import java.util.stream.Collectors;

public class MovieFunctions {

    //Här är klassens instanser av funktionsgränssnitt som kan användas för att göra olika beräkningar med metoderna
    public static FindExtreme findMax = Double::max;
    public static FindExtreme findMin = Double::min;
    public static MovieAttributeMapper countLanguages = Movie::getLanguages;
    public static MovieAttributeMapper countGenres = Movie::getGenres;

    //Hämtar antalet filmer ett visst årtalg
    public long getNumberOfMovies(List<Movie> movies, int year){
        return movies.stream().filter(movie -> movie.getYear() == year).count();
    }

    //Kan hämta längsta eller kortaste runtime
    public double getLongestOrShortestRuntime(List<Movie> movies, FindExtreme findExtreme){
        return movies.stream().mapToDouble(Movie::getRuntime).reduce(findExtreme::maxOrMin).orElse(0);
    }

    //Räkna antal UNIKA av någonting, i vårt fall språk och genrer
    public long countDistinctAttributes(List<Movie> movies, MovieAttributeMapper movieAttributeMapper) {
        return movies.stream().map(movieAttributeMapper::getAttribute).flatMap(List::stream).distinct().count();
    }

    //Ger lista med cast i filmer med högst rating eller lägst rating
    public List<String> getMovieCastFromRating(List<Movie> movies, FindExtreme findExtreme){
        double max = movies.stream().map(Movie::getImdbRating).mapToDouble(rating -> rating).reduce(findExtreme::maxOrMin).orElse(0);
        return movies.stream().filter(e -> e.getImdbRating() == max).map(Movie::getCast)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
    }

    //Ger namn på filmer med störst eller minst cast
    public String getMoviesWithSmallestOrLargestCast(List<Movie> movies, FindExtreme findExtreme){
        double castSize = movies.stream().mapToDouble(e -> e.getCast().size()).reduce(findExtreme::maxOrMin).orElse(0);
        return movies.stream().filter(e->e.getCast().size() == castSize).map(Movie::getTitle).collect(Collectors.joining(", "));
    }

    public long getNumberOfActorsInSeveralMovies(List<Movie> movies){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    //Kan få ut skådisar i flest eller minst filmer (sista ger alla som bara varit med i 1 film i listan)
    public List<String> getActorsStarringInMostOrLeastMovies(List<Movie> movies, FindExtreme findExtreme){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        double maxAmountOfMovies = actorFrequencyMap.values().stream().mapToDouble(e->e).reduce(findExtreme::maxOrMin).orElse(0);
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() == maxAmountOfMovies)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

}
