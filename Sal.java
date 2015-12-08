import java.util.*;
public class Sal {
    private int salId;
    private int rows;
    private int seatsInRow;
    private ArrayList<Sal> sale;
    
    public Sal(int salId, int rows, int seatsInRow) {
        this.salId = salId;
        this.rows = rows;
        this.seatsInRow = seatsInRow;
    }
    
    public Sal(ArrayList<Sal> sale) {
        this.sale = sale;
    }
    
    public int getSalId() {
        return this.salId;
    }
    
    public int getRows(){
       return this.rows;
    }
   
    public int getSeatsInRow() {
       return this.seatsInRow;
    }
}