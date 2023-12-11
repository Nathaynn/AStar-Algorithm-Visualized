import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class Options extends JPanel implements ActionListener{

    boolean isSolid = false;
    Font font = new Font("Consolas", Font.PLAIN, 20);
    public Options() {
        setPreferredSize(new Dimension(DemoPanel.screenWidth/3, DemoPanel.screenHeight));
        // Size of Panel

        JCheckBox solids = new JCheckBox();
        add(solids);
        solids.setText("Solidify Selected Nodes");
        solids.setFocusable(false);
        solids.setFont(font);
        // Checkbox for selecting solids

        JCheckBox goalBox = new JCheckBox();
        add(goalBox);
        goalBox.setText("Select Goal");
        goalBox.setFocusable(false);
        goalBox.setFont(font);
        // Checkbox for selecting goal

        JCheckBox startBox = new JCheckBox();
        add(startBox);
        startBox.setText("Select Start");
        startBox.setFocusable(false);
        startBox.setFont(font);
        // Checkbox for selecting goal
    }   

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}