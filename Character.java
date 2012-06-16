public abstract class Character {
  
  public static final double MAX_LEVEL = 30;
    
  private MapNode currentSpace;
  
  private Weapon hand;
  private Inventory inventory;
  private double health;
  private double maxHealth;
  private double attack;
  private double strength;
  private double level;
  private boolean isFriendly;
  
  Character(MapNode m, double h, double maxH, double at, double s, double l) {
    currentSpace = m;
    health = h;
    maxHealth = maxH;
    attack = at;
    strength = s;
    level = l;
    inventory = new Inventory(Inventory.DEFAULT_BAG_SIZE);
    hand = null;
  }
  
  Character(MapNode m, Inventory i, double h, double maxH, double at, double s, double l) {
    this(m,h,maxH,at,s,l);
    inventory = i;
  }
  
  public void kill() {
    currentSpace.putCharacter(null);
    currentSpace = null;
    health = 0;
  }
  
  public void move(int direction) {
      MapNode next = currentSpace.getDirection(direction);
      if(next.isPassable()){
        if(next.getCharacter() == null) {
            currentSpace.putCharacter(null);
            currentSpace = currentSpace.getDirection(direction);
            currentSpace.putCharacter(this);
        }
        else if(!next.getCharacter().isFriendly()) {
            attack(direction);
        }
      }
  }
  
  public void attack(int direction) {
      Character attacked;
      if(hand != null && hand.getAmmo().getShots() > 0) {
          attacked = hand.fire(direction);
      }
      else {
          attacked = melee(direction);
      }
      if(attacked != null && attacked.getHealth() <= 0) {
          level += attacked.getExp();
          attacked.kill();
          if(level > MAX_LEVEL)
              level = MAX_LEVEL;
      }
  }
  
  public Character melee(int direction) {
      MapNode next = currentSpace.getDirection(direction);
      if(next.getCharacter() != null && !next.getCharacter().isFriendly()) {
          next.getCharacter().addHealth(-attack);
          return next.getCharacter();
      }
      return null;
  }
  
  public Inventory getInventory() {
      return inventory;
  }
  
  public void setInventory(Inventory i) {
    inventory = i;
  }
  
  public double getExp() {
      return level;
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