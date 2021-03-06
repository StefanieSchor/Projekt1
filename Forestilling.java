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
    private boolean[][] pladser;

    public Forestilling(int showID, java.util.Date date, Long showStart, Movie movie, Sal sal) {
        this.sal = sal;
        this.showID = showID;
        this.movie = movie;
        this.showStart = showStart;
        this.date = date;
        this.pladser = makeSeatings();
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

    public static Forestilling findById(int id) {
        for (Forestilling show : shows) {
            if (id == show.showID)
                return show;
        }
        return null;
    }

    public boolean[][] makeSeatings() {
        int rows = sal.getRows();
        int seatsInRow = sal.getSeatsInRow();
        pladser = new boolean[rows][seatsInRow];
        return pladser;
    }

    public boolean[][] getSeatings() {
        return this.pladser;
    }

    public void setReserved(ArrayList<int[]> seats, ArrayList<Integer> rows){
        boolean allSeatsFree = true;
        for(int i = 0; i < rows.size(); i++){
            for(int o = 0; o < seats.get(i).length; o++){
                if(pladser[rows.get(i)][seats.get(i)[o]] == true) {
                    allSeatsFree = false;
                    System.out.println(allSeatsFree);
                }
            }
        }
        if  (allSeatsFree) {
            for(int i = 0; i < rows.size(); i++){
                for(int o = 0; o < seats.get(i).length; o++){
                    if(allSeatsFree) {
                        pladser[rows.get(i)][seats.get(i)[o]] = true;
                        System.out.println(pladser[rows.get(i)][seats.get(i)[o]]);
                    }
                }
            }
        }
    }

    public void removeReserved(ArrayList<int[]> seats, ArrayList<Integer> rows){
        boolean allSeatsTaken = true;
        for(int i = 0; i < rows.size(); i++){
            for(int o = 0; o < seats.get(i).length; o++){
                if(pladser[rows.get(i)][seats.get(i)[o]] == false) {
                    allSeatsTaken = false;
                }
            }
        }
        if  (allSeatsTaken) {
            for(int i = 0; i < rows.size(); i++){
                for(int o = 0; o < seats.get(i).length; o++){
                    if(allSeatsTaken) {
                        pladser[rows.get(i)][seats.get(i)[o]] = false;
                    }
                }
            }
        }
    }

    public boolean checkIfFree(int row, int[] seats){
        boolean taken = false;
        for(int i = 0; i < seats.length; i++) {
            if(pladser[row][seats[i]]) {
                taken = true;
            }
        }
        return taken;
    }
}