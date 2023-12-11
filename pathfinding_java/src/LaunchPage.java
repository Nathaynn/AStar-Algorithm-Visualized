import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LaunchPage {

    JFrame window = new JFrame();
    JPanel mainPanel = new JPanel();
    DemoPanel Astar = new DemoPanel();
    Options options = new Options();
    //Instance Variable, creates a customizable window

    public LaunchPage() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // When the user closes the window, then the program is FULLY terminated.
        window.setResizable(false);
        // The user can't resize the window

        mainPanel.add(Astar, BorderLayout.CENTER);
        mainPanel.add(options, BorderLayout.WEST);
        // Add other JPanels into a singular mainPanel to hold everything
        window.add(mainPanel);
        // Adds the MainPanel into the window
        
        window.pack();
        // Fits the window into its preferred size
        window.setLocationRelativeTo(null);
        // The window is framed in the center of the screen
        window.setVisible(true);
    }

}
