public abstract class Character {

    private Inventory inventory;
    private double health;
    private double maxHealth;
    private double power;
    private double maxPower;
    private double strength;

    Character(double h, double maxH, double p, double maxP, double s) {
	health = h;
	maxHealth = maxH;
	strength = s;
	i = new Inventory(Inventory.DEFAULT_INVENTORY_SIZE);
    }

    Character(Inventory i, double h, double maxH, double p, double maxP, double s) {
	super(h,maxH,p,maxP,s);
	inventory = i;
    }

}