class Door extends MapNode {

    public static final int DOOR_DEFENSE = 1;
    public static final int DOOR_TRACTION = 3;
    
    public Door(Level g, int x, int y) {
        super(g,x,y, "Door", DOOR_DEFENSE, DOOR_TRACTION, false);
        setSymbol(Level.DOOR_CHAR);
    }
    
    public void open() {
        setPassability(true);
    }
    
    public void close() {
        setPassability(false);
    }
    
}
