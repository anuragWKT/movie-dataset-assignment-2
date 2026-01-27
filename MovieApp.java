
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
            System.out.println("4. Get Movies by director name");
            System.out.println("5. Get Movies by release year");
            System.out.println("6. Get Movies by release year range");
            System.out.println("7. add new movie");
            System.out.println("8. update movie rating");
            System.out.println("9. delete movie");
            System.out.println("10. top 15 movies by release year");
            System.out.println("11. top 5 directors with most movies");
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
                case 4:
                    Utils.getMoviesByDirector();
                    break;
                case 5:
                    Utils.getMoviesByReleaseYear();
                    break;
                case 6:
                    Utils.getMoviesByReleaseYearRange();
                    break;
                case 7:
                    Utils.addNewMovie();
                    break;
                case 8:
                    Utils.updateMovieRating();
                    break;
                case 9:
                    Utils.deleteMovie();
                    break;
                case 10:
                    Utils.getTop15MoviesByReleaseYear();
                    break;
                case 11:
                    Utils.getTop5DirectorsWithMostMovies();
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
