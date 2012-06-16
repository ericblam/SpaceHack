class BlastDoor extends MapNode {

    public static final int BLAST_DOOR_DEFENSE = 1;
    public static final int BLAST_DOOR_TRACTION = 3;
    
    public BlastDoor(Level g, int x, int y) {
        super(g,x,y, "BlastDoor", BLAST_DOOR_DEFENSE, BLAST_DOOR_TRACTION, false);
    }
    
    public void open() {
        setPassability(true);
    }
    
    public void close() {
        setPassability(false);
    }
    
}
