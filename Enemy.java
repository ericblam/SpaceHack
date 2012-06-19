public abstract class Enemy extends Unit {
    
    public Enemy(MapNode node, double health, double maxHealth, double attack, double strength, double level, String name) {
        super(node,health,maxHealth,attack,strength,level,false,name);
        setSymbol('E');
    }
    
    // Is used to provide a direction to move.
    public abstract int findLocation();
    
    public void search() {
        int direction = findLocation();
        if(getNode().getDirection(direction) instanceof Door) {
            ((Door)(getNode().getDirection(direction))).open();
        }
        move(direction);
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
            int maxY = (int)(Math.round(Math.sqrt(Math.pow(weaponRange, 2) + Math.pow(currX - x,2))));
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
}
