import java.util.ArrayList;

class Adventurer {
    private int id;
    private String name;
    private ArrayList<Bottle> bottles;

    public void setAdventurer(int id, String name, ArrayList<Bottle> bottles) {
        this.id = id;
        this.bottles = bottles;
        this.name = name;
    }

    public ArrayList<Bottle> getBottles() {
        return bottles;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}