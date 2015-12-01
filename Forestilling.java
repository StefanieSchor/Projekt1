import java.sql.*;
import java.util.*;

public class Forestilling {
    private String movieName;
    // private Time movieLength;
    private Long showStart;
    private int sal;
    private int showID;
    private int movieID;
    // private Movie movie;
    public static ArrayList<Forestilling> shows;
        
    public Forestilling(int showID, Long showStart, Movie movie, int sal) {
        this.sal = sal;
        this.showID = showID;
        this.movieID = movieID;
        this.showStart = showStart;
    }
    
    public Forestilling(ArrayList<Forestilling> shows) {
        this.shows = shows;
    }
    
    
}