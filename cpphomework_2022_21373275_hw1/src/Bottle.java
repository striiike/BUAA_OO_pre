class Bottle {
    private int id;
    private String name;
    private long price;
    private double capacity;
    private boolean filled;

    public void setBottle(int id, String name, long price, double capacity, boolean filled) {
        this.id = id;
        this.capacity = capacity;
        this.filled = filled;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public double getCapacity() {
        return capacity;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "The bottle's id is " + this.id + ", name is " + this.name + ", capacity is "
                + this.capacity + ", filled is " + this.filled + ".";
    }
}