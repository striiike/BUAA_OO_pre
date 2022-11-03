class ExpBottle extends Bottle {
    private double expRatio;

    public void setExpRatio(double expRatio) {
        this.expRatio = expRatio;
    }

    @Override
    public String toString() {
        return "The expBottle's id is " + this.getId()
                + ", name is " + this.getName()
                + ", capacity is " + this.getCapacity()
                + ", filled is " + this.isFilled()
                + ", expRatio is " + this.expRatio
                + ".";
    }

    @Override
    public void usedBy(Adventurer user) throws Exception {
        super.usedBy(user);
        user.setExp(user.getExp() * expRatio);
        System.out.println(user.getName() +
                "'s exp became " + user.getExp() +
                ".");

    }
}