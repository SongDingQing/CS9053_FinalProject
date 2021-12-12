package DataType;

import java.io.Serializable;

public class StatusData implements Serializable {
	// Data shown on the StatusPanel
    private int maxHp,hp,food,wood,coal,iron,unit,time;
    private int timeCounter;
    public StatusData(){
        this.maxHp=100;
        this.hp=100;
        this.food=0;
        this.wood=0;
        this.coal=0;
        this.iron=0;
        this.unit=0;
        this.time=0;
        timeCounter =0;
    }
    public StatusData(int maxHp,int hp,int food,int wood,int coal,int iron,int unit,int time){
        this.maxHp=maxHp;
        this.hp=hp;
        this.food=food;
        this.wood=wood;
        this.coal=coal;
        this.iron=iron;
        this.unit=unit;
        this.time=time;
        timeCounter =0;

    }
    public int getCoal() {
        return coal;
    }
    public int getHp() {
        return hp;
    }
    public int getIron() {
        return iron;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getTime() {
        return time;
    }
    public int getUnit() {
        return unit;
    }
    public int getWood() {
        return wood;
    }
    public int getFood() {
        return food;
    }
    public void setCoal(int coal) {
        this.coal = coal;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setIron(int iron) {
        this.iron = iron;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setUnit(int unit) {
        this.unit = unit;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public void addHp(int amount){
        this.hp+=amount;
    }
    public void addWood(int item){
        this.wood+=item;
    }
    public void addFood(int item){
        this.food+=item;
    }
    public void addCoal(int item){
        this.coal+=item;
    }
    public void addIron(int item){
        this.iron+=item;
    }
}
