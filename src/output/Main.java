package src.output;

import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.AbegoTreeLayout;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration;
import src.output.Board.puzzle;
import src.output.Traceability.CustomEdge;
import src.output.Traceability.CustomEdge2;
import src.output.Traceability.RectangleLabelCell;
import src.output.algorithms.AStar;

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
    private int nodeIt =0;
    ArrayList<String> winningStates = new ArrayList<>();
    ArrayList<String> allStates = new ArrayList<>();


    private Node getNodeFromGridPane(GridPane pane, int col, int row) {
        for (Node node : pane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("24-Puzzle AI");
        ArrayList<RectangleLabelCell> cells = new ArrayList<>();

        final SplitPane root = new SplitPane();
        root.setStyle("-fx-background-color: #545351;");

        Graph tree = new Graph();
        root.getItems().add(tree.getCanvas());

        Stage secondaryStage = new Stage();
        final Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

        DropShadow shadow = new DropShadow();

        //Random Selection
        Button Random = new Button();
        Random.setText("Random");
        Random.setMinWidth(20);
        Random.setMinHeight(20);
        Random.setStyle("-fx-base: purple;");
        Random.setEffect(shadow);

        //Reset button - not used
        /*Button Reset = new Button();
        Reset.setText("Reset");
        Reset.setMinWidth(20);
        Reset.setMinHeight(20);*/

        //AStar Algorithm button
        Button AStar = new Button();
        AStar.setText("AStar");
        AStar.setMinWidth(20);
        AStar.setMinHeight(20);
        AStar.setStyle("-fx-base: purple;");
        AStar.setEffect(shadow);


        //Shuffle button
        Button shuffle = new Button();
        shuffle.setText("Shuffle");
        shuffle.setMinWidth(20);
        shuffle.setMinHeight(20);
        shuffle.setStyle("-fx-base: purple;");
        shuffle.setEffect(shadow);

        //Shuffle Amount input
        TextField shuffleAmt = new TextField ();
        shuffleAmt.setMinWidth(20);
        shuffleAmt.setMinHeight(20);
        shuffleAmt.setPromptText("Shuffle Amount. Eg 50");

        //Results button
        Button Results = new Button();
        Results.setText("Results");
        Results.setMinWidth(20);
        Results.setMinHeight(20);
        Results.setStyle("-fx-base: purple;");
        Results.setEffect(shadow);

        //Advance graph button
        Button advanceGraph = new Button();
        advanceGraph.setText("Next Node");
        advanceGraph.setMinWidth(20);
        advanceGraph.setMinHeight(20);
        advanceGraph.setStyle("-fx-base: purple;");
        advanceGraph.setEffect(shadow);

        //Game Complete label - not used
        /*Label popupLabel = new Label("Game Complete");
        popupLabel.setMinWidth(200);
        popupLabel.setMinHeight(200);
        popupLabel.setAlignment(BASELINE_CENTER);
        popupLabel.setStyle(" -fx-background-color: white;");*/

        //Game Complete popup - not used
        /*Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(popupLabel);*/

        //Game Board button positions arrays
        ArrayList<ArrayList<Integer>> Positions = new ArrayList<>();
        ArrayList<ArrayList<Integer>> PositionsDup = new ArrayList<>();
        ArrayList<Integer> sq1 = new ArrayList<>();
        sq1.add(0);
        sq1.add(0);
        Positions.add(sq1);
        PositionsDup.add(sq1);

        ArrayList<Integer> sq2 = new ArrayList<>();
        sq2.add(1);
        sq2.add(0);
        Positions.add(sq2);
        PositionsDup.add(sq2);

        ArrayList<Integer> sq3 = new ArrayList<>();
        sq3.add(2);
        sq3.add(0);
        Positions.add(sq3);
        PositionsDup.add(sq3);

        ArrayList<Integer> sq4 = new ArrayList<>();
        sq4.add(3);
        sq4.add(0);
        Positions.add(sq4);
        PositionsDup.add(sq4);

        ArrayList<Integer> sq5 = new ArrayList<>();
        sq5.add(4);
        sq5.add(0);
        Positions.add(sq5);
        PositionsDup.add(sq5);

        ArrayList<Integer> sq6 = new ArrayList<>();
        sq6.add(0);
        sq6.add(1);
        Positions.add(sq6);
        PositionsDup.add(sq6);

        ArrayList<Integer> sq7 = new ArrayList<>();
        sq7.add(1);
        sq7.add(1);
        Positions.add(sq7);
        PositionsDup.add(sq7);

        ArrayList<Integer> sq8 = new ArrayList<>();
        sq8.add(2);
        sq8.add(1);
        Positions.add(sq8);
        PositionsDup.add(sq8);

        ArrayList<Integer> sq9 = new ArrayList<>();
        sq9.add(3);
        sq9.add(1);
        Positions.add(sq9);
        PositionsDup.add(sq9);

        ArrayList<Integer> sq10 = new ArrayList<>();
        sq10.add(4);
        sq10.add(1);
        Positions.add(sq10);
        PositionsDup.add(sq10);

        ArrayList<Integer> sq11 = new ArrayList<>();
        sq11.add(0);
        sq11.add(2);
        Positions.add(sq11);
        PositionsDup.add(sq11);

        ArrayList<Integer> sq12 = new ArrayList<>();
        sq12.add(1);
        sq12.add(2);
        Positions.add(sq12);
        PositionsDup.add(sq12);

        ArrayList<Integer> sq13 = new ArrayList<>();
        sq13.add(2);
        sq13.add(2);
        Positions.add(sq13);
        PositionsDup.add(sq13);

        ArrayList<Integer> sq14 = new ArrayList<>();
        sq14.add(3);
        sq14.add(2);
        Positions.add(sq14);
        PositionsDup.add(sq14);

        ArrayList<Integer> sq15 = new ArrayList<>();
        sq15.add(4);
        sq15.add(2);
        Positions.add(sq15);
        PositionsDup.add(sq15);

        ArrayList<Integer> sq16 = new ArrayList<>();
        sq16.add(0);
        sq16.add(3);
        Positions.add(sq16);
        PositionsDup.add(sq16);

        ArrayList<Integer> sq17 = new ArrayList<>();
        sq17.add(1);
        sq17.add(3);
        Positions.add(sq17);
        PositionsDup.add(sq17);

        ArrayList<Integer> sq18 = new ArrayList<>();
        sq18.add(2);
        sq18.add(3);
        Positions.add(sq18);
        PositionsDup.add(sq18);

        ArrayList<Integer> sq19 = new ArrayList<>();
        sq19.add(3);
        sq19.add(3);
        Positions.add(sq19);
        PositionsDup.add(sq19);

        ArrayList<Integer> sq20 = new ArrayList<>();
        sq20.add(4);
        sq20.add(3);
        Positions.add(sq20);
        PositionsDup.add(sq20);

        ArrayList<Integer> sq21 = new ArrayList<>();
        sq21.add(0);
        sq21.add(4);
        Positions.add(sq21);
        PositionsDup.add(sq21);

        ArrayList<Integer> sq22 = new ArrayList<>();
        sq22.add(1);
        sq22.add(4);
        Positions.add(sq22);
        PositionsDup.add(sq22);

        ArrayList<Integer> sq23 = new ArrayList<>();
        sq23.add(2);
        sq23.add(4);
        Positions.add(sq23);
        PositionsDup.add(sq23);

        ArrayList<Integer> sq24 = new ArrayList<>();
        sq24.add(3);
        sq24.add(4);
        Positions.add(sq24);
        PositionsDup.add(sq24);

        ArrayList<Integer> sq25 = new ArrayList<>();
        sq25.add(4);
        sq25.add(4);
        Positions.add(sq25);
        PositionsDup.add(sq25);
        ///////////////////////////////////////////////

        ArrayList<Button> butts = new ArrayList<>();

        //Game menu GUI setup
        Pane backpane = new Pane();
        backpane.setStyle("-fx-background-color: grey;");
        backpane.prefHeight(600);
        backpane.prefWidth(1000);

        HBox back = new HBox();
        back.prefHeight(600);
        back.prefWidth(1000);


        //Text output area
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
        vbox2.getChildren().addAll(text_area,advanceGraph);

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
        hbox.getChildren().addAll(Random,AStar,shuffle,shuffleAmt,Results);

        vbox1.getChildren().addAll(sqs,hbox);

        //Main scene
        primaryStage.setScene(new Scene(backpane, 1040, 600));
        primaryStage.show();

        //Game board buttons
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

        text_area.appendText("> Enter A value in the box below and click 'Shuffle' to shuffle the board.\n");
        text_area.appendText("\n");
        /////////////////////////////////////////////


        //Random Selection thread
        Task<Void> random = new Task<>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                Set<String> boardState = new LinkedHashSet<>();
                // Add delay code from initial attempt
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                text_area.appendText("> Random is now running.....\n");
                text_area.appendText("\n");

                while(!(checkWin(butts,PositionsDup))){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);
                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));

                    String board = getBoardState(sqs);
                    boardState.add(board);

                    Platform.runLater(()->buttsNear.get(i).fire());
                    if(!(checkWin(butts,PositionsDup))){
                        Thread.sleep(100);
                    }
                    text_area.appendText("> Moves made: "+ counter+"\n");
                    text_area.appendText("\n");
                }
                text_area.appendText("> Random has finished.\n");

                return null;
            }
        };


        //AStar Algorithm Thread
        Task<Void> aStar = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                text_area.appendText("> AStar is now running.....\n");
                text_area.appendText("\n");


                int [][] desired = {{1,2,3,4,5}, {6,7,8,9,10},{11,12,13,14,15}, {16,17,18,19,20},{21,22,23,24,-1}};
                int [][] puzzle  = new int [5][5];

                ArrayList<String> winningMoves;


                String board = getBoardState(sqs);
                String [] boardNums = board.split(",");

                int boardIndex = 0;
                for(int i=0;i<5;i++){
                    for (int j=0;j<5;j++){
                        puzzle[i][j] = Integer.parseInt(boardNums[boardIndex]);
                        boardIndex++;
                    }
                }






                puzzle p = new puzzle(src.output.algorithms.AStar.converStateToString(puzzle),	"root", puzzle, 0, src.output.algorithms.AStar.fitness(desired, puzzle));
                winningMoves = src.output.algorithms.AStar.PuzzleSolvingObj(p, desired);
                System.out.println(winningMoves.size());
                winningMoves.remove("root");
                Collections.reverse(winningMoves);
                System.out.println("-------------------------------------\n");
                winningStates = winningMoves;




                System.out.println("Begin.......");
                for(int i=1;i<winningMoves.size();i++){
                    String currentBoard = winningMoves.get(i-1);
                    List<String> currentBoardLayout = new ArrayList<>(Arrays.asList(currentBoard.split("\\.")));

                    String nextBoard = winningMoves.get(i);
                    List<String> nextBoardLayout = new ArrayList<>(Arrays.asList(nextBoard.split("\\.")));
                    int indexOfMove = nextBoardLayout.indexOf("-1");
                    String moveToMake = currentBoardLayout.get(indexOfMove);

                    for (Button butt : butts) {
                        if (butt.getText().equals(moveToMake)) {
                            Platform.runLater(butt::fire);
                        }
                    }
                    Thread.sleep(500);
                }
                System.out.println("End.......");
                text_area.appendText("> AStar has finished.\n");
                text_area.appendText("\n");
                text_area.appendText("> Click results to see a graph of the winning moves.\n");
                text_area.appendText("\n");
                text_area.appendText("> After clicking 'Results' you can click 'Next Node' to add the next board state to the graph.\n");
                text_area.appendText("\n");
                text_area.appendText("> The winning path is represented by the board states that are light-blue colored\n and connected with green lines.\n");
                text_area.appendText("\n");
                text_area.appendText("> You can pan around as well as zoom in an out of the graph using your mouse.\n");
                text_area.appendText("\n");
                return null;
            }
        };

        Task<Void> shuffleBoard = new Task<>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                int shuffleLevel = 0;
                int shuffleAmount = Integer.parseInt(shuffleAmt.getText());
                shuffling =true;
                text_area.appendText("> The board is now shuffling, please wait.....\n");
                text_area.appendText("\n");
                // Add delay code from initial attempt
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(shuffleLevel!=shuffleAmount){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);
                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));

                    Platform.runLater(()->buttsNear.get(i).fire());

                    Thread.sleep(200);
                    shuffleLevel++;
                }

                shuffling=false;
                text_area.appendText("> The board has been shuffled to the level of "+shuffleLevel+".\n");
                text_area.appendText("\n");
                text_area.appendText("> Choose either 'Random' or 'AStar' to solve the board.\n");
                text_area.appendText("\n");


                return null;
            }

        };


        /*Reset.setOnAction(e -> {
            primaryStage.hide();
            text_area.clear();
            start(new Stage());
        });*/

        //Random Selection thread action
        Random.setOnAction(e -> new Thread(random).start());

        //
        AStar.setOnAction(e -> new Thread(aStar).start());

        shuffle.setOnAction(e -> new Thread(shuffleBoard).start());

        Results.setOnAction(e -> {
            LinkedHashMap<String, puzzle> metStates = src.output.algorithms.AStar.getMetStates();
            secondaryStage.setScene(scene);
            secondaryStage.show();

            for (Map.Entry<String, puzzle> stringPuzzleEntry : metStates.entrySet()) {
                String nextState = PrintPuzzle(metStates.get(stringPuzzleEntry.getKey()).getState());
                String tempState = nextState.replaceAll(",", ".");
                tempState = tempState.replaceAll("X", "-1");
                int tmpCount = 0;
                String [] re2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","-1"};
                ArrayList<String> ref = new ArrayList<>();
                ref.addAll(Arrays.asList(re2));

                String [] tmpArr = tempState.split("\\.");
                for (int i =0;i<ref.size();i++){
                    if(!ref.get(i).equals(tmpArr[i])){
                        tmpCount++;
                    }
                }

                RectangleLabelCell cell;
                if (winningStates.contains(tempState)) {
                    //System.out.print("Win");
                    cell = new RectangleLabelCell(nextState, true, tmpCount);
                } else {
                    cell = new RectangleLabelCell(nextState, false, tmpCount);
                }
                allStates.add(nextState);
                cells.add(cell);
            }

            if(nodeIt < cells.size()) {
                addTreeComponent(tree, nodeIt, cells, allStates,true);
                nodeIt++;
            }
        });

        advanceGraph.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                SplitPane newRoot = new SplitPane();

                Graph newTree = new Graph();

                newTree.getCanvas().setScale(0.4);
                newTree.getCanvas().setPivot(-200.0,400.0);
                //newTree.setScale();


                if (nodeIt < cells.size()) {
                    addTreeComponent(newTree, nodeIt, cells, allStates, false);

                    nodeIt++;

                    newRoot.setStyle("-fx-background-color: #545351;");
                    newRoot.getItems().add(newTree.getCanvas());
                    Scene newScene = new Scene(newRoot, 1920, 1080);
                    newScene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                    secondaryStage.setScene(newScene);
                } else {
                    text_area.appendText("> All board states have been added to the graph.\n");
                    text_area.appendText("\n");
                }
            }
        });




        s1.setOnAction(e -> {
            if(shuffling)
            {
                moveTile(s1,s25,sqs);
            }
            else if(proximityCheck(s1, s25)) {
                moveTile(s1,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s2.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s2,s25,sqs);
            }
            else if(proximityCheck(s2,s25)) {
                moveTile(s2,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s3.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s3,s25,sqs);
            }
            else if(proximityCheck(s3, s25)) {
                moveTile(s3,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s4.setOnAction(e -> {

            if (shuffling) {
                moveTile(s4, s25, sqs);
            } else if (proximityCheck(s4, s25)) {
                moveTile(s4, s25, sqs);
                counter();
                label.setText("Moves: " + counter);
            } else {
                System.out.println("false");
            }
        });
        s5.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s5,s25,sqs);
            }
            else if(proximityCheck(s5, s25)) {
                moveTile(s5,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s6.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s6,s25,sqs);
            }
            else if(proximityCheck(s6, s25)) {
                moveTile(s6,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s7.setOnAction(e -> {

            if (shuffling) {
                moveTile(s7, s25, sqs);
            } else if (proximityCheck(s7, s25)) {
                moveTile(s7, s25, sqs);
                counter();
                label.setText("Moves: " + counter);
            } else {
                System.out.println("false");
            }
        });
        s8.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s8,s25,sqs);
            }
            else if(proximityCheck(s8, s25)) {
                moveTile(s8,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s9.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s9,s25,sqs);
            }
            else if(proximityCheck(s9, s25)) {
                moveTile(s9,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s10.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s10,s25,sqs);
            }
            else if(proximityCheck(s10, s25)) {
                moveTile(s10,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);

            }
            else{
                System.out.println("false");
            }
        });
        s11.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s11,s25,sqs);
            }
            else if(proximityCheck(s11,s25)) {
                moveTile(s11,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s12.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s12,s25,sqs);
            }
            else if(proximityCheck(s12, s25)) {
                moveTile(s12,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s13.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s13,s25,sqs);
            }
            else if(proximityCheck(s13, s25)) {
                moveTile(s13,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s14.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s14,s25,sqs);
            }
            else if(proximityCheck(s14, s25)) {
                moveTile(s14,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s15.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s15,s25,sqs);
            }
            else if(proximityCheck(s15, s25)) {
                moveTile(s15,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s16.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s16,s25,sqs);
            }
            else if(proximityCheck(s16,s25)) {
                moveTile(s16,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s17.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s17,s25,sqs);
            }
            else if(proximityCheck(s17, s25)) {
                moveTile(s17,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s18.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s18,s25,sqs);
            }
            else if(proximityCheck(s18, s25)) {
                moveTile(s18,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s19.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s19,s25,sqs);
            }
            else if(proximityCheck(s19, s25)) {
                moveTile(s19,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s20.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s20,s25,sqs);
            }
            //moveTile(s20,s25,sqs);
            else if(proximityCheck(s20, s25)) {
                moveTile(s20,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s21.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s21,s25,sqs);
            }
            else if(proximityCheck(s21, s25)) {
                moveTile(s21,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s22.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s22,s25,sqs);
            }
            else if(proximityCheck(s22, s25)) {
                moveTile(s22,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s23.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s23,s25,sqs);
            }
            else if(proximityCheck(s23, s25)) {
                moveTile(s23,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
            }
        });
        s24.setOnAction(e -> {

            if(shuffling)
            {
                moveTile(s24,s25,sqs);
            }
            else if(proximityCheck(s24, s25)) {
                moveTile(s24,s25,sqs);
                counter();
                label.setText("Moves: "+ counter);
            }
            else{
                System.out.println("false");
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

    /*public int getTilesOutOfPlace(List<String> boardState) {

        int tOOP = 0;
        List<String> winningState = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","-1"));

        for(int i =0;i< winningState.size();i++){
            if(!(winningState.get(i).equals(boardState.get(i)))){
                tOOP++;
            }
        }

        for(int i = 0;i<PositionsDup.size();i++)
        {
            int buttonC = GridPane.getColumnIndex(butts.get(i));
            int buttonR = GridPane.getRowIndex(butts.get(i));

            int posC = PositionsDup.get(i).get(0);
            int posR = PositionsDup.get(i).get(1);

            if (!(buttonC == posC && buttonR == posR)){
                tOOP++;
            }

        }
        return tOOP;
    }*/

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
        return (emptyPos.equals("44")) && (buttPos.equals("34") || buttPos.equals("43"));
    }

    public void counter(){
        counter++;
    }



    public String getBoardState(GridPane sqs){
        StringBuilder boardState = new StringBuilder();

        for (int i =0;i<5;i++){
            for (int j = 0; j<5;j++){
                Button tmp = (Button) getNodeFromGridPane(sqs,j,i);
                if (tmp != null) {
                    boardState.append(tmp.getText()).append(",");
                }
            }
        }
        boardState = new StringBuilder(boardState.substring(0, boardState.length() - 1));

        return boardState.toString();
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
        ArrayList<Button> buttsNear = new ArrayList<>();

        int tmpR = GridPane.getRowIndex(s25);
        int tmpC = GridPane.getColumnIndex(s25);
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
        StringBuilder puzzle_out= new StringBuilder();
        for (int[] ints : puzzle) {
            //puzzle_out+="|\t";
            for (int j = 0; j < puzzle.length; j++) {
                if (ints[j] != -1) {
                    //puzzle_out+=puzzle[i][j] + "\t|\t";
                    puzzle_out.append(ints[j]).append(",");
                    //System.out.print(puzzle[i][j] + "\t");
                } else {
                    //puzzle_out+="X\t|\t";
                    puzzle_out.append("X,");
                    //System.out.print("X\t");
                }
            }
            //puzzle_out+="\n--------------------------------------------------------\n\n";
            //puzzle_out+="\n";
            //System.out.println();
        }
        //System.out.println();
        return puzzle_out.toString();
    }


    private void addTreeComponent(Graph graph, int boardNum, ArrayList<RectangleLabelCell>cells, ArrayList<String> states, Boolean rootNode) throws IndexOutOfBoundsException{
        final Model model = graph.getModel();

        ArrayList<Integer> hVals = AStar.gethVals();
        ArrayList<String> winStates = new ArrayList<>();
        //System.out.println("in");
        //System.out.println(winningStates.get(0));

        for(int a = 0;a<winningStates.size();a++){
            String tempState = winningStates.get(a).replaceAll("\\.", ",");
            winStates.add(tempState.replaceAll("-1", "X"));
        }
        System.out.println(winningStates.get(0));
        System.out.println(states.get(0));
        graph.beginUpdate();
        int j = 0;
        while (j <= boardNum){
            model.addCell(cells.get(j));
            j++;
        }
        if(!rootNode){
            for (int i = 1; i <= nodeIt; i++) {
                if(i==1){
                    if(winStates.contains(states.get(1))){
                        CustomEdge edge = new CustomEdge(cells.get(1), cells.get(0));
                        model.addEdge(edge);
                    }
                    else{
                        CustomEdge2 edge = new CustomEdge2(cells.get(1), cells.get(0));
                        model.addEdge(edge);
                    }
                }
                if(i>1){
                    for(int k = 0; k<i; k++){
                        if (hasEdge(states.get(i), states.get(k))) {
                            //String winState = winningStates.get(k).replaceAll(".",",");
                            if((winStates.contains(states.get(k)) && winStates.contains(states.get(i))) && ((winStates.indexOf(states.get(i)) -  winStates.indexOf(states.get(k))) == 1)){
                                CustomEdge edge = new CustomEdge(cells.get(i), cells.get(k));
                                model.addEdge(edge);
                            }
                            else{
                                CustomEdge2 edge = new CustomEdge2(cells.get(i), cells.get(k));
                                model.addEdge(edge);
                            }
                        }
                    }
                }
            }
        }






        //graph.getModel().addCell(new RectangleLabelCell("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,X"));
        //model.addCell(new RectangleLabelCell("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,X"));

        graph.endUpdate();
        graph.layout(new AbegoTreeLayout(300, 300, Configuration.Location.Top));
    }

    /*public ICell createBoardNode(String state){
        return new RectangleLabelCell(state);
    }*/

    public boolean hasEdge(String nextCell, String prevCell){
        String [] nextCellNums = nextCell.split(",");
        String [] prevCellNums = prevCell.split(",");

        //System.out.println(nextCell);
        //System.out.println(prevCell);



        int ncn = -1;
        int pcn = -1;

        for (int i=0;i<nextCellNums.length;i++) {
            if (nextCellNums[i].equals("X")) {
                ncn = i;
            }
        }
        for (int i=0;i<prevCellNums.length;i++) {
            if (prevCellNums[i].equals("X")) {
                pcn = i;
            }
        }

        //System.out.println(ncn);
        //System.out.println(pcn);
        String temp = prevCellNums[pcn];
        prevCellNums[pcn] = prevCellNums[ncn];
        prevCellNums[ncn] = temp;
        if(Arrays.equals(prevCellNums,nextCellNums)){
            return ncn - pcn == 5 || ncn - pcn == -5 || ncn - pcn == 1 || ncn - pcn == -1;
        }
        else{
            return false;
        }


    }






    public static void main(String[] args) {
        launch(args);
    }
}



