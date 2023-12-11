import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
        
        setBackground(Color.pink);
        setForeground(Color.pink);
        addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }
    
}
