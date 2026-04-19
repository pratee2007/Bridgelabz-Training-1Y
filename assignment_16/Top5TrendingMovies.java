import java.util.*;
import java.util.stream.*;

public class Top5TrendingMovies {

    static class Movie {
        String title;
        double rating;
        int releaseYear;

        Movie(String title, double rating, int releaseYear) {
            this.title = title;
            this.rating = rating;
            this.releaseYear = releaseYear;
        }

        @Override
        public String toString() {
            return title + " (" + releaseYear + ") - Rating: " + rating;
        }
    }

    public static void main(String[] args) {
        List<Movie> movies = Arrays.asList(
            new Movie("Inception", 8.8, 2010),
            new Movie("The Dark Knight", 9.0, 2008),
            new Movie("Interstellar", 8.6, 2014),
            new Movie("Avengers: Endgame", 8.4, 2019),
            new Movie("Parasite", 8.5, 2019),
            new Movie("Tenet", 7.4, 2020),
            new Movie("Dune", 8.0, 2021),
            new Movie("Oppenheimer", 8.9, 2023),
            new Movie("Barbie", 6.9, 2023),
            new Movie("The Batman", 7.8, 2022)
        );

        System.out.println("=== Top 5 Trending Movies ===");

        List<Movie> top5 = movies.stream()
            .filter(m -> m.releaseYear >= 2015)                        // Only recent movies
            .sorted(Comparator.comparingDouble((Movie m) -> m.rating)
                .reversed()
                .thenComparingInt((Movie m) -> m.releaseYear)
                .reversed())                                            // Sort by rating desc, then year desc
            .limit(5)                                                   // Take top 5
            .collect(Collectors.toList());

        top5.forEach(System.out::println);
    }
}
