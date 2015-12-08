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
    private java.util.Date date;
    public java.util.Date[] dates;
    public ArrayList<java.util.Date> showDates;
        
    public Forestilling(int showID, java.util.Date date, Long showStart, Movie movie, Sal sal) {
        this.sal = sal;
        this.showID = showID;
        this.movie = movie;
        this.showStart = showStart;
        this.date = date;
    }
    
    public Forestilling(ArrayList<Forestilling> shows) {
        this.shows = shows;
        ArrayList<java.util.Date> showDates = new ArrayList<java.util.Date>();
        for (int i = 0; i < shows.size(); i++)
            showDates.add(shows.get(i).date);
        // some function to remove duplicate dates needed her.
        dates = new java.util.Date[showDates.size()];
        for (int i = 0; i < showDates.size(); i++)
            dates[i] = showDates.get(i);
    }
    
    public static ArrayList<Forestilling> getShows() {
        return shows;
    }
    
    public java.util.Date getShowDate() {
        return this.date;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public Long getShowStart() {
        return this.showStart;
    }
    
    public Sal getSal() {
        return this.sal;
    }
    
    public int getShowID() {
        return this.showID;
    }
}