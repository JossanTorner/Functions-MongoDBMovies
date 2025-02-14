import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieFunctionTest {

    MovieFunction movieFunction = new MovieFunction();

    public List<Movie> getTestMovieList(){
        List<Movie> movieList = new ArrayList();
        movieList.add(new Movie("1", "Inception", 2010, List.of("Action", "Sci-Fi", "Thriller"),
                "Christopher Nolan", List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"),
                8.8, List.of("English", "Japanese", "French"), 148));

        movieList.add(new Movie("2", "The Shawshank Redemption", 1994, List.of("Drama"),
                "Frank Darabont", List.of("Tim Robbins", "Morgan Freeman", "Bob Gunton"),
                9.3, List.of("English"), 142));

        movieList.add(new Movie("3", "Parasite", 2019, List.of("Comedy", "Drama", "Thriller"),
                "Bong Joon-ho", List.of("Song Kang-ho", "Lee Sun-kyun", "Cho Yeo-jeong"),
                8.6, List.of("Korean", "English"), 132));

        movieList.add(new Movie("4", "The Dark Knight", 2008, List.of("Action", "Crime", "Drama"),
                "Christopher Nolan", List.of("Christian Bale", "Heath Ledger", "Aaron Eckhart"),
                9.0, List.of("English"), 152));

        movieList.add(new Movie("4", "Small cast movie", 2008, List.of("Drama"),
                "No one", List.of("Morgan Freeman"),
                9.0, List.of("English"), 100));
        return movieList;

    }

    @Test
    void amountOfMoviesTest() {
        assertEquals(5, movieFunction.amountOfMovies(getTestMovieList()));
        assertNotEquals(0, movieFunction.amountOfMovies(getTestMovieList()));
    }

    @Test
    void getLongestMovieTest() {
        assertEquals(152, movieFunction.getLongestMovie(getTestMovieList()));
    }

    @Test
    void getGenresTest() {
        assertEquals(6, movieFunction.getGenres(getTestMovieList()));
    }

    @Test
    void getHighestRatingMovieCastTest() {
        assertTrue(movieFunction.getHighestRatingMovieCast(getTestMovieList()).contains("Morgan Freeman"));
        assertTrue(movieFunction.getHighestRatingMovieCast(getTestMovieList()).contains("Tim Robbins"));
        assertTrue(movieFunction.getHighestRatingMovieCast(getTestMovieList()).contains("Bob Gunton"));
        assertFalse(movieFunction.getHighestRatingMovieCast(getTestMovieList()).contains("Elliot Page"));
    }

    @Test
    void getMovieWithSmallestCastTest() {
        assertEquals("Small cast movie", movieFunction.getMovieWithSmallestCast(getTestMovieList()));
    }

    @Test
    void getAmountOfActorsStarringInSeveralMoviesTest(){
        assertEquals(1, movieFunction.getAmountOfActorsStarringInSeveralMovies(getTestMovieList()));
    }

    @Test
    void getMostPopularActorsTest(){
        assertTrue(movieFunction.getMostPopularActor(getTestMovieList()).equalsIgnoreCase("Morgan Freeman"));
        assertFalse(movieFunction.getMostPopularActor(getTestMovieList()).equalsIgnoreCase("Heath Ledger"));
    }

    @Test
    void getNumberOfLanguagesTest(){
        assertEquals(4, movieFunction.getNumberOfLanguages(getTestMovieList()));
    }

    @Test
    void movieNameDuplicateExistsTest(){
        assertFalse(movieFunction.movieNameDuplicateExists(getTestMovieList()));
    }
}