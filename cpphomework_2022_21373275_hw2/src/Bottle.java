class Bottle extends Equipment {
    private double capacity;
    private boolean filled;

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "The bottle's id is " + this.getId()
                + ", name is " + this.getName()
                + ", capacity is " + this.capacity
                + ", filled is " + this.filled
                + ".";
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public void usedBy(Adventurer user) throws Exception {
        if (!isFilled()) {
            throw new Exception("Failed to use " + getName() + " because it is empty.");
        }
        user.setHealth(user.getHealth() + capacity / 10);
        setFilled(false);
        setPrice(getPrice() / 10);

        System.out.println(user.getName() +
                " drank " + getName() +
                " and recovered " + capacity / 10 +
                ".");
    }
}
