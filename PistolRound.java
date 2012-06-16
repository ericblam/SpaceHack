class PistolRound extends Ammo {

    public static final double PISTOLROUND_WEIGHT = 1.5;
    public static final int PISTOLROUND_MAG_SIZE = 7;

    public PistolRound() {
        super(PISTOLROUND_WEIGHT, PISTOLROUND_MAG_SIZE, Ammo.STANDARD_AMMO_BONUS, PISTOLROUND_MAG_SIZE);
    }

    public PistolRound(int shots, int bonus) {
        super(PISTOLROUND_WEIGHT, shots, bonus, PISTOLROUND_MAG_SIZE);
    }
}
