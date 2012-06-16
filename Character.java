public abstract class Character {
  
  private MapNode currentSpace;
  
  private Weapon hand;
  private Inventory inventory;
  private double health;
  private double maxHealth;
  private double strength;
  private int level;
  private boolean isFriendly;
  
  Character(MapNode m, double h, double maxH, double s, int l) {
    currentSpace = m;
    health = h;
    maxHealth = maxH;
    strength = s;
    level = l;
    inventory = new Inventory(Inventory.DEFAULT_BAG_SIZE);
    hand = null;
  }
  
  Character(MapNode m, Inventory i, double h, double maxH, double s, int l) {
    this(m,h,maxH,s,l);
    inventory = i;
  }
  
  public void kill() {
    currentSpace = null;
    health = 0;
  }
  
  public void move(int direction) {
      MapNode next = currentSpace.getDirection(direction);
      if(next.getCharacter() == null) {
        currentSpace.putCharacter(null);
        currentSpace = currentSpace.getDirection(direction);
        currentSpace.putCharacter(this);
      }
      else if(!next.getCharacter().isFriendly()) {
        attack(direction);
      }
  }
  
  public void attack(int direction) {
      if(hand != null) {
          hand.fire(direction);
      }
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
  
  public boolean isFriendly() {
      return isFriendly;
  }
  
}