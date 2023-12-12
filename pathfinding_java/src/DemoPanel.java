import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DemoPanel extends JPanel {
    // In simple terms, JPanel is a means to add components into a JFrame object

    // SCREEN SETTINGS
    final static int maxCol = 15;
    final static int maxRow = 10;
    final static int nodeSize = 70;
    final static int screenWidth = nodeSize * maxCol;
    final static int screenHeight = nodeSize * maxRow;

    // NODE
    Node[][] node = new Node[maxCol][maxRow];  // 2D array of Node Objects
    Node startNode, goalNode, currentNode; 
    ArrayList<Node> openList = new ArrayList<>(); 
    ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    boolean goalReached = false;

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

        // START AND GOAL NODE
        setStartNode(2, 3);
        setGoalNode(10, 9);

        // SOLID NODE(S)
        for (int i = 0; i < 9; i++) {
            setSolidNode(5, i);
        }

        // SET COST
        setCostOnNodes();

        // TEST CODE 
        while (!goalReached) {
            search();
        }

        completePath();
    }
    
    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
        
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {

            getCost(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }
    private void getCost(Node node) {
        // G COST (The distance from the start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H COST (The distance from the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F COST (total)
        node.fCost = node.gCost + node.hCost;

        if (node != startNode && node != goalNode){
            String text = "<html> F: "  + node.fCost + "<br> G: " + node.gCost + "<br> H: " + node.hCost;
            // Apparently you can use HTML tags in java, this is used to <br> the lines since \n doesnt suffice
            node.setText(text);
        }
    }

    public void search() {
        // When using the search method, we will be setting whatever node we are on as checked and adding it to 
        // The checked list, while also removing it from the list of open nodes and adding adjacent nodes to open

        if (!goalReached) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // After checking the node, the adjacent nodes will be opened
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]); // North Node
            }
            if (col + 1 < maxCol) {
            openNode(node[col + 1][row]); // East Node
            }
            if (row + 1 < maxRow) {
            openNode(node[col][row + 1]); // South Node
            }
            if (col - 1 >= 0 ) {
                openNode(node[col - 1][row]); // West Node
            }

            // Algorithm for finding the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                Node nodey = openList.get(i);
                int num = nodey.fCost;
                if (num < bestNodefCost) {
                    // If node at index 'i' has a lower fCost, then bestNodeIndex = 'i'
                    // And bestNodefCost = openList.get(i).fCost
                    bestNodeIndex = i;
                    bestNodefCost = num;
                }
                else if (num == bestNodefCost && nodey.gCost < openList.get(bestNodeIndex).gCost) {
                    // Else if the node has the same cost as the best node, then compare gCosts (Distance from start node)
                    bestNodeIndex = i;
                    bestNodefCost = num;
                }
            }
            // After loop, the currentNode becomes the next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
            }
        }
    }

    private void openNode(Node node) {
        // If node is open, then set 'open' to true and add to open list
        if (!node.open && !node.checked && !node.solid) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node); // The nodes within the list will be compared for best cost
            
        }
    }

    private void completePath() {
        currentNode = currentNode.parent; // currentNode is at 
        while (currentNode.parent != null) {
            currentNode.setAsPath();
            currentNode = currentNode.parent;
        }
    }
}
