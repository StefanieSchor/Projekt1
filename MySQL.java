import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class MySQL {
    // Database name and login-information
    static final String DB = "projsem1";
    static final String USER = "projekt";
    static final String PASS = "iamdb321";
    
    //JDBC driver name and database url
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.itu.dk/" + DB;
    
    // arraylists for movie names, and length.
    public ArrayList<Forestilling> shows = new ArrayList<Forestilling>();
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    
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
                movies.add(new Movie(rmovies.getInt("moviekey"), rmovies.getString("movieName"), rmovies.getLong("movieLength"))); // fix the long time thing.
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
            
            String sql = "SELECT * FROM showList";
            ResultSet rshows = statement.executeQuery(sql);
            while (rshows.next()) {
                if (!rshows.getBoolean("shown")) {
                    shows.add(new Forestilling(rshows.getInt("showID"), rshows.getLong("startTime"), movies.get(rshows.getInt("movieID")-1), rshows.getInt("sal")));
                }
            }
        }
        catch (Exception e) {
            System.out.println("IT DIEDED!!!");
        }
    }
    
    public void createShow(int showID) {
        Connection connection = null;
        Statement statement = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM showList WHERE showID = " + showID;
            ResultSet rStepOne = statement.executeQuery(sql);
            while (rStepOne != null && rStepOne.next()) {
                int salID = rStepOne.getInt("sal");
                String sqlStepTwo = "SELECT * FROM sal WHERE salId = " + salID;
                ResultSet rStepTwo = statement.executeQuery(sqlStepTwo);
                while (rStepTwo.next()) {
                    int rows = rStepTwo.getInt("rows");
                    int seatsInRow = rStepTwo.getInt("seatsInRow");
                    String sqlStepThree = "CREATE TABLE show_id" + showID + " (seatID int(11) PRIMARY KEY auto_increment, occupied int(11) NOT NULL)";
                    statement.executeQuery(sqlStepThree);
                    String sqlStepFour = " INSERT INTO show_id" + showID + " (occupied) VALUES (0)";
                    for (int i = 0; i < rows * seatsInRow; i++) {
                        statement.executeQuery(sqlStepFour);
                    }
                    }
            }
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void sendMovies() {
            Movie movie = new Movie(movies);
    }
    
    public void sendShows() {
        Forestilling forestillinger = new Forestilling(shows);
    }
    
    
}