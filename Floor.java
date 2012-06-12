public class Floor extends MapNode {

    public static final int FLOOR_DEFENSE = 1;
    public static final int FLOOR_TRACTION = 3;
    
    public Floor() {
	super("Floor", FLOOR_DEFENSE, FLOOR_TRACTION);
    }

}