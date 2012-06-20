
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Enemy extends Unit {
    
    private static final char MARKED = '0';
    
    public Enemy(MapNode node, double health, double maxHealth, double attack, double strength, double level, String name) {
        super(node,health,maxHealth,attack,strength,level,false,name);
        setSymbol('E');
    }
    
    // Is used to provide a direction to move.
    public abstract int findLocation();
    
    public void search() {
        int direction;
        if(isEnemyInRange()) {
            // direction = findLocation();
            direction = 4;
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
            if(i != 5)
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
