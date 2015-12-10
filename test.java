public class test {
    boolean[][] seats;
    int[] chosenRow;
    int[][] chosenSeats;
    
    public test() {
        seats = new boolean[][] {{true, true, false, false}, {true, true, false, false}, {true, true, false, false}};
        chosenRow = new int[] {0,1};
        chosenSeats = new int[][] {{2, 3}, {2, 3}};
        reserveSeats(chosenRow, chosenSeats);
    }
    
    public void reserveSeats(int[] a, int[][] b) {
        boolean allSeatsFree = true;
        for (int i = 0; i < a.length; i++) {
            for (int o = 0; o < b[i].length; o++) {
                if (seats[a[i]][b[i][o]] == false) {
                    System.out.println("seat " + b[i][o] + " at row " + a[i] + " is free");
                }
                else {
                    allSeatsFree = false;
                    System.out.println("seat " + b[i][o] + " at row " + a[i] + " is occupied");
                }
            }
        }
        if (allSeatsFree) {
            for (int i = 0; i < a.length; i++) {
                for (int o = 0; o < b[i].length; o++) {
                    if (seats[a[i]][b[i][o]] == false) {
                    seats[a[i]][b[i][o]] = true;
                    }
                }
            }
            System.out.println("SUCCESS");
        }
        else
            System.out.println("fail");
    }
    
    public void chooseSeats(String seat, int numTickets) {
        boolean rowFound = false;
        int rowPlace = 0;
        for (int i = 0; rowFound == false; i++) {
            if (seat.substring(i, i + 1).equals("-")) {
                rowFound = true;
                rowPlace = i;
            }
        }
        int row = Integer.parseInt(seat.substring(0, rowPlace));
        int seats[] = new int[numTickets];
        int firstSeat = Integer.parseInt(seat.substring(rowPlace+1,seat.length()));
        for (int i = 0; i < numTickets; i++) {
            seats[i] = firstSeat +i;
        }
    }
}