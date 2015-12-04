import java.util.*;

public class Movie extends Date {
    public long movieLength;
    public String movieName;
    public int movieID;
    public static ArrayList<Movie> movies;
    
    public Movie (int movieID, String movieName, long time) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieLength = movieLength;
    }
    
    public Movie(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    
    public static ArrayList<Movie> getMovies() {
        return movies;
    }
    
    public static void printMovies() {
        for(Movie movie : movies) {
            System.out.println(movie.movieName);
            System.out.println(movie.movieLength);
        }
    }
    
    public String getName() {
        return movieName;
    }
    
    public Movie getMovie() {
        return this;
    }
}
