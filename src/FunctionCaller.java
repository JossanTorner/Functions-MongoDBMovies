import java.util.List;

public class FunctionCaller {

    private final MovieFunctions func = new MovieFunctions();
    private final List<Movie> movieList;

    public FunctionCaller(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void callAllOutputs(){
        outputAmountOfMovies();
        outputLongestMovie();
        outputAmountOfGenres();
        outputActorsInTopRatedMovies();
        outputMovieWithSmallestCast();
        outputAmountOfActorsStarringInSeveralMovies();
        outputMostPopularActors();
        outputNumberOfLanguages();
        outputDuplicateMovieName();
    }

    public void outputAmountOfMovies() {
        System.out.println("\nMovies 1975: " + func.getNumberOfMovies(movieList, 1975));
    }

    public void outputAmountOfGenres() {
        System.out.println("\nAmount of genres: " + func.countDistinctAttributes(movieList, MovieFunctions.countGenres));
    }

    public void outputNumberOfLanguages(){
        System.out.println("\nAmount of languages: " + func.countDistinctAttributes(movieList, MovieFunctions.countLanguages));
    }

    public void outputLongestMovie() {
        System.out.println("\nLongest movie: " + func.getRuntime(movieList, MovieFunctions.calculateMax) + " min");
    }

    public void outputActorsInTopRatedMovies() {
        System.out.println("\nActors in top rated movie: ");
        func.getMovieCastFromRating(movieList, MovieFunctions.calculateMax).forEach(System.out::println);
    }

    public void outputMovieWithSmallestCast() {
        System.out.println("\nMovies with the smallest cast: " + func.getMoviesAfterCastSize(movieList, MovieFunctions.calculateMin));
    }

    public void outputAmountOfActorsStarringInSeveralMovies() {
        System.out.println("\nActors that occurs in more than 1 movie: " + func.getNumberOfActorsInSeveralMovies(movieList));
    }

    public void outputMostPopularActors(){
        System.out.println("\nActors starring in most movies:");
        func.getActorsInAmountOfMovies(movieList, MovieFunctions.calculateMax).forEach(System.out::println);
        System.out.println("AVERAGE");
        func.getActorsInAmountOfMovies(movieList, MovieFunctions.calculateAverage).forEach(System.out::println);
    }

    public void outputDuplicateMovieName(){
        String answer = func.movieNameDuplicateExists(movieList) ? "\nThere exists at least one duplicate movie name" : "\nThere exists no duplicate movie names";
        System.out.println(answer);
    }

}
