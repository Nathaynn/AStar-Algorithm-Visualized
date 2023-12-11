import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class Options extends JPanel implements ActionListener{

    boolean isSolid = false;
    boolean isGoal = false;
    boolean isStart = false;
    JCheckBox solids;
    JCheckBox goalBox;
    JCheckBox startBox;
    int checkTrack = 0;
    Font font = new Font("Consolas", Font.PLAIN, 20);
    public Options() {
        setPreferredSize(new Dimension(DemoPanel.screenWidth/3, DemoPanel.screenHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Settings for Panel

        solids = new JCheckBox();
        add(solids);
        solids.setText("Solidify Selected Nodes");
        solids.setFocusable(false);
        solids.setFont(font);
        // Checkbox for selecting solids

        goalBox = new JCheckBox();
        add(goalBox);
        goalBox.setText("Select Goal");
        goalBox.setFocusable(false);
        goalBox.setFont(font);
        // Checkbox for selecting goal

        startBox = new JCheckBox();
        add(startBox);
        startBox.setText("Select Start");
        startBox.setFocusable(false);
        startBox.setFont(font);
        // Checkbox for selecting goal
    }   

    public void limitCheckBoxes() {
        if (checkTrack == 1) {
            solids.setSelected(false);
            goalBox.setSelected(false);
            startBox.setSelected(false);
        }
    }

    public void solidSelector() {
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}