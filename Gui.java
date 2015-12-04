import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Stefanie, Lucas og Niels - Projekt 1. semester - Biograf
 * Denne klasse håndterer brugergrænsefladen, som ekspedienten håndterer reservationer med.
 */
public class Gui extends JComponent implements ItemListener {
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
        image = Toolkit.getDefaultToolkit().createImage("background.jpg");
        image = image.getScaledInstance(1000, 667, image.SCALE_DEFAULT);
        chosenDateIndex = 0;
        chosenMovieIndex = 0;
               
        // resizing
    }
    
    public JPanel topMenu() {
        topMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, width/16, 0));
        topMenu.setBackground(Color.DARK_GRAY);
        
        String itemList[] = new String[] {"front", "Reserver", "Titler", "Ret reservation"};
        JComboBox<String> cb = new JComboBox<>(itemList);
        cb.addItemListener(this);
        
        /**
        JButton reserver = new JButton("Reserver");
        reserver.addActionListener(this);
        reserver.setPreferredSize(new Dimension(width/4, height/7));
        
        
        JButton titler = new JButton("Titler");
        titler.addActionListener(this);
        titler.setPreferredSize(new Dimension(width/4, height/7));
        
        
        JButton resRet = new JButton("Ret reservation");
        resRet.addActionListener(this);
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
        */
        topMenu.add(cb);
        return topMenu;
    }
    
    public void makeFrontPage(Container side)  {
        topMenu();
        // 
        
        side.setLayout(new BorderLayout());     
        side.add(topMenu, BorderLayout.NORTH);
        side.add(this);
        JPanel mainWindow1 = new JPanel(); // frontpage
        
        JPanel mainWindow2 = new JPanel(new BorderLayout());
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
        
        JPanel centerPanel = new JPanel();
        
        
        mainWindow2.add(westPanel, BorderLayout.WEST);
        mainWindow2.add(centerPanel, BorderLayout.CENTER);
        // for (Forestilling forestilling : )
        
        mainWindow = new JPanel(new CardLayout());
        mainWindow.add(mainWindow1, "front");
        mainWindow.add(mainWindow2, "Reserver");
        mainWindow.setPreferredSize(new Dimension((width * 100)/ 80, (height - height * 100) / 30));
        side.add(mainWindow);
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
    
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void goToReservation() {
        makeReservationPage();
    }
    
    public void goToTitles() {
        makeTitlePage();
    }
    
    public void goToChange() {
        makeChangePage();
    }
    
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout)(mainWindow.getLayout());
        cl.show(mainWindow, (String)e.getItem());
    }
    
    public void makeReservationPage() {
        frame = new JFrame("Cinematron");
        Container frontPage = frame.getContentPane();
        
        topMenu();
        
        frontPage.setLayout(new BorderLayout());     
        frontPage.add(topMenu, BorderLayout.NORTH);
        
        mainWindow = new JPanel(new BorderLayout());
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
        
        JPanel centerPanel = new JPanel();
        // for (Forestilling forestilling : )
        
        mainWindow.add(westPanel, BorderLayout.WEST);
        mainWindow.add(centerPanel, BorderLayout.CENTER);
        frame.add(mainWindow);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);       
    }

    public String[] getNext20String() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate aDay = null;
        String nextDays[] = new String[next20Dates.length+1];
        for (int i = 0; i < next20Dates.length; i++) {
            if (i == 0)
                nextDays[i] = "Pick date";
            else {
                aDay = LocalDate.parse(format.format(next20Dates[i]));
                nextDays[i] = aDay.toString().substring(8, 10) + "-" + aDay.toString().substring(5, 7) + "-" + aDay.toString().substring(0, 4);
                System.out.println(nextDays[i]);
            }
            }
        return nextDays;
    }
    
    public String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        LocalDate ld = LocalDate.parse(format.format(date));
        return format.format(date);
    }
    
    public LocalDate stringToDate(String string) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        LocalDate date = LocalDate.parse(format.format(string));
        return LocalDate.parse(format.format(date));
    }
    
    public void showShows(int dateIndex, int movieIndex) {
        shownShows = new ArrayList<Forestilling>();
        if (dateIndex != 0 || movieIndex != 0) {
            for (Forestilling show : Forestilling.getShows()) {
                if ((dateIndex == 0 || next20Dates[dateIndex].equals(show.getShowDate())) &&  (movieIndex == 0 || movieNames[movieIndex].equals(show.getMovie().getName()))) {
                    shownShows.add(show);
                }
            }
        }
        for (Forestilling show : shownShows) {
            System.out.println(show.getShowID());
        };
    }
    
    public void makeTitlePage() {
        
    }
    
    public void makeChangePage() {
        
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
}