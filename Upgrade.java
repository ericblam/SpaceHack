public abstract class Upgrade extends Item {
 
  private int b;
  
  public Upgrade(double weight, int bonus) {
    super(weight, null);
    b = bonus;
  }
  
  public int getBonus() {
      return b;
  }
  
}