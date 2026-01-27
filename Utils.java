import java.io.*;
import java.util.*;
public class Utils {
    public static Scanner sc = new Scanner(System.in);
    static List<Movie> movies = new ArrayList<>();
    static List<Actor> actors = new ArrayList<>();
    static List<Director> directors = new ArrayList<>();

    static Map<Integer, Movie> movieMap = new HashMap<>();
    static Map<Integer, Actor> actorMap = new HashMap<>();
    static Map<Integer, Director> directorMap = new HashMap<>();

    static class Movie {
        int movieId;
        String title;
        int releaseYear;
        String genre;
        double rating;
        int duration;
        int directorId;
        List<Integer> actorIds;

        Movie(int movieId, String title, int releaseYear, String genre,
                double rating, int duration, int directorId, List<Integer> actorIds) {
            this.movieId = movieId;
            this.title = title;
            this.releaseYear = releaseYear;
            this.genre = genre;
            this.rating = rating;
            this.duration = duration;
            this.directorId = directorId;
            this.actorIds = actorIds;
        }

        @Override
        public String toString() {
            return "Movie ID: " + movieId +
                    "\nTitle: " + title +
                    "\nRelease Year: " + releaseYear +
                    "\nGenre: " + genre +
                    "\nRating: " + rating +
                    "\nDuration: " + duration + " mins" +
                    "\nDirector ID: " + directorId +
                    "\nActor IDs: " + actorIds;
        }

    }

    static class Actor {
        int actorId;
        String name;
        String dateOfBirth;
        String nationality;

        Actor(int actorId, String name, String dateOfBirth, String nationality) {
            this.actorId = actorId;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.nationality = nationality;
        }

        @Override
        public String toString() {
            return "Actor ID: " + actorId +
                    ", Name: " + name +
                    ", DOB: " + dateOfBirth +
                    ", Nationality: " + nationality;
        }

    }

    static class Director {
        int directorId;
        String name;
        String dateOfBirth;
        String nationality;

        Director(int directorId, String name, String dateOfBirth, String nationality) {
            this.directorId = directorId;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.nationality = nationality;
        }

        @Override
        public String toString() {
            return "Director ID: " + directorId +
                    ", Name: " + name +
                    ", DOB: " + dateOfBirth +
                    ", Nationality: " + nationality;
        }

    }

    static void readMovies(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 8);

                String actorIdString = data[7].replace("\"", "");
                List<Integer> actorIds = new ArrayList<>();
                for (String id : actorIdString.split(",")) {
                    actorIds.add(Integer.parseInt(id.trim()));
                }

                Movie movie = new Movie(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        Double.parseDouble(data[4]),
                        Integer.parseInt(data[5]),
                        Integer.parseInt(data[6]),
                        actorIds);

                movies.add(movie);
                movieMap.put(movie.movieId, movie);
            }
        } catch (Exception e) {
            System.out.println("Error reading movies");
        }
    }

    static void readActors(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Actor actor = new Actor(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3]);

                actors.add(actor);
                actorMap.put(actor.actorId, actor);
            }
        } catch (Exception e) {
            System.out.println("Error reading actors");
        }
    }

    static void readDirectors(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Director director = new Director(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3]);

                directors.add(director);
                directorMap.put(director.directorId, director);
            }
        } catch (Exception e) {
            System.out.println("Error reading directors");
        }
    }
    public static void getMovieInfo() {

        System.out.println("Search movie by:");
        System.out.println("1. Movie ID");
        System.out.println("2. Movie Title");
        int choice = sc.nextInt();
        sc.nextLine();

        Movie movie = null;

        if (choice == 1) {
            System.out.print("Enter Movie ID: ");
            int id = sc.nextInt();
            movie = movieMap.get(id);

        } else if (choice == 2) {
            System.out.print("Enter Movie Title: ");
            String title = sc.nextLine();

            for (Movie m : movies) {
                if (m.title.equalsIgnoreCase(title)) {
                    movie = m;
                    break;
                }
            }
        }

        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        System.out.println("\n===== MOVIE DETAILS =====");
        System.out.println("Movie ID: " + movie.movieId);
        System.out.println("Title: " + movie.title);
        System.out.println("Release Year: " + movie.releaseYear);
        System.out.println("Genre: " + movie.genre);
        System.out.println("Rating: " + movie.rating);
        System.out.println("Duration: " + movie.duration + " mins");

        // Director details
        Director director = directorMap.get(movie.directorId);
        if (director != null) {
            System.out.println("\nDirector:");
            System.out.println("Name: " + director.name);
            System.out.println("DOB: " + director.dateOfBirth);
            System.out.println("Nationality: " + director.nationality);
        }

        // Actor details
        System.out.println("\nActors:");
        for (Integer actorId : movie.actorIds) {
            Actor actor = actorMap.get(actorId);
            if (actor != null) {
                System.out.println(
                        "- " + actor.name +
                                " | DOB: " + actor.dateOfBirth +
                                " | Nationality: " + actor.nationality);
            }
        }
    }

}
