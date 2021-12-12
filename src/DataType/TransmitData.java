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
    private static Lock lock = new ReentrantLock();

    public TransmitData() {
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, 999
                , 999, 999, 999, 999, 999);
        unitDataAL = new ArrayList<UnitData>(0);
        enemyUnitDataAL = new ArrayList<UnitData>(0);
    }
    public TransmitData(int playerNum) {
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
        //System.out.println(unitDataAL.get(0).getId());
    }
    // this method is used to take resource for the needed command:
    // the command's availability was checked before sent to server
    public void takeResource(int unitType){
        switch (unitType){
            case 1:
                statusData.addFood(-50);
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

    public synchronized void update(ArrayList<UnitData> enemyUnitDataAL) {
        lock.lock();
        updateTime();
        updateUnits();
        setEnemyUnit(enemyUnitDataAL);
        if(cookieCounter>=8){
            clearDiedUnit();
        }
        System.out.println(unitDataAL.size()+"   ");
        //System.out.println(enemyUnitDataAL.size());
        cookieCounter++;
        lock.unlock();
    }
    public void clearDiedUnit(){
        ArrayList<UnitData> temp= new ArrayList<UnitData>();
        for(UnitData unitData: unitDataAL) {
            if(unitData.isAlive()){
                temp.add(unitData);
            }
        }
        unitDataAL=temp;
    }
    public void updateTime(){
        statusData.setTime(Variable.time);
    }
    public synchronized void updateUnits(){
        for(UnitData unitData: unitDataAL) {
            unitData.update(playerNum);
        }

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

    public synchronized ArrayList<UnitData> getUnitDataAL() {
        return unitDataAL;
    }
    public synchronized ArrayList<UnitData> getEnemyUnitDataAL() {
        return enemyUnitDataAL;
    }

    public synchronized StatusData getStatusData() {
        return statusData;
    }
}
