public class VacSuit extends Armor {

  public static final double VACSUIT_WEIGHT = 50.0;
  public static final int VACSUIT_ATTACK = 1;
  public static final int VACSUIT_DEFENSE = 3;
  
  public VacSuit(double weight, int attack, int defense) {
    super(VACSUIT_WEIGHT, VACSUIT_ATTACK, VACSUIT_DEFENSE);
  }
  
  public void use() {
  }
  
}