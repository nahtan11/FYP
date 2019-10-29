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
import javafx.scene.shape.SVGPath;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * Main
 */
public class Main extends Application {

    private int counter = 0;
    private Label label = new Label("Moves: 0");

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
        //s25.setText("25");
        s25.setMinWidth(100);
        s25.setMinHeight(100);
        sqs.add(s25, Positions.get(24).get(0), Positions.get(24).get(1));
        //s25.setStyle("-fx-background-color: MediumSeaGreen");
        butts.add(s25);

        Task<Void> random = new Task<Void>() {// Implement required call() method
            @Override
            protected Void call() throws Exception {
                // Add delay code from initial attempt
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                while(!(checkWin(butts,PositionsDup))){

                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);

                    int i = (int) Math.floor(Math.random() * (buttsNear.size()));
                    System.out.println();
                    //System.out.println("Blank " + blank);
                    //System.out.println("index " +i );

                    for(int j =0;j<buttsNear.size();j++){
                        System.out.print(sqs.getRowIndex(buttsNear.get(j)));
                        System.out.println(sqs.getColumnIndex(buttsNear.get(j)));
                    }

                    Platform.runLater(()->buttsNear.get(i).fire());
                    //Thread.sleep(500);
                    if(!(checkWin(butts,PositionsDup))){
                        Thread.sleep(50);
                    }
                };
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

                int level = 0;
                //int tOOP1 = getTilesOutOfPlace(PositionsDup,butts);
                //int value1 = level + tOOP1;

                ArrayList<Integer> open = new ArrayList<Integer>();
                ArrayList<Integer> closed = new ArrayList<Integer>();
                //System.out.print(value1);
                //f(n) = g(n) + h(n)
                while(!(checkWin(butts,PositionsDup))){

                    int tOOP = getTilesOutOfPlace(PositionsDup,butts);
                    ArrayList<Button> buttsNear = getSurroundingTiles(sqs,s25);

                    int value = level + tOOP;


                }







                // We're not interested in the return value, so return null
                return null;
            }
        };


        Random.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(random).start();
                /*for(int j =0;j<butts.size();j++){
                    System.out.print(butts.get(j));
                    System.out.println(PositionsDup.get(j));
                }*/
            }
        });
        AStar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new Thread(aStar).start();
                int buttonC = GridPane.getColumnIndex(butts.get(0));
                int buttonR = GridPane.getRowIndex(butts.get(0));
                //System.out.print(PositionsDup.get(0).get(0));
                //System.out.println(PositionsDup.get(0).get(1));
                //System.out.print(buttonR);
                //System.out.println(buttonC);

            }
        });

        s1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                if(proximityCheck(s1, s25)) {
                    int tmpR = GridPane.getRowIndex(s1);
                    int tmpC = GridPane.getColumnIndex(s1);
                    sqs.getChildren().remove(s1);
                    sqs.add(s1, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s2, s25)) {
                    int tmpR = GridPane.getRowIndex(s2);
                    int tmpC = GridPane.getColumnIndex(s2);
                    sqs.getChildren().remove(s2);
                    sqs.add(s2, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s3, s25)) {
                    int tmpR = GridPane.getRowIndex(s3);
                    int tmpC = GridPane.getColumnIndex(s3);
                    sqs.getChildren().remove(s3);
                    sqs.add(s3, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s4, s25)) {
                    int tmpR = GridPane.getRowIndex(s4);
                    int tmpC = GridPane.getColumnIndex(s4);
                    sqs.getChildren().remove(s4);
                    sqs.add(s4, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s5, s25)) {
                    int tmpR = GridPane.getRowIndex(s5);
                    int tmpC = GridPane.getColumnIndex(s5);
                    sqs.getChildren().remove(s5);
                    sqs.add(s5, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s6, s25)) {
                    int tmpR = GridPane.getRowIndex(s6);
                    int tmpC = GridPane.getColumnIndex(s6);
                    sqs.getChildren().remove(s6);
                    sqs.add(s6, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s7,s25)) {
                    int tmpR = GridPane.getRowIndex(s7);
                    int tmpC = GridPane.getColumnIndex(s7);
                    sqs.getChildren().remove(s7);
                    sqs.add(s7, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s8, s25)) {
                    int tmpR = GridPane.getRowIndex(s8);
                    int tmpC = GridPane.getColumnIndex(s8);
                    sqs.getChildren().remove(s8);
                    sqs.add(s8, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s8, s25)) {
                    int tmpR = GridPane.getRowIndex(s8);
                    int tmpC = GridPane.getColumnIndex(s8);
                    sqs.getChildren().remove(s8);
                    sqs.add(s8, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s9, s25)) {
                    int tmpR = GridPane.getRowIndex(s9);
                    int tmpC = GridPane.getColumnIndex(s9);
                    sqs.getChildren().remove(s9);
                    sqs.add(s9, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s10, s25)) {
                    int tmpR = GridPane.getRowIndex(s10);
                    int tmpC = GridPane.getColumnIndex(s10);
                    sqs.getChildren().remove(s10);
                    sqs.add(s10, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s11, s25)) {
                    int tmpR = GridPane.getRowIndex(s11);
                    int tmpC = GridPane.getColumnIndex(s11);
                    sqs.getChildren().remove(s11);
                    sqs.add(s11, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s12, s25)) {
                    int tmpR = GridPane.getRowIndex(s12);
                    int tmpC = GridPane.getColumnIndex(s12);
                    sqs.getChildren().remove(s12);
                    sqs.add(s12, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s13, s25)) {
                    int tmpR = GridPane.getRowIndex(s13);
                    int tmpC = GridPane.getColumnIndex(s13);
                    sqs.getChildren().remove(s13);
                    sqs.add(s13, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s14, s25)) {
                    int tmpR = GridPane.getRowIndex(s14);
                    int tmpC = GridPane.getColumnIndex(s14);
                    sqs.getChildren().remove(s14);
                    sqs.add(s14, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s15, s25)) {
                    int tmpR = GridPane.getRowIndex(s15);
                    int tmpC = GridPane.getColumnIndex(s15);
                    sqs.getChildren().remove(s15);
                    sqs.add(s15, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s16, s25)) {
                    int tmpR = GridPane.getRowIndex(s16);
                    int tmpC = GridPane.getColumnIndex(s16);
                    sqs.getChildren().remove(s16);
                    sqs.add(s16, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s17, s25)) {
                    int tmpR = GridPane.getRowIndex(s17);
                    int tmpC = GridPane.getColumnIndex(s17);
                    sqs.getChildren().remove(s17);
                    sqs.add(s17, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s18, s25)) {
                    int tmpR = GridPane.getRowIndex(s18);
                    int tmpC = GridPane.getColumnIndex(s18);
                    sqs.getChildren().remove(s18);
                    sqs.add(s18, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s19, s25)) {
                    int tmpR = GridPane.getRowIndex(s19);
                    int tmpC = GridPane.getColumnIndex(s19);
                    sqs.getChildren().remove(s19);
                    sqs.add(s19, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s20, s25)) {
                    int tmpR = GridPane.getRowIndex(s20);
                    int tmpC = GridPane.getColumnIndex(s20);
                    sqs.getChildren().remove(s20);
                    sqs.add(s20, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s21, s25)) {
                    int tmpR = GridPane.getRowIndex(s21);
                    int tmpC = GridPane.getColumnIndex(s21);
                    sqs.getChildren().remove(s21);
                    sqs.add(s21, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s22, s25)) {
                    int tmpR = GridPane.getRowIndex(s22);
                    int tmpC = GridPane.getColumnIndex(s22);
                    sqs.getChildren().remove(s22);
                    sqs.add(s22, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s23, s25)) {
                    int tmpR = GridPane.getRowIndex(s23);
                    int tmpC = GridPane.getColumnIndex(s23);
                    sqs.getChildren().remove(s23);
                    sqs.add(s23, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

                if(proximityCheck(s24, s25)) {
                    int tmpR = GridPane.getRowIndex(s24);
                    int tmpC = GridPane.getColumnIndex(s24);
                    sqs.getChildren().remove(s24);
                    sqs.add(s24, GridPane.getColumnIndex(s25), GridPane.getRowIndex(s25));
                    sqs.getChildren().remove(s25);
                    sqs.add(s25, tmpC, tmpR);
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

    public int getTilesOutOfPlace(ArrayList<ArrayList<Integer>> PositionsDup, ArrayList<Button> butts) {

        int tOOP = 0;

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