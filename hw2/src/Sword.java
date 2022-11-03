// although called Sword, weapon actually...

class Sword extends Equipment {
    private double sharpness;

    public double getSharpness() {
        return sharpness;
    }

    public void setSharpness(double sharpness) {
        this.sharpness = sharpness;
    }

    @Override
    public String toString() {
        return "The sword's id is " + this.getId()
                + ", name is " + this.getName()
                + ", sharpness is " + this.getSharpness()
                + ".";
    }

    @Override
    public void usedBy(Adventurer user) {
        user.setHealth(user.getHealth() - 10.0);
        user.setExp(user.getExp() + 10.0);
        user.setMoney(user.getMoney() + sharpness);
        System.out.println(user.getName() +
                " used " + getName() +
                " and earned " + sharpness +
                ".");
    }
}



