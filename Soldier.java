public class Soldier extends Character {
  
  public static final int SOLDIER_INVENTORY_SIZE = 25;
  public static final double DEFAULT_SOLDIER_HEALTH = 50.0;
  public static final double DEFAULT_SOLDIER_STRENGTH = 100.0;
  
  public Soldier(MapNode m, double p, double maxP, double s, int l) {
    super(m,DEFAULT_SOLDIER_HEALTH,DEFAULT_SOLDIER_HEALTH,DEFAULT_SOLDIER_STRENGTH,1);
    setInventory(new Inventory(SOLDIER_INVENTORY_SIZE));
    // Starts equipped with AR, two Medpacks, some Ammo
    pickUp(new AssaultRifle());
    pickUp(new Medpack());
    pickUp(new Medpack());
    for(int i = 0; i < 4; i++) {
        pickUp(new ARRound());
    }
  }
  
}