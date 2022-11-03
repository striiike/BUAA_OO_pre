class EpicSword extends Sword {
    private double evolveRatio;

    public double getEvolveRatio() {
        return evolveRatio;
    }

    public void setEvolveRatio(double evolveRatio) {
        this.evolveRatio = evolveRatio;
    }

    @Override
    public String toString() {
        return "The epicSword's id is " + this.getId()
                + ", name is " + this.getName()
                + ", sharpness is " + this.getSharpness()
                + ", evolveRatio is " + this.getEvolveRatio()
                + ".";
    }

    @Override
    public void usedBy(Adventurer user) {
        super.usedBy(user);
        setSharpness(getSharpness() * evolveRatio);
        System.out.println(getName() +
                "'s sharpness became " + getSharpness() +
                ".");
    }
}