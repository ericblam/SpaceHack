import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class Enemy extends Unit {
    
    private static final char MARKED = '0';
    private static final char MARKED_BAD = 'z';
    public static final char ENEMY_CHAR = 'E';
    
    private Stack<Integer> lastDirection;
    private int countEmpty;
    
    public Enemy(MapNode node, double health, double maxHealth, double attack, double strength, double level, String name) {
        super(node,health,maxHealth,attack,strength,level,false,name);
        setSymbol(ENEMY_CHAR);
        lastDirection = new Stack<Integer>();
        countEmpty = 0;
    }
    
    public void search() {
        int direction;
        if(getWeapon().getAmmo().getShots() == 0) {
            Ammo reload = null;
            for(int i = 0; i < getInventory().bagSize(); i++) {
                if(getInventory().get(i) instanceof Ammo && ((Ammo)(getInventory().get(i))).getShots() != 0)
                    reload = (Ammo)(getInventory().get(i));
            }
            if(reload != null)
                reload(reload);
        }
        if(isEnemyInRange()) {
            direction = findLocation();
            if(getNode().getDirection(direction) instanceof Door) {
                ((Door)(getNode().getDirection(direction))).open();
            }
        }
        else {
            direction = randomDirection();
        }
        int firing = firingDirection();
        if(firing != MapNode.THIS_SPACE && getWeapon() != null && getWeapon().getAmmo().getShots() != 0)
            attack(firing);
        else
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
        lastDirection = new Stack<Integer>();
        int currX = getNode().getX();
        int currY = getNode().getY();
        char[][] map = getNode().getGrid().getSH().fileToCharArray(SpaceHack.DECK_3plain);
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();           
        map = findNonFriendly(map,currX,currY);
        int direction = MapNode.THIS_SPACE;
        if(map != null)
            direction = goForward();
        else
            direction = goToward();
        return direction;
    }
    
    private char[][] findNonFriendly(char[][] grid, int x, int y) {
        int currX = getNode().getX();
        int currY = getNode().getY();
        int weaponRange = 1;
        if(getWeapon() != null)
            weaponRange = getWeapon().getRange();
        char[][] ans = null;
        
        grid[y][x] = MARKED;
        // printCharArray(grid);
        //if(lastDirection.empty())
        //    return null;
        if(getNode().getGrid().getGrid()[y][x].getCharacter() != null &&
                getNode().getGrid().getGrid()[y][x].getCharacter().isFriendly())
            return grid;
        int nextSpace = nextSpace(grid,x,y);
        if(nextSpace > -1) {
            if(nextSpace == MapNode.RIGHT) {
                lastDirection.push(MapNode.RIGHT);
                ans = findNonFriendly(grid,x+1,y);
            }
            else if(nextSpace == MapNode.LEFT) {
                lastDirection.push(MapNode.LEFT);
                ans = findNonFriendly(grid,x-1,y);
            }
            else if(nextSpace == MapNode.UP) {
                lastDirection.push(MapNode.UP);
                ans = findNonFriendly(grid,x,y-1);
            }
            else if(nextSpace == MapNode.DOWN) {
                lastDirection.push(MapNode.DOWN);
                ans = findNonFriendly(grid,x,y+1);
            }
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
            if(map[y][x+1] == Level.FLOOR_CHAR)
                ans = MapNode.RIGHT;

            else if(map[y][x-1] == Level.FLOOR_CHAR)
                ans = MapNode.LEFT;

            else if(map[y-1][x] == Level.FLOOR_CHAR)
                ans = MapNode.UP;

            else if(map[y+1][x] == Level.FLOOR_CHAR)
                ans = MapNode.DOWN;
        }
        return ans;
  }
    
    private int goBack(char[][] grid, int x, int y) {
        if(lastDirection.empty()) {
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
        else {
            grid[y][x] = MARKED_BAD;
            int lastDir = lastDirection.pop();
            int direction = 5;
            if(lastDir == MapNode.UP)
                direction = MapNode.DOWN;
            else if(lastDir == MapNode.DOWN)
                direction = MapNode.UP;
            else if(lastDir == MapNode.RIGHT)
                direction = MapNode.LEFT;
            else if(lastDir == MapNode.LEFT)
                direction = MapNode.RIGHT;
            return direction;
        }
    }
    
    private int goForward() {
        int ans = 5;
        while(!lastDirection.empty()) {
            ans = lastDirection.pop();
        }
        return ans;
    }
    
    private int goToward() {
        int direction = MapNode.THIS_SPACE;
        Unit player = getNode().getGrid().getSH().getPlayer();
        int playerx = player.getNode().getX();
        int playery = player.getNode().getY();
        int thisx = getNode().getX();
        int thisy = getNode().getY();
        if(playerx > thisx && playery > thisy)
            direction = MapNode.DOWN_RIGHT;
        else if(playerx == thisx && playery > thisy)
            direction = MapNode.DOWN;
        else if(playerx < thisx && playery > thisy)
            direction = MapNode.DOWN_LEFT;
        else if(playerx < thisx && playery == thisy)
            direction = MapNode.LEFT;
        else if(playerx < thisx && playery < thisy)
            direction = MapNode.UP_LEFT;
        else if(playerx == thisx && playery < thisy)
            direction = MapNode.UP;
        else if(playerx > thisx && playery < thisy)
            direction = MapNode.UP_RIGHT;
        else if(playerx > thisx && playery == thisy)
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
    
    public int firingDirection() {
        int direction = MapNode.THIS_SPACE;
        if(isToDirection(getNode(),MapNode.RIGHT,getWeapon().getRange()))
            direction = MapNode.RIGHT;
        else if(isToDirection(getNode(),MapNode.LEFT,getWeapon().getRange()))
            direction = MapNode.LEFT;
        else if(isToDirection(getNode(),MapNode.UP,getWeapon().getRange()))
            direction = MapNode.UP;
        else if(isToDirection(getNode(),MapNode.DOWN,getWeapon().getRange()))
            direction = MapNode.DOWN;
        return direction;
    }
    
    public boolean isToDirection(MapNode m, int direction, int range) {
        if(m.getCharacter() != null && m.getCharacter().isFriendly() != isFriendly())
            return true;
        else if (range <= 0 || !m.isPassable()) {
            return false;
        }
        else
            return isToDirection(m.getDirection(direction), direction, range - 1);
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
    
    private void printCharArray(char[][] array) {
        String print = "";
        for(int r = 0; r < array.length; r++) {
            for(int c = 0; c < array[r].length; c++) {
                print += "" + array[r][c];
            }
            print += "\n";
        }
        System.out.println(print);
    }
    
}
