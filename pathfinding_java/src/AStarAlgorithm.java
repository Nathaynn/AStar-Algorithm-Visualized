public class AStarAlgorithm {

    public static void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while (col < DemoPanel.maxCol && row < DemoPanel.maxRow) {

            getCost(DemoPanel.node[col][row]);

            col++;
            if (col == DemoPanel.maxCol) {
                col = 0;
                row++;
            }
        }
    }
    private static void getCost(Node node) {
        // G COST (The distance from the start node)
        int xDistance = Math.abs(node.col - DemoPanel.startNode.col);
        int yDistance = Math.abs(node.row - DemoPanel.startNode.row);
        node.gCost = xDistance + yDistance;

        // H COST (The distance from the goal node)
        xDistance = Math.abs(node.col - DemoPanel.goalNode.col);
        yDistance = Math.abs(node.row - DemoPanel.goalNode.row);
        node.hCost = xDistance + yDistance;

        // F COST (total)
        node.fCost = node.gCost + node.hCost;

        if (node != DemoPanel.startNode && node != DemoPanel.goalNode){
            String text = "<html> F: "  + node.fCost + "<br> G: " + node.gCost + "<br> H: " + node.hCost;
            // Apparently you can use HTML tags in java, this is used to <br> the lines since \n doesnt suffice
            node.setText(text);
        }
    }

    public static void search() {
        // When using the search method, we will be setting whatever node we are on as checked and adding it to 
        // The checked list, while also removing it from the list of open nodes and adding adjacent nodes to open

        if (!DemoPanel.goalReached) {
            int col = DemoPanel.currentNode.col;
            int row = DemoPanel.currentNode.row;

            DemoPanel.currentNode.setAsChecked();
            DemoPanel.checkedList.add(DemoPanel.currentNode);
            DemoPanel.openList.remove(DemoPanel.currentNode);

            // After checking the node, the adjacent nodes will be opened
            if (row - 1 >= 0) {
                DemoPanel.openNode(DemoPanel.node[col][row - 1]); // North Node
            }
            if (col + 1 < DemoPanel.maxCol) {
                DemoPanel.openNode(DemoPanel.node[col + 1][row]); // East Node
            }
            if (row + 1 < DemoPanel.maxRow) {
                DemoPanel.openNode(DemoPanel.node[col][row + 1]); // South Node
            }
            if (col - 1 >= 0 ) {
                DemoPanel.openNode(DemoPanel.node[col - 1][row]); // West Node
            }
            
            //If there is no path, then return 
            if (DemoPanel.openList.size() == 0) {
                return;
            }

            // Algorithm for finding the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < DemoPanel.openList.size(); i++) {
                Node nodey = DemoPanel.openList.get(i);
                int num = nodey.fCost;
                if (num < bestNodefCost) {
                    // If node at index 'i' has a lower fCost, then bestNodeIndex = 'i'
                    // And bestNodefCost = openList.get(i).fCost
                    bestNodeIndex = i;
                    bestNodefCost = num;
                }
                else if (num == bestNodefCost && nodey.gCost < DemoPanel.openList.get(bestNodeIndex).gCost) {
                    // Else if the node has the same cost as the best node, then compare gCosts (Distance from start node)
                    bestNodeIndex = i;
                    bestNodefCost = num;
                }
            }
            // After loop, the currentNode becomes the next step
            DemoPanel.currentNode = DemoPanel.openList.get(bestNodeIndex);

            if (DemoPanel.currentNode == DemoPanel.goalNode) {
                DemoPanel.goalReached = true;
            }
        }
    }

}
