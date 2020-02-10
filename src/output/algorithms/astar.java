package src.output.algorithms;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;
import src.output.Board.puzzle;

import java.awt.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class astar {

    static LinkedHashMap<String, puzzle> metStates  = new LinkedHashMap<String, puzzle>();
    static LinkedHashMap<String, puzzle> openStates = new LinkedHashMap<String, puzzle> ();



    public static ArrayList<String> PuzzleSolvingObj(puzzle newState, int [][] desired){

        metStates.put(newState.getCode(), newState);
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
            int itrtmp = itr;
            /*Platform.runLater(()->{
                text_area.appendText("Level: "+itrtmp+"\n");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });*/
            System.out.println("Level: "+itr);
            //int h = metStates.size();
            //if(h % 150 ==0)
            //System.out.println(h+" "+openStates.size()+" "+itr++);

            String code = bestToMoveOn(openStates);
            PrintPuzzle(metStates.get(code).getState());
            System.out.println(code);
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
                        stop=true;
                        winningMoves = tracBack(converStateToString(n_puzzle1));
                        //System.out.println("h1");
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
                        stop=true;
                        winningMoves = tracBack(converStateToString(n_puzzle2));
                        //System.out.println("h2");
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
                        stop=true;
                        winningMoves = tracBack(converStateToString(n_puzzle3));
                        //System.out.println("h3");
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
                        //System.out.println("h4");
                        break;
                    }
                }
            }

            openStates.remove(code);

        }
        //System.out.println(winningMoves.get(0) + "moves");
        return winningMoves;

       /* metStates.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+ " " + entry.getValue());
        });*/


    }


    public static ArrayList<String> tracBack(String code){
        System.out.println("Trace back ...");
        ArrayList<String> winningMoves = new ArrayList<>();
        String father = metStates.get(code).getParentCode();
        winningMoves.add(code);
        winningMoves.add(father);
        PrintPuzzle(metStates.get(code).getState());

        //text_area.appendText("Trace Back\n");
        while(!father.equals("root")) {
            PrintPuzzle(metStates.get(father).getState());
            father = metStates.get(father).getParentCode();
            winningMoves.add(father);
            //System.out.println("hello");
        }

        /*for(int i=0;i<winningMoves.size();i++){
            PrintPuzzle(metStates.get(winningMoves.get(i)).getState());
            //System.out.println(winningMoves.get(i));
        }*/
        //System.out.println("hello");
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


    public static void PrintPuzzle(int [][] puzzle){
        String puzzle_out="";
        for (int i=0;i<puzzle.length;i++) {
            for (int j=0;j<puzzle.length;j++) {
                if(puzzle[i][j]!=-1) {

                    puzzle_out+=puzzle[i][j] + "\t";
                    System.out.print(puzzle[i][j] + "\t");
                }
                else {
                    puzzle_out+="X\t";
                    System.out.print("X\t");
                }
            }
            puzzle_out+="\n";
            System.out.println();
        }
        System.out.println();
        String puzz = puzzle_out;
        /*Platform.runLater(()->{
            text_area.textProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<?> observable, Object oldValue,
                                    Object newValue) {
                    text_area.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                    //use Double.MIN_VALUE to scroll to the top
                }
            });

            text_area.appendText(puzz);
            text_area.appendText("\n");
        });
        Thread.sleep(50);*/
        //System.out.println();
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
                    //System.out.println(src.output.Board.puzzle[i][j]+"  "+desired[i][j]);
                }
            }

            //System.out.println(x1+" "+x2+" "+y1+" "+y2);
            cost += Math.abs(x1-x2)+Math.abs(y1-y2);
        }
        return cost;
    }

    public static LinkedHashMap<String, puzzle> getMetStates() {
        return metStates;
    }

    /*public static int fitnessless(int [][] desired, int [][] src.output.Board.puzzle) {
        int cost = src.output.Board.puzzle.length*src.output.Board.puzzle.length;

        for (int i=0;i<src.output.Board.puzzle.length;i++) {
            for (int j=0;j<src.output.Board.puzzle.length;j++) {
                if(desired[i][j] == src.output.Board.puzzle[i][j]) {
                    cost--;
                }
            }
        }

        return cost;
    }*/

}
