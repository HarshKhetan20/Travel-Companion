import java.awt.*;
import javax.swing.*;

public class splash extends JFrame implements Runnable {
    JLabel image1;
    Thread T1;

    public splash() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen size dynamically
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Loading and scaling the image to fit the screen size
        ImageIcon image_1 = new ImageIcon(ClassLoader.getSystemResource("icons/man.gif"));
        Image scaledImage = image_1.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        ImageIcon image_2 = new ImageIcon(scaledImage);
        image1 = new JLabel(image_2);

        add(image1);
        setUndecorated(true);
        setVisible(true);
        setSize(screenWidth, screenHeight);
        
        // Starting the thread
        T1 = new Thread(this);
        T1.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new splash();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(7000); // Display splash screen for 7 seconds
            this.setVisible(false);
            new login(); // Assuming a login class exists
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
