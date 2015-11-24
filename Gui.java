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
    
    public Gui() {
        this.width = 700;
        this.height = 500;
        makeFrame();
        
    }
    
    public void makeFrame() {
        frame = new JFrame("Reservationssystem");
        JPanel reservationsPanel = new JPanel();
        
        frame.setSize(width, height);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }
    
    public void paint(Graphics g) {
        
    }
}