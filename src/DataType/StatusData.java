package DataType;

public class StatusData {
	// Data shown on the StatusPanel
    private int maxHp,hp,food,wood,coal,iron,unit,time;
    public StatusData(int maxHp,int hp,int food,int wood,int coal,int iron,int unit,int time){
        this.maxHp=maxHp;
        this.hp=hp;
        this.food=food;
        this.wood=wood;
        this.coal=coal;
        this.iron=iron;
        this.unit=unit;
        this.time=time;
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
}
