public class EnemySpawnPoint extends Floor {
    
    public EnemySpawnPoint(Level g, int x, int y) {
        super(g,x,y);
        setSymbol(Level.FLOOR_CHAR);
    }
    
    public void spawn() {
        if(getCharacter() == null) {
            Enemy grunt = new Grunt(this);
            putCharacter(grunt);
            getGrid().getSH().addUnit(grunt);
        }
    }
    
}