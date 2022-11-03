import java.util.HashMap;

public class Branch {
    private HashMap<Integer, Adventurer> adventurers = new HashMap<>();
    private int[][] employMap = new int[3][60000];
    private HashMap<Integer, Commodity> commodityHashMap = new HashMap<>();
    private int employNum = 0;

    public HashMap<Integer, Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(HashMap<Integer, Adventurer> adventurers) {
        this.adventurers = adventurers;
    }

    public int[][] getEmployMap() {
        return employMap;
    }

    public void setEmployMap(int[][] employMap) {
        this.employMap = employMap;
    }

    public HashMap<Integer, Commodity> getCommodityHashMap() {
        return commodityHashMap;
    }

    public void setCommodityHashMap(HashMap<Integer, Commodity> commodityHashMap) {
        this.commodityHashMap = commodityHashMap;
    }

    public int getEmployNum() {
        return employNum;
    }

    public void setEmployNum(int employNum) {
        this.employNum = employNum;
    }
}
