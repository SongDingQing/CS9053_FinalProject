package DataType;

import Data.ServerData.Data_init;

import java.io.Serializable;
import java.util.ArrayList;

import Data.ServerData.Variable;

public class TransmitData implements Serializable {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;

    public TransmitData(int i) {
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, 999
                , 999, 999, 999, 999, 999);
        unitDataAL = new ArrayList<UnitData>();
    }
    public TransmitData() {
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, Data_init.Food
                , Data_init.Wood, Data_init.Coal, Data_init.Iron, Data_init.Unit, 0);
        unitDataAL = new ArrayList<UnitData>(0);
        unitDataAL.add(new UnitData(1,100,500));
        unitDataAL.add(new UnitData(2,300,300));
        unitDataAL.add(new UnitData(3,100,600));
    }

    public void update() {
        updateTime();
        updateStatus();
        updateUnits();
    }
    public void updateTime(){
        statusData.setTime(Variable.time);
    }
    public void updateStatus(){
    }
    public void updateUnits(){
        for(UnitData unitData: unitDataAL) {
            unitData.setY(unitData.getY()+ unitData.getState());
        }
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

    public StatusData getStatusData() {
        return statusData;
    }
}
