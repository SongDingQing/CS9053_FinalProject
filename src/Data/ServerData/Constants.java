package Data.ServerData;
/*** This Class is Configuration of game
 * @author Chengzuo Song
 * @version 1.2.4
 */
public class Constants {
    //The text in log window
    public static String VersionText="This our budge game version 1.2.4\n";

    //Configuration on the <<<<SIZE>>>>> of the game
    // 1. FRAME CONSTANT
    public static final int Height = 800;
    public static final int Width = 1298;
    // 2. MAP PANEL CONSTANT
    public static final int Map_Height = 800;
    public static final int Map_Width = 801;
    public static int Pixels_Height = 60;
    public static int Pixels_Width = 80;
    // 3. CONTROL PANEL CONSTANT
    public static final int Cpanel_Height = 800;
    public static final int Cpanel_Width = 480;
    public static final int Status_Height = 200;
    public static final int Status_Width = 480;
    public static final int Command_Height = 400;
    public static final int Command_Width = 480;
    public static final int Log_Height = 200;
    public static final int Log_Width = 480;
    
    //Game Play Configuration
    public static final int Delay =40;
    //------------Logger------------
    //hitPoint
    public static final int hitPoint_Logger=40;
    //move speed
    public static final int Speed_Logger=1;
    //capacity
    public static final int MaxCapacity_Logger =20;
    // collecting rate in second is this/25
    public static final int CollectingRate_Logger =20;
    //------------Fisher------------
    //hitPoint
    public static final int hitPoint_Fisher=40;
    //move speed
    public static final int Speed_Fisher=1;
    //capacity
    public static final int MaxCapacity_Fisher =30;
    // collecting rate in second is this/25
    public static final int CollectingRate_Fisher =5;
    //------------Miner------------
    //hitPoint
    public static final int hitPoint_Miner=60;
    //move speed
    public static final int Speed_Miner=1;
    //capacity
    public static final int MaxCapacity_Miner =40;
    // collecting rate in second is this/25
    public static final int CollectingRate_Miner =5;
    //--------Warrior---------------
    //hitPoint
    public static final int hitPoint_Warrior=400;
    //move speed
    public static final int Speed_Warrior=1;
    // attack rate in second is this/25
    public static final int AttackRate_Warrior =50;
    //--------Archer---------------
    //hitPoint
    public static final int hitPoint_Archer=80;
    //move speed
    public static final int Speed_Archer=1;
    // attack rate in second is this/25
    public static final int AttackRate_Archer =50;
    //--------Fence---------------
    //hitPoint
    public static final int hitPoint_Fence=500;


}
