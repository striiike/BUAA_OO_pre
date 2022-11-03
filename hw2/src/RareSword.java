class RareSword extends Sword {
    private double extraExpBonus;

    public double getExtraExpBonus() {
        return extraExpBonus;
    }

    public void setExtraExpBonus(double extraExpBonus) {
        this.extraExpBonus = extraExpBonus;
    }

    @Override
    public String toString() {
        return "The rareSword's id is " + this.getId()
                + ", name is " + this.getName()
                + ", sharpness is " + this.getSharpness()
                + ", extraExpBonus is " + this.getExtraExpBonus()
                + ".";
    }

    @Override
    public void usedBy(Adventurer user) {
        super.usedBy(user);
        user.setExp(user.getExp() + extraExpBonus);
        System.out.println(user.getName() +
                " got extra exp " + extraExpBonus +
                ".");
    }
}