import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class loading extends JFrame implements Runnable {
    JLabel tatmLabel = new JLabel("TRAVEL AND TOURISM APPLICATION");
    JProgressBar progressBar = new JProgressBar();
    JLabel welcomeLabel = new JLabel("");
    JPanel panel = new JPanel();
    Thread thread; // Thread to manage the loading process
    Container container;
    String username = ""; // To hold the username passed

    // Constructor accepting the username as a parameter
    loading(String user) {
        username = user;
        container = getContentPane(); // Get the content pane of the frame

        // Get the screen size and set the frame size accordingly
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize); // Set frame size to screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximized window mode
        setUndecorated(true); // Remove window borders if you want fullscreen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create a border for the panel
        Border border = BorderFactory.createLineBorder(Color.GRAY, 2);

        // Set the font for the title
        Font fontTitle = new Font("Algerian", Font.BOLD, 25);

        // Panel settings
        panel.setBorder(border);
        panel.setLayout(null);
        panel.setBounds(0, 0, screenSize.width, screenSize.height); // Full panel size
        panel.setBackground(Color.white);

        // Add components to the panel and set their properties
        tatmLabel.setBounds(screenSize.width / 2 - 225, 50, 450, 40); // Center title
        tatmLabel.setForeground(Color.blue);
        tatmLabel.setFont(fontTitle);

        // Progress bar settings (center it horizontally)
        progressBar.setBounds(screenSize.width / 2 - 125, screenSize.height / 3, 250, 25);
        progressBar.setStringPainted(true);

        // Load and add images (fly, please wait, and loader)
        ImageIcon flyIcon = new ImageIcon(ClassLoader.getSystemResource("icons/fly.gif"));
        ImageIcon resizedFlyIcon = new ImageIcon(flyIcon.getImage().getScaledInstance(160, 120, Image.SCALE_DEFAULT));
        JLabel flyLabel = new JLabel(resizedFlyIcon);
        flyLabel.setBounds(screenSize.width - 150, screenSize.height - 160, 120, 110);
        flyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon waitIcon = new ImageIcon(ClassLoader.getSystemResource("icons/pleasewait.gif"));
        ImageIcon resizedWaitIcon = new ImageIcon(waitIcon.getImage().getScaledInstance(220, 100, Image.SCALE_DEFAULT));
        JLabel waitLabel = new JLabel(resizedWaitIcon);
        waitLabel.setBounds(screenSize.width / 2 - 110, screenSize.height / 3 + 40, 230, 110);
        waitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon loaderIcon = new ImageIcon(ClassLoader.getSystemResource("icons/loader3.gif"));
        ImageIcon resizedLoaderIcon = new ImageIcon(loaderIcon.getImage().getScaledInstance(65, 70, Image.SCALE_DEFAULT));
        JLabel loaderLabel = new JLabel(resizedLoaderIcon);
        loaderLabel.setBounds(screenSize.width / 2 - 35, screenSize.height / 2, 65, 70);
        loaderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Welcome label settings
        welcomeLabel.setBounds(20, screenSize.height - 80, 800, 40);
        welcomeLabel.setText("WELCOME " + username);
        welcomeLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 25));

        // Add components to the panel
        panel.add(welcomeLabel);
        panel.add(flyLabel);
        panel.add(waitLabel);
        panel.add(loaderLabel);
        panel.add(progressBar);
        panel.add(tatmLabel);

        // Add the panel to the container
        container.add(panel);

        // Start the loading thread
        thread = new Thread(this);
        thread.start();

        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        new loading("N/A"); // Pass username
    }

    @Override
    public void run() {
        try {
            // Simulate a loading process with a progress bar
            for (int i = 0; i < 101; i++) {
                int max = progressBar.getMaximum();
                int value = progressBar.getValue();
                if (value < max) {
                    progressBar.setValue(value + 1);
                } else {
                    setVisible(false);
                    Thread.sleep(40);
                    new dashboard(username); // Load dashboard (assuming you have this class)
                }
                Thread.sleep(50); // Control the speed of the progress bar
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
