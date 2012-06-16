public class Level {

  public static final char WALL_CHAR = '#';
  public static final char FLOOR_CHAR = '.';
  public static final char SPACE_CHAR = ' ';
  public static final char DOOR_CHAR = '=';
  public static final char ELEVATOR_CHAR = 'X';
  public static final char BLAST_DOOR_CHAR = '+';
  public static final char PLAYER_BED_CHAR = '~';
  public static final char CREW_SPAWN_CHAR = 'o';
  
  private MapNode[][] grid;
  
  public Level() {
      grid = null;
  }
  
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
  
  public String toString() {
      String level = "^[[0;0H";
      Unit charOn;
      for(int r = 0; r < grid.length; r++) {
          for(int c = 0; c < grid[r].length; c++) {
              charOn = grid[r][c].getCharacter();
              if(grid[r][c].getCharacter() != null) {
                  level += charOn.toString();
              }
              else {
                  level += grid[r][c].toString();
              }
          }
          level += "\n";
      }
      return level;
  }
  
}