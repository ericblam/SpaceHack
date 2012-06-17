public abstract class Armor extends Item {
  
  private double aVal;
  private double dVal;
  
  public Armor(double weight, double attack, double defense) {
    super(weight, null);
    aVal = attack;
    dVal = defense;
  }
  
  public double getAttack() {
      return aVal;
  }
  
  public double getDefense() {
      return dVal;
  }
  
  public String inventoryPrint() {
      return super.inventoryPrint() + "(" + (int)aVal + "/" + (int)dVal + ")";
  }
  
}