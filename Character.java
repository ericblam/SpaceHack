public abstract class Character {
  
  private Inventory inventory;
  private double health;
  private double maxHealth;
  private double power;
  private double maxPower;
  private double strength;
  private int level;
  
  Character(double h, double maxH, double p, double maxP, double s, int l) {
    health = h;
    maxHealth = maxH;
    strength = s;
    level = l;
    i = new Inventory(Inventory.DEFAULT_INVENTORY_SIZE);
  }
  
  Character(Inventory i, double h, double maxH, double p, double maxP, double s, int l) {
    super(h,maxH,p,maxP,s,l);
    inventory = i;
  }
  
}