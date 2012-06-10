public class Wall extends MapNode {
  
  public static final int WALL_DEFENSE = 0;
  public static final int WALL_TRACTION = 0;
  
  public Wall() {
    super("Wall", WALL_DEFENSE, WALL_TRACTION);
  }
  
}