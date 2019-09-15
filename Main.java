import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Main
 */
public class Main extends Application {

    private int counter = 0;
    private Label label = new Label("Moves: 0");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        BorderPane back = new BorderPane();
        GridPane sqs = new GridPane();
        back.setCenter(sqs);
        back.setRight(label);
        sqs.setHgap(5);
        sqs.setVgap(5);
        sqs.setPadding(new Insets(5));

        //Image x = new Image(getClass().getResourceAsStream(""));
        //Image o = new Image(getClass().getResourceAsStream(""));

        ArrayList<ArrayList<Integer>> Positions = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> sq1 = new ArrayList<Integer>();
        sq1.add(0);
        sq1.add(0);
        Positions.add(sq1);

        ArrayList<Integer> sq2 = new ArrayList<Integer>();
        sq2.add(1);
        sq2.add(0);
        Positions.add(sq2);

        ArrayList<Integer> sq3 = new ArrayList<Integer>();
        sq3.add(2);
        sq3.add(0);
        Positions.add(sq3);

        ArrayList<Integer> sq4 = new ArrayList<Integer>();
        sq4.add(0);
        sq4.add(1);
        Positions.add(sq4);

        ArrayList<Integer> sq5 = new ArrayList<Integer>();
        sq5.add(1);
        sq5.add(1);
        Positions.add(sq5);

        ArrayList<Integer> sq6 = new ArrayList<Integer>();
        sq6.add(2);
        sq6.add(1);
        Positions.add(sq6);

        ArrayList<Integer> sq7 = new ArrayList<Integer>();
        sq7.add(0);
        sq7.add(2);
        Positions.add(sq7);

        ArrayList<Integer> sq8 = new ArrayList<Integer>();
        sq8.add(1);
        sq8.add(2);
        Positions.add(sq8);

        ArrayList<Integer> sq9 = new ArrayList<Integer>();
        sq9.add(2);
        sq9.add(2);
        Positions.add(sq9);

        Collections.shuffle(Positions);


        Button s1 = new Button();
        s1.setText("1");
        sqs.add(s1, Positions.get(0).get(0), Positions.get(0).get(1));
        s1.setMinWidth(200);
        s1.setMinHeight(200);
        s1.setStyle("-fx-background-color: MediumSeaGreen");

        Button s2 = new Button();
        s2.setText("2");
        s2.setMinWidth(200);
        s2.setMinHeight(200);
        sqs.add(s2, Positions.get(1).get(0), Positions.get(1).get(1));
        s2.setStyle("-fx-background-color: MediumSeaGreen");

        Button s3 = new Button();
        s3.setText("3");
        s3.setMinWidth(200);
        s3.setMinHeight(200);
        sqs.add(s3, Positions.get(2).get(0), Positions.get(2).get(1));
        s3.setStyle("-fx-background-color: MediumSeaGreen");

        Button s4 = new Button();
        s4.setText("4");
        s4.setMinWidth(200);
        s4.setMinHeight(200);
        sqs.add(s4, Positions.get(3).get(0), Positions.get(3).get(1));
        s4.setStyle("-fx-background-color: MediumSeaGreen");

        Button s5 = new Button();
        s5.setText("5");
        s5.setMinWidth(200);
        s5.setMinHeight(200);
        sqs.add(s5, Positions.get(4).get(0), Positions.get(4).get(1));
        s5.setStyle("-fx-background-color: MediumSeaGreen");

        Button s6 = new Button();
        s6.setText("6");
        s6.setMinWidth(200);
        s6.setMinHeight(200);
        sqs.add(s6, Positions.get(5).get(0), Positions.get(5).get(1));
        s6.setStyle("-fx-background-color: MediumSeaGreen");

        Button s7 = new Button();
        s7.setText("7");
        s7.setMinWidth(200);
        s7.setMinHeight(200);
        sqs.add(s7, Positions.get(6).get(0), Positions.get(6).get(1));
        s7.setStyle("-fx-background-color: MediumSeaGreen");

        Button s8 = new Button();
        s8.setText("8");
        s8.setMinWidth(200);
        s8.setMinHeight(200);
        sqs.add(s8, Positions.get(7).get(0), Positions.get(7).get(1));
        s8.setStyle("-fx-background-color: MediumSeaGreen");

        Button s9 = new Button();
        s9.setMinWidth(200);
        s9.setMinHeight(200);
        sqs.add(s9, Positions.get(8).get(0), Positions.get(8).get(1));

        s1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s1,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s1);
                    int tmpC = GridPane.getColumnIndex(s1);
                    sqs.getChildren().remove(s1);
                    sqs.add(s1, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s2,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s2);
                    int tmpC = GridPane.getColumnIndex(s2);
                    sqs.getChildren().remove(s2);
                    sqs.add(s2, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s3,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s3);
                    int tmpC = GridPane.getColumnIndex(s3);
                    sqs.getChildren().remove(s3);
                    sqs.add(s3, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s4,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s4);
                    int tmpC = GridPane.getColumnIndex(s4);
                    sqs.getChildren().remove(s4);
                    sqs.add(s4, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s5.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s5,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s5);
                    int tmpC = GridPane.getColumnIndex(s5);
                    sqs.getChildren().remove(s5);
                    sqs.add(s5, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s6.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s6,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s6);
                    int tmpC = GridPane.getColumnIndex(s6);
                    sqs.getChildren().remove(s6);
                    sqs.add(s6, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s7.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s7,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s7);
                    int tmpC = GridPane.getColumnIndex(s7);
                    sqs.getChildren().remove(s7);
                    sqs.add(s7, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });
        s8.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s8,s9) == true) {
                    int tmpR = GridPane.getRowIndex(s8);
                    int tmpC = GridPane.getColumnIndex(s8);
                    sqs.getChildren().remove(s8);
                    sqs.add(s8, GridPane.getColumnIndex(s9), GridPane.getRowIndex(s9));
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, tmpC, tmpR);
                    label.setText("Moves: "+Integer.toString(counter));
                    counter();
                }
                else{
                    System.out.println("false");
                }
            }
        });

        Scene scene = new Scene(back, 750, 620);
        primaryStage.setTitle("8-Puzzle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage




    }

    public Boolean proximityCheck(Button butt, Button emptyButt){

        int emptyC = GridPane.getColumnIndex(emptyButt);
        int emptyR = GridPane.getRowIndex(emptyButt);
        String emptyPos = emptyC + "" + emptyR;

        int tmpC = GridPane.getColumnIndex(butt);
        int tmpR = GridPane.getRowIndex(butt);
        String buttPos = tmpC + "" + tmpR;

        System.out.println(emptyPos);
        System.out.println(buttPos);


        if((emptyPos.equals("00")) && (buttPos.equals("10") || buttPos.equals("01"))){
            return true;
        }
        if((emptyPos.equals("10")) && (buttPos.equals("00") || buttPos.equals("11") || buttPos.equals("20"))){
            return true;
        }
        if((emptyPos.equals("20")) && (buttPos.equals("10") || buttPos.equals("21"))){
            return true;
        }
        if((emptyPos.equals("01")) && (buttPos.equals("00") || buttPos.equals("11") || buttPos.equals("02"))){
            return true;
        }
        if((emptyPos.equals("11")) && (buttPos.equals("01") || buttPos.equals("10") || buttPos.equals("21") || buttPos.equals("12"))){
            return true;
        }
        if((emptyPos.equals("21")) && (buttPos.equals("11") || buttPos.equals("20") || buttPos.equals("22"))){
            return true;
        }
        if((emptyPos.equals("02")) && (buttPos.equals("01") || buttPos.equals("12"))){
            return true;
        }
        if((emptyPos.equals("12")) && (buttPos.equals("02") || buttPos.equals("11") || buttPos.equals("22"))){
            return true;
        }
        if((emptyPos.equals("22")) && (buttPos.equals("12") || buttPos.equals("21"))){
            return true;
        }

        return false;



    }

    public void counter(){
        counter++;
        //System.out.println(counter);

    }

    public static void main(String[] args) {
        launch(args);
    }
}