
import java.util.*;

public class Reservation {
   private int numberOfSeats;
   private Forestilling forestilling;
   int[][] seats;
   int[] rows;
   private String tel;
   private String name;
   public static ArrayList<Reservation> reservations;
   
   public Reservation(Forestilling forestilling, int[][] seats, int[] rows, int numberOfSeats, String tel, String name) {
       this.forestilling = forestilling;
       this.seats = seats;
       this.rows = rows;
       this.numberOfSeats = numberOfSeats;
       this.tel = tel;
       this.name = name;
       this.reservations = new ArrayList<Reservation>();
       
       forestilling.setReserved(seats, rows);
   }
   
   public Reservation(Forestilling forestilling, int[][] seats, int[] rows, int numberOfSeats, String tel) {
       this.forestilling = forestilling;
       this.seats = seats;
       this.rows = rows;
       this.numberOfSeats = numberOfSeats;
       this.tel = tel;
       this.name = null;
       this.reservations = new ArrayList<Reservation>();
      
       
       forestilling.setReserved(seats, rows);
   }
  
   public void editReservation(Forestilling forestilling, int[][] seats, int[] rows, int numberOfSeats, String tel, String name) {
       forestilling.removeReserved(seats, rows);
       
       this.forestilling = forestilling;
       this.seats = seats;
       this.rows = rows;
       this.numberOfSeats = numberOfSeats;
       this.tel = tel;
       this.name = name;
       
       forestilling.setReserved(seats, rows);
    }
           
   public void removeReservation() {
       forestilling.removeReserved(seats, rows);
      
       this.forestilling = null;
       this.seats = null;
       this.rows = null;
       this.numberOfSeats = 0;
       this.tel = null;
       this.name = null;
       
       reservations.remove(this);
   }
  
   public String getName() {
       return name;
   }
  
   public String getTel() {
       return tel;
   }
  
   public static ArrayList<Reservation> getReservations() {
       return reservations;
   }
  
   public int getNumberOfSeats() {
       return numberOfSeats;
   }
   
   public Forestilling getForestilling() {
       return forestilling;
   }
   
   public int[][] getSeats() {
       return seats;
   }
   
   public int[] getRows() {
       return rows;
   }
}