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
}
