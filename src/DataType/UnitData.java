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
    private int life;
    private boolean isAlive;
    private int capacity;//item carried
    private int counter;

    public UnitData(int unitType, int x, int y) {
        counter =0;
        capacity=0;
        state = -1;
        this.x = x;
        this.y = y;
        this.unitType = unitType;
        workLoc=Constants.Pixels_Height-1;
        life=4000;
        isAlive=true;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void update(int playerNum) {
        if(life<=0){
            isAlive=false;
        }else{
            if(unitType==1){
                updateLogger(playerNum);
            }else if(unitType==2){
                updateFisher();
            }else if(unitType==3){
                updateMiner();
            } else if(unitType==4){
                updateWarrior(playerNum);
            }
            life--;
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
        if(y<workLoc*10+80){
            //branch: working
            if(capacity<Constants.MaxCapacity_Logger){
                state=0;
                if(counter <Constants.CollectingRate_Logger){
                  counter++;
                }else{
                    counter =0;
                    capacity++;
                    //System.out.println(capacity);
                }
            }else{
                state=1;
            }
        }else if(y>680){
            state=-1;
            capacity=0;
            if(playerNum==1){
                Variable.data1.getStatusData().addWood(Constants.MaxCapacity_Logger);
            }else{
                Variable.data2.getStatusData().addWood(Constants.MaxCapacity_Logger);
            }
            //System.out.println(capacity);
        }
        y=y+Constants.Speed_Logger*state;


    }

    public void updateFisher(){
        if(y<80){ state=1; }else if(y>680){ state=-1; }
        y=y+state;
    }
    public void updateMiner(){
        if(y<80){ state=1; }else if(y>680){ state=-1; }
        y=y+state;
    }
    public void updateWarrior(int playerNum){
        if(y<80){
            state=0;
            if(counter>=Constants.AttackRate_Warrior){
                counter=0;
                if(playerNum==1){
                    Variable.data2.getStatusData().addHp(-1);
                }else{
                    Variable.data1.getStatusData().addHp(-1);
                }
                //System.out.print("2");
            }else{
                //System.out.print("1");
                counter++;
            }

        }
        y=y+Constants.Speed_Warrior*state;
    }
}
