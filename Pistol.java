public class Pistol extends Weapon {
    
    public static final double PISTOL_WEIGHT = 7.0;
    public static final int PISTOL_ATTACK = 2;
    public static final int PISTOL_RANGE = 6;
    public static final double PISTOL_ACCURACY = 0.95;
    
    public Pistol() {
        super(PISTOL_WEIGHT, new PistolRound(), PISTOL_ATTACK, PISTOL_RANGE, PISTOL_ACCURACY);
    }
    
    public Ammo load(Ammo a) {
        Ammo oldMag = getAmmo();
        if(a instanceof PistolRound) {
            setMag(a);
        }
        else {
            setMag(null);
        }
        return oldMag;
    }
    
}
