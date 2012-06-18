public class Crewman extends Unit {
    
  public static final int CREWMAN_INVENTORY_SIZE = 10;
  public static final double DEFAULT_CREWMAN_HEALTH = 20.0;
  public static final double DEFAULT_CREWMAN_ATTACK = 1.5;
  public static final double DEFAULT_CREWMAN_STRENGTH = 100.0;
  
  public Crewman(MapNode m, String name) {
    super(m,DEFAULT_CREWMAN_HEALTH,DEFAULT_CREWMAN_HEALTH,DEFAULT_CREWMAN_ATTACK,DEFAULT_CREWMAN_STRENGTH,1.0,true,name);
    setInventory(new Inventory(CREWMAN_INVENTORY_SIZE));
    pickUp(new Pistol());
    equip((Weapon)(getInventory().get(0)));
    pickUp(new WorkTunic());
    putOn((Armor)(getInventory().get(1)));
    pickUp(new Medpack());
    for(int i = 0; i < 2; i++) {
        pickUp(new PistolRound());
    }
    setSymbol('C');
  }
    
}
