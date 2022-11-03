// Equipment contains bottle (potion) and sword (weapon)

import java.math.BigInteger;

public class Equipment implements Commodity, Cloneable {
    private int id;
    private String name;
    private BigInteger price;

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setEquipment(int id, String name, BigInteger price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public void usedBy(Adventurer user) throws Exception {

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
    public Equipment clone() throws CloneNotSupportedException {
        Equipment clone = new Equipment();
        clone.setEquipment(this.getId(), this.getName(), this.getPrice());
        return clone;
    }

}



