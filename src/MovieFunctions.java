import java.util.*;
import java.util.stream.Collectors;

public class MovieFunctions {

    //Här är klassens instanser av funktionsgränssnitt som kan användas för att göra olika beräkningar med metoderna
    public static MovieAttributeSelector countLanguages = Movie::getLanguages;
    public static MovieAttributeSelector countGenres = Movie::getGenres;
    public static Calculator calculateMax = e->e.max().orElse(0);
    public static Calculator calculateMin = e->e.min().orElse(0);

    //Fråga: Hur många filmer gjordes 1975?
    //Hämtar antalet filmer ett visst årtal
    public long getNumberOfMovies(List<Movie> movies, int year){
        return movies.stream().filter(movie -> movie.getYear() == year).count();
    }

    //Fråga: Hitta längden på den film som var längst (högst runtime).
    //Kan hämta längsta, kortaste runtime av filmer i listan
    public double getRuntime(List<Movie> movies, Calculator calculator){
        return calculator.calculate(movies.stream().mapToDouble(Movie::getRuntime));
    }

    //Fråga: Hur många UNIKA genrer har filmerna?
    //Fråga: Hur många UNIKA språk har filmerna?
    //Räkna antal UNIKA av någonting, i vårt fall språk och genrer
    public long countDistinctAttributes(List<Movie> movies, MovieAttributeSelector movieAttributeSelector) {
        return movies.stream().map(movieAttributeSelector::getAttribute).flatMap(List::stream).distinct().count();
    }

    //Fråga: Vilka skådisar spelade i den film som hade högst imdb-rating?
    //Ger lista med cast i filmer med högst eller lägst rating
    public List<String> getMovieCastFromRating(List<Movie> movies, Calculator calculator){
        double foundRating = calculator.calculate(movies.stream().map(Movie::getImdbRating).mapToDouble(rating -> rating));
        return movies.stream().filter(e -> e.getImdbRating() == foundRating).map(Movie::getCast)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
    }

    //Fråga: Vad är titeln på den film som hade minst antal skådisar listade?
    //Ger namn på filmer med störst eller minst roll-lista
    public List<String> getMoviesAfterCastSize(List<Movie> movies, Calculator calculator){
        double castSize = calculator.calculate(movies.stream().mapToDouble(e -> e.getCast().size()));
        return movies.stream().filter(e->e.getCast().size() == castSize).map(Movie::getTitle).collect(Collectors.toList());
    }

    //Fråga: Hur många skådisar var med i mer än 1 film?
    public long getNumberOfActorsInSeveralMovies(List<Movie> movies){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast)
                .flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() > 1).count();
    }

    //Fråga: Vad hette den skådis som var med i flest filmer?
    //Kan få ut skådis/skådisar i flest eller minst filmer
    public List<String> getActorsInAmountOfMovies(List<Movie> movies, Calculator calculator){
        Map<String, Long> actorFrequencyMap = movies.stream().map(Movie::getCast).flatMap(List::stream).collect(Collectors.groupingBy(actor->actor, Collectors.counting()));
        double amountOfMovies = calculator.calculate(actorFrequencyMap.values().stream().mapToDouble(e->e));
        return actorFrequencyMap.entrySet().stream().filter(e->e.getValue() == amountOfMovies)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    //Fråga: Finns det någon titel som mer än en film har?
    //Plockar ut true eller false om det finns filmer som heter samma sak
    public boolean movieNameDuplicateExists(List<Movie> movies){
        return movies.stream().map(Movie::getTitle).collect(Collectors.groupingBy(n->n, Collectors.counting()))
                .entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }
}
