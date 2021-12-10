package DataType;

import Data.ServerData.Data_init;

import java.io.Serializable;
import java.util.ArrayList;

import Data.ServerData.Variable;

public class TransmitData implements Serializable {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;
    private ArrayList<UnitData> enemyUnitDataAL;
    private int playerNum;

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
    }
    public void createUnit(int unitType,int locX){
        unitDataAL.add(new UnitData(unitType,locX/10*10,670));
    }
    public void createEnemyUnit(int unitType,int locX){
        enemyUnitDataAL.add(new UnitData(unitType,locX/10*10,670));
    }

    public void update(ArrayList<UnitData> enemyUnitDataAL) {
        updateTime();
        updateStatus();
        updateUnits();
        updateEnemyUnit(enemyUnitDataAL);
    }
    public void updateTime(){
        statusData.setTime(Variable.time);
    }
    public void updateStatus(){
    }
    public void updateUnits(){
        for(UnitData unitData: unitDataAL) {
            unitData.update(playerNum);
        }

    }
    public void updateEnemyUnit(ArrayList<UnitData> enemyUnitDataAL){
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
