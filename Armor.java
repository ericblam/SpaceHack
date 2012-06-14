public abstract class Armor extends Item {
  
  private int aVal;
  private int dVal;
  
  public Armor(int weight, int attack, int defense) {
    super(weight);
    aVal = attack;
    dVal = defense;
  }
  
}