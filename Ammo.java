public abstract class Ammo extends Item {
  
  private int shots;
  private int maxShots;
  
  public Ammo(int w, int s, int mShots) {
    super(w,null);
    shots = s;
    maxShots = mShots;
  }
  
  public int getShots() {
    return shots;
  }
  
}