public class AssaultRifle extends Weapon {
    
    public static final int AR_WEIGHT = 15;
    public static final int AR_ATTACK = 3;
    
    public AssaultRifle() {
        super(AR_WEIGHT, new ARRound(), AR_ATTACK);
    }
    
    public void fire(){
        
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
