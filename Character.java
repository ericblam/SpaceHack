public abstract class Character {
  
    private MapNode currentSpace;
    private int heading;

    private Inventory inventory;
    private double health;
    private double maxHealth;
    private double power;
    private double maxPower;
    private double strength;
    private int level;
  
    Character(MapNode m, double h, double maxH, double p, double maxP, double s, int l) {
	currentSpace = m;
	health = h;
	maxHealth = maxH;
	strength = s;
	level = l;
	inventory = new Inventory(Inventory.DEFAULT_INVENTORY_SIZE);
	heading = DEFAULT_HEADING;
    }
  
    Character(MapNode m, Inventory i, double h, double maxH, double p, double maxP, double s, int l) {
	super(m,h,maxH,p,maxP,s,l);
	inventory = i;
    }
  
}