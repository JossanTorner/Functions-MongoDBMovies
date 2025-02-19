import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieFunctionsTest {

    MovieFunctions movieFunction = new MovieFunctions();

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
                "No one", List.of("Morgan Freeman", "Leonardo DiCaprio"),
                9.0, List.of("English"), 100));
        movieList.add(new Movie("4", "Another small cast movie", 2008, List.of("Drama"),
                "No one", List.of("Blabla", "Trala"),
                8.8, List.of("English"), 100));
        return movieList;

    }

    @Test
    void countDistinctAttributesTest(){
        assertEquals(4, movieFunction.countDistinctAttributes(getTestMovieList(), MovieFunctions.countLanguages));
        assertEquals(6, movieFunction.countDistinctAttributes(getTestMovieList(), MovieFunctions.countGenres));
    }

    @Test
    void getNumberOfMovies1975Test() {
        assertEquals(0, movieFunction.getNumberOfMovies(getTestMovieList(), 1975));
        assertNotEquals(5, movieFunction.getNumberOfMovies(getTestMovieList(), 1975));
        assertEquals(3, movieFunction.getNumberOfMovies(getTestMovieList(), 2008));
    }

    @Test
    void getMovieCastFromRatingTest() {
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Morgan Freeman"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Tim Robbins"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Bob Gunton"));
        assertFalse(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Elliot Page"));

        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Song Kang-ho"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Lee Sun-kyun"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Cho Yeo-jeong"));
    }

    @Test
    void getNumberOfActorsInSeveralMoviesTest(){
        assertEquals(2, movieFunction.getNumberOfActorsInSeveralMovies(getTestMovieList()));
        assertNotEquals(1, movieFunction.getNumberOfActorsInSeveralMovies(getTestMovieList()));
    }

    @Test
    void getActorsStarringInMostOrLeastMoviesTest(){
        List<String> expectedMostMovies = Arrays.asList("Leonardo DiCaprio", "Morgan Freeman");
        assertEquals(expectedMostMovies, movieFunction.getActorsStarringInMostOrLeastMovies(getTestMovieList(), MovieFunctions.calculateMax));
        assertFalse(movieFunction.getActorsStarringInMostOrLeastMovies(getTestMovieList(), MovieFunctions.calculateMin).contains("Morgan Freeman"));
        assertFalse(movieFunction.getActorsStarringInMostOrLeastMovies(getTestMovieList(), MovieFunctions.calculateMin).contains("Leonardo DiCaprio"));
    }

    @Test
    void movieNameDuplicateExistsTest(){
        assertFalse(movieFunction.movieNameDuplicateExists(getTestMovieList()));
    }

    @Test
    void getRuntimeTest(){
        assertEquals(152, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMax));
        assertEquals(100, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMin));
        assertEquals(129, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateAverage));
        assertFalse(movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMax) == 60);
    }

    @Test
    void getMoviesWithSmallestOrLargestCastTest(){
        assertEquals("Inception, The Shawshank Redemption, Parasite, The Dark Knight",
                movieFunction.getMoviesWithSmallestOrLargestCast(getTestMovieList(), MovieFunctions.calculateMax));
        assertEquals("Small cast movie, Another small cast movie",
                movieFunction.getMoviesWithSmallestOrLargestCast(getTestMovieList(), MovieFunctions.calculateMin));
    }
}