import java.sql.*;
import java.util.*;

public class Forestilling {
    private String movieName;
    // private Time movieLength;
    private Long showStart;
    private Sal sal;
    private int showID;
    private Movie movie;
    public static ArrayList<Forestilling> shows;
        
    public Forestilling(int showID, Long showStart, Movie movie, Sal sal) {
        this.sal = sal;
        this.showID = showID;
        this.movie = movie;
        this.showStart = showStart;
    }
    
    public Forestilling(ArrayList<Forestilling> shows) {
        this.shows = shows;
    }
    
}