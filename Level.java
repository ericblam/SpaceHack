public class Level {

  public static final char WALL_CHAR = '#';
  public static final char FLOOR_CHAR = '-';
  public static final char SPACE_CHAR = '.';
  public static final char DOOR_CHAR = '=';
  public static final char ELEVATOR_CHAR = 'X';
  
  private MapNode[][] grid;
  
  public Level(MapNode[][] g) {
    grid = g;
  }
  
  public MapNode getNode(int x, int y) {
    return grid[y][x];
  }
  
  public MapNode[][] getGrid() {
    return grid;
  }
  
  public void setGrid(MapNode[][] n) {
    grid = n;
  }
  
}