public abstract class Enemy extends Unit {
    
    public Enemy(MapNode node, double health, double maxHealth, double attack, double strength, double level, String name) {
        super(node,health,maxHealth,attack,strength,level,false,name);
    }
    
    // Is used to provide a direction to move.
    public abstract int findLocation(MapNode target);
    
}
