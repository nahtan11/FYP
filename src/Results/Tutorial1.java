package src.Results;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import javafx.scene.control.Button;
import javax.swing.*;
import java.awt.*;

public class Tutorial1 {
    public static void main(String args[]) {
        //System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Object[][] data = new Object[][] {
                {1, 2, 3, 4,5 },
                {6, 7, 8, 9,10 },
                {11, 12, 13, 14,15 },
                {16, 17, 18, 19,20 },
                {21, 22, 23, 24,"X" }
        };
        String[] columns = new String[] {
                "Id", "Name", "Hourly Rate", "Part Time","hello"
        };
        JTable root = new JTable(data,columns);
        JTable n1 = new JTable(data,columns);
        JTable n1_1 = new JTable(data,columns);
        JTable n1_2 = new JTable(data,columns);
        JTable n1_3 = new JTable(data,columns);
        JTable n2 = new JTable(data,columns);
        JTable n2_1 = new JTable(data,columns);

        Graph graph = new SingleGraph("Tutorial 1");

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        Button s13 = new Button();
        s13.setText("13\t12");
        Node A = graph.getNode("A");
        A.addAttribute("ui.label", s13);

        graph.display();
    }
}

