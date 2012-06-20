public abstract class Unit extends BasicThing {
  
  public static final double MAX_LEVEL = 30;
  public static String[] names = {"Joe","Mindy","Mark","JonAlf","Mike","Kelly","Eric","Lee",
                                  "EdwardJames","Bob","Hugo","Mr.Cool","Alej","Kevn",
                                  "Jaxo","Blakè","Q","q","R4","Blay","Mr.Rhee"};
    
  private MapNode currentSpace;
  
  private Weapon hand;
  private Armor clothes;
  private Inventory inventory;
  
  private String name;
  
  private double health;
  private double maxHealth;
  private double attack;
  private double strength;
  private double level;
  private boolean isFriendly;
  private char symbol;
  
  Unit(MapNode m, double h, double maxH, double at, double s, double l, boolean friendly, String n) {
    super();
    currentSpace = m;
    m.putCharacter(this);
    health = h;
    maxHealth = maxH;
    attack = at;
    strength = s;
    level = l;
    inventory = new Inventory(Inventory.DEFAULT_BAG_SIZE);
    hand = null;
    clothes = null;
    setSymbol('@');
    isFriendly = friendly;
    name = n;
  }
  
  public void kill() {
    for(int i = 0; i < inventory.bagSize(); i++) {
        if(inventory.get(i) != null) {
            drop(inventory.get(i));
        }
    }
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
        else if(isFriendly != next.getCharacter().isFriendly()) {
            attack(direction);
        }
      }
  }
  
  public static String randomName() {
      return Unit.names[(int)(Math.random() * names.length)];
  }
  
  public void attack(int direction) {
      Unit attacked;
      if(hand != null && hand.getAmmo().getShots() > 0) {
          attacked = hand.fire(direction);
          if(attacked != null && getNode().getGrid().getSH().getPlayer() == this)
              System.out.println("You shot " + attacked.getName() + "!");
          
      }
      else {
          attacked = melee(direction);
          if(attacked != null && getNode().getGrid().getSH().getPlayer() == this)
              System.out.println("You hit " + attacked.getName() + "!");
      }
      if(attacked != null && attacked.getHealth() <= 0) {
          if(attacked.isFriendly())
            level -= Math.sqrt(attacked.getExp());
          else
            level += Math.sqrt(attacked.getExp());
          attacked.kill();
          if(level < 1.0)
              level = 1.0;
          if(level > MAX_LEVEL)
              level = MAX_LEVEL;
          System.out.println(attacked.getName() + " was killed!");
      }
  }
  
  public Unit melee(int direction) {
      MapNode next = currentSpace.getDirection(direction);
      if(next.getCharacter() != null && !next.getCharacter().isFriendly()) {
          next.getCharacter().addHealth(-attack);
          return next.getCharacter();
      }
      else if(next.getCharacter() != null && next.getCharacter().isFriendly()) {
          if(PromptGroups.clarificationPrompt()) {
            next.getCharacter().addHealth(-attack);
            return next.getCharacter();
          }
      }
      return null;
  }
  
  public Ammo reload(Ammo i) {
      return hand.load(i);
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
  
  public boolean pickUp(Item i) {
    if(inventory.weight() + i.weight() <= strength) {
        if(inventory.add(i)) {
            i.pickedUpBy(this);
            return true;
        }
    }
    return false;
  }
  
  public void drop(Item i) {
      i.pickedUpBy(null);
      if(i == hand)
          hand = null;
      if(i == clothes)
          clothes = null;
      currentSpace.putItemDown(i);
  }
  
  public Weapon getWeapon() {
      return hand;
  }
  
  public Weapon equip(Weapon w) {
      Weapon oldWeapon = hand;
      hand = w;
      return oldWeapon;
  }
  
  public Ammo loadWeapon(Ammo i) {
    return hand.load(i);
  }
  
  public Armor putOn(Armor a) {
      Armor oldArmor = clothes;
      clothes = a;
      return oldArmor;
  }
  
  public Armor getArmor() {
      return clothes;
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
  
  public String getName() {
      return name;
  }
  
  public String toString() {
      return "" + getSymbol();
  }
  
  public String statString() {
      String stat = "";
      stat += name + " the " + getClass().getName() + "\t";
      stat += "HP: " + health + "/" + maxHealth + "\t";
      stat += "Level/Exp: " + level + "\t";
      stat += "Character at: (" + getNode().getX() + "," + getNode().getY() + ")";
      stat += "\tWearing: ";
      if(clothes == null)
          stat += "nothing";
      else
          stat += clothes.inventoryPrint();
      stat += "\nWeilding: ";
      if(hand == null)
          stat += "nothing";
      else
          stat += hand.inventoryPrint();
      stat += "\tMax Weight Carryable: " + strength + "\tInventory Weight: " + inventory.weight();
      return stat;
  }
  
}