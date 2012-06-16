public class MapNode {
  
  public static final int UP = 8;
  public static final int UP_RIGHT = 9;
  public static final int RIGHT = 6;
  public static final int DOWN_RIGHT = 3;
  public static final int DOWN = 2;
  public static final int DOWN_LEFT = 1;
  public static final int LEFT = 4;
  public static final int UP_LEFT = 7;
  
  // What level this node belongs to
  private Level grid;
  
  // What character is on the node
  private Character characterOn;
  
  private int xC;
  private int yC;
  private String terrain;
  private int defense;
  private double traction;
  private boolean passable;
  
  public MapNode(Level g, int x, int y, String t, int d, double tr, boolean p) {
    grid = g;
    characterOn = null;
    xC = x;
    yC = y;
    terrain = t;
    defense = d;
    traction = tr;
    passable = p;
  }
  
  public void effect(){
  }
  
  public int getX() {
    return xC;
  }
  
  public int getY() {
    return yC;
  }
  
  public MapNode getDirection(int d) {
    MapNode[][] g = grid.getGrid();
    if(d == UP && yC > 0) {
      return g[yC - 1][xC];
    }
    else if(d == UP_RIGHT && yC > 0 && xC < g[0].length) {
      return g[yC - 1][xC + 1];
    }
    else if(d == RIGHT && xC < g[0].length) {
      return g[yC][xC + 1];
    }
    else if(d == DOWN_RIGHT && yC < g.length && xC < g[0].length) {
      return g[yC + 1][xC + 1];
    }
    else if(d == DOWN && yC < g.length) {
      return g[yC + 1][xC];
    }
    else if(d == DOWN_LEFT && xC > 0 && yC < g.length) {
      return g[yC + 1][xC - 1];
    }
    else if(d == LEFT && xC > 0) {
      return g[yC][xC - 1];
    }
    else if(d == UP_LEFT && xC > 0 && yC > 0) {
      return g[yC - 1][xC - 1];
    }
    else
      return null;
  }
  
  public Character getCharacter() {
    return characterOn;
  }
  
  public Level getGrid() {
    return grid;
  }
  
  public boolean isPassable() {
      return passable;
  }
  
  public void setPassability(boolean pass) {
      passable = pass;
  }
  
}