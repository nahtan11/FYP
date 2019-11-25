package src.output;

import javafx.scene.control.Button;

public class BoardInfo {
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
