class HealingPotion extends Bottle {
    private double efficiency;

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "The healingPotion's id is " + this.getId()
                + ", name is " + this.getName()
                + ", capacity is " + this.getCapacity()
                + ", filled is " + this.isFilled()
                + ", efficiency is " + this.efficiency
                + ".";
    }

    @Override
    public void usedBy(Adventurer user) throws Exception {
        super.usedBy(user);
        user.setHealth(user.getHealth() + getCapacity() * getEfficiency());
        System.out.println(user.getName() +
                " recovered extra " + getCapacity() * getEfficiency() +
                ".");

    }

}