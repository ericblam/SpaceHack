import java.util.LinkedList;
import java.util.Queue;

public class Grunt extends Enemy {
    
  public static final int GRUNT_INVENTORY_SIZE = 5;
  public static final double DEFAULT_GRUNT_HEALTH = 20.0;
  public static final double DEFAULT_GRUNT_ATTACK = 1.5;
  public static final double DEFAULT_GRUNT_STRENGTH = 50.0;
    
  public Grunt(MapNode m) {
      super(m,DEFAULT_GRUNT_HEALTH,DEFAULT_GRUNT_HEALTH,DEFAULT_GRUNT_ATTACK,DEFAULT_GRUNT_STRENGTH,2.0,"Grunt");
      pickUp(new PhasePistol());
      equip((Weapon)(getInventory().get(0)));
      for(int i = 0; i < 2; i++) {
        pickUp(new Battery());
      }
  }
  
  /*public int findLocation() {
      char[][] map = getNode().getGrid().getSH().fileToCharArray(SpaceHack.DECK_3);
      
      Queue<MapNode> path = new LinkedList<MapNode>();
      path.add( getNode() );
      
      while ( !path.isEmpty() ) {
          
        MapNode m = path.remove();
        int x = m.getX();
        int y = m.getY();
        
        if(m.getCharacter().isFriendly()) {
            break;
        }
        
        maze[x][y] = PATH;

        if ( maze[x][ y + 1 ] == '#' ||
            maze[x][ y + 1 ] == '$' )
            path.add( new Location( x, y + 1 ) );

        if ( maze[x][ y - 1 ] == '#' ||
            maze[x][ y - 1 ] == '$' )
            path.add( new Location( x, y - 1 ) );

        if ( maze[ x + 1 ][ y ] == '#' ||
            maze[ x + 1 ][ y ] == '$' )
            path.add( new Location( x + 1, y ) );

        if ( maze[ x - 1 ][ y ] == '#' ||
            maze[ x - 1 ][ y ] == '$' )
            path.add( new Location( x - 1, y ) );
          
      }
      
      return 0;
  }
  
  public int findLocation() {
      // Copies map for reference.
      char[][] map = getNode().getGrid().getSH().fileToCharArray(SpaceHack.DECK_3);
      int startX = getNode().getX();
      int startY = getNode().getY();
      map[startY][startX] = '0';
      findLocation(map, startX, startY);
      
      int nextSpace = 5;
      if(map[startY][startX+1] == '0')
          nextSpace = MapNode.RIGHT;
      else if(map[startY][startX-1] == '0')
          nextSpace = MapNode.LEFT;
      else if(map[startY-1][startX] == '0')
          nextSpace = MapNode.UP;
      else if(map[startY+1][startX] == '0')
          nextSpace = MapNode.DOWN;
      
      return nextSpace;
  }
  
  public void findLocation(char[][] map, int x, int y) {
    if(getNode().getGrid().getGrid()[y][x].getCharacter() != getNode().getGrid().getSH().getPlayer()) {
        map[y][x] = '0';
        int nextSpace = nextSpace(map,x,y);
        if(nextSpace > -1) {
            if(nextSpace == MapNode.RIGHT)
                findLocation(map,x+1,y);
            else if(nextSpace == MapNode.LEFT)
                findLocation(map,x-1,y);
            else if(nextSpace == MapNode.UP)
                findLocation(map,x,y-1);
            else if(nextSpace == MapNode.DOWN)
                findLocation(map,x,y+1);
        }
        else {
            int previousSpace = previousSpace(map,x,y);
            if(previousSpace == MapNode.RIGHT)
                findLocation(map,x+1,y);
            else if(previousSpace == MapNode.LEFT)
                findLocation(map,x-1,y);
            else if(previousSpace == MapNode.UP)
                findLocation(map,x,y-1);
            else if(previousSpace == MapNode.DOWN)
                findLocation(map,x,y+1);
        }
    }
    
  }
  
  // Checks if the node is a blastdoor, wall, or unit.
  private int nextSpace(char[][] map, int x, int y) {
      int ans = -1;
      if(map[y][x+1] != Level.BLAST_DOOR_CHAR && map[y][x+1] != Level.WALL_CHAR && 
              getNode().getGrid().getGrid()[y][x+1].getCharacter() == null)
          ans = MapNode.RIGHT;
      else if(map[y][x-1] != Level.BLAST_DOOR_CHAR && map[y][x-1] != Level.WALL_CHAR && 
              getNode().getGrid().getGrid()[y][x-1].getCharacter() == null)
          ans = MapNode.LEFT;
      else if(map[y-1][x] != Level.BLAST_DOOR_CHAR && map[y-1][x] != Level.WALL_CHAR && 
              getNode().getGrid().getGrid()[y][x-1].getCharacter() == null)
          ans = MapNode.UP;
      else if(map[y+1][x] != Level.BLAST_DOOR_CHAR && map[y+1][x] != Level.WALL_CHAR && 
              getNode().getGrid().getGrid()[y][x-1].getCharacter() == null)
          ans = MapNode.DOWN;
      return ans;
  }
  
  private int previousSpace(char[][] map, int x, int y) {
      int ans = -1;
      map[y][x] = '#';
      if(map[y+1][x] == '0')
          ans = MapNode.DOWN;
      else if(map[y-1][x] == '0')
          ans = MapNode.UP;
      else if(map[y][x-1] == '0')
          ans = MapNode.LEFT;
      else if(map[y][x+1] == '0')
          ans = MapNode.RIGHT;
      return ans;
  }
  */
  
}
