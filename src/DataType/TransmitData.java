package DataType;

import Data.ServerData.Data_init;

import java.io.Serializable;
import java.util.ArrayList;

public class TransmitData implements Serializable {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;
    private int timeCounter;

    public TransmitData(int i) {
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, 999
                , 999, 999, 999, 999, 999);
        unitDataAL = new ArrayList<UnitData>();
    }
    public TransmitData() {
        statusData = new StatusData(Data_init.Max_HitPoint, Data_init.HitPoint, Data_init.Food
                , Data_init.Wood, Data_init.Coal, Data_init.Iron, Data_init.Unit, 0);
        unitDataAL = new ArrayList<UnitData>(0);
        unitDataAL.add(new UnitData(1,300,300));
    }

    public void update() {
        if (timeCounter < 20) {
            timeCounter++;
        } else {
            statusData.setTime(statusData.getTime() + 1);
            timeCounter = 0;
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
