import java.math.BigInteger;

public interface Commodity extends Comparable<Commodity> {
    void usedBy(Adventurer user) throws Exception;

    BigInteger getPrice();

    String toString();

    int getId();

}
