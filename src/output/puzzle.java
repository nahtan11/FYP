package src.output;

public class puzzle {
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
