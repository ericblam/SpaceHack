public class Grunt extends Enemy {
    
  public static final int GRUNT_INVENTORY_SIZE = 5;
  public static final double DEFAULT_GRUNT_HEALTH = 20.0;
  public static final double DEFAULT_GRUNT_ATTACK = 1.5;
  public static final double DEFAULT_GRUNT_STRENGTH = 50.0;
    
  public Grunt(MapNode m) {
      super(m,DEFAULT_GRUNT_HEALTH,DEFAULT_GRUNT_HEALTH,DEFAULT_GRUNT_ATTACK,DEFAULT_GRUNT_STRENGTH,2.0,"Grunt");
  }
  
  public int findLocation(MapNode target) {
      return 0;
  }
  
}
