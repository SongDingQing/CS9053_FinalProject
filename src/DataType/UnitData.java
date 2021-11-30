package DataType;

import GUI.Unit.Unit;

import java.util.ArrayList;

public class UnitData {
    private int x;
    private int y;
    //state: -1 is going upward 0 is standing still 1 going downward
    private int state;
    public UnitData(int x,int y){
        state=-1;
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
