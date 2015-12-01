import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.*;

/**
 * Stefanie, Lucas og Niels - Projekt 1. semester - Biograf
 * Denne klasse håndterer brugergrænsefladen, som ekspedienten håndterer reservationer med.
 */
public class Gui extends JComponent {
    private JFrame frame;
    private int width;
    private int height;
    private Forestilling currentForestilling;
    private int antal;
    private int padding;
    private Image image;
    private JPanel topMenu;
    
    public Gui() {
        getScreenSize();
        makeFrontPage();
        padding = 20;
        image = Toolkit.getDefaultToolkit().createImage("background.jpg");
        image = image.getScaledInstance(1000, 667, image.SCALE_DEFAULT);
        // resizing
    }
    
    public JPanel topMenu() {
        topMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, width/16, 0));
        topMenu.setBackground(Color.DARK_GRAY);
        
        JButton reserver = new JButton("Reserver");
        reserver.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { goToReservation(); } });
        reserver.setPreferredSize(new Dimension(width/4, height/7));
        topMenu.add(reserver);
        
        JButton titler = new JButton("Titler");
        titler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { goToTitles(); } });
        titler.setPreferredSize(new Dimension(width/4, height/7));
        topMenu.add(titler);
        
        JButton resRet = new JButton("Ret reservation");
        resRet.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { goToChange(); } });
        resRet.setPreferredSize(new Dimension(width/4, height/7));
        topMenu.add(resRet);
        return topMenu;
    }
    
    public void makeFrontPage() {
        frame = new JFrame("Cinematron");
        Container frontPage = frame.getContentPane();
        
        topMenu();
        
        frontPage.setLayout(new BorderLayout());     
        frontPage.add(topMenu, BorderLayout.NORTH);
        // topMenu.setBorder(new EtchedBorder());
        JPanel mainWindow = new JPanel();
        mainWindow.setPreferredSize(new Dimension((width * 100)/ 80, (height - height * 100) / 30));
        
        frontPage.add(mainWindow, BorderLayout.CENTER);
        
        // vinduesstørrelse mm.
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
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
    
    public void makeReservationPage() {
        frame = new JFrame("Reservation");
        Container reservationPage = frame.getContentPane();
        
        topMenu();
        
        reservationPage.setLayout(new BorderLayout());
        reservationPage.add(topMenu, BorderLayout.NORTH);
        
        
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