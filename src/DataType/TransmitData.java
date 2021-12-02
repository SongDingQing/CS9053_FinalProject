package DataType;
import Data.ServerData.Data_init;
import Data.ServerData.Data_init.*;

import java.io.Serializable;
import java.util.ArrayList;

public class TransmitData implements Serializable {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;
    public TransmitData(){
        statusData = new StatusData(Data_init.Max_HitPoint,Data_init.HitPoint,Data_init.Food
                ,Data_init.Wood,Data_init.Coal,Data_init.Iron,Data_init.Unit,0);
        unitDataAL= new ArrayList<UnitData>(0);
    }

    public void setStatusData(StatusData statusData) {
        this.statusData = statusData;
    }

    public void setUnitDataAL(ArrayList<UnitData> unitDataAL) {
        this.unitDataAL = unitDataAL;
    }

    public StatusData getStatusData() {
        return statusData;
    }
}
