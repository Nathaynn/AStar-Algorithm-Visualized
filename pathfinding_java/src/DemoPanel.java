import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DemoPanel extends JPanel implements ActionListener {
    // In simple terms, JPanel is a means to add components into a JFrame object

    // SCREEN SETTINGS
    final static int maxCol = 15;
    final static int maxRow = 10;
    final static int nodeSize = 70;
    final static int screenWidth = nodeSize * maxCol;
    final static int screenHeight = nodeSize * maxRow;

    // NODE
    static Node[][] node = new Node[maxCol][maxRow];  // 2D array of Node Objects
    static Node startNode, goalNode, currentNode; 
    static ArrayList<Node> openList = new ArrayList<>(); 
    static ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    static boolean goalReached = false;

    public DemoPanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Sets the size of the panel
        // Dimension takes in width/height arguments
        // The 'Dimension' class includes methods to set/retrieve those values
        setBackground(Color.black);
        // Sets the background to be black
        // On MAC_OS, the background won't be shown and the black outline is made by the Node border
        setLayout(new GridLayout(maxRow, maxCol));
        // setLayout tells the JPanel component what kind of Layout Manger it will be using
        // GridLayout is the chosen Layout Manager, which organizes components in grids of rows and columns
        
        // PLACE NODES
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            
            node[col][row] = new Node(col, row);
            add(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
        // Personal thought: I was confused on how the last node was being place for each row, but
        // I realized that the nodes work just like arrays, where it starts at [0][0] and the last node we see
        // in each row is [14][row]
    }
    
    public static void setStartNode(int col, int row) {
        if (startNode != null) {
            node[startNode.col][startNode.row].setAsRestart();
        }
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    public static void setGoalNode(int col, int row) {
        if (goalNode != null) {
            node[goalNode.col][goalNode.row].setAsRestart();
        }
        node[col][row].setAsGoal();
        goalNode = node[col][row];
        
    }

    public static void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    public static void setFreeNode(int col, int row) {
        if (node[col][row] == startNode) {
            startNode = null;
        }
        else if (node[col][row] == goalNode) {
            goalNode = null;
        }
        node[col][row].setAsRestart();

    }
    public static void openNode(Node node) {
        // If node is open, then set 'open' to true and add to open list
        if (!node.open && !node.checked && !node.solid) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node); // The nodes within the list will be compared for best cost
            
        }
    }

    public static void completePath() {
        currentNode = currentNode.parent; // currentNode is at 
        while (currentNode.parent != null) {
            currentNode.setAsPath();
            currentNode = currentNode.parent;
        }
    }

    public static void updater() {
        if (startNode != null && goalNode != null) {
        
            if (goalReached) {
                openList = new ArrayList<>();
                checkedList = new ArrayList<>();
                int x = 0;
                int y = 0;
                while (x < maxCol && y < maxRow) {
                    Node nodey = node[x][y];
                    if (!nodey.solid) {
                        node[x][y].setAsRestart();
                    }
                    x++;
                    if (x == maxCol) {
                        x = 0;
                        y++;
                    }
                }
                node[goalNode.col][goalNode.row].setAsGoal();
                node[startNode.col][startNode.row].setAsStart();
                goalReached = false;
            }

            // Update costs on nodes
            AStarAlgorithm.setCostOnNodes();
            
            // Compute path
            while (!goalReached) {
                AStarAlgorithm.search();
                if (openList.size() == 0) {
                    break;
                }
            }
            
            // Show green path IF a path is found
            if (goalReached) {
                completePath();
            }

        }
    }

    public static void boardWiper() {
        int x = 0;
        int y = 0;
        while (x < maxCol && y < maxRow) {
            node[x][y].setAsRestart();
            x++;
            
            if (x == maxCol){
                x = 0;
                y++;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
