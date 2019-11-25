package src.output.Board;

public class puzzle {

    String code;
    String ParentCode;
    int [][] state;
    int level;
    int cost;

    public puzzle(String code,	String ParentCode, int [][] state, int level, int cost) {
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
