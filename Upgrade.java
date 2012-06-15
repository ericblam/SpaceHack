public abstract class Upgrade extends Item {
 
  private int b;
  
  public Upgrade(int weight, int bonus) {
    super(weight, null);
    b = bonus;
  }
  
}