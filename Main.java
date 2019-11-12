import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.CountDownLatch;

class astar {

    static Map<String, puzzle> metStates  = new HashMap<String, puzzle> ();
    static Map<String, puzzle> openStates = new HashMap<String, puzzle> ();



    public static ArrayList<String> PuzzleSolvingObj(puzzle newState, int [][] desired) {

        metStates.put(newState.getCode(), newState);
        System.out.print("Running....");





        int [][] new_puzzle1 = move(1, newState.getState());
        int [][] new_puzzle2 = move(2, newState.getState());
        int [][] new_puzzle3 = move(3, newState.getState());
        int [][] new_puzzle4 = move(4, newState.getState());

        ArrayList<String> winningMoves =new ArrayList<>();

        boolean stop = false;
        if(new_puzzle1.length >2) {
            int cost1 = fitness(desired, new_puzzle1);
            puzzle p1 = new puzzle(converStateToString(new_puzzle1),	newState.getCode(), new_puzzle1, newState.getLevel()+1, cost1);
            metStates.put(p1.getCode(), p1);
            openStates.put(p1.getCode(), p1);
            if(cost1 == 0) {
                stop = true;
                winningMoves = tracBack(converStateToString(new_puzzle1));
            }
        }

        if(new_puzzle2.length >2) {
            int cost2 = fitness(desired, new_puzzle2);
            puzzle p2 = new puzzle(converStateToString(new_puzzle2),	newState.getCode(), new_puzzle2, newState.getLevel()+1, cost2);
            metStates.put(p2.getCode(), p2);
            openStates.put(p2.getCode(), p2);
            if(cost2 == 0) {
                stop = true;
                winningMoves = tracBack(converStateToString(new_puzzle2));
            }
        }

        if(new_puzzle3.length >2) {
            int cost3 = fitness(desired, new_puzzle3);
            puzzle p3 = new puzzle(converStateToString(new_puzzle3),	newState.getCode(), new_puzzle3, newState.getLevel()+1, cost3);
            metStates.put(p3.getCode(), p3);
            openStates.put(p3.getCode(), p3);
            if(cost3 == 0) {
                stop = true;
                winningMoves = tracBack(converStateToString(new_puzzle3));
            }
        }
        if(new_puzzle4.length >2) {
            int cost4 = fitness(desired, new_puzzle4);
            puzzle p4 = new puzzle(converStateToString(new_puzzle4),	newState.getCode(), new_puzzle4, newState.getLevel()+1, cost4);
            metStates.put(p4.getCode(), p4);
            openStates.put(p4.getCode(), p4);
            if(cost4 == 0) {
                stop = true;
                winningMoves = tracBack(converStateToString(new_puzzle4));

            }
        }



        int itr = 0;
        while(!stop) {
            itr++;
            System.out.println("Level: "+itr);
            //int h = metStates.size();
            //if(h % 150 ==0)
            //System.out.println(h+" "+openStates.size()+" "+itr++);

            String code = bestToMoveOn(openStates);
            //System.out.println(openStates.get(code).getCost()+"\tcode");
            //System.out.println(h+" "+openStates.size()+" "+openStates.get(code).getCost());

            //System.out.println(code+"\tcode");
            //PrintPuzzle(openStates.get(code).getState());
            //System.out.println("++++++++++++++");




            int [][] n_puzzle1 = move(1, openStates.get(code).getState());
            int [][] n_puzzle2 = move(2, openStates.get(code).getState());
            int [][] n_puzzle3 = move(3, openStates.get(code).getState());
            int [][] n_puzzle4 = move(4, openStates.get(code).getState());

            /*if(itr==1) {
                //System.out.println(costInWhile1+"  1");
                //PrintPuzzle(openStates.get(code).getState());
                //PrintPuzzle(newState.getState());
            }*/

            if(n_puzzle1.length >2) {
                int costInWhile1 = fitness(desired, n_puzzle1);

			/*if(itr==1) {
				System.out.println(costInWhile1+"  1");
				PrintPuzzle(n_puzzle1);
			}*/
                puzzle newP1 = new puzzle(converStateToString(n_puzzle1), code, n_puzzle1, openStates.get(code).getLevel()+1,  costInWhile1);
                if(!metStates.containsKey(converStateToString(n_puzzle1))) {
                    openStates.put(converStateToString(n_puzzle1), newP1);
                    //System.out.println("in2");
                    metStates.put(converStateToString(n_puzzle1), newP1);

                    if(fitness(desired, n_puzzle1) == 0) {
                        //PrintPuzzle(desired);
                        //PrintPuzzle(n_puzzle1);

                        winningMoves = tracBack(converStateToString(n_puzzle1));
                        break;

                    }
                }
            }

            if(n_puzzle2.length >2) {
                int costInWhile2 = fitness(desired, n_puzzle2);
			/*if(itr==1) {
				System.out.println(costInWhile2+"  2");
				PrintPuzzle(n_puzzle2);
			}*/
                puzzle newP2 = new puzzle(converStateToString(n_puzzle2), code, n_puzzle2, openStates.get(code).getLevel()+1,  costInWhile2);
                if(!metStates.containsKey(converStateToString(n_puzzle2))) {
                    openStates.put(converStateToString(n_puzzle2), newP2);
                    metStates.put(converStateToString(n_puzzle2), newP2);

                    if(fitness(desired, n_puzzle2) == 0) {
                        //PrintPuzzle(desired);
                        //PrintPuzzle(n_puzzle2);

                        winningMoves = tracBack(converStateToString(n_puzzle2));
                        break;
                    }
                }
            }

            if(n_puzzle3.length >2) {
                int costInWhile3 = fitness(desired, n_puzzle3);
			/*if(itr==1) {
				System.out.println(costInWhile3+"  3");
				PrintPuzzle(n_puzzle3);
			}*/
                puzzle newP3 = new puzzle(converStateToString(n_puzzle3), code, n_puzzle3, openStates.get(code).getLevel()+1,  costInWhile3);
                if(!metStates.containsKey(converStateToString(n_puzzle3))) {
                    openStates.put(converStateToString(n_puzzle3), newP3);
                    metStates.put(converStateToString(n_puzzle3), newP3);

                    if(fitness(desired, n_puzzle3) == 0){
                        //PrintPuzzle(desired);
                        //PrintPuzzle(n_puzzle3);

                        winningMoves = tracBack(converStateToString(n_puzzle3));
                        break;
                    }
                }
            }

            if(n_puzzle4.length >2 ) {
                int costInWhile4 = fitness(desired, n_puzzle4);
			/*if(itr==1) {
				System.out.println(costInWhile4+"  4");
				PrintPuzzle(n_puzzle4);
			}*/
                puzzle newP4 = new puzzle(converStateToString(n_puzzle4), code, n_puzzle4, openStates.get(code).getLevel()+1,  costInWhile4);
                if(!metStates.containsKey(converStateToString(n_puzzle4))) {
                    openStates.put(converStateToString(n_puzzle4), newP4);
                    metStates.put(converStateToString(n_puzzle4), newP4);

                    if(fitness(desired, n_puzzle4) == 0){
                        //PrintPuzzle(desired);
                        //PrintPuzzle(n_puzzle4);
                        stop=true;
                        winningMoves = tracBack(converStateToString(n_puzzle4));
                        break;
                    }
                }
            }

            openStates.remove(code);

        }
        return winningMoves;

       /* metStates.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+ " " + entry.getValue());
        });*/


    }


    public static ArrayList<String> tracBack(String code) {
        System.out.println("Trace back ...");
        ArrayList<String> winningMoves = new ArrayList<>();
        String father = metStates.get(code).getParentCode();
        winningMoves.add(code);
        winningMoves.add(father);
        boolean doUntil = false;
        //String coder = metStates.get(code).getCode();
        //String father = metStates.get(code).getParentCode();
        //String grandfather = metStates.get(father).getParentCode();
        //String grand2father = metStates.get(grandfather).getParentCode();
        //String grand3father = metStates.get(grand2father).getParentCode();
        PrintPuzzle(metStates.get(code).getState());

        while(!father.equals("root")) {
            PrintPuzzle(metStates.get(father).getState());
            father = metStates.get(father).getParentCode();
            winningMoves.add(father);
            //System.out.println(father);
        }
        for(int i=0;i<winningMoves.size();i++){
            System.out.println(winningMoves.get(i));
        }
        return winningMoves;

    }


    public static String bestToMoveOn(Map<String, puzzle> openSt) {

        ArrayList <puzzle> q = new ArrayList <puzzle>();



        int cost = 2147480647;
        String code = "";
        for(puzzle p : openSt.values()) {
            if((p.getCost()+p.getLevel()) < cost ) {
                cost = p.getCost()+p.getLevel();

            }
        }

        for(puzzle p : openSt.values()) {
            if((p.getCost()+p.getLevel()) == cost ) {
                q.add(p);
            }
        }

        int dept = 2147480647;
        for(puzzle p : q) {
            if(dept>p.getLevel()) {
                dept = p.getLevel();
                code = p.getCode();
            }
        }

        return code;

    }



    public static String converStateToString(int [][] puzzle) {
        String State = "";
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                State = State + puzzle[i][j]+".";
            }
        }
        return State;
    }


    public static void PrintPuzzle(int [][] puzzle) {
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                if(puzzle[i][j]!=-1)
                    System.out.print(puzzle[i][j]+"\t");
                else
                    System.out.print("X\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    /*public static boolean hasChanged(int [][] puzzle1, int [][] puzzle2) {
        for (int i=0;i<puzzle1.length;i++) {
            for (int j=0;j<puzzle1.length;j++) {
                if(puzzle1[i][j] != puzzle2[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }*/

    public static int [] X_location(int [][] puzzle) {
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                if(puzzle[i][j] == -1) {
                    return new int [] {i, j};
                }
            }
        }
        return null;
    }


    public static int [][] move(int direction, int [][] puzzle) {

        int empty_x = X_location(puzzle)[0];
        int empty_y = X_location(puzzle)[1];
        int [][] new_puzzle = new int [puzzle.length][puzzle.length];
        int [][] nullArray = {{1,1},{1,1}} ;
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                new_puzzle[i][j] = puzzle[i][j];
            }
        }
        if(direction == 1) {
            if(empty_x>0) {
                int temp = new_puzzle[empty_x][empty_y];
                new_puzzle[empty_x][empty_y] = new_puzzle[empty_x-1][empty_y];
                new_puzzle[empty_x-1][empty_y] = temp;
                return new_puzzle;
            }
        }
        if(direction == 2) {
            if(empty_y>0) {
                int temp = new_puzzle[empty_x][empty_y];
                new_puzzle[empty_x][empty_y] = new_puzzle[empty_x][empty_y-1];
                new_puzzle[empty_x][empty_y-1] = temp;
                return new_puzzle;
            }
        }
        if(direction == 3) {
            if(empty_x<new_puzzle.length-1) {
                int temp = new_puzzle[empty_x][empty_y];
                new_puzzle[empty_x][empty_y] = new_puzzle[empty_x+1][empty_y];
                new_puzzle[empty_x+1][empty_y] = temp;
                return new_puzzle;
            }
        }
        if(direction == 4) {
            if(empty_y<new_puzzle.length-1) {
                int temp = new_puzzle[empty_x][empty_y];
                new_puzzle[empty_x][empty_y] = new_puzzle[empty_x][empty_y+1];
                new_puzzle[empty_x][empty_y+1] = temp;
                return new_puzzle;
            }
        }


        return nullArray;
    }



    public static int fitness(int [][] desired, int [][] puzzle) {
        int cost = 0;
        for(int t = 1; t<puzzle.length*puzzle.length;t++) {
            int x1 = -1;int y1 = -1;int x2 = -1;int y2 = -1;
            for (int i=0;i<puzzle.length;i++) {
                for (int j=0;j<puzzle.length;j++) {
                    if(desired[i][j] == t) {
                        x1 = i;
                        y1 = j;
                    }
                    if(puzzle[i][j] == t) {
                        x2 = i;
                        y2 = j;
                    }
                    //System.out.println(puzzle[i][j]+"  "+desired[i][j]);
                }
            }

            //System.out.println(x1+" "+x2+" "+y1+" "+y2);
            cost += Math.abs(x1-x2)+Math.abs(y1-y2);
        }
        return cost;
    }

    /*public static int fitnessless(int [][] desired, int [][] puzzle) {
        int cost = puzzle.length*puzzle.length;

        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                if(desired[i][j] == puzzle[i][j]) {
                    cost--;
                }
            }
        }

        return cost;
    }*/

}

class puzzle {

    String code;
    String ParentCode;
    int [][] state;
    int level;
    int cost;

    puzzle(String code,	String ParentCode, int [][] state, int level, int cost) {
        this.code = code;
        this.ParentCode = ParentCode;

        this.state = new int [state.length][state.length];
        for(int i=0;i<state.length;i++) {
            for(int j=0;j<state.length;j++) {
                this.state[i][j] = state[i][j];
            }
        }
        this.level = level;
        this.cost = cost;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public void setParentCode(String parentcode) {
        this.ParentCode = parentcode;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCode() {
        return this.code;
    }

    public String getParentCode() {
        return this.ParentCode;
    }


    public int  getLevel() {
        return this.level;
    }

    public int[][]  getState() {
        return this.state;
    }

    public int getCost() {
        return this.cost;
    }
}

/**
 * Main
 */

public class Main extends Application {

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
    public void start(Stage primaryStage)
    {
        BorderPane back = new BorderPane();

        HBox hBox = new HBox();
        back.setTop(hBox);

        GridPane sqs = new GridPane();
        back.setCenter(sqs);
        back.setRight(label);
        sqs.setHgap(5);
        sqs.setVgap(5);
        sqs.setPadding(new Insets(5));

        Button Random = new Button();
        Random.setText("Random");
        Random.setMinWidth(20);
        Random.setMinHeight(20);
        //back.setTop(Random);
        hBox.getChildren().add(Random);

        Button AStar = new Button();
        AStar.setText("AStar");
        AStar.setMinWidth(20);
        AStar.setMinHeight(20);
        //back.setTop(AStar);
        hBox.getChildren().add(AStar);

        Button shuffle = new Button();
        shuffle.setText("Shuffle Board");
        shuffle.setMinWidth(20);
        shuffle.setMinHeight(20);
        //back.setTop(AStar);
        hBox.getChildren().add(shuffle);

        // create a label
        Label popupLabel = new Label("You Win!");
        popupLabel.setMinWidth(200);
        popupLabel.setMinHeight(200);
        popupLabel.setAlignment(Pos.BASELINE_CENTER);
        popupLabel.setStyle(" -fx-background-color: white;");

        // create a popup
        Popup popup = new Popup();
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




        Button s1 = new Button();
        s1.setText("1");
        sqs.add(s1, Positions.get(0).get(0), Positions.get(0).get(1));
        s1.setMinWidth(100);
        s1.setMinHeight(100);
        s1.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s1);

        Button s2 = new Button();
        s2.setText("2");
        s2.setMinWidth(100);
        s2.setMinHeight(100);
        sqs.add(s2, Positions.get(1).get(0), Positions.get(1).get(1));
        s2.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s2);

        Button s3 = new Button();
        s3.setText("3");
        s3.setMinWidth(100);
        s3.setMinHeight(100);
        sqs.add(s3, Positions.get(2).get(0), Positions.get(2).get(1));
        s3.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s3);

        Button s4 = new Button();
        s4.setText("4");
        s4.setMinWidth(100);
        s4.setMinHeight(100);
        sqs.add(s4, Positions.get(3).get(0), Positions.get(3).get(1));
        s4.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s4);

        Button s5 = new Button();
        s5.setText("5");
        s5.setMinWidth(100);
        s5.setMinHeight(100);
        sqs.add(s5, Positions.get(4).get(0), Positions.get(4).get(1));
        s5.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s5);

        Button s6 = new Button();
        s6.setText("6");
        s6.setMinWidth(100);
        s6.setMinHeight(100);
        sqs.add(s6, Positions.get(5).get(0), Positions.get(5).get(1));
        s6.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s6);

        Button s7 = new Button();
        s7.setText("7");
        s7.setMinWidth(100);
        s7.setMinHeight(100);
        sqs.add(s7, Positions.get(6).get(0), Positions.get(6).get(1));
        s7.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s7);

        Button s8 = new Button();
        s8.setText("8");
        s8.setMinWidth(100);
        s8.setMinHeight(100);
        sqs.add(s8, Positions.get(7).get(0), Positions.get(7).get(1));
        s8.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s8);

        Button s9 = new Button();
        s9.setText("9");
        s9.setMinWidth(100);
        s9.setMinHeight(100);
        sqs.add(s9, Positions.get(8).get(0), Positions.get(8).get(1));
        s9.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s9);

        Button s10 = new Button();
        s10.setText("10");
        s10.setMinWidth(100);
        s10.setMinHeight(100);
        sqs.add(s10, Positions.get(9).get(0), Positions.get(9).get(1));
        s10.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s10);

        Button s11 = new Button();
        s11.setText("11");
        s11.setMinWidth(100);
        s11.setMinHeight(100);
        sqs.add(s11, Positions.get(10).get(0), Positions.get(10).get(1));
        s11.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s11);

        Button s12 = new Button();
        s12.setText("12");
        s12.setMinWidth(100);
        s12.setMinHeight(100);
        sqs.add(s12, Positions.get(11).get(0), Positions.get(11).get(1));
        s12.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s12);

        Button s13 = new Button();
        s13.setText("13");
        s13.setMinWidth(100);
        s13.setMinHeight(100);
        sqs.add(s13, Positions.get(12).get(0), Positions.get(12).get(1));
        s13.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s13);

        Button s14 = new Button();
        s14.setText("14");
        s14.setMinWidth(100);
        s14.setMinHeight(100);
        sqs.add(s14, Positions.get(13).get(0), Positions.get(13).get(1));
        s14.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s14);

        Button s15 = new Button();
        s15.setText("15");
        s15.setMinWidth(100);
        s15.setMinHeight(100);
        sqs.add(s15, Positions.get(14).get(0), Positions.get(14).get(1));
        s15.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s15);

        Button s16 = new Button();
        s16.setText("16");
        s16.setMinWidth(100);
        s16.setMinHeight(100);
        sqs.add(s16, Positions.get(15).get(0), Positions.get(15).get(1));
        s16.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s16);

        Button s17 = new Button();
        s17.setText("17");
        s17.setMinWidth(100);
        s17.setMinHeight(100);
        sqs.add(s17, Positions.get(16).get(0), Positions.get(16).get(1));
        s17.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s17);

        Button s18 = new Button();
        s18.setText("18");
        s18.setMinWidth(100);
        s18.setMinHeight(100);
        sqs.add(s18, Positions.get(17).get(0), Positions.get(17).get(1));
        s18.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s18);

        Button s19 = new Button();
        s19.setText("19");
        s19.setMinWidth(100);
        s19.setMinHeight(100);
        sqs.add(s19, Positions.get(18).get(0), Positions.get(18).get(1));
        s19.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s19);

        Button s20 = new Button();
        s20.setText("20");
        s20.setMinWidth(100);
        s20.setMinHeight(100);
        sqs.add(s20, Positions.get(19).get(0), Positions.get(19).get(1));
        s20.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s20);

        Button s21 = new Button();
        s21.setText("21");
        s21.setMinWidth(100);
        s21.setMinHeight(100);
        sqs.add(s21, Positions.get(20).get(0), Positions.get(20).get(1));
        s21.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s21);

        Button s22 = new Button();
        s22.setText("22");
        s22.setMinWidth(100);
        s22.setMinHeight(100);
        sqs.add(s22, Positions.get(21).get(0), Positions.get(21).get(1));
        s22.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s22);

        Button s23 = new Button();
        s23.setText("23");
        s23.setMinWidth(100);
        s23.setMinHeight(100);
        sqs.add(s23, Positions.get(22).get(0), Positions.get(22).get(1));
        s23.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s23);

        Button s24 = new Button();
        s24.setText("24");
        s24.setMinWidth(100);
        s24.setMinHeight(100);
        sqs.add(s24, Positions.get(23).get(0), Positions.get(23).get(1));
        s24.setStyle("-fx-background-color: MediumSeaGreen");
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






                int lev = 0;
                //System.out.println("in1");
                puzzle p = new puzzle(astar.converStateToString(puzzle),	"root", puzzle, 0, astar.fitness(desired, puzzle));
                /*System.out.println(p.getCode());*/
                winningMoves = astar.PuzzleSolvingObj(p, desired);
                winningMoves.remove("root");
                Collections.reverse(winningMoves);
                //System.out.println(winningMoves.get(0));
                //System.out.println("All States");
                /*for (int j=0;j<winningMoves.size();j++){
                    System.out.println(winningMoves.get(j));
                }*/
                System.out.println("-------------------------------------\n");
                //System.out.print(winningMoves.size());




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
                    System.out.println(currentBoard);

                    for(int j=0;j<butts.size();j++){
                        if(butts.get(j).getText().equals(moveToMake)){
                            Button btMove = butts.get(j);
                            Platform.runLater(()->btMove.fire());
                        }
                    }
                    //String winningMove = currentBoardLayout.get(nextBoardLayout.size()-1);

                    /*for(int k=0;k<butts.size();k++){
                        if(butts.get(k).getText().equals(winningMove)){
                            Button btMove = butts.get(k);
                            Platform.runLater(()->btMove.fire());
                        }
                    }*/



                    //System.out.println(tempWM);
                    //System.out.println();
                    //int tempBlank = tempWM.indexOf("-1");
                    //System.out.print(tempBlank);
                    //System.out.println("Move: "+boardLayout.indexOf("-1"));
                    /*for (int j=0;j<butts.size();j++){
                        System.out.print(butts.get(j).getText() + ".");
                    }*/
                    /*System.out.println();
                    System.out.println("Button: " + butts.get(boardLayout.indexOf("-1")).getText());
                    for (int j=0;j<boardLayout.size();j++){
                        System.out.print(boardLayout.get(j) + ".");
                    }
                    System.out.println();
                    System.out.println("Index: " + boardLayout.indexOf("-1"));
                    for (int j=0;j<butts.size();j++){
                        System.out.print(butts.get(j).getText() + ".");
                    }
                    System.out.println();
                    int boardInd = boardLayout.indexOf("-1");
                    int boardIndTmp = boardLayout.indexOf("-1");*/
                    //Platform.runLater(()->butts.get(boardLayout.indexOf("-1")).fire());
                    Thread.sleep(250);
                }


                /*

                //Map<String,BoardInfo> open = new HashMap<String,BoardInfo>();
                //Map<String,BoardInfo> close = new HashMap<String,BoardInfo>();
                List<BoardInfo> open = new ArrayList<BoardInfo>();
                List<BoardInfo> close = new ArrayList<BoardInfo>();

                String initialBoard = getBoardState(sqs);
                List<String> boardLayoutStart = new ArrayList<String>(Arrays.asList(initialBoard.split(",")));
                //close.put(initialBoard,new BoardInfo(getTilesOutOfPlace(boardLayoutStart)+level,null));
                close.add(new BoardInfo(initialBoard,getTilesOutOfPlace(boardLayoutStart)+level,null));

                //Set<String> boardState = new LinkedHashSet<String>();

                while(!(checkWin(butts,PositionsDup))){
                    String board2 = getBoardState(sqs);

                    List<Button> buttsNear = getSurroundingTiles(sqs,s25);



                    level++;
                    for(int i = 0;i<buttsNear.size();i++){
                        List<String> boardLayout = new ArrayList<String>(Arrays.asList(board.split(",")));
                        String buttTemp;
                        buttTemp = buttsNear.get(i).getText();
                        Collections.swap(boardLayout, boardLayout.indexOf(buttTemp), boardLayout.indexOf("-1"));

                        String boardOrder = "";

                        for (String s : boardLayout)
                        {
                            boardOrder += s + ",";
                        }
                        boardOrder = boardOrder.substring(0, boardOrder.length() - 1);

                        int boardScore = getTilesOutOfPlace(boardLayout) + level;
                        //open.put(boardOrder,new BoardInfo(boardScore,buttsNear.get(i)));
                        open.add(new BoardInfo(boardOrder,boardScore,buttsNear.get(i)));

                    }
                    Collections.sort(open, new Comparator<BoardInfo>(){
                        public int compare(BoardInfo o1, BoardInfo o2) {
                            return o1.getScore() - o2.getScore();
                        }
                    });
                    close.add(open.get(0));

                    int levelNext = level;
                    //System.out.println(open.get(0).button.getText());
                    boolean prox = proximityCheck(open.get(0).button,s25);
                    if(prox){
                        System.out.println("in 1");
                        Platform.runLater(()->open.get(0).button.fire());
                    }else{
                        System.out.println("in 2");
                        Platform.runLater(()->close.get(close.size()-levelNext).button.fire());
                    }
                    open.remove(0);




                    if(!(checkWin(butts,PositionsDup))){
                        Thread.sleep(50);
                    }

                }*/
                return null;
            }
        };

        Task<Void> shuffleBoard = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {

                Set<String> boardState = new LinkedHashSet<String>();
                int shuffleLevel = 0;
                shuffling =true;
                // Add delay code from initial attempt
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                while(shuffleLevel!=75){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);
                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));

                    Platform.runLater(()->buttsNear.get(i).fire());
                    //Thread.sleep(50);

                    Thread.sleep(15);
                    shuffleLevel++;
                }

                shuffling=false;
                return null;
            }

        };


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
                else if(proximityCheck(s2, s25)) {
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
                else if(proximityCheck(s11, s25)) {
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
                else if(proximityCheck(s16, s25)) {
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


        Scene scene = new Scene(back, 750, 750);
        primaryStage.setTitle("8-Puzzle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage



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

    public static void main(String[] args) {
        launch(args);
    }
}

class BoardInfo{
    String board;
    int score;
    Button button;
    BoardInfo(String board, int score, Button button){
        this.board = board;
        this.score = score;
        this.button = button;
    }
    public int getScore (){
        return score;
    }
}

class CustomComparator implements Comparator<BoardInfo> {
    @Override
    public int compare(BoardInfo o1, BoardInfo o2) {
        return o1.getScore() - o2.getScore();
    }
}