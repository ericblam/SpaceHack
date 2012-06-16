public abstract class Ammo extends Item {

    public static final int STANDARD_AMMO_BONUS = 0;

    private int shots;
    private int maxShots;
    private int attackBonus;

    public Ammo(int w, int s, int aB, int mShots) {
        super(w, null);
        shots = s;
        maxShots = mShots;
        attackBonus = aB;
    }

    public int getShots() {
        return shots;
    }

    public int attackBonus() {
        return attackBonus;
    }
}