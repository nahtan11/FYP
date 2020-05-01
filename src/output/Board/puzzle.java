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
            System.arraycopy(state[i], 0, this.state[i], 0, state.length);
        }
        this.level = level;
        this.cost = cost;
    }


    @SuppressWarnings("unused")
    public void setCode(String code) {
        this.code = code;
    }
    @SuppressWarnings("unused")
    public void setParentCode(String parentcode) {
        this.ParentCode = parentcode;
    }

    @SuppressWarnings("unused")
    public void setLevel(int level) {
        this.level = level;
    }
    @SuppressWarnings("unused")
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
