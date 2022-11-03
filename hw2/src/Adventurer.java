import java.util.HashMap;

class Adventurer {
    private int id;
    private String name;
    private double health = 100.0;
    private double exp = 0.0;
    private double money = 0.0;
    private HashMap<Integer, Equipment> equipments = new HashMap<>();

    public HashMap<Integer, Equipment> getEquipments() {
        return equipments;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "The adventurer's id is " + this.id
                + ", name is " + this.name
                + ", health is " + this.health
                + ", exp is " + this.exp
                + ", money is " + this.money
                + ".";
    }

    public void use(Equipment equipment) {
        try {
            equipment.usedBy(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}