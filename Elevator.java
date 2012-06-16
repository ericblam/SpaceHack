class Elevator extends MapNode {

    public static final int ELEVATOR_DEFENSE = 2;
    public static final int ELEVATOR_TRACTION = 3;
    
    public Elevator(Level g, int x, int y) {
        super(g,x,y, "Elevator", ELEVATOR_DEFENSE, ELEVATOR_TRACTION, true);
        setSymbol(Level.ELEVATOR_CHAR);
    }
    
}
