class ARRound extends Ammo {

    public static final int ARROUND_WEIGHT = 2;
    public static final int ARROUND_MAG_SIZE = 30;
    
    public ARRound() {
        super(ARROUND_WEIGHT, ARROUND_MAG_SIZE, ARROUND_MAG_SIZE);
    }
    
    public ARRound(int shots) {
        super(ARROUND_WEIGHT, shots, ARROUND_MAG_SIZE);
    }
    
}
