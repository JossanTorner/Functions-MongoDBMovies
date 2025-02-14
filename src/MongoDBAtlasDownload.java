
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoDBAtlasDownload {

    private static List<Movie> movieList;

    private static List<Movie> getData(){
        List<Movie> downloadedData = new ArrayList<>();
        String uri = "mongodb+srv://admin:1234@cluster0.zc31r.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    downloadedData.add(Movie.fromDocument(doc));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return downloadedData;
    }

    public static List<Movie> getAllMovies(){
        if (movieList == null) {
            movieList = getData();
        }
        return movieList;
    }

}
