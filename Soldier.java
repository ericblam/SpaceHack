public class Soldier extends Character {
  
  public static final int SOLDIER_INVENTORY_SIZE = 25;
  
  public Soldier(MapNode m, double h, double maxH, double p, double maxP, double s, int l) {
    super(m,h,maxH,p,maxP,s,l);
    setInventory(new Inventory(SOLDIER_INVENTORY_SIZE));
    // Starts equipped with AR, two Medpacks, some Ammo
  }
  
}