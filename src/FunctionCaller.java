import java.util.List;

public class FunctionCaller {

    MovieFunctions func = new MovieFunctions();
    List<Movie> movieList;

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
        System.out.println("\nMovies 1975: " + func.getNumberOfMovies(movieList));
    }

    public void outputAmountOfGenres() {
        System.out.println("\nAmount of genres: " + func.countDistinctAttributes(movieList, MovieFunctions.countGenres));
    }

    public void outputNumberOfLanguages(){
        System.out.println("\nAmount of languages: " + func.countDistinctAttributes(movieList, MovieFunctions.countLanguages));
    }

    public void outputLongestMovie() {
        System.out.println("\nLongest movie: " + func.getRuntime(movieList, MovieFunctions.findMax) + " min");
    }

    public void outputActorsInTopRatedMovies() {
        System.out.println("\nActors in top rated movie: ");
        func.getHighestRatingMovieCast(movieList).forEach(System.out::println);
    }

    public void outputMovieWithSmallestCast() {
        System.out.println("\nMovies with the smallest cast: " + func.getMoviesWithSmallestOrLargestCast(movieList, MovieFunctions.findMin));
    }

    public void outputAmountOfActorsStarringInSeveralMovies() {
        System.out.println("\nActors that occurs in more than 1 movie: " + func.getNumberOfActorsInSeveralMovies(movieList));
    }

    public void outputMostPopularActors(){
        System.out.println("\nActors starring in most movies:");
        func.getActorsStarringInMostMovies(movieList).forEach(System.out::println);
    }

    public void outputDuplicateMovieName(){
        System.out.println("\nDoes a movie name occur more than once? : " + func.movieNameDuplicateExists(movieList));
    }

}
