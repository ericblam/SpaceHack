public class AssaultRifle extends Weapon {
    
    public static final double AR_WEIGHT = 15.0;
    public static final double AR_ATTACK = 3;
    public static final int AR_RANGE = 4;
    public static final double AR_ACCURACY = 0.75;
    
    public AssaultRifle() {
        super(AR_WEIGHT, new ARRound(), AR_ATTACK, AR_RANGE, AR_ACCURACY);
    }
    
    public Ammo load(Ammo a) {
        Ammo oldMag = getAmmo();
        if(a instanceof ARRound) {
            setMag(a);
        }
        else {
            setMag(null);
        }
        return oldMag;
    }
    
}
