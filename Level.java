public class Level {
  
  MapNode[][] grid;
  
  public Level(MapNode[][] g) {
    grid = g;
  }
  
  public MapNode getNode(int x, int y) {
    return grid[y][x];
  }
  
}