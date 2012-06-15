public abstract class Weapon extends Item {
  
  private Ammo magazine;
  private Upgrade upgrade;
  
  // per shot
  private int aVal;
  
  public Weapon(int w, Ammo a, int attack) {
    super(w, null);
    magazine = a;
    aVal = attack;
    upgrade = null;
  }
  
  public abstract void fire();
  
  public Ammo load(Ammo a) {
    Ammo oldMag = magazine;
    magazine = a;
    return oldMag;
  }
  
}