import javax.swing.JFrame;

public class LaunchPage {

    JFrame window = new JFrame();
    //Instance Variable, creates a customizable window

    public LaunchPage() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // When the user closes the window, then the program is FULLY terminated.
        window.setResizable(false);
        // The user can't resize the window
        window.add(new DemoPanel());
        // Adds the DemoPanel into the window
        
        window.pack();
        // Fits the window into its preferred size
        window.setLocationRelativeTo(null);
        // The window is framed in the center of the screen
        window.setVisible(true);
    }

}
