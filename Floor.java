public class Floor extends MapNode {
  
  public static final int FLOOR_DEFENSE = 1;
  public static final int FLOOR_TRACTION = 3;
  
  public Floor(Level g, int x, int y) {
    super(g, x, y, "Floor", FLOOR_DEFENSE, FLOOR_TRACTION, true);
  }
  
}