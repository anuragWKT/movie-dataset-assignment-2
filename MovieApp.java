
public class MovieApp {
    public static void main(String[] args) {
        Utils.readMovies("movies_large.csv");
        Utils.readActors("actors_large.csv");
        Utils.readDirectors("directors_large.csv");
        while (true) {
            System.out.println("-------menu----------");
            System.out.println("1. Get Movie Information");
            System.out.println("2. Get Top 10 Rated Movies");
            System.out.println("3. Get Movies by genre");
            System.out.println("14. Exit");

            int choice = Utils.sc.nextInt();
            Utils.sc.nextLine();
            switch (choice) {
                case 1:
                    Utils.getMovieInfo();
                    break;
                case 2:
                    Utils.getTopTenMovies();
                    break;
                case 3:
                    Utils.getMoviesByGenre();
                    break;
                case 14:
                    System.out.println("Exiting program...");
                    return;
                default:
                    break;
            }
        }
    }
}
