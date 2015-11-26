import java.sql.*;
import java.util.ArrayList;

public class MySQL {
    // Database name and login-information
    static final String DB = "projsem1";
    static final String USER = "projekt";
    static final String PASS = "iamdb321";
    
    //JDBC driver name and database url
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.itu.dk/" + DB;
    
    // arraylists for movie names, and length.
    private ArrayList<String> movieNames = new ArrayList<String>();
    private ArrayList<String> movieLength = new ArrayList<String>();
    private ArrayList<Integer> movieID = new ArrayList<Integer>();
    
    // arraylists for upcoming shows.
    private ArrayList<Integer> showID = new ArrayList<Integer>();
    private ArrayList<Date> showDate = new ArrayList<Date>();
    private ArrayList<Time> showTime = new ArrayList<Time>();
    
    public void getMovies() {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM movieTitles";
            ResultSet rmovies = statement.executeQuery(sql);
            while (rmovies.next()) {
                if (rmovies.getBoolean("active")) {
                    movieNames.add(rmovies.getString("movieName"));
                    movieLength.add(rmovies.getString("movieLength"));
                    movieID.add(rmovies.getInt("movieID"));
                }
            }
        }
        catch (Exception e) {
            System.out.println("oh crap it fucked up");
        }
    }
    
    public void getShows() {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM forestillinger";
            ResultSet rshows = statement.executeQuery(sql);
            while (rshows.next()) {
                if (!rshows.getBoolean("shown")) {
                    showID.add(rshows.getInt("movieID"));
                    showDate.add(rshows.getDate("Date"));
                    showTime.add(rshows.getTime("startTime"));
                }
            }
        }
        catch (Exception e) {
            System.out.println("IT DIEDED!!!");
        }
    }
    
    public ArrayList<String> getMoviesList() {
        return movieNames;
    }
    
    public ArrayList<String> getLengthList() {
        return movieLength;
    }
    
    public ArrayList<Integer> getIDList() {
        return movieID;
    }
}