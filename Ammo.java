public abstract class Ammo extends Item {

    public static final int STANDARD_AMMO_BONUS = 0;

    private int shots;
    private int maxShots;
    private int attackBonus;

    public Ammo(double w, int s, int aB, int mShots) {
        super(w, null);
        shots = s;
        maxShots = mShots;
        attackBonus = aB;
    }

    public int getShots() {
        return shots;
    }
    
    public void removeRounds(int rounds) {
        shots -= rounds;
        if(shots < 0)
            shots = 0;
    }

    public int attackBonus() {
        return attackBonus;
    }
    
    public String inventoryPrint() {
        return super.inventoryPrint() + "(" + shots + "/" + maxShots + ")";
    }
    
}