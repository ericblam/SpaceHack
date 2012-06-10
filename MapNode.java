public class MapNode {
  
  private int xC;
  private int yC;
  private String terrain;
  private int defense;
  private double traction;
  
  public MapNode(int x, int y, String t, int d, double tr) {
    xC = x;
    yC = y;
    terrain = t;
    defense = d;
    traction = tr;
  }
  
  public void effect(){
  }

}