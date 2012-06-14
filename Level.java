public class Level {
  
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