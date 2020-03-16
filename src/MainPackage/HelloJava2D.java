package MainPackage;

import javax.swing.*;
import java.awt.*;

public class HelloJava2D extends JPanel {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Hello Java2D");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(100, 50));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new HelloJava2D());
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
    }
}