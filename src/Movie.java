import java.util.List;

import org.bson.Document;

public class Movie {

    private final String id;
    private final String title;
    private final int year;
    private final int runtime;
    private final List<String> genres;
    private final String director;
    private final List<String> cast;
    private final double imdbRating;
    private final List<String> languages;

    // Constructor
    public Movie(String id, String title, int year, List<String> genres, String director, List<String> cast, double imdbRating, List<String> languages, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.cast = cast;
        this.imdbRating = imdbRating;
        this.languages = languages;
    }

    // Method to convert MongoDB document to Movie object
    public static Movie fromDocument(Document doc) {
        return new Movie(
                doc.getObjectId("_id").toString(),
                doc.getString("title"),
                doc.getInteger("year", 0),
                doc.getList("genres", String.class),
                doc.getString("director"),
                doc.getList("cast", String.class),
                doc.get("imdb", Document.class) != null ? doc.get("imdb", Document.class).getDouble("rating") : 0.0,
                doc.getList("languages", String.class),
                doc.getInteger("runtime", 0)
        );
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public int getRuntime() { return runtime; }
    public List<String> getGenres() { return genres; }
    public String getDirector() { return director; }
    public List<String> getCast() { return cast; }
    public double getImdbRating() { return imdbRating; }
    public List<String> getLanguages() { return languages; }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", genres=" + genres +
                ", director='" + director + '\'' +
                ", cast=" + cast +
                ", imdbRating=" + imdbRating +
                ", languages=" + languages +
                '}';
    }
}
