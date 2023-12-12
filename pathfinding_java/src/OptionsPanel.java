import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel implements ActionListener{
    static boolean isOpen = false;
    static boolean isSolid = false;
    static boolean isGoal = false;
    static boolean isStart = false;
    Font font = new Font("Consolas", Font.PLAIN, 20);
    Options setOpen = new Options("Open Selected Nodes");
    Options solids = new Options("Solidify Selected Nodes");
    Options goalBox = new Options("Select Goal");
    Options startBox = new Options("Select Start");
    ArrayList<Options> components = new ArrayList<>();


    public OptionsPanel() {
        setPreferredSize(new Dimension(DemoPanel.screenWidth/3, DemoPanel.screenHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Size of Panel

        add(setOpen);
        components.add(setOpen);
        setOpen.addActionListener(this);
        // Checkbox for opening nodes

        add(solids);
        components.add(solids);
        solids.addActionListener(this);
        // Checkbox for selecting solids

        add(goalBox);
        components.add(goalBox);
        goalBox.addActionListener(this);
        // Checkbox for selecting goal

        add(startBox);
        components.add(startBox);
        startBox.addActionListener(this);
        // Checkbox for selecting goal

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Options component : components) {
            component.setSelected(false);
            component.clicked = false;
        }

        ((Options) e.getSource()).setSelected(true);
        ((Options) e.getSource()).clicked = true;

        isOpen = setOpen.clicked;
        isSolid = solids.clicked;
        isGoal = goalBox.clicked;
        isStart = startBox.clicked;
    }
}