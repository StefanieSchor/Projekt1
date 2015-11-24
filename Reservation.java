public class Reservation {
    private int antal;
    private Forestilling forestilling;
    private int[][] seats;
    private String telefonnummer;
    private String navn;
    
    public Reservation(int antal, int[][] seats) {
        this.antal = antal;
        this.seats = seats;
    }
    
    
}