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

    public static void getTopTenMovies() {
        movies.stream()
                .sorted((m1, m2) -> Double.compare(m2.rating, m1.rating))
                .limit(10)
                .forEach(movie -> {
                    System.out.println(movie);
                    System.out.println("----");
                });
    }

    public static void getMoviesByGenre() {
        System.out.print("Enter genre: ");
        String genreInput = sc.nextLine();
        List<Movie> result = movies.stream()
                .filter(movie -> movie.genre.equalsIgnoreCase(genreInput))
                .toList();

        if (result.isEmpty()) {
            System.out.println("No movies found in genre: " + genreInput);
        } else {
            result.forEach(movie -> {
                System.out.println(movie);
                System.out.println("---------------------");
            });
        }
    }

    public static void getMoviesByDirector() {
        System.out.print("Enter director name: ");
        String directorName = sc.nextLine();

        Optional<Integer> directorId = directors.stream()
                .filter(d -> d.name.equalsIgnoreCase(directorName))
                .map(d -> d.directorId)
                .findFirst();

        if (directorId.isEmpty()) {
            System.out.println("Director not found: " + directorName);
            return;
        }

        movies.stream()
                .filter(movie -> movie.directorId == directorId.get())
                .forEach(movie -> {
                    System.out.println(movie);
                    System.out.println("---------------------");
                });
    }

    public static void getMoviesByReleaseYear() {
        
        System.out.print("Enter release year: ");
        int year = sc.nextInt();

        List<Movie> result = movies.stream()
                .filter(movie -> movie.releaseYear == year)
                .toList();

        if (result.isEmpty()) {
            System.out.println("No movies found for year: " + year);
        } else {
            result.forEach(movie -> {
                System.out.println(movie);
                System.out.println("---------------------");
            });
        }
    }

    public static void getMoviesByReleaseYearRange() {
        
        System.out.print("Enter release year range (e.g. 2014-2020): ");
        String input = sc.nextLine();

        String[] years = input.split("-");
        int startYear = Integer.parseInt(years[0].trim());
        int endYear = Integer.parseInt(years[1].trim());

        movies.stream()
                .filter(movie -> movie.releaseYear >= startYear &&
                        movie.releaseYear <= endYear)
                .forEach(movie -> {
                    System.out.println(movie);
                    System.out.println("---------------------");
                });
    }

    public static void addNewMovie() {
        

        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();
        sc.nextLine(); // consume newline

        if (movieMap.containsKey(movieId)) {
            System.out.println("Movie with this ID already exists!");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Release Year: ");
        int releaseYear = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter Rating: ");
        double rating = sc.nextDouble();

        System.out.print("Enter Duration (mins): ");
        int duration = sc.nextInt();

        System.out.print("Enter Director ID: ");
        int directorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Actor IDs (comma separated): ");
        String actorInput = sc.nextLine();

        List<Integer> actorIds = Arrays.stream(actorInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        Movie movie = new Movie(
                movieId,
                title,
                releaseYear,
                genre,
                rating,
                duration,
                directorId,
                actorIds);

        movies.add(movie);
        movieMap.put(movieId, movie);

        System.out.println("Movie added successfully!");
    }

}
