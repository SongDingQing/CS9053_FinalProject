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
    private static int numOfInstance = 0;
    private int id;
    private int hp;
    private boolean haveWorkLoc;
    private boolean isAttacked;
    private int workType;//this is used for miner only

    public UnitData(int unitType, int x, int y) {
        workType=0;
        isAttacked=false;
        haveWorkLoc=false;
        counter = 0;
        capacity = 0;
        state = -1;
        this.x = x;
        this.y = y;
        this.unitType = unitType;
        workLoc = Constants.Pixels_Height - 1;
        life = 4000;
        isAlive = true;
        numOfInstance++;
        id = numOfInstance;
        hpConfig();
    }

    public void hpConfig() {
        switch (unitType) {
            case 1:
            case 2:
                hp = 40;
                break;
            case 3:
                hp = 60;
                break;
            case 4:
                hp = 240;
                break;
            case 5:
                hp = 80;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
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

    public int getId() {
        return id;
    }

    public void setIsAttacked(boolean a) {
        isAttacked=a;
    }
    public void addHp(int amount){
        hp+=amount;
    }

    public void update(int playerNum) {
        if (life <= 0) {
            isAlive = false;
        } else {
            if (unitType == 1) {
                updateLogger(playerNum);
            } else if (unitType == 2) {
                updateFisher(playerNum);
            } else if (unitType == 3) {
                updateMiner(playerNum);
            } else if (unitType == 4) {
                updateWarrior(playerNum);
            } else if (unitType == 5) {
                updateArcher(playerNum);
            }
            life--;
            //System.out.println(life+"from playNUm  "+playerNum);
        }


    }

    public void updateLogger(int playerNum) {
        if(hp>0){
            //find working location
            if(playerNum==1){
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData1[tempX][i] == 1) {
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }

            } else if (playerNum == 2) {
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData2[tempX][i] == 1) {
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }
            }
            if(haveWorkLoc&&!isAttacked){
                if (y <= workLoc * 10 + 80) {
                    //branch: working
                    if (capacity < Constants.MaxCapacity_Logger) {
                        state = 0;
                        if (counter < Constants.CollectingRate_Logger) {
                            counter++;
                        } else {
                            counter = 0;
                            capacity++;
                            //System.out.println(capacity);
                        }
                    } else {
                        state = 1;
                    }
                } else if (y > 680) {
                    state = -1;
                    if (playerNum == 1) {
                        Variable.data1.getStatusData().addWood(capacity);
                    } else {
                        Variable.data2.getStatusData().addWood(capacity);
                    }
                    capacity = 0;
                }
                y = y + Constants.Speed_Logger * state;
            }
        }else{
            isAlive=false;
        }

    }

    public void updateFisher(int playerNum) {
        if(hp>0){
            //find working location
            if(playerNum==1){
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData1[tempX][i] == 6) {
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }

            } else if (playerNum == 2) {
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData2[tempX][i] == 6) {
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }
            }
            if(haveWorkLoc&&!isAttacked){
                if (y <= workLoc * 10 + 80) {
                    //branch: working
                    if (capacity < Constants.MaxCapacity_Fisher) {
                        state = 0;
                        if (counter < Constants.CollectingRate_Fisher) {
                            counter++;
                        } else {
                            counter = 0;
                            capacity++;
                            //System.out.println(capacity);
                        }
                    } else {
                        state = 1;
                    }
                } else if (y > 680) {
                    state = -1;
                    if (playerNum == 1) {
                        Variable.data1.getStatusData().addFood(capacity);
                    } else {
                        Variable.data2.getStatusData().addFood(capacity);
                    }
                    capacity = 0;
                }
                y = y + Constants.Speed_Fisher * state;
            }
        }else{
            isAlive=false;
        }
    }

    public void updateMiner(int playerNum) {
        if(hp>0){
            //find working location
            if(playerNum==1){
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData1[tempX][i] == 4) {
                            workType=1;
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }else if (Variable.pixelData1[tempX][i] == 5) {
                            workType=2;
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }

            } else if (playerNum == 2) {
                if (workLoc == Constants.Pixels_Height - 1) {
                    //find wood
                    int tempX = x / 10;
                    for (int i = Constants.Pixels_Height - 1; i >= 0; i--) {
                        if (Variable.pixelData2[tempX][i] == 4) {
                            workType=1;
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }else if (Variable.pixelData2[tempX][i] == 5) {
                            workType=2;
                            workLoc = i;
                            haveWorkLoc=true;
                            break;
                        }
                    }
                }
            }
            if(workType!=0&&!isAttacked){
                if (y <= workLoc * 10 + 80) {
                    //branch: working
                    if (capacity < Constants.MaxCapacity_Miner) {
                        state = 0;
                        if (counter < Constants.CollectingRate_Miner) {
                            counter++;
                        } else {
                            counter = 0;
                            capacity++;
                            //System.out.println(capacity);
                        }
                    } else {
                        state = 1;
                    }
                } else if (y > 680) {
                    state = -1;
                    if (playerNum == 1) {
                        if(workType==1){
                            Variable.data1.getStatusData().addCoal(capacity);
                        }else{
                            Variable.data1.getStatusData().addIron(capacity);
                        }

                    } else {
                        if(workType==1){
                            Variable.data2.getStatusData().addCoal(capacity);
                        }else{
                            Variable.data2.getStatusData().addIron(capacity);
                        }
                    }
                    capacity = 0;
                }
                y = y + Constants.Speed_Miner * state;
            }
        }else{
            isAlive=false;
        }
    }

    public void updateWarrior(int playerNum) {
        if(hp<=0){
            isAlive=false;
        }else{
            boolean enemyExist=false;
            if (playerNum == 1) {
                for(UnitData ud:Variable.data2.getUnitDataAL()){
                    if(((ud.getX()-x)<=10&&(ud.getX()-x)>=-10) &&(y+ud.getY())<760){// reaching statement
                        if(ud.isAlive){
                            enemyExist=true;
                            ud.setIsAttacked(true);
                            ud.addHp(-1);
                            state=0;
                            hp--;
                            if(ud.getHp()<=0){
                                state=-1;
                            }
                            if(hp<=0){
                                ud.setIsAttacked(false);
                                ud.setState(-1);
                            }
                            break;
                        }

                    }
                }
                if(!enemyExist){
                    state=-1;
                }
                if (y < 80) {// reaching enemy base
                    state = 0;
                    if (counter >= Constants.AttackRate_Warrior) {
                        counter = 0;
                        Variable.data2.getStatusData().addHp(-1);
                        hp-=48;
                    } else {
                        counter++;
                    }

                }
            } else if(playerNum==2){
                for(UnitData ud:Variable.data1.getUnitDataAL()){
                    if(((ud.getX()-x)<=10&&(ud.getX()-x)>=-10) &&(y+ud.getY())<760){// reaching statement
                        if(ud.isAlive){
                            enemyExist=true;
                            ud.setIsAttacked(true);
                            ud.addHp(-1);
                            state=0;
                            hp--;
                            if(ud.getHp()<=0){
                                state=-1;
                            }
                            if(hp<=0){
                                ud.setIsAttacked(false);
                                ud.setState(-1);
                            }
                            break;
                        }

                    }
                }
                if(!enemyExist){
                    state=-1;
                }
                if (y < 80) {//reaching enemy state
                    state = 0;
                    if (counter >= Constants.AttackRate_Warrior) {
                        counter = 0;
                        Variable.data1.getStatusData().addHp(-1);
                        hp-=48;
                        //System.out.println(hp);
                    } else {
                        counter++;
                    }

                }
            }
            y = y + Constants.Speed_Warrior * state;
        }

    }

    public void updateArcher(int playerNum) {
        if(hp<=0){
            isAlive=false;
        }else{
            if (playerNum == 1) {
                boolean enemyExist=false;
                for(UnitData ud:Variable.data2.getUnitDataAL()){
                    if(((ud.getX()-x)<=20&&(ud.getX()-x)>=-20) &&(y+ud.getY())<800){// reaching statement
                        if(ud.isAlive){
                            enemyExist=true;
                            ud.setIsAttacked(true);
                            ud.addHp(-1);
                            state=0;
                            if(ud.getUnitType()==4){
                                hp--;
                                ud.setIsAttacked(false);
                            }else if(ud.getUnitType()==5){
                                hp--;
                            }
                            if(ud.getHp()<=0){
                                state=-1;
                            }
                            if(hp<=0){
                                ud.setIsAttacked(false);
                                ud.setState(-1);
                            }
                            break;
                        }

                    }
                }
                if(!enemyExist){//check if enemy is found
                    state=-1;
                }
                if (y < 80) {// reaching enemy base
                    state = 0;
                    if (counter >= Constants.AttackRate_Archer) {
                        counter = 0;
                        Variable.data2.getStatusData().addHp(-1);
                        hp-=60;
                    } else {
                        counter++;
                    }

                }
            } else if(playerNum==2){
                boolean enemyExist=false;
                for(UnitData ud:Variable.data1.getUnitDataAL()){
                    if(((ud.getX()-x)<=20&&(ud.getX()-x)>=-20) &&(y+ud.getY())<800){// reaching statement
                        if(ud.isAlive){
                            enemyExist=true;
                            ud.setIsAttacked(true);
                            ud.addHp(-1);
                            state=0;
                            if(ud.getUnitType()==4){
                                hp--;
                                ud.setIsAttacked(false);
                            }else if(ud.getUnitType()==5){
                                hp--;
                            }
                            if(ud.getHp()<=0){
                                state=-1;
                            }
                            if(hp<=0){
                                ud.setIsAttacked(false);
                                ud.setState(-1);
                            }
                            break;
                        }
                    }
                }
                if(!enemyExist){//check if enemy is found
                    state=-1;
                }
                if (y < 80) {//reaching enemy state
                    state = 0;
                    if (counter >= Constants.AttackRate_Archer) {
                        counter = 0;
                        Variable.data1.getStatusData().addHp(-1);
                        hp-=60;
                        //System.out.println(hp);
                    } else {
                        counter++;
                    }

                }
            }
            y = y + Constants.Speed_Archer * state;
        }

    }
}
