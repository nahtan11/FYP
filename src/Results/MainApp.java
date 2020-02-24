package src.Results;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.cells.CellGestures;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.AbegoTreeLayout;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration.Location;

import java.util.ArrayList;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        final SplitPane root = new SplitPane();

        Graph tree = new Graph();
        addTreeComponents(tree);
        root.getItems().add(tree.getCanvas());

        /*SequenceDiagram seqDiagram = new SequenceDiagram();
        addSequenceDiagramComponents(seqDiagram);
        root.getItems().add(seqDiagram.getCanvas());*/

        final Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }







    private void addTreeComponents(Graph graph) {
        final Model model = graph.getModel();
        graph.beginUpdate();

        final ICell cellA = new RectangleLabelCell();
        final ICell cellB = new RectangleLabelCell();
        final ICell cellC = new RectangleLabelCell();
        final ICell cellD = new RectangleLabelCell();
        final ICell cellE = new RectangleLabelCell();
        final ICell cellF = new RectangleLabelCell();
        final ICell cellG = new RectangleLabelCell();

        model.addCell(cellA);
        model.addCell(cellB);
        model.addCell(cellC);
        model.addCell(cellD);
        model.addCell(cellE);
        model.addCell(cellF);
        model.addCell(cellG);

        model.addEdge(cellA, cellB);
        model.addEdge(cellA, cellC);
        model.addEdge(cellA, cellD);
        model.addEdge(cellB, cellE);
        model.addEdge(cellC, cellF);
        model.addEdge(cellD, cellG);

        //final Edge edgeAB = new Edge(cellA, cellB);
        //edgeAB.textProperty().set("Edges can have text too!");
        //model.addEdge(edgeAB);
        //final CorneredEdge edgeAC = new CorneredEdge(cellA, cellC, Orientation.HORIZONTAL);
        //edgeAC.textProperty().set("Edges can have corners too!");
        //model.addEdge(edgeAC);
        //model.addEdge(cellB, cellD);
        //final DoubleCorneredEdge edgeBE = new DoubleCorneredEdge(cellB, cellE, Orientation.HORIZONTAL);
        //edgeBE.textProperty().set("You can implement custom edges and nodes too!");
        //model.addEdge(edgeBE);
        //model.addEdge(cellC, cellF);
        //model.addEdge(cellC, cellG);

        graph.endUpdate();
        graph.layout(new AbegoTreeLayout(300, 300, Location.Bottom));
    }

    /*private void addSequenceDiagramComponents(SequenceDiagram seqDiagram) {
        ActorCell actorA = new ActorCell("Actor A", 400d);
        ActorCell actorB = new ActorCell("Actor B", 400d);
        ActorCell actorC = new ActorCell("Actor C", 400d);
        Arrays.asList(actorA, actorB, actorC).forEach(actor -> seqDiagram.addActor(actor));

        seqDiagram.addMessage(actorA, actorB, "checkEmail");
        seqDiagram.addMessage(actorB, actorC, "readSavedUser");
        seqDiagram.addMessage(actorC, actorB, "savedUser");
        seqDiagram.addMessage(actorB, actorA, "noNewEmails");

        seqDiagram.layout();
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}

class RectangleLabelCell extends AbstractCell {
    public RectangleLabelCell() {
    }

    public Region getGraphic(Graph graph) {
        //Rectangle view = new Rectangle(50.0D, 50.0D);
        Pane back = new Pane();
        back.setStyle("-fx-background-color: grey;");
        back.prefWidth(100.0);
        back.prefHeight(100.0);
        GridPane sqs = new GridPane();
        sqs.setHgap(5);
        sqs.setVgap(5);
        sqs.setPadding(new Insets(5));
        back.getChildren().addAll(sqs);

        ArrayList<ArrayList<Integer>> Positions = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> PositionsDup = new ArrayList<ArrayList<Integer>>();
        /*int k=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;i++){
                ArrayList<Integer> sq = new ArrayList<Integer>();
                sq.add(j);
                sq.add(i);
                Positions.add(sq);
                PositionsDup.add(sq);
                String kDup = k+"";
                Button bt = new Button();
                bt.setText(kDup);
                bt.setMinWidth(10);
                bt.setMinHeight(10);
                bt.setStyle("-fx-background-color: Turquoise");
                sqs.add(bt, Positions.get(k).get(0), Positions.get(k).get(1));
                k++;
            }
        }*/

        ArrayList<Integer> sq1 = new ArrayList<Integer>();
        sq1.add(0);
        sq1.add(0);
        Positions.add(sq1);
        PositionsDup.add(sq1);

        ArrayList<Integer> sq2 = new ArrayList<Integer>();
        sq2.add(1);
        sq2.add(0);
        Positions.add(sq2);
        PositionsDup.add(sq2);

        ArrayList<Integer> sq3 = new ArrayList<Integer>();
        sq3.add(2);
        sq3.add(0);
        Positions.add(sq3);
        PositionsDup.add(sq3);

        ArrayList<Integer> sq4 = new ArrayList<Integer>();
        sq4.add(3);
        sq4.add(0);
        Positions.add(sq4);
        PositionsDup.add(sq4);

        ArrayList<Integer> sq5 = new ArrayList<Integer>();
        sq5.add(4);
        sq5.add(0);
        Positions.add(sq5);
        PositionsDup.add(sq5);

        ArrayList<Integer> sq6 = new ArrayList<Integer>();
        sq6.add(0);
        sq6.add(1);
        Positions.add(sq6);
        PositionsDup.add(sq6);

        ArrayList<Integer> sq7 = new ArrayList<Integer>();
        sq7.add(1);
        sq7.add(1);
        Positions.add(sq7);
        PositionsDup.add(sq7);

        ArrayList<Integer> sq8 = new ArrayList<Integer>();
        sq8.add(2);
        sq8.add(1);
        Positions.add(sq8);
        PositionsDup.add(sq8);

        ArrayList<Integer> sq9 = new ArrayList<Integer>();
        sq9.add(3);
        sq9.add(1);
        Positions.add(sq9);
        PositionsDup.add(sq9);

        ArrayList<Integer> sq10 = new ArrayList<Integer>();
        sq10.add(4);
        sq10.add(1);
        Positions.add(sq10);
        PositionsDup.add(sq10);

        ArrayList<Integer> sq11 = new ArrayList<Integer>();
        sq11.add(0);
        sq11.add(2);
        Positions.add(sq11);
        PositionsDup.add(sq11);

        ArrayList<Integer> sq12 = new ArrayList<Integer>();
        sq12.add(1);
        sq12.add(2);
        Positions.add(sq12);
        PositionsDup.add(sq12);

        ArrayList<Integer> sq13 = new ArrayList<Integer>();
        sq13.add(2);
        sq13.add(2);
        Positions.add(sq13);
        PositionsDup.add(sq13);

        ArrayList<Integer> sq14 = new ArrayList<Integer>();
        sq14.add(3);
        sq14.add(2);
        Positions.add(sq14);
        PositionsDup.add(sq14);

        ArrayList<Integer> sq15 = new ArrayList<Integer>();
        sq15.add(4);
        sq15.add(2);
        Positions.add(sq15);
        PositionsDup.add(sq15);

        ArrayList<Integer> sq16 = new ArrayList<Integer>();
        sq16.add(0);
        sq16.add(3);
        Positions.add(sq16);
        PositionsDup.add(sq16);

        ArrayList<Integer> sq17 = new ArrayList<Integer>();
        sq17.add(1);
        sq17.add(3);
        Positions.add(sq17);
        PositionsDup.add(sq17);

        ArrayList<Integer> sq18 = new ArrayList<Integer>();
        sq18.add(2);
        sq18.add(3);
        Positions.add(sq18);
        PositionsDup.add(sq18);

        ArrayList<Integer> sq19 = new ArrayList<Integer>();
        sq19.add(3);
        sq19.add(3);
        Positions.add(sq19);
        PositionsDup.add(sq19);

        ArrayList<Integer> sq20 = new ArrayList<Integer>();
        sq20.add(4);
        sq20.add(3);
        Positions.add(sq20);
        PositionsDup.add(sq20);

        ArrayList<Integer> sq21 = new ArrayList<Integer>();
        sq21.add(0);
        sq21.add(4);
        Positions.add(sq21);
        PositionsDup.add(sq21);

        ArrayList<Integer> sq22 = new ArrayList<Integer>();
        sq22.add(1);
        sq22.add(4);
        Positions.add(sq22);
        PositionsDup.add(sq22);

        ArrayList<Integer> sq23 = new ArrayList<Integer>();
        sq23.add(2);
        sq23.add(4);
        Positions.add(sq23);
        PositionsDup.add(sq23);

        ArrayList<Integer> sq24 = new ArrayList<Integer>();
        sq24.add(3);
        sq24.add(4);
        Positions.add(sq24);
        PositionsDup.add(sq24);

        ArrayList<Integer> sq25 = new ArrayList<Integer>();
        sq25.add(4);
        sq25.add(4);
        Positions.add(sq25);
        PositionsDup.add(sq25);

        Button s1 = new Button();
        s1.setText("1");
        sqs.add(s1, Positions.get(0).get(0), Positions.get(0).get(1));
        s1.setMinWidth(30);
        s1.setMinHeight(30);
        s1.setStyle("-fx-background-color: Turquoise");

        Button s2 = new Button();
        s2.setText("2");
        s2.setMinWidth(30);
        s2.setMinHeight(30);
        sqs.add(s2, Positions.get(1).get(0), Positions.get(1).get(1));
        s2.setStyle("-fx-background-color: Turquoise");

        Button s3 = new Button();
        s3.setText("3");
        s3.setMinWidth(30);
        s3.setMinHeight(30);
        sqs.add(s3, Positions.get(2).get(0), Positions.get(2).get(1));
        s3.setStyle("-fx-background-color: Turquoise");

        Button s4 = new Button();
        s4.setText("4");
        s4.setMinWidth(30);
        s4.setMinHeight(30);
        sqs.add(s4, Positions.get(3).get(0), Positions.get(3).get(1));
        s4.setStyle("-fx-background-color: Turquoise");

        Button s5 = new Button();
        s5.setText("5");
        s5.setMinWidth(30);
        s5.setMinHeight(30);
        sqs.add(s5, Positions.get(4).get(0), Positions.get(4).get(1));
        s5.setStyle("-fx-background-color: Turquoise");

        Button s6 = new Button();
        s6.setText("6");
        s6.setMinWidth(30);
        s6.setMinHeight(30);
        sqs.add(s6, Positions.get(5).get(0), Positions.get(5).get(1));
        s6.setStyle("-fx-background-color: Turquoise");

        Button s7 = new Button();
        s7.setText("7");
        s7.setMinWidth(30);
        s7.setMinHeight(30);
        sqs.add(s7, Positions.get(6).get(0), Positions.get(6).get(1));
        s7.setStyle("-fx-background-color: Turquoise");

        Button s8 = new Button();
        s8.setText("8");
        s8.setMinWidth(30);
        s8.setMinHeight(30);
        sqs.add(s8, Positions.get(7).get(0), Positions.get(7).get(1));
        s8.setStyle("-fx-background-color: Turquoise");

        Button s9 = new Button();
        s9.setText("9");
        s9.setMinWidth(30);
        s9.setMinHeight(30);
        sqs.add(s9, Positions.get(8).get(0), Positions.get(8).get(1));
        s9.setStyle("-fx-background-color: Turquoise");

        Button s10 = new Button();
        s10.setText("10");
        s10.setMinWidth(30);
        s10.setMinHeight(30);
        sqs.add(s10, Positions.get(9).get(0), Positions.get(9).get(1));
        s10.setStyle("-fx-background-color: Turquoise");

        Button s11 = new Button();
        s11.setText("11");
        s11.setMinWidth(30);
        s11.setMinHeight(30);
        sqs.add(s11, Positions.get(10).get(0), Positions.get(10).get(1));
        s11.setStyle("-fx-background-color: Turquoise");

        Button s12 = new Button();
        s12.setText("12");
        s12.setMinWidth(30);
        s12.setMinHeight(30);
        sqs.add(s12, Positions.get(11).get(0), Positions.get(11).get(1));
        s12.setStyle("-fx-background-color: Turquoise");

        Button s13 = new Button();
        s13.setText("13");
        s13.setMinWidth(30);
        s13.setMinHeight(30);
        sqs.add(s13, Positions.get(12).get(0), Positions.get(12).get(1));
        s13.setStyle("-fx-background-color: Turquoise");

        Button s14 = new Button();
        s14.setText("14");
        s14.setMinWidth(30);
        s14.setMinHeight(30);
        sqs.add(s14, Positions.get(13).get(0), Positions.get(13).get(1));
        s14.setStyle("-fx-background-color: Turquoise");

        Button s15 = new Button();
        s15.setText("15");
        s15.setMinWidth(30);
        s15.setMinHeight(30);
        sqs.add(s15, Positions.get(14).get(0), Positions.get(14).get(1));
        s15.setStyle("-fx-background-color: Turquoise");

        Button s16 = new Button();
        s16.setText("16");
        s16.setMinWidth(30);
        s16.setMinHeight(30);
        sqs.add(s16, Positions.get(15).get(0), Positions.get(15).get(1));
        s16.setStyle("-fx-background-color: Turquoise");

        Button s17 = new Button();
        s17.setText("17");
        s17.setMinWidth(30);
        s17.setMinHeight(30);
        sqs.add(s17, Positions.get(16).get(0), Positions.get(16).get(1));
        s17.setStyle("-fx-background-color: Turquoise");

        Button s18 = new Button();
        s18.setText("18");
        s18.setMinWidth(30);
        s18.setMinHeight(30);
        sqs.add(s18, Positions.get(17).get(0), Positions.get(17).get(1));
        s18.setStyle("-fx-background-color: Turquoise");

        Button s19 = new Button();
        s19.setText("19");
        s19.setMinWidth(30);
        s19.setMinHeight(30);
        sqs.add(s19, Positions.get(18).get(0), Positions.get(18).get(1));
        s19.setStyle("-fx-background-color: Turquoise");

        Button s20 = new Button();
        s20.setText("20");
        s20.setMinWidth(30);
        s20.setMinHeight(30);
        sqs.add(s20, Positions.get(19).get(0), Positions.get(19).get(1));
        s20.setStyle("-fx-background-color: Turquoise");

        Button s21 = new Button();
        s21.setText("21");
        s21.setMinWidth(30);
        s21.setMinHeight(30);
        sqs.add(s21, Positions.get(20).get(0), Positions.get(20).get(1));
        s21.setStyle("-fx-background-color: Turquoise");

        Button s22 = new Button();
        s22.setText("22");
        s22.setMinWidth(30);
        s22.setMinHeight(30);
        sqs.add(s22, Positions.get(21).get(0), Positions.get(21).get(1));
        s22.setStyle("-fx-background-color: Turquoise");

        Button s23 = new Button();
        s23.setText("23");
        s23.setMinWidth(30);
        s23.setMinHeight(30);
        sqs.add(s23, Positions.get(22).get(0), Positions.get(22).get(1));
        s23.setStyle("-fx-background-color: Turquoise");

        Button s24 = new Button();
        s24.setText("24");
        s24.setMinWidth(30);
        s24.setMinHeight(30);
        sqs.add(s24, Positions.get(23).get(0), Positions.get(23).get(1));
        s24.setStyle("-fx-background-color: Turquoise");

        Button s25 = new Button();
        s25.setText("X");
        s25.setTextFill(Color.rgb(200,200,200,0.0));

        s25.setMinWidth(30);
        s25.setMinHeight(30);
        sqs.add(s25, Positions.get(24).get(0), Positions.get(24).get(1));
        //s25.setStyle("-fx-background-color: MediumSeaGreen");

        Button view = new Button("1\t\t2\t\t3\t4\t\t5\t\t\n\n6\t\t7\t\t8\t\t9\t\t10\t\t\n\n11\t\t12\t\t13\t\t14\t15\t\t\n\n16\t\t17\t\t18\t\t19\t\t20\t\t\n\n21\t\t22\t\t23\t\t24\t\tX\t\t");
        //view.setStroke(Color.DODGERBLUE);
        //view.setFill(Color.DODGERBLUE);
        Pane pane = new Pane(new Node[]{sqs});
        pane.setStyle("-fx-background-color: grey;");
        pane.setPrefSize(180.0D, 180.0D);
        sqs.prefWidthProperty().bind(pane.prefWidthProperty());
        sqs.prefHeightProperty().bind(pane.prefHeightProperty());
        CellGestures.makeResizable(pane);
        return pane;
    }
}