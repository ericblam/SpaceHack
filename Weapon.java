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
  
  // If ammo is correct, will load. Otherwise will just eject magazine.
  public abstract Ammo load(Ammo a);
  
  public Ammo getAmmo() {
      return magazine;
  }
  
  public void setMag(Ammo a) {
      magazine = a;
  }
  
}