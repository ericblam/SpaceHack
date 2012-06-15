public abstract class Character {
  
  public static final int DEFAULT_HEADING = MapNode.UP;
  
  private MapNode currentSpace;
  private int heading;
  
  private Weapon hand;
  private Inventory inventory;
  private double health;
  private double maxHealth;
  private double strength;
  private int level;
  
  Character(MapNode m, double h, double maxH, double s, int l) {
    currentSpace = m;
    health = h;
    maxHealth = maxH;
    strength = s;
    level = l;
    inventory = new Inventory(Inventory.DEFAULT_BAG_SIZE);
    heading = DEFAULT_HEADING;
    hand = null;
  }
  
  Character(MapNode m, Inventory i, double h, double maxH, double s, int l) {
    this(m,h,maxH,s,l);
    inventory = i;
  }
  
  public int getHeading() {
      return heading;
  }
  
  public void kill() {
    currentSpace = null;
    health = 0;
  }
  
  public void setInventory(Inventory i) {
    inventory = i;
  }
  
  public void pickUp(Item i) {
    inventory.add(i);
    i.pickedUpBy(this);
  }
  
  public void equip(Weapon w) {
      hand = w;
  }
  
  public Ammo loadWeapon(Ammo i) {
    return hand.load(i);
  }
  
  public double getHealth() {
      return health;
  }
  
  public void addHealth(double h) {
      health += h;
      if(health > maxHealth) {
          health = maxHealth;
      }
  }
  
  public MapNode getNode() {
      return currentSpace;
  }
  
}