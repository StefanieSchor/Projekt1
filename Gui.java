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
    private ArrayList<Forestilling> shownShows;
    private int antal;
    private int padding;
    private Image image;
    private JPanel topMenu;
    private JPanel mainWindow;
    private int currentPage;
    private Movie movie;
    public String[] nextDays;
    public String[] movieNames;
    public Date[] next20Dates;
    JComboBox<String> movieBox;
    JComboBox<String> DateBox;
    int chosenDateIndex;
    int chosenMovieIndex;
    JPanel centerPanel;
    JPanel mainWindow2;
    JPanel mainWindow3;
    CardLayout cl;
    boolean seats[][];

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
        topMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, width/16, 0));
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
        mainWindow2.add(centerPanel);
        mainWindow2.add(westPanel, BorderLayout.WEST);

        mainWindow3 = new JPanel(new BorderLayout());
        mainWindow3.setBackground(Color.DARK_GRAY);

        movieNames = new String[Movie.getMovies().size()];
        for (int i= 1 ; i < movie.getMovies().size() ; i++) {
            movieNames[i] = movie.getMovies().get(i).movieName;
        }

        mainWindow3.setLayout(new GridLayout(movieNames.length, 3, 20, 20));

        JButton movieButtons[] = new JButton[movieNames.length];
        JLabel nameLabel;
        for (int i = 1; i < movieNames.length; i++) {
            nameLabel = new JLabel(movieNames[i]);
            movieButtons[i] = new JButton();
            //movieButtons[i].setLayout(new BorderLayout());
            movieButtons[i].add(BorderLayout.CENTER, nameLabel);
            mainWindow3.add(movieButtons[i]);
            movieButtons[i].addActionListener(new ActionListener()  {
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gui gui = new Gui();
        gui.makeFrontPage(frame.getContentPane());

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
        shownShows = new ArrayList<Forestilling>();
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
        JLabel showLabel = new JLabel("Show " + showID + ", movie: " + chosenShow.getMovie().getName()
                + ", date: " + dateToText(chosenShow.getShowDate()) + " at: " + chosenShow.getShowStart());
        reservationPanel.add(showLabel, BorderLayout.NORTH);

        JButton buttonarray[][] = new JButton[chosenShow.getSal().getRows()][chosenShow.getSal().getSeatsInRow()];
        seats = chosenShow.getSeatings();
        JPanel seatPanel = new JPanel(new GridLayout(seats.length, seats[0].length));
        for(int i = 0 ; i < chosenShow.getSal().getRows(); i++) {
            for (int o = 0 ; o < chosenShow.getSal().getSeatsInRow(); o++) {
                if (seats[i][o] == false ||seats[i][o] == false) {
                    buttonarray[i][o] = new JButton(i + " - " + o);
                    buttonarray[i][o].setBackground(Color.GREEN);
                    seatPanel.add(buttonarray[i][o]);
                }
                else {
                    buttonarray[i][o] = new JButton(i + " - " + 0);
                    buttonarray[i][o].setBackground(Color.RED);
                    seatPanel.add(buttonarray[i][o]);
                }
            }
        }
        reservationPanel.add(seatPanel, BorderLayout.CENTER);
        int numberOptions[] = new int[seats[0].length];
        for (int i = 0; i < seats[0].length ; i++) {
            numberOptions[i] = i+1;
        }

        JComboBox<Integer> numberBox = new JComboBox<>();
        numberBox.addItemListener(
            new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                    if(event.getStateChange() == ItemEvent.SELECTED) {
                        int numberIndex = numberBox.getSelectedIndex();
                        chooseSeats(numberIndex + 1);
                    }
                }});

        //}
        //catch(Exception e) {
        //     JLabel errorLabel = new JLabel("Show could not be found!");
        //     System.out.println(e.getMessage());
        //     contentPane.add(errorLabel);
        // }
        contentPane.add(reservationPanel);
        reservationWindow.pack();
        reservationWindow.setVisible(true);
    }

    public void chooseSeats(int numTickets) {
        // seats[numberBox.getSelectedIndex()]
    }
}