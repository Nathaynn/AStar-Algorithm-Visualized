import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Node extends JButton implements ActionListener {
    // When extending to JButton, it makes the class a interactable object to the user
    // When the object of interest is clicked, ActionListener takes this into account and then does whatever action you code
    // Which is the @Overrided method 'actionPerformed'

    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        // Button Color
        setForeground(Color.black);
        // Text color

        setBorder(new LineBorder(Color.BLACK));
        setOpaque(true);
        // On Windows_OS these two lines of code aren't needed, but since I am on Mac I use it to show the outline grid
        addActionListener(this);
        // When clicked, does actionPerformed
    }

    public void setAsRestart() {
        setBackground(Color.white);
        setForeground(Color.black);
        parent= null;
        start = false;
        goal = false;
        solid = false;
        open = false;
        checked = false;
    }
    // Distincts the node that is the start point
    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;

    }
    // Distincts the node that is the end point
    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.white);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if (start == false && goal == false) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
        }


    public void updateNode() {

        if (OptionsPanel.isSolid) {
            DemoPanel.setSolidNode(col, row);
        }
        else if (OptionsPanel.isOpen) {
            DemoPanel.setFreeNode(col, row);
        }
        else if (OptionsPanel.isStart) {
            DemoPanel.setStartNode(col, row);
        }
        else if (OptionsPanel.isGoal) {
            DemoPanel.setGoalNode(col, row);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("X: " + this.col + ", Y: " + this.row);
        updateNode();
        DemoPanel.updater();
    }
}
