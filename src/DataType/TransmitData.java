package DataType;
import Data.ClientData.Variable;

import java.util.ArrayList;

public class TransmitData {
    private StatusData statusData;
    private ArrayList<UnitData> unitDataAL;
    public TransmitData(){
        statusData = new StatusData(Variable.M);
    }
}
