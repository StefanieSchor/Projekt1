
import java.util.*;

public class Reservation {
   private Forestilling forestilling;
   ArrayList<int[]> seats;
   ArrayList<Integer> rows;
   private String tel;
   private String name;
   public static ArrayList<Reservation> reservations;
   
   public Reservation(Forestilling forestilling, ArrayList<int[]> seats, ArrayList<Integer> rows, String tel) {
       this.forestilling = forestilling;
       this.seats = seats;
       this.rows = rows;
       this.tel = tel;
       this.name = null;
       this.reservations = new ArrayList<Reservation>();
      
       
       forestilling.setReserved(seats, rows);
   }
          
   public void removeReservation() {
       forestilling.removeReserved(seats, rows);
      
       this.forestilling = null;
       this.seats = null;
       this.rows = null;
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
     
   public Forestilling getForestilling() {
       return forestilling;
   }
   
   public ArrayList<int[]> getSeats() {
       return seats;
   }
   
   public ArrayList<Integer> getRows() {
       return rows;
   }
}