// Equipment contains bottle (potion) and sword (weapon)

public class Equipment {
    private int id;
    private String name;
    private long price;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setEquipment(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void usedBy(Adventurer user) throws Exception {

    }
}



