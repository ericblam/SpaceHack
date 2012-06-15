public abstract class Armor extends Item {
  
  private int aVal;
  private int dVal;
  
  public Armor(int weight, int attack, int defense) {
    super(weight, null);
    aVal = attack;
    dVal = defense;
  }
  
}