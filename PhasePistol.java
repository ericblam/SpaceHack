public class PhasePistol extends Weapon {
    
    public static final double PP_WEIGHT = 8.0;
    public static final double PP_ATTACK = 2.5;
    public static final int PP_RANGE = 5;
    public static final double PP_ACCURACY = 0.9;
    
    public PhasePistol() {
        super(PP_WEIGHT, new Battery(), PP_ATTACK, PP_RANGE, PP_ACCURACY);
    }
    
    public Ammo load(Ammo a) {
        Ammo oldMag = getAmmo();
        if(a instanceof Battery) {
            setMag(a);
        }
        else {
            setMag(null);
        }
        return oldMag;
    }
    
}
