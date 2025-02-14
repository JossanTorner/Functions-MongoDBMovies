public class Main {

    public static void main(String[] args) {
        FunctionCaller c = new FunctionCaller(MongoDBAtlasDownload.getDownloadedMovies());
        c.callAllOutputs();
    }
}
