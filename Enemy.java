import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class Enemy extends Unit {
    
    private static final char MARKED = '0';
    private static final char MARKED_BAD = 'z';
    public static final char ENEMY_CHAR = 'E';
    
    public Enemy(MapNode node, double health, double maxHealth, double attack, double strength, double level, String name) {
        super(node,health,maxHealth,attack,strength,level,false,name);
        setSymbol(ENEMY_CHAR);
    }
    
    public void search() {
        int direction;
        if(isEnemyInRange()) {
            direction = findLocation();
            if(getNode().getDirection(direction) instanceof Door) {
                ((Door)(getNode().getDirection(direction))).open();
            }
        }
        else {
            direction = randomDirection();
        }
        move(direction);
    }
    
    public int randomDirection() {
        ArrayList<Integer> dir = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++) {
            if(i != MapNode.THIS_SPACE)
                dir.add(i);
        }
        int direction = dir.get((int)(Math.random() * dir.size()));
        MapNode nextNode = getNode().getDirection(direction);
        
        while((!(nextNode instanceof Door) || !nextNode.isPassable()) && dir.size() > 1) {
            for(int i = 0; i < dir.size(); i++) {
                if(dir.get(i) == direction) {
                    dir.remove(i);
                    break;
                }
            }
            direction = dir.get((int)(Math.random() * dir.size()));
            nextNode = getNode().getDirection(direction);
        }
        
        if(nextNode instanceof Door) {
            ((Door)nextNode).open();
        }
        return direction;
    }
    
    public int findLocation() {
        int currX = getNode().getX();
        int currY = getNode().getY();
        char[][] map = getNode().getGrid().getSH().fileToCharArray(SpaceHack.DECK_3plain);
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();           
        map = findNonFriendly(map,currX,currY);
        int direction = goBack(map,currX,currY);
        return 0;
    }
    
    private char[][] findNonFriendly(char[][] grid, int x, int y) {
        int currX = getNode().getX();
        int currY = getNode().getY();
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();
        char[][] ans = null;
        
        grid[y][x] = MARKED;
        
        if(getNode().getGrid().getGrid()[y][x].getCharacter() != null &&
                getNode().getGrid().getGrid()[y][x].getCharacter().isFriendly())
            return grid;
        String print = "^[[0;0H";
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                print += "" + grid[r][c];
            }
            print += "\n";
        }
        System.out.println(print);
        
        int nextSpace = nextSpace(grid,x,y);
        System.out.println(nextSpace);
        if(nextSpace > -1) {
            if(nextSpace == MapNode.RIGHT)
                ans = findNonFriendly(grid,x+1,y);
            else if(nextSpace == MapNode.LEFT)
                ans = findNonFriendly(grid,x-1,y);
            else if(nextSpace == MapNode.UP)
                ans = findNonFriendly(grid,x,y-1);
            else if(nextSpace == MapNode.DOWN)
                ans = findNonFriendly(grid,x,y+1);
        }
        else {
            int previousSpace = goBack(grid,x,y);
            if(previousSpace == MapNode.RIGHT)
                ans = findNonFriendly(grid,x+1,y);
            else if(previousSpace == MapNode.LEFT)
                ans = findNonFriendly(grid,x-1,y);
            else if(previousSpace == MapNode.UP)
                ans = findNonFriendly(grid,x,y-1);
            else if(previousSpace == MapNode.DOWN)
                ans = findNonFriendly(grid,x,y+1);
        }
        
        return ans;
    }
    
    private int nextSpace(char[][] map, int x, int y) {
        int ans = -1;
        int currX = getNode().getX();
        int currY = getNode().getY();
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();
        
        if(Math.sqrt(Math.pow(currX - x, 2) + Math.pow(currY - y, 2)) < weaponRange) {
            if(map[y][x+1] == Level.BLAST_DOOR_CHAR && map[y][x+1] != Level.WALL_CHAR && 
                    map[y][x+1] != MARKED_BAD && map[y][x+1] != MARKED &&
                    getNode().getGrid().getGrid()[y][x+1].getCharacter() == null)
                ans = MapNode.RIGHT;

            else if(map[y][x-1] != Level.BLAST_DOOR_CHAR && map[y][x-1] != Level.WALL_CHAR &&  
                    map[y][x-1] != MARKED_BAD &&  map[y][x-1] != MARKED &&
                    getNode().getGrid().getGrid()[y][x-1].getCharacter() == null)
                ans = MapNode.LEFT;

            else if(map[y-1][x] != Level.BLAST_DOOR_CHAR && map[y-1][x] != Level.WALL_CHAR &&  
                    map[y-1][x] != MARKED_BAD &&  map[y-1][x] != MARKED &&
                    getNode().getGrid().getGrid()[y-1][x].getCharacter() == null)
                ans = MapNode.UP;

            else if(map[y+1][x] != Level.BLAST_DOOR_CHAR && map[y+1][x] != Level.WALL_CHAR &&  
                    map[y+1][x] != MARKED_BAD &&  map[y+1][x] != MARKED &&
                    getNode().getGrid().getGrid()[y+1][x].getCharacter() == null)
                ans = MapNode.DOWN;
        }
        return ans;
  }
    
    private int goBack(char[][] grid, int x, int y) {
        int direction = MapNode.THIS_SPACE;
        grid[y][x] = MARKED_BAD;
        if(grid[y+1][x] == MARKED)
            direction = MapNode.DOWN;
        else if(grid[y-1][x] == MARKED)
            direction = MapNode.UP;
        else if(grid[y][x-1] == MARKED)
            direction = MapNode.LEFT;
        else if(grid[y][x+1] == MARKED)
            direction = MapNode.RIGHT;
        return direction;
    }
    
    public boolean isEnemyInRange() {
        int currX = getNode().getX();
        int currY = getNode().getY();
        MapNode[][] grid = getNode().getGrid().getGrid();
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();
        for(int x = currX - weaponRange; x <= currX + weaponRange; x++) {
            if(x < 0)
                x = 0;
            if(x >= grid[0].length)
                x = grid[0].length - 1;
            int maxY = (int)(Math.round(Math.sqrt(Math.pow(weaponRange, 2) - Math.pow(currX - x,2))));
            for(int y = currY + maxY; y >= currY; y--) {
                if(y < 0)
                    y = 0;
                if(y >= grid.length)
                    y = grid.length - 1;
                if(grid[y][x].getCharacter() != null && grid[y][x].getCharacter().isFriendly())
                    return true;
            }
            for(int y = currY - maxY; y <= currY; y++) {
                if(y < 0)
                    y = 0;
                if(y >= grid.length)
                    y = grid.length - 1;
                if(grid[y][x].getCharacter() != null && grid[y][x].getCharacter().isFriendly())
                    return true;
            }
        }
        return false;
    }
    
    public boolean isEnemyInRange2(char[][] grid) {
        int currX = getNode().getX();
        int currY = getNode().getY();
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();
        
        Queue<MapNode> path = new LinkedList<MapNode>();
        path.add(getNode());
        MapNode m;
        while (!path.isEmpty()) {
            m = path.remove();
            int x = m.getX();
            int y = m.getY();
            if(m.getCharacter() != null && m.getCharacter().isFriendly()) {
                return true;
            }
            
            grid[y][x] = MARKED;
            
            // For each node checked, make sure it hasn't been visited,
            // has no unit on it, and is "in range."
            if(grid[y][x+1] != MARKED &&
                    (getNode().getDirection(MapNode.RIGHT).getCharacter() == null &&
                    Math.sqrt(Math.pow(currX - x + 1, 2) + Math.pow(currY - y, 2)) <= weaponRange))
                path.add(getNode().getDirection(MapNode.RIGHT));
            if(grid[y][x-1] != MARKED &&
                    (getNode().getDirection(MapNode.LEFT).getCharacter() == null &&
                    Math.sqrt(Math.pow(currX - x - 1, 2) + Math.pow(currY - y, 2)) <= weaponRange))
                path.add(getNode().getDirection(MapNode.LEFT));
            if(grid[y-1][x] != MARKED &&
                    (getNode().getDirection(MapNode.UP).getCharacter() == null &&
                    Math.sqrt(Math.pow(currX - x, 2) + Math.pow(currY - y - 1, 2)) <= weaponRange))
                path.add(getNode().getDirection(MapNode.UP));
            if(grid[y+1][x] != MARKED &&
                    (getNode().getDirection(MapNode.DOWN).getCharacter() == null &&
                    Math.sqrt(Math.pow(currX - x, 2) + Math.pow(currY - y + 1, 2)) <= weaponRange))
                path.add(getNode().getDirection(MapNode.DOWN));
        }
        return false;
    }
}
