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

    //Fråga: Hur många UNIKA genrer har filmerna?
    //Fråga: Hur många UNIKA språk har filmerna?
    @Test
    void countDistinctAttributesTest(){
        assertEquals(4, movieFunction.countDistinctAttributes(getTestMovieList(), MovieFunctions.countLanguages));
        assertEquals(6, movieFunction.countDistinctAttributes(getTestMovieList(), MovieFunctions.countGenres));
    }

    //Fråga: Hur många filmer gjordes 1975?
    @Test
    void getNumberOfMovies1975Test() {
        assertEquals(0, movieFunction.getNumberOfMovies(getTestMovieList(), 1975));
        assertNotEquals(5, movieFunction.getNumberOfMovies(getTestMovieList(), 1975));
        assertEquals(3, movieFunction.getNumberOfMovies(getTestMovieList(), 2008));
    }

    //Fråga: Vilka skådisar spelade i den film som hade högst imdb-rating?
    @Test
    void getMovieCastFromRatingTest() {
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Morgan Freeman"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Tim Robbins"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Bob Gunton"));
        assertFalse(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMax).contains("Elliot Page"));

        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Song Kang-ho"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Lee Sun-kyun"));
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateMin).contains("Cho Yeo-jeong"));

        //Average blir 8.9 vilket ingen film har, listan borde vara tom
        assertTrue(movieFunction.getMovieCastFromRating(getTestMovieList(), MovieFunctions.calculateAverage).isEmpty());
    }

    //Fråga: Hur många skådisar var med i mer än 1 film?
    @Test
    void getNumberOfActorsInSeveralMoviesTest(){
        assertEquals(2, movieFunction.getNumberOfActorsInSeveralMovies(getTestMovieList()));
        assertNotEquals(1, movieFunction.getNumberOfActorsInSeveralMovies(getTestMovieList()));
    }

    //Fråga: Vad hette den skådis som var med i flest filmer?
    @Test
    void getActorsInAmountOfMoviesTest(){
        List<String> expectedMostMovies = Arrays.asList("Leonardo DiCaprio", "Morgan Freeman");
        assertEquals(expectedMostMovies, movieFunction.getActorsInAmountOfMovies(getTestMovieList(), MovieFunctions.calculateMax));
        assertFalse(movieFunction.getActorsInAmountOfMovies(getTestMovieList(), MovieFunctions.calculateMin).contains("Morgan Freeman"));
        assertFalse(movieFunction.getActorsInAmountOfMovies(getTestMovieList(), MovieFunctions.calculateMin).contains("Leonardo DiCaprio"));
        //Min och average borde vara samma pga avrundning för att få fram vanligast förkomna antal filmer
        assertEquals(movieFunction.getActorsInAmountOfMovies(getTestMovieList(), MovieFunctions.calculateAverage),
                movieFunction.getActorsInAmountOfMovies(getTestMovieList(), MovieFunctions.calculateMin));
    }

    //Fråga: Finns det någon titel som mer än en film har?
    @Test
    void movieNameDuplicateExistsTest(){
        assertFalse(movieFunction.movieNameDuplicateExists(getTestMovieList()));
    }

    //Fråga: Hitta längden på den film som var längst (högst runtime).
    @Test
    void getRuntimeTest(){
        assertEquals(152, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMax));
        assertEquals(100, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMin));
        assertEquals(129, movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateAverage));
        assertFalse(movieFunction.getRuntime(getTestMovieList(), MovieFunctions.calculateMax) == 60);
    }

    //Fråga: Vad är titeln på den film som hade minst antal skådisar listade?
    @Test
    void getMoviesAfterCastSizeTest(){
        List<String> expectedMax = Arrays.asList("Inception", "The Shawshank Redemption", "Parasite", "The Dark Knight");
        List<String> expectedMin = Arrays.asList("Small cast movie", "Another small cast movie");

        assertEquals(expectedMax, movieFunction.getMoviesAfterCastSize(getTestMovieList(), MovieFunctions.calculateMax));
        assertEquals(expectedMin, movieFunction.getMoviesAfterCastSize(getTestMovieList(), MovieFunctions.calculateMin));
        assertTrue(movieFunction.getMoviesAfterCastSize(getTestMovieList(), MovieFunctions.calculateAverage).contains("Inception"));
        //Max och average vara samma pga min avrundning för att få fram vanligaste förekomna cast size
        assertEquals(expectedMax, (movieFunction.getMoviesAfterCastSize(getTestMovieList(), MovieFunctions.calculateAverage)));
    }
}