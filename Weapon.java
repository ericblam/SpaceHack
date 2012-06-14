public abstract class Weapon extends Item {
  
  private Ammo magazine;
  private Upgrade upgrade;
  
  public Weapon(int w, Ammo a) {
    super(w);
    magazine = a;
    upgrade = null;
  }
  
  public abstract void fire();
  
  public Ammo load(Ammo a) {
    Ammo oldMag = magazine;
    magazine = a;
    return oldMag;
  }
  
  public void use() {
    fire();
  }
  
}