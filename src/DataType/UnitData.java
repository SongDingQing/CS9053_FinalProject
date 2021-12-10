package DataType;

import Data.ServerData.Constants;
import Data.ServerData.Variable;

import java.io.Serializable;

public class UnitData implements Serializable {
    private int unitType;
    private int x, y;
    //state: -1 is going upward 0 is standing still 1 going downward
    private int state;
    private int workLoc;

    public UnitData(int unitType, int x, int y) {
        state = -1;
        this.x = x;
        this.y = y;
        this.unitType = unitType;
        workLoc=Constants.Pixels_Height-1;
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

    public void update(int playerNum) {
        if(unitType==1){
            updateLogger(playerNum);
        }else if(unitType==2){
            updateFisher();
        }else if(unitType==3){
            updateMiner();
        }

    }
    public void updateLogger(int playerNum){
        if(playerNum==2){
            if(workLoc==Constants.Pixels_Height-1){
                //find wood
                int tempX=x/10;
                for(int i = Constants.Pixels_Height-1;i>=0;i--){
                    if(Variable.pixelData2[tempX][i]==1){
                        workLoc=i;
                        //System.out.println(i);
                        break;
                    }
                }
            }
        }else{
            if(workLoc==Constants.Pixels_Height-1){
                //find wood
                int tempX=x/10;
                for(int i = Constants.Pixels_Height-1;i>=0;i--){
                    if(Variable.pixelData1[tempX][i]==1){
                        workLoc=i;
                        //System.out.println(i);
                        break;
                    }
                }
            }

        }
        if(y<workLoc*10+80){ state=1; }else if(y>670){ state=-1; }
        y=y+state;


    }
    public void updateFisher(){
        if(y<80){ state=1; }else if(y>670){ state=-1; }
        y=y+state;
    }
    public void updateMiner(){
        if(y<80){ state=1; }else if(y>670){ state=-1; }
        y=y+state;
    }
}
