
public class MovieApp {
    public static void main(String[] args) {
        Utils.readMovies("movies_large.csv");
        Utils.readActors("actors_large.csv");
        Utils.readDirectors("directors_large.csv");
    }
}
