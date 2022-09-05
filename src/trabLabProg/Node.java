package trabLabProg;

import java.util.Comparator;

public class Node implements Comparator<Node> {
    String name;
    int cost;

    public Node(){}
    public Node(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
