public abstract class Ammo extends Item {
  
  private int shots;
  
  public Ammo(int w, int s) {
    super(w);
    shots = s;
  }
  
  public void use(){}
  
  public int getShots() {
    return shots;
  }
  
}