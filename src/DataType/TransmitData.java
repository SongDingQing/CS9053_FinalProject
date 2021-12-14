package DataType;

import Data.ServerData.Data_init;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Data.ServerData.Variable;

public class TransmitData implements Serializable {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;
    private ArrayList<UnitData> enemyUnitDataAL;
    private int playerNum;
    private int cookieCounter;
    private int gameEnd;//0 for default, 1 for win -1 for lost

    public TransmitData() {
        gameEnd=0;
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, -999
                , -999, -999, -999, -999, 999);
        unitDataAL = new ArrayList<UnitData>(0);
        enemyUnitDataAL = new ArrayList<UnitData>(0);
    }
    public TransmitData(int playerNum) {
        gameEnd=0;
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, Data_init.Food
                , Data_init.Wood, Data_init.Coal, Data_init.Iron, Data_init.Unit, 0);
        unitDataAL = new ArrayList<UnitData>(0);
        enemyUnitDataAL = new ArrayList<UnitData>(0);
        this.playerNum=playerNum;
        cookieCounter=0;
    }
    public void createUnit(int unitType,int locX){
        takeResource(unitType);
        unitDataAL.add(new UnitData(unitType,locX/10*10,670));
        statusData.addUnit(1);
        //System.out.println(unitDataAL.get(0).getId());
    }
    // this method is used to take resource for the needed command:
    // the command's availability was checked before sent to server
    public void takeResource(int unitType){
        if(unitType==1){
            statusData.addFood(-40);
        }else if(unitType==2){
            statusData.addFood(-30);
            statusData.addWood(-20);
        }else if(unitType==3){
            statusData.addFood(-40);
            statusData.addWood(-40);
        }else if(unitType==4){
            statusData.addFood(-50);
            statusData.addWood(-20);
            statusData.addCoal(-40);
            statusData.addIron(-30);
        }else if(unitType==5){
            statusData.addFood(-90);
            statusData.addWood(-90);
            statusData.addIron(-20);
        }else if(unitType==6){
            statusData.addWood(-200);
        }

    }
    public void updateEnemyUnit(){
        if(playerNum==1){
            enemyUnitDataAL= Variable.data2.getUnitDataAL();
        }else{
            enemyUnitDataAL= Variable.data1.getUnitDataAL();
        }

        //System.out.println(enemyUnitDataAL.get(0).getId());
    }

    public void update(ArrayList<UnitData> enemyUnitDataAL) {
        if(gameEnd==0){
            updateTime();
            updateUnits();
            setEnemyUnit(enemyUnitDataAL);
            if(cookieCounter>=100){
                clearDiedUnit();
            }
            cookieCounter++;
        }
    }
    public void clearDiedUnit(){
        ArrayList<UnitData> temp= new ArrayList<UnitData>();
        for(UnitData unitData: unitDataAL) {
            if(unitData.isAlive()){
                temp.add(unitData);
            }
        }
        unitDataAL=temp;
        statusData.setUnit(temp.size());
    }
    public void updateTime(){
        statusData.setTime(Variable.time);
    }
    public void updateUnits(){
        for(UnitData unitData: unitDataAL) {
            unitData.update(playerNum);
        }

    }
    public void lost(){
        gameEnd=-1;
    }
    public void win(){
        gameEnd=1;
    }
    public int getGameEnd(){
        return gameEnd;
    }
    public void setEnemyUnit(ArrayList<UnitData> enemyUnitDataAL){
        this.enemyUnitDataAL=enemyUnitDataAL;
    }

    public void setStatusData(StatusData statusData) {
        this.statusData = statusData;
    }

    public void setUnitDataAL(ArrayList<UnitData> unitDataAL) {
        this.unitDataAL = unitDataAL;
    }

    public ArrayList<UnitData> getUnitDataAL() {
        return unitDataAL;
    }
    public ArrayList<UnitData> getEnemyUnitDataAL() {
        return enemyUnitDataAL;
    }

    public StatusData getStatusData() {
        return statusData;
    }
}
