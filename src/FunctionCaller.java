import java.util.List;

public class FunctionCaller {

    Functions func = new Functions();
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
        outputMostPopularActor();
        outputNumberOfLanguages();
        outputDuplicateMovieName();
    }

    public void outputAmountOfMovies() {
        System.out.println("Movies 1975: " + func.getNumberOfMovies(movieList));
    }

    public void outputAmountOfGenres() {
        System.out.println("Amount of genres: " + func.countDistinctAttributes(movieList, Movie::getGenres));
    }

    public void outputNumberOfLanguages(){
        System.out.println("Amount of languages: " + func.countDistinctAttributes(movieList, Movie::getLanguages));
    }

    public void outputLongestMovie() {
        System.out.println("Longest movie: " + func.getLongestMovieRuntime(movieList) + " min");
    }


    public void outputActorsInTopRatedMovies() {
        System.out.println("Actors in top rated movie: ");
        func.getHighestRatingMovieCast(movieList).forEach(System.out::println);
    }

    public void outputMovieWithSmallestCast() {
        System.out.println("Movie with the smallest cast: " + func.getMovieWithSmallestCast(movieList));
    }

    public void outputAmountOfActorsStarringInSeveralMovies() {
        System.out.println("Actors that occurs in more than 1 movie: " + func.getNumberOfActorsInSeveralMovies(movieList));
    }

    public void outputMostPopularActor(){
//        System.out.println("Actor starring in most movies: " + func.getMostPopularActor(movieList));
        System.out.println("Actors starring in most movies:");
        func.getMostPopularActors(movieList).forEach(System.out::println);
    }


    public void outputDuplicateMovieName(){
        System.out.println("A movie name occurs more than once: " + func.movieNameDuplicateExists(movieList));
    }

}
