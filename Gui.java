import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;

/**
 * Stefanie, Lucas og Niels - Projekt 1. semester - Biograf
 * Denne klasse håndterer brugergrænsefladen, som ekspedienten håndterer reservationer med.
 */
public class Gui extends JPanel implements ItemListener {
    private JFrame frame;
    private int width;
    private int height;
    private int antal;
    private int padding;
    private Image image;
    private JPanel topMenu;
    private JPanel mainWindow;
    private int currentPage;
    private Movie movie;
    private String[] nextDays;
    private String[] movieNames;
    private Date[] next20Dates;
    private int chosenDateIndex;
    private int chosenMovieIndex;
    private JPanel centerPanel;
    private JPanel mainWindow2;
    private JPanel mainWindow3;
    private CardLayout cl;
    private JButton buttonArray[][];
    private boolean[][] seats;
    private ArrayList<int[]> chosenSeats;
    private ArrayList<Integer> chosenRows;
    private Forestilling chosenShow;
    
    public Gui() {
        getScreenSize(); 
        next20Dates = new Date[] {null, new Date(116, 0, 1), new Date(116, 0, 2), new Date(116, 0, 3)};
        nextDays = getNext20String();
        movieNames = new String[Movie.getMovies().size()+1];
        for (int i= -1 ; i < movie.getMovies().size() ; i++) {
            if (i == -1)
                movieNames[i+1] = "Pick movie";
            else
                movieNames[i+1] = movie.getMovies().get(i).movieName;
        }

        padding = 20;
        // image = Toolkit.getDefaultToolkit().createImage("background.jpg");
        // image = image.getScaledInstance(1000, 667, image.SCALE_DEFAULT);
        chosenDateIndex = 0;
        chosenMovieIndex = 0;

        // resizing
    }

    public JPanel topMenu() {
        //topMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, width/16, 0));
        topMenu = new JPanel(new GridLayout(1, 3, width/16, 0));
        topMenu.setBackground(Color.DARK_GRAY);

        JButton reserver = new JButton("Reserver");
        reserver.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    CardLayout cl = (CardLayout)(mainWindow.getLayout());
                    cl.show(mainWindow, (String)e.getActionCommand());;             
                }});
        reserver.setPreferredSize(new Dimension(width/4, height/7));

        JButton titler = new JButton("Titler");
        titler.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    CardLayout cl = (CardLayout)(mainWindow.getLayout());
                    cl.show(mainWindow, (String)e.getActionCommand());; 
                }});
        titler.setPreferredSize(new Dimension(width/4, height/7));

        JButton resRet = new JButton("Ret reservation");
        resRet.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    CardLayout cl = (CardLayout)(mainWindow.getLayout());
                    cl.show(mainWindow, (String)e.getActionCommand());; 
                }});
        resRet.setPreferredSize(new Dimension(width/4, height/7));

        if (currentPage == 2)
            reserver.setBackground(Color.RED);
        else if (currentPage == 3)
            titler.setBackground(Color.RED);
        else if (currentPage == 4)
            resRet.setBackground(Color.RED);

        topMenu.add(reserver);
        topMenu.add(titler);
        topMenu.add(resRet);

        return topMenu;
    }

    public void makeFrontPage(Container side)  {
        topMenu();
        // 

        side.setLayout(new BorderLayout());     
        side.add(topMenu, BorderLayout.NORTH);

        JPanel mainWindow1 = new JPanel(new BorderLayout()); // frontpage

        try{
            image = ImageIO.read(new File("background.jpg"));
        } catch (IOException ex) {

        }

        image = Toolkit.getDefaultToolkit().createImage("background.jpg");
        image = image.getScaledInstance(1000, 667, image.SCALE_DEFAULT);

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        mainWindow1.add(imageLabel, BorderLayout.CENTER);

        mainWindow2 = new JPanel(new BorderLayout());
        mainWindow2.setBackground(Color.DARK_GRAY);
        JPanel westPanel = new JPanel();
        // westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        JComboBox<String> dateBox = new JComboBox<>(nextDays);
        dateBox.addItemListener(
            new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if(event.getStateChange() == ItemEvent.SELECTED) {
                        chosenDateIndex = dateBox.getSelectedIndex();
                        showShows(chosenDateIndex, chosenMovieIndex);
                    }
                }});
        westPanel.add(dateBox);

        JComboBox<String> movieBox = new JComboBox<>(movieNames);
        movieBox.setEditable(false);
        movieBox.addItemListener(
            new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if(event.getStateChange() == ItemEvent.SELECTED) {
                        chosenMovieIndex = movieBox.getSelectedIndex();
                        showShows(chosenDateIndex, chosenMovieIndex);
                    }
                }});
        westPanel.add(movieBox);
        centerPanel = new JPanel();
        mainWindow2.add(centerPanel, BorderLayout.CENTER);
        mainWindow2.add(westPanel, BorderLayout.WEST);

        //Titel side

        mainWindow3 = new JPanel(new BorderLayout());
        mainWindow3.setBackground(Color.DARK_GRAY);
        mainWindow3.setLayout(new BoxLayout(mainWindow3, BoxLayout.PAGE_AXIS));
        mainWindow3.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        mainWindow3.add(Box.createHorizontalGlue());

        JButton movieButtons[] = new JButton[movieNames.length - 1];
        JLabel nameLabel;
        for (int i = 1; i <= movieNames.length -1; i++) {
            movieButtons[i-1] = new JButton(movieNames[i]);
            movieButtons[i-1].setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            mainWindow3.add(movieButtons[i-1]);
            mainWindow3.add(Box.createVerticalStrut(10));
            movieButtons[i-1].addActionListener(new ActionListener()  {
                    public void actionPerformed(ActionEvent e)  {
                        JOptionPane opPane = new JOptionPane();
                        JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.", "Movie Description", JOptionPane.PLAIN_MESSAGE);
                    }
                });
        }        

        JPanel mainWindow4 = new JPanel();
        //scrollbar
        JScrollPane sp = new JScrollPane(mainWindow);
        sp.setHorizontalScrollBarPolicy(sp.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);
        side.add(sp, BorderLayout.CENTER);
        mainWindow = new JPanel(new CardLayout());
        mainWindow.add(mainWindow1, "front");
        mainWindow.add(mainWindow2, "Reserver");
        mainWindow.add(mainWindow3, "Titler");
        mainWindow.add(mainWindow4, "Ret reservation");
        mainWindow.setPreferredSize(new Dimension((width * 100)/ 80, (height - height * 100) / 30));
        side.add(mainWindow);
    }

    public String dateToText(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate aDay = LocalDate.parse(format.format(date));
        return aDay.toString().substring(8,10) + "-" + aDay.toString().substring(5,7);
    }

    public void makeGui() {
        frame = new JFrame("Cinematron");
        makeFrontPage(frame.getContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void itemStateChanged(ItemEvent e) {
        cl = (CardLayout)(mainWindow.getLayout());
        cl.show(mainWindow, (String)e.getItem());
    }

    public String[] getNext20String() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate aDay = null;
        String nextDays[] = new String[next20Dates.length];
        for (int i = 0; i < next20Dates.length; i++) {
            if (i == 0)
                nextDays[i] = "Pick date";
            else {
                aDay = LocalDate.parse(format.format(next20Dates[i]));
                nextDays[i] = aDay.toString().substring(8, 10) + "-" + aDay.toString().substring(5, 7) + "-" + aDay.toString().substring(0, 4); 
            }
        }
        return nextDays;
    }

    public void showShows(int dateIndex, int movieIndex) {
        ArrayList<Forestilling> shownShows = new ArrayList<Forestilling>();
        centerPanel.removeAll();
        if (dateIndex != 0 || movieIndex != 0) {
            for (Forestilling show : Forestilling.getShows()) {
                if ((dateIndex == 0 || next20Dates[dateIndex].equals(show.getShowDate())) &&  (movieIndex == 0 || movieNames[movieIndex].equals(show.getMovie().getName()))) {
                    shownShows.add(show);
                }
            }
        }

        centerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton movieButtons[] = new JButton[shownShows.size()];
        JLabel dateLabel;
        JLabel nameLabel;
        JLabel salAndFreeSeats;
        for (int i = 0; i < shownShows.size(); i++) {   
            dateLabel = new JLabel(dateToText(shownShows.get(i).getShowDate()) + " kl. " + shownShows.get(i).getShowStart());
            nameLabel = new JLabel(shownShows.get(i).getMovie().getName());
            salAndFreeSeats = new JLabel(shownShows.get(i).getSal().getSalId() + "");
            movieButtons[i] = new JButton(shownShows.get(i).getShowID() + "");
            movieButtons[i].setLayout(new BorderLayout());
            movieButtons[i].add(BorderLayout.NORTH, dateLabel);
            movieButtons[i].add(BorderLayout.CENTER, nameLabel);
            movieButtons[i].add(BorderLayout.SOUTH, salAndFreeSeats);
            movieButtons[i].setPreferredSize(new Dimension(centerPanel.getWidth()/2 - 20, centerPanel.getHeight()/4));
            centerPanel.add(movieButtons[i]);
            movieButtons[i].addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) {
                        makeReservation(Integer.parseInt((String)e.getActionCommand()));   
                    }});

        }

        mainWindow2.add(centerPanel, BorderLayout.CENTER);
        mainWindow2.revalidate();
        mainWindow2.repaint();
    }

    public void setWindowSize(int width, int height) {
        width = width;
        height = height;
    }
    // http://stackoverflow.com/questions/3680221/how-can-i-get-the-monitor-size-in-java - copied.
    public void getScreenSize() { 
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
    }    

    public void makeReservation(int showID) {
        JFrame reservationWindow = new JFrame();
        Container contentPane = reservationWindow.getContentPane();
        JPanel reservationPanel = new JPanel(new BorderLayout());
        // try {
        Forestilling chosenShow = Forestilling.findById(showID);

        JPanel northResPanel = new JPanel(new FlowLayout());
        String labelText = "Show " + showID + ", movie: " + chosenShow.getMovie().getName()+ ", date: " + dateToText(chosenShow.getShowDate());
        labelText +=  " at: " + chosenShow.getShowStart();
        JLabel showLabel = new JLabel(labelText, SwingConstants.CENTER);
        northResPanel.add(showLabel);
        reservationPanel.add(northResPanel);

        JPanel centerResPanel = new JPanel(new BorderLayout());

        JPanel westResPanel = new JPanel();
        westResPanel.setLayout(new BoxLayout(westResPanel, BoxLayout.Y_AXIS));
        JLabel boxLabel = new JLabel("Choose number of tickets below");

        /** numberBox.addItemListener(
        new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
        if(event.getStateChange() == ItemEvent.SELECTED) {
        int numberIndex = numberBox.getSelectedIndex();
        chooseSeats(numberIndex + 1);
        }
        }});
         */
        JPanel boxPanel = new JPanel();
        seats = chosenShow.getSeatings();
        String numberOptions[] = new String[seats[0].length];
        for (int i = 0; i < seats[0].length ; i++) {
            numberOptions[i] = i + 1 + "";
        }

        JComboBox<String> numberBox = new JComboBox<>(numberOptions);
        numberBox.setSelectedIndex(1);
        buttonArray = new JButton[chosenShow.getSal().getRows()][chosenShow.getSal().getSeatsInRow()];
        JPanel seatPanel = new JPanel(new GridLayout(seats.length, seats[0].length, 0, 10));
        for(int i = 0 ; i < chosenShow.getSal().getRows(); i++) {
            for (int o = 0 ; o < chosenShow.getSal().getSeatsInRow(); o++) {
                if (seats[i][o] == false) {
                    buttonArray[i][o] = new JButton(i + "-" + o);
                    buttonArray[i][o].setBackground(Color.GREEN);
                    seatPanel.add(buttonArray[i][o]);
                    buttonArray[i][o].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                chooseSeats((String)e.getActionCommand(), (Integer)numberBox.getSelectedIndex()+1, chosenShow);
                            }});
                }
                else {
                    buttonArray[i][o] = new JButton(i + " - " + 0);
                    buttonArray[i][o].setBackground(Color.RED);
                    seatPanel.add(buttonArray[i][o]);
                }
            }
        }

        centerResPanel.add(seatPanel, BorderLayout.CENTER);
        boxPanel.add(numberBox);
        westResPanel.add(boxLabel);
        westResPanel.add(boxPanel);
        centerResPanel.add(westResPanel, BorderLayout.WEST);    
        reservationPanel.add(northResPanel, BorderLayout.NORTH);
        reservationPanel.add(centerResPanel, BorderLayout.CENTER);

        //}
        //catch(Exception e) {
        //     JLabel errorLabel = new JLabel("Show could not be found!");
        //     System.out.println(e.getMessage())
        //     contentPane.add(errorLabel);
        // }
        frame.setDefaultCloseOperation(clearChoices());
        contentPane.add(reservationPanel);
        reservationWindow.pack();
        reservationWindow.setVisible(true);
    }

    public void chooseSeats(String seat, int numTickets, Forestilling show) {
        boolean rowFound = false;
        int rowPlace = 0;
        for (int i = 0; rowFound == false; i++) {
            if (seat.substring(i, i + 1).equals("-")) {
                rowFound = true;
                rowPlace = i;
            }
        }
        int row = Integer.parseInt(seat.substring(0, rowPlace));
        int seatsArray[] = new int[numTickets];
        
        int firstSeat = Integer.parseInt(seat.substring(rowPlace+1,seat.length()));
        for (int i = 0; i < numTickets; i++) {
            seatsArray[i] = firstSeat +i;
        }
        
        if (show.checkIfFree(row, seatsArray) == false)
            changeSeatStatus(show, seatsArray, row);
            System.out.println(JFrame.DISPOSE_ON_CLOSE);
        /**
        ArrayList<int[]> seatsArrayList = new ArrayList<int[]>();
        ArrayList<Integer> rowsArrayList = new ArrayList<Integer>();
        int numberRows = 1;
        if (show.checkIfFree(row, seatsArray) == false) {
            JOptionPane opPane = new JOptionPane();
            // moreSeats = JOptionPane.showConfirmDialog(null, "Add more seats?", "Options", JOptionPane.YES_NO_OPTION);
            if (moreSeats == 1) {
                for (int i = 0; i < numberRows; i++) {
                    seatsArrayList.add(seatsArray);
                    rowsArrayList.add(row);
                }
                phoneNumber = JOptionPane.showInputDialog("Please enter a phonenumber");
                new Reservation(show, seatsArrayList, rowsArrayList, phoneNumber);
                changeSeatStatus(show, seatsArrayList, rowsArrayList);
            }
        }
        */
    }
    
    public void changeSeatStatus(Forestilling show, int[] seatsArray, int row) {
        chosenSeats = new ArrayList<int[]>();
        chosenRows = new ArrayList<Integer>();
        chosenShow = show;
        for(int i = 0; i < seatsArray.length; i++) {
            buttonArray[row][seatsArray[i]].setBackground(Color.MAGENTA);
        }
        chosenSeats.add(seatsArray);
        chosenRows.add(row);
    }
    
    public void orderSeats(Forestilling show) {
        String phoneNumber = JOptionPane.showInputDialog("Please enter a phonenumber");
        new Reservation(show, chosenSeats, chosenRows, phoneNumber);
    }
    
    public int clearChoices() {
        if (chosenSeats != null) 
            chosenSeats.clear();
        if (chosenRows != null)
            chosenRows.clear();
        if (chosenShow != null)
            chosenShow = null;
        return JFrame.DISPOSE_ON_CLOSE;
    }
    
}