public class Wall extends MapNode {
  
  public static final int WALL_DEFENSE = 0;
  public static final int WALL_TRACTION = 0;
  
  public Wall(Level g, int x, int y) {
    super(g, x, y, "Wall", WALL_DEFENSE, WALL_TRACTION, false);
  }
  
}