import java.awt.*;
import javax.swing.*;
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
    
    public Gui() {
        getScreenSize();
        makeFrame();
        padding = 20;
    }
    
    public void makeFrame() {
        frame = new JFrame("Reservationssystem");
        JPanel reservationsPanel = new JPanel();
        frame.setSize(width, height);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
    }
    
    public void makeMenu() {
        
    }
    
    public void paint(Graphics g) {
        g.fillRect(padding, padding, width-3*padding, height-4*padding);
    }
    
    public void setPadding(int number) {
        padding = number;
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