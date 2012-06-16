public class Space extends MapNode {
  
  public static final int SPACE_DEFENSE = 1;
  public static final int SPACE_TRACTION = 3;
  
  public Space(Level g, int x, int y) {
    super(g, x, y, "Space", SPACE_DEFENSE, SPACE_TRACTION, true);
    setSymbol(Level.SPACE_CHAR);
  }
  
}