import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Adventurer implements Commodity {
    private int id;
    private String name;
    private double health = 100.0;
    private double exp = 0.0;
    private double money = 0.0;
    //    private HashMap<Integer, Equipment> equipments = new HashMap<>();
    private HashMap<Integer, Commodity> commodities = new HashMap<>();

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

    public void use(Commodity commodity) {
        try {
            commodity.usedBy(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<Integer, Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(HashMap<Integer, Commodity> commodities) {
        this.commodities = commodities;
    }

    public void usedBy(Adventurer user) {
        this.setCommodities(God.sortValues(this.getCommodities()));
        Set<Map.Entry<Integer, Commodity>> set = getCommodities().entrySet();
        for (Object o : set) {
            Map.Entry map = (Map.Entry) o;
            user.use((Commodity) map.getValue());
        }
    }

    @Override
    public BigInteger getPrice() {
        BigInteger priceTotal = BigInteger.ZERO;
        for (Integer i : getCommodities().keySet()) {
            BigInteger price = getCommodities().get(i).getPrice();
            priceTotal = priceTotal.add(price);
        }
        return priceTotal;
    }

    @Override
    public int compareTo(Commodity other) {
        if (this.getPrice().compareTo(other.getPrice()) > 0) {
            return -1;
        } else if (this.getPrice().compareTo(other.getPrice()) < 0) {
            return 1;
        }

        if (this.getId() < other.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}