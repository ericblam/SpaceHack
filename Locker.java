public class Locker extends MapNode {
    
    public static final int LOCKER_DEFENSE = 1;
    public static final int LOCKER_TRACTION = 3;
    
    public Locker(Level g, int x, int y) {
        super(g,x,y, "Locker", LOCKER_DEFENSE,LOCKER_TRACTION,true);
        setSymbol(Level.LOCKER_CHAR);
        /*
        putItemDown(new StandardArmor());
        putItemDown(new VacSuit());
        for(int i = 0; i < 3; i++) {
            putItemDown(new Medpack());
        }
        putItemDown(new Pistol());
        putItemDown(new PistolRound());
        putItemDown(new PistolRound());
        */
    }
    
}
