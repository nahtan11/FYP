package src.output;

import com.fxgraph.cells.AbstractCell;
import com.fxgraph.cells.CellGestures;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.AbegoTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;
import src.output.Board.puzzle;
import src.output.algorithms.astar;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

import static javafx.geometry.Pos.BASELINE_CENTER;

/**
 * src.output.Main
 */

public class Main extends Application {

    public TextArea text_area = new TextArea();
    private int counter = 0;
    private Label label = new Label("Moves: 0");
    private boolean shuffling = false;

    private Node getNodeFromGridPane(GridPane pane, int col, int row) {
        for (Node node : pane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    static Map<String, puzzle> states  = new HashMap<String, puzzle> ();


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("..\\output\\UI\\UserInterface.fxml"));
        primaryStage.setTitle("24-Puzzle AI");

        //JFrame treeFrame = new JFrame();
        //treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //treeFrame.setSize(1000, 1000);
        //treeFrame.setVisible(true);
        //treeStructure(treeFrame);

        Button Random = new Button();
        Random.setText("Random");
        Random.setMinWidth(20);
        Random.setMinHeight(20);

        Button Reset = new Button();
        Reset.setText("Reset");
        Reset.setMinWidth(20);
        Reset.setMinHeight(20);


        Button AStar = new Button();
        AStar.setText("AStar");
        AStar.setMinWidth(20);
        AStar.setMinHeight(20);
        //back.setTop(AStar);


        Button shuffle = new Button();
        shuffle.setText("Shuffle Board");
        shuffle.setMinWidth(20);
        shuffle.setMinHeight(20);

        TextField shuffleAmt = new TextField ();
        shuffleAmt.setMinWidth(20);
        shuffleAmt.setMinHeight(20);
        shuffleAmt.setPromptText("Shuffle Amount. Eg 50");

        Button Results = new Button();
        Results.setText("Results");
        Results.setMinWidth(20);
        Results.setMinHeight(20);

        // create a label
        Label popupLabel = new Label("Game Complete");
        popupLabel.setMinWidth(200);
        popupLabel.setMinHeight(200);
        popupLabel.setAlignment(BASELINE_CENTER);
        popupLabel.setStyle(" -fx-background-color: white;");

        // create a popup
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(popupLabel);

        ArrayList<ArrayList<Integer>> Positions = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> PositionsDup = new ArrayList<ArrayList<Integer>>();
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


        //Collections.shuffle(Positions);

        ArrayList<Button> butts = new ArrayList<Button>();

        Pane backpane = new Pane();
        backpane.setStyle("-fx-background-color: grey;");
        backpane.prefHeight(600);
        backpane.prefWidth(1000);

        HBox back = new HBox();
        back.prefHeight(600);
        back.prefWidth(1000);


        text_area.setMinSize(500.0,525.0);
        text_area.setPadding(new Insets(0, 0, 0, 0));
        text_area.setStyle("-fx-text-fill: white ;-fx-control-inner-background:#000000;") ;

        VBox vbox1 = new VBox();
        vbox1.prefHeight(600);
        vbox1.prefWidth(500);
        vbox1.setStyle("-fx-background-color: grey;");

        VBox vbox2 = new VBox();
        vbox2.prefHeight(600);
        vbox2.prefWidth(500);
        vbox2.setStyle("-fx-background-color: grey;");
        vbox2.getChildren().addAll(text_area);

        backpane.getChildren().addAll(back);
        back.getChildren().addAll(vbox1,vbox2);

        GridPane sqs = new GridPane();
        sqs.setHgap(5);
        sqs.setVgap(5);
        sqs.setPadding(new Insets(5));

        HBox hbox = new HBox();
        hbox.setAlignment(BASELINE_CENTER);
        hbox.prefHeight(100);
        hbox.prefWidth(200);
        hbox.getChildren().addAll(Reset,Random,AStar,shuffle,shuffleAmt,Results);

        vbox1.getChildren().addAll(sqs,hbox);

        primaryStage.setScene(new Scene(backpane, 1040, 600));
        primaryStage.show();




        Button s1 = new Button();
        s1.setText("1");
        sqs.add(s1, Positions.get(0).get(0), Positions.get(0).get(1));
        s1.setMinWidth(100);
        s1.setMinHeight(100);
        s1.setStyle("-fx-background-color: Turquoise");
        butts.add(s1);

        Button s2 = new Button();
        s2.setText("2");
        s2.setMinWidth(100);
        s2.setMinHeight(100);
        sqs.add(s2, Positions.get(1).get(0), Positions.get(1).get(1));
        s2.setStyle("-fx-background-color: Turquoise");
        butts.add(s2);

        Button s3 = new Button();
        s3.setText("3");
        s3.setMinWidth(100);
        s3.setMinHeight(100);
        sqs.add(s3, Positions.get(2).get(0), Positions.get(2).get(1));
        s3.setStyle("-fx-background-color: Turquoise");
        butts.add(s3);

        Button s4 = new Button();
        s4.setText("4");
        s4.setMinWidth(100);
        s4.setMinHeight(100);
        sqs.add(s4, Positions.get(3).get(0), Positions.get(3).get(1));
        s4.setStyle("-fx-background-color: Turquoise");
        butts.add(s4);

        Button s5 = new Button();
        s5.setText("5");
        s5.setMinWidth(100);
        s5.setMinHeight(100);
        sqs.add(s5, Positions.get(4).get(0), Positions.get(4).get(1));
        s5.setStyle("-fx-background-color: Turquoise");
        butts.add(s5);

        Button s6 = new Button();
        s6.setText("6");
        s6.setMinWidth(100);
        s6.setMinHeight(100);
        sqs.add(s6, Positions.get(5).get(0), Positions.get(5).get(1));
        s6.setStyle("-fx-background-color: Turquoise");
        butts.add(s6);

        Button s7 = new Button();
        s7.setText("7");
        s7.setMinWidth(100);
        s7.setMinHeight(100);
        sqs.add(s7, Positions.get(6).get(0), Positions.get(6).get(1));
        s7.setStyle("-fx-background-color: Turquoise");
        butts.add(s7);

        Button s8 = new Button();
        s8.setText("8");
        s8.setMinWidth(100);
        s8.setMinHeight(100);
        sqs.add(s8, Positions.get(7).get(0), Positions.get(7).get(1));
        s8.setStyle("-fx-background-color: Turquoise");
        butts.add(s8);

        Button s9 = new Button();
        s9.setText("9");
        s9.setMinWidth(100);
        s9.setMinHeight(100);
        sqs.add(s9, Positions.get(8).get(0), Positions.get(8).get(1));
        s9.setStyle("-fx-background-color: Turquoise");
        butts.add(s9);

        Button s10 = new Button();
        s10.setText("10");
        s10.setMinWidth(100);
        s10.setMinHeight(100);
        sqs.add(s10, Positions.get(9).get(0), Positions.get(9).get(1));
        s10.setStyle("-fx-background-color: Turquoise");
        butts.add(s10);

        Button s11 = new Button();
        s11.setText("11");
        s11.setMinWidth(100);
        s11.setMinHeight(100);
        sqs.add(s11, Positions.get(10).get(0), Positions.get(10).get(1));
        s11.setStyle("-fx-background-color: Turquoise");
        butts.add(s11);

        Button s12 = new Button();
        s12.setText("12");
        s12.setMinWidth(100);
        s12.setMinHeight(100);
        sqs.add(s12, Positions.get(11).get(0), Positions.get(11).get(1));
        s12.setStyle("-fx-background-color: Turquoise");
        butts.add(s12);

        Button s13 = new Button();
        s13.setText("13");
        s13.setMinWidth(100);
        s13.setMinHeight(100);
        sqs.add(s13, Positions.get(12).get(0), Positions.get(12).get(1));
        s13.setStyle("-fx-background-color: Turquoise");
        butts.add(s13);

        Button s14 = new Button();
        s14.setText("14");
        s14.setMinWidth(100);
        s14.setMinHeight(100);
        sqs.add(s14, Positions.get(13).get(0), Positions.get(13).get(1));
        s14.setStyle("-fx-background-color: Turquoise");
        butts.add(s14);

        Button s15 = new Button();
        s15.setText("15");
        s15.setMinWidth(100);
        s15.setMinHeight(100);
        sqs.add(s15, Positions.get(14).get(0), Positions.get(14).get(1));
        s15.setStyle("-fx-background-color: Turquoise");
        butts.add(s15);

        Button s16 = new Button();
        s16.setText("16");
        s16.setMinWidth(100);
        s16.setMinHeight(100);
        sqs.add(s16, Positions.get(15).get(0), Positions.get(15).get(1));
        s16.setStyle("-fx-background-color: Turquoise");
        butts.add(s16);

        Button s17 = new Button();
        s17.setText("17");
        s17.setMinWidth(100);
        s17.setMinHeight(100);
        sqs.add(s17, Positions.get(16).get(0), Positions.get(16).get(1));
        s17.setStyle("-fx-background-color: Turquoise");
        butts.add(s17);

        Button s18 = new Button();
        s18.setText("18");
        s18.setMinWidth(100);
        s18.setMinHeight(100);
        sqs.add(s18, Positions.get(17).get(0), Positions.get(17).get(1));
        s18.setStyle("-fx-background-color: Turquoise");
        butts.add(s18);

        Button s19 = new Button();
        s19.setText("19");
        s19.setMinWidth(100);
        s19.setMinHeight(100);
        sqs.add(s19, Positions.get(18).get(0), Positions.get(18).get(1));
        s19.setStyle("-fx-background-color: Turquoise");
        butts.add(s19);

        Button s20 = new Button();
        s20.setText("20");
        s20.setMinWidth(100);
        s20.setMinHeight(100);
        sqs.add(s20, Positions.get(19).get(0), Positions.get(19).get(1));
        s20.setStyle("-fx-background-color: Turquoise");
        butts.add(s20);

        Button s21 = new Button();
        s21.setText("21");
        s21.setMinWidth(100);
        s21.setMinHeight(100);
        sqs.add(s21, Positions.get(20).get(0), Positions.get(20).get(1));
        s21.setStyle("-fx-background-color: Turquoise");
        butts.add(s21);

        Button s22 = new Button();
        s22.setText("22");
        s22.setMinWidth(100);
        s22.setMinHeight(100);
        sqs.add(s22, Positions.get(21).get(0), Positions.get(21).get(1));
        s22.setStyle("-fx-background-color: Turquoise");
        butts.add(s22);

        Button s23 = new Button();
        s23.setText("23");
        s23.setMinWidth(100);
        s23.setMinHeight(100);
        sqs.add(s23, Positions.get(22).get(0), Positions.get(22).get(1));
        s23.setStyle("-fx-background-color: Turquoise");
        butts.add(s23);

        Button s24 = new Button();
        s24.setText("24");
        s24.setMinWidth(100);
        s24.setMinHeight(100);
        sqs.add(s24, Positions.get(23).get(0), Positions.get(23).get(1));
        s24.setStyle("-fx-background-color: Turquoise");
        butts.add(s24);

        Button s25 = new Button();
        s25.setText("-1");
        s25.setTextFill(Color.rgb(200,200,200,0.0));

        s25.setMinWidth(100);
        s25.setMinHeight(100);
        sqs.add(s25, Positions.get(24).get(0), Positions.get(24).get(1));
        //s25.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s25);





        Task<Void> random = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                Set<String> boardState = new LinkedHashSet<String>();
                // Add delay code from initial attempt
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                while(!(checkWin(butts,PositionsDup))){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);
                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));

                    String board = getBoardState(sqs);
                    boardState.add(board);
                    System.out.println(boardState);

                    Platform.runLater(()->buttsNear.get(i).fire());
                    //Thread.sleep(50);
                    if(!(checkWin(butts,PositionsDup))){
                        Thread.sleep(50);
                    }
                }

                return null;
            }
        };

        Task<Void> aStar = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }


                int [][] desired = {{1,2,3,4,5}, {6,7,8,9,10},{11,12,13,14,15}, {16,17,18,19,20},{21,22,23,24,-1}};
                int [][] puzzle  = new int [5][5];

                ArrayList<String> winningMoves = new ArrayList<>();

                String board = getBoardState(sqs);
                String [] boardNums = board.split(",");

                int boardIndex = 0;
                for(int i=0;i<5;i++){
                    for (int j=0;j<5;j++){
                        puzzle[i][j] = Integer.parseInt(boardNums[boardIndex]);
                        boardIndex++;
                    }
                }






                puzzle p = new puzzle(astar.converStateToString(puzzle),	"root", puzzle, 0, astar.fitness(desired, puzzle));
                winningMoves = astar.PuzzleSolvingObj(p, desired);
                System.out.println(winningMoves.size());
                winningMoves.remove("root");
                Collections.reverse(winningMoves);
                System.out.println("-------------------------------------\n");




                //Collections.swap(boardLayout, boardLayout.indexOf(buttTemp), boardLayout.indexOf("-1"));
                System.out.println("Begin.......");
                for(int i=1;i<winningMoves.size();i++){
                    //List<String> boardLayout = new ArrayList<String>(Arrays.asList(board.split(",")));
                    String currentBoard = winningMoves.get(i-1).toString();
                    List<String> currentBoardLayout = new ArrayList<String>(Arrays.asList(currentBoard.split("\\.")));

                    String nextBoard = winningMoves.get(i).toString();
                    List<String> nextBoardLayout = new ArrayList<String>(Arrays.asList(nextBoard.split("\\.")));
                    int indexOfMove = nextBoardLayout.indexOf("-1");
                    String moveToMake = currentBoardLayout.get(indexOfMove);
                    //System.out.println(currentBoard);

                    for(int j=0;j<butts.size();j++){
                        if(butts.get(j).getText().equals(moveToMake)){
                            Button btMove = butts.get(j);
                            Platform.runLater(()->btMove.fire());
                        }
                    }
                    Thread.sleep(400);
                }
                System.out.println("End.......");
                return null;
            }
        };

        Task<Void> shuffleBoard = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                Set<String> boardState = new LinkedHashSet<String>();
                int shuffleLevel = 0;
                int shuffleAmount = Integer.parseInt(shuffleAmt.getText());
                shuffling =true;
                // Add delay code from initial attempt
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                while(shuffleLevel!=shuffleAmount){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);
                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));

                    Platform.runLater(()->buttsNear.get(i).fire());
                    //Thread.sleep(50);

                    Thread.sleep(50);
                    shuffleLevel++;
                }

                shuffling=false;
                return null;
            }

        };

        Task<Void> results = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {
                Stage secondaryStage = new Stage();
                final SplitPane root = new SplitPane();
                final Scene scene2 = new Scene(root, 1024, 768);
                scene2.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                secondaryStage.setScene(scene2);
                secondaryStage.show();

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                //Stage secondaryStage = new Stage();
               // final SplitPane root = new SplitPane();

                Graph tree = new Graph();


                //final Scene scene2 = new Scene(root, 1024, 768);
                //scene2.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

                //secondaryStage.setScene(scene2);
                //secondaryStage.show();

                //Stage secondStage = new Stage();
                //secondStage.setScene(new Scene(new HBox(4, new Label("Second window"))));
                //secondStage.show();

                //mxGraph graph = new mxGraph();
                //Object parent = graph.getDefaultParent();

                LinkedHashMap<String, puzzle> metStates = astar.getMetStates();
                Iterator it = metStates.entrySet().iterator();
                int i=0;
                int x=0,y =0;
                while (it.hasNext()) {
                    x+=40;
                    y+=40;
                    Map.Entry pair = (Map.Entry)it.next();
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                    String nextState = PrintPuzzle(metStates.get(pair.getKey()).getState());
                    String stateOut = metStates.get(pair.getKey()).getState().toString();
                    System.out.println(stateOut);
                    Label boardState = new Label();
                    boardState.setText(nextState);
                    System.out.println(nextState);
                    String text = text_area.getText();
                    //text = text + "\n" + nextState;
                    //String areaText = text;
                    i++;
                    int iterate = i;
                    int x1=x;
                    int y1=y;

                    Platform.runLater(()->{
                        //text_area.appendText("\nMove: "+iterate+"\n--------------------------------------------------------\n" + nextState);
                        //graph.getModel().beginUpdate();
                        //try{
                        //    Object v1 = graph.insertVertex(parent, null, boardState.getText(), x1, y1, 150,
                        //            150);
                        //}
                        //finally
                        //{
                        //    graph.getModel().endUpdate();
                        //}
                        //treeFrame.invalidate();
                        //treeFrame.validate();
                        //treeFrame.repaint();
                        //mxGraphComponent graphComponent = new mxGraphComponent(graph);
                        //treeFrame.getContentPane().add(graphComponent);

                        addTreeComponents(tree);
                        root.getItems().add(tree.getCanvas());

                    });
                    it.remove(); // avoids a ConcurrentModificationException
                    //Thread.sleep(50);

                    Thread.sleep(500);


                }

                return null;
            }
        };

        Reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                primaryStage.hide();
                text_area.clear();
                try {
                    start(new Stage());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Random.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(random).start();
            }
        });
        AStar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(aStar).start();
            }
        });
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(shuffleBoard).start();
            }
        });
        Results.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(results).start();
            }
        });




        s1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(shuffling)
                {
                    moveTile(s1,s25,sqs);
                }
                else if(proximityCheck(s1, s25)) {
                    moveTile(s1,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s2,s25,sqs);
                }
                else if(proximityCheck(s2,s25)) {
                    moveTile(s2,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s3,s25,sqs);
                }
                else if(proximityCheck(s3, s25)) {
                    moveTile(s3,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s4,s25,sqs);
                }
                else if(proximityCheck(s4, s25)) {
                    moveTile(s4,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s5.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s5,s25,sqs);
                }
                else if(proximityCheck(s5, s25)) {
                    moveTile(s5,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s6.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s6,s25,sqs);
                }
                else if(proximityCheck(s6, s25)) {
                    moveTile(s6,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s7.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s7,s25,sqs);
                }
                else if(proximityCheck(s7,s25)) {
                    moveTile(s7,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s8.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s8,s25,sqs);
                }
                else if(proximityCheck(s8, s25)) {
                    moveTile(s8,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s9.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s9,s25,sqs);
                }
                else if(proximityCheck(s9, s25)) {
                    moveTile(s9,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s10.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s10,s25,sqs);
                }
                else if(proximityCheck(s10, s25)) {
                    moveTile(s10,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s11.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s11,s25,sqs);
                }
                else if(proximityCheck(s11,s25)) {
                    moveTile(s11,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s12.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s12,s25,sqs);
                }
                else if(proximityCheck(s12, s25)) {
                    moveTile(s12,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s13.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s13,s25,sqs);
                }
                else if(proximityCheck(s13, s25)) {
                    moveTile(s13,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s14.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s14,s25,sqs);
                }
                else if(proximityCheck(s14, s25)) {
                    moveTile(s14,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s15.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s15,s25,sqs);
                }
                else if(proximityCheck(s15, s25)) {
                    moveTile(s15,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s16.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s16,s25,sqs);
                }
                else if(proximityCheck(s16,s25)) {
                    moveTile(s16,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s17.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s17,s25,sqs);
                }
                else if(proximityCheck(s17, s25)) {
                    moveTile(s17,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s18.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s18,s25,sqs);
                }
                else if(proximityCheck(s18, s25)) {
                    moveTile(s18,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s19.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s19,s25,sqs);
                }
                else if(proximityCheck(s19, s25)) {
                    moveTile(s19,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s20.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s20,s25,sqs);
                }
                //moveTile(s20,s25,sqs);
                else if(proximityCheck(s20, s25)) {
                    moveTile(s20,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s21.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s21,s25,sqs);
                }
                else if(proximityCheck(s21, s25)) {
                    moveTile(s21,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s22.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s22,s25,sqs);
                }
                else if(proximityCheck(s22, s25)) {
                    moveTile(s22,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s23.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s23,s25,sqs);
                }
                else if(proximityCheck(s23, s25)) {
                    moveTile(s23,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s24.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(shuffling)
                {
                    moveTile(s24,s25,sqs);
                }
                else if(proximityCheck(s24, s25)) {
                    moveTile(s24,s25,sqs);
                    counter();
                    label.setText("Moves: "+Integer.toString(counter));
                    if(checkWin(butts,PositionsDup)){
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                }
                else{
                    System.out.println("false");
                }
            }
        });



    }

    public void  moveTile(Button button, Button blank, GridPane sqs){
        int tmpR = GridPane.getRowIndex(button);
        int tmpC = GridPane.getColumnIndex(button);
        sqs.getChildren().remove(button);
        sqs.add(button, GridPane.getColumnIndex(blank), GridPane.getRowIndex(blank));
        sqs.getChildren().remove(blank);
        sqs.add(blank, tmpC, tmpR);
    }

    public int getTilesOutOfPlace(/*ArrayList<ArrayList<Integer>> PositionsDup, ArrayList<Button> butts*/List<String> boardState) {

        int tOOP = 0;
        List<String> winningState = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","-1"));

        for(int i =0;i< winningState.size();i++){
            if(!(winningState.get(i).equals(boardState.get(i)))){
                tOOP++;
            }
        }

        /*for(int i = 0;i<PositionsDup.size();i++)
        {
            int buttonC = GridPane.getColumnIndex(butts.get(i));
            int buttonR = GridPane.getRowIndex(butts.get(i));

            int posC = PositionsDup.get(i).get(0);
            int posR = PositionsDup.get(i).get(1);

            if (!(buttonC == posC && buttonR == posR)){
                tOOP++;
            }

        }*/
        return tOOP;
    }

    public Boolean proximityCheck(Button butt, Button emptyButt){
        int emptyC = GridPane.getColumnIndex(emptyButt);
        int emptyR = GridPane.getRowIndex(emptyButt);
        String emptyPos = emptyC + "" + emptyR;

        int tmpC = GridPane.getColumnIndex(butt);
        int tmpR = GridPane.getRowIndex(butt);
        String buttPos = tmpC + "" + tmpR;

        if((emptyPos.equals("00")) && (buttPos.equals("10") || buttPos.equals("01"))){
            return true;
        }
        if((emptyPos.equals("10")) && (buttPos.equals("00") || buttPos.equals("11") || buttPos.equals("20"))){
            return true;
        }
        if((emptyPos.equals("20")) && (buttPos.equals("10") || buttPos.equals("21")|| buttPos.equals("30"))){
            return true;
        }
        if((emptyPos.equals("30")) && (buttPos.equals("20") || buttPos.equals("31")|| buttPos.equals("40"))){
            return true;
        }
        if((emptyPos.equals("40")) && (buttPos.equals("30") || buttPos.equals("41"))){
            return true;
        }
        if((emptyPos.equals("01")) && (buttPos.equals("00") || buttPos.equals("11") || buttPos.equals("02"))){
            return true;
        }
        if((emptyPos.equals("11")) && (buttPos.equals("01") || buttPos.equals("10") || buttPos.equals("21") || buttPos.equals("12"))){
            return true;
        }
        if((emptyPos.equals("21")) && (buttPos.equals("11") || buttPos.equals("20") || buttPos.equals("22")|| buttPos.equals("31"))){
            return true;
        }
        if((emptyPos.equals("31")) && (buttPos.equals("21") || buttPos.equals("32")|| buttPos.equals("41")|| buttPos.equals("30"))){
            return true;
        }
        if((emptyPos.equals("41")) && (buttPos.equals("31") || buttPos.equals("42")|| buttPos.equals("40"))){
            return true;
        }
        if((emptyPos.equals("02")) && (buttPos.equals("01") || buttPos.equals("12")|| buttPos.equals("03"))){
            return true;
        }
        if((emptyPos.equals("12")) && (buttPos.equals("02") || buttPos.equals("11") || buttPos.equals("22")|| buttPos.equals("13"))){
            return true;
        }
        if((emptyPos.equals("22")) && (buttPos.equals("12") || buttPos.equals("21")|| buttPos.equals("23")|| buttPos.equals("32"))){
            return true;
        }
        if((emptyPos.equals("32")) && (buttPos.equals("22") || buttPos.equals("33")|| buttPos.equals("42")|| buttPos.equals("31"))){
            return true;
        }
        if((emptyPos.equals("42")) && (buttPos.equals("32") || buttPos.equals("43")|| buttPos.equals("41"))){
            return true;
        }
        if((emptyPos.equals("03")) && (buttPos.equals("02") || buttPos.equals("13")|| buttPos.equals("04"))){
            return true;
        }
        if((emptyPos.equals("13")) && (buttPos.equals("03") || buttPos.equals("12") || buttPos.equals("23")|| buttPos.equals("14"))){
            return true;
        }
        if((emptyPos.equals("23")) && (buttPos.equals("13") || buttPos.equals("22") || buttPos.equals("33")|| buttPos.equals("24"))){
            return true;
        }
        if((emptyPos.equals("33")) && (buttPos.equals("23") || buttPos.equals("32")|| buttPos.equals("43")|| buttPos.equals("34"))){
            return true;
        }
        if((emptyPos.equals("43")) && (buttPos.equals("33") || buttPos.equals("42")|| buttPos.equals("44"))){
            return true;
        }
        if((emptyPos.equals("04")) && (buttPos.equals("03") || buttPos.equals("14"))){
            return true;
        }
        if((emptyPos.equals("14")) && (buttPos.equals("04") || buttPos.equals("13") || buttPos.equals("24"))){
            return true;
        }
        if((emptyPos.equals("24")) && (buttPos.equals("14") || buttPos.equals("23") || buttPos.equals("34"))){
            return true;
        }
        if((emptyPos.equals("34")) && (buttPos.equals("24") || buttPos.equals("33")|| buttPos.equals("44"))){
            return true;
        }
        if((emptyPos.equals("44")) && (buttPos.equals("34") || buttPos.equals("43"))){
            return true;
        }



        return false;
    }

    public void counter(){
        counter++;
    }



    public String getBoardState(GridPane sqs){
        String boardState = "";

        for (int i =0;i<5;i++){
            for (int j = 0; j<5;j++){
                Button tmp = (Button) getNodeFromGridPane(sqs,j,i);
                boardState += tmp.getText() + ",";
            }
        }
        boardState = boardState.substring(0, boardState.length()-1);

        return boardState;
    }

    public boolean checkWin(ArrayList<Button> butts, ArrayList<ArrayList<Integer>> PositionsDup){

        boolean temp = true;
        int i = 0;
        while (temp && i < butts.size()) {

            int buttonC = GridPane.getColumnIndex(butts.get(i));
            int buttonR = GridPane.getRowIndex(butts.get(i));

            int posC = PositionsDup.get(i).get(0);
            int posR = PositionsDup.get(i).get(1);

            if (buttonC == posC && buttonR == posR) {
                temp = true;
                i++;
            }else{
                temp = false;
            }
        }

        return temp;

    }

    public ArrayList<Button> getSurroundingTiles(GridPane sqs, Button s25){
        ArrayList<Button> buttsNear = new ArrayList<Button>();

        int tmpR = sqs.getRowIndex(s25);
        int tmpC = sqs.getColumnIndex(s25);
        String blank = tmpC + "" + tmpR;

        if(blank.equals("00")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,1));
        }

        if(blank.equals("10")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,0));
        }

        if(blank.equals("20")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,0));
        }

        if(blank.equals("30")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,0));
        }

        if(blank.equals("40")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,1));
        }

        if(blank.equals("01")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,2));
        }

        if(blank.equals("11")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,1));
        }

        if(blank.equals("21")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,1));
        }

        if(blank.equals("31")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,1));
        }

        if(blank.equals("41")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,0));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,2));
        }

        if(blank.equals("02")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,3));

        }

        if(blank.equals("12")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,2));
        }

        if(blank.equals("22")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,2));
        }

        if(blank.equals("32")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,2));
        }

        if(blank.equals("42")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,1));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,3));
        }

        if(blank.equals("03")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,4));

        }

        if(blank.equals("13")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,3));
        }

        if(blank.equals("23")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,3));
        }

        if(blank.equals("33")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,3));
        }

        if(blank.equals("43")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,2));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,4));
        }

        if(blank.equals("04")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,4));

        }

        if(blank.equals("14")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,0,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,4));
        }

        if(blank.equals("24")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,1,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,4));
        }

        if(blank.equals("34")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,2,4));
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,4));
        }

        if(blank.equals("44")){
            buttsNear.add((Button)getNodeFromGridPane(sqs,4,3));
            buttsNear.add((Button)getNodeFromGridPane(sqs,3,4));
        }

        return buttsNear;
    }

    public static String PrintPuzzle(int [][] puzzle){
        String puzzle_out="";
        for (int i=0;i<puzzle.length;i++) {
            //puzzle_out+="|\t";
            puzzle_out+="";
            for (int j=0;j<puzzle.length;j++) {
                if(puzzle[i][j]!=-1) {

                    //puzzle_out+=puzzle[i][j] + "\t|\t";
                    puzzle_out+=puzzle[i][j]+",";
                    //System.out.print(puzzle[i][j] + "\t");
                }
                else {
                    //puzzle_out+="X\t|\t";
                    puzzle_out+="X,";
                    //System.out.print("X\t");
                }
            }
            //puzzle_out+="\n--------------------------------------------------------\n\n";
            //puzzle_out+="\n";
            //System.out.println();
        }
        //System.out.println();
        return puzzle_out;

    }

    public static String convertStateToString(int [][] puzzle) {
        String State = "";
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                State = State + puzzle[i][j]+".";
            }
        }
        return State;
    }

    private static final long serialVersionUID = -2707712944901661771L;
    public void treeStructure(JFrame frame)
    {
        //super("Hello, World!");
        LinkedHashMap<String, puzzle> metStates = astar.getMetStates();
        System.out.println(metStates);
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            /*Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
                    30);
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);*/
            Iterator it = metStates.entrySet().iterator();
            int i=0;
            int x = 20;
            int y = 20;
            while (it.hasNext()) {
                x+=40;
                y+=40;
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(i);
                String nextState = PrintPuzzle(metStates.get(pair.getKey()).getState());
                Object v1 = graph.insertVertex(parent, null, nextState, x, y, 80,
                        30);
                i++;

                it.remove(); // avoids a ConcurrentModificationException
                //Thread.sleep(50);


            }
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        frame.getContentPane().add(graphComponent);
    }

    private void addTreeComponents(Graph graph) {
        List<String> input = Arrays.asList("1,2,3,X,4,6,7,8,9,5,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20", "1,2,X,3,4,6,7,8,9,5,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20","1,2,3,9,4,6,7,8,X,5,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20","1,2,3,4,X,6,7,8,9,5,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20","1,2,3,4,5,6,7,8,9,X,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20","1,2,3,4,5,6,7,8,X,9,11,12,13,14,10,16,17,18,19,15,21,22,23,24,20","1,2,3,4,5,6,7,8,9,10,11,12,13,14,X,16,17,18,19,15,21,22,23,24,20","1,2,3,4,5,6,7,8,9,10,11,12,13,X,14,16,17,18,19,15,21,22,23,24,20","1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,X,21,22,23,24,20","1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,X,19,21,22,23,24,20","1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,X");
        ArrayList<ICell> cells = new ArrayList<ICell>();
        final Model model = graph.getModel();
        graph.beginUpdate();
        //for(int i=0; i<input.size();i++)
        //{


        ICell cell = new RectangleLabelCell("hi");
            /*final ICell cellB = new RectangleLabelCell();
            final ICell cellC = new RectangleLabelCell();
            final ICell cellD = new RectangleLabelCell();
            final ICell cellE = new RectangleLabelCell();
            final ICell cellF = new RectangleLabelCell();
            final ICell cellG = new RectangleLabelCell();*/

        model.addCell(cell);
            //cells.add(cell);
            //if(i>0){
            //    model.addEdge(cells.get(i),cells.get(i-1));
            //}
            /*model.addCell(cellB);
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
            model.addEdge(cellD, cellG);*/

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


        //}
        graph.endUpdate();
        graph.layout(new AbegoTreeLayout(300, 300, Configuration.Location.Bottom));
    }




    public static void main(String[] args) {
        launch(args);
    }
}

class RectangleLabelCell extends AbstractCell {
    public RectangleLabelCell(String board) {
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

