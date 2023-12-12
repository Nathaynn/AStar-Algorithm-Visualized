import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Options extends JPanel implements ActionListener{
    static boolean isOpen = false;
    static boolean isSolid = false;
    static boolean isGoal = false;
    static boolean isStart = false;
    Font font = new Font("Consolas", Font.PLAIN, 20);
    JCheckBox setOpen, solids, goalBox, startBox;
    ArrayList<JCheckBox> components = new ArrayList<>();


    public Options() {
        setPreferredSize(new Dimension(DemoPanel.screenWidth/3, DemoPanel.screenHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Size of Panel

        JCheckBox setOpen = new JCheckBox();
        add(setOpen);
        setOpen.setText("Open Selected Nodes");
        setOpen.setFocusable(false);
        setOpen.setFont(font);
        setOpen.addActionListener(this);
        components.add(setOpen);
        // Checkbox for opening nodes

        JCheckBox solids = new JCheckBox();
        add(solids);
        solids.setText("Solidify Selected Nodes");
        solids.setFocusable(false);
        solids.setFont(font);
        solids.addActionListener(this);
        components.add(solids);
        // Checkbox for selecting solids

        JCheckBox goalBox = new JCheckBox();
        add(goalBox);
        goalBox.setText("Select Goal");
        goalBox.setFocusable(false);
        goalBox.setFont(font);
        goalBox.addActionListener(this);
        components.add(goalBox);

        // Checkbox for selecting goal

        JCheckBox startBox = new JCheckBox();
        add(startBox);
        startBox.setText("Select Start");
        startBox.setFocusable(false);
        startBox.setFont(font);
        startBox.addActionListener(this);
        components.add(startBox);
        // Checkbox for selecting goal

        components.add(solids);

    }   

    @Override
    public void actionPerformed(ActionEvent e) {
    
        for (JCheckBox checkBoxes : components) {
            checkBoxes.setSelected(false);
        }

        ((JCheckBox) e.getSource()).setSelected(true);

        isOpen = setOpen.isSelected();
        isSolid = solids.isSelected();
        isStart = startBox.isSelected();
        isGoal = goalBox.isSelected();


    }

}