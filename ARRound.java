class ARRound extends Ammo {

    public static final double ARROUND_WEIGHT = 2.0;
    public static final int ARROUND_MAG_SIZE = 15;

    public ARRound() {
        super(ARROUND_WEIGHT, ARROUND_MAG_SIZE, Ammo.STANDARD_AMMO_BONUS, ARROUND_MAG_SIZE);
    }

    public ARRound(int shots, int bonus) {
        super(ARROUND_WEIGHT, shots, bonus, ARROUND_MAG_SIZE);
    }
}
