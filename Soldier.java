public class Soldier extends Unit {
  
  public static final int SOLDIER_INVENTORY_SIZE = 25;
  public static final double DEFAULT_SOLDIER_HEALTH = 50.0;
  public static final double DEFAULT_SOLDIER_ATTACK = 3.5;
  public static final double DEFAULT_SOLDIER_STRENGTH = 200.0;
  
  public Soldier(MapNode m, String name) {
    super(m,DEFAULT_SOLDIER_HEALTH,DEFAULT_SOLDIER_HEALTH,DEFAULT_SOLDIER_ATTACK,DEFAULT_SOLDIER_STRENGTH,1.0,true,name);
    setInventory(new Inventory(SOLDIER_INVENTORY_SIZE));
    // Starts equipped with AR, two Medpacks, some Ammo
    pickUp(new AssaultRifle());
    equip((Weapon)(getInventory().get(0)));
    pickUp(new StandardArmor());
    putOn((Armor)(getInventory().get(1)));
    pickUp(new Medpack());
    pickUp(new Medpack());
    for(int i = 0; i < 4; i++) {
        pickUp(new ARRound());
    }
  }
  
}