package DataType;

import java.io.Serializable;

public class UnitData implements Serializable {
    private int unitType;
    private int x,y;
    //state: -1 is going upward 0 is standing still 1 going downward
    private int state;
    public UnitData(int unitType,int x,int y){
        state=-1;
        this.x=x;
        this.y=y;
        this.unitType=unitType;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getState() {
        return state;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getUnitType() {
        return unitType;
    }
}
