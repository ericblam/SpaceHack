public abstract class Weapon extends Item {

    private Ammo magazine;
    private Upgrade upgrade;
    // per shot
    private int aVal;
    private int r;
    private double acc;

    public Weapon(double weight, Ammo a, int attack, int range, double accuracy) {
        super(weight, null);
        magazine = a;
        aVal = attack + a.attackBonus();
        r = range;
        upgrade = null;
        acc = accuracy;
    }

    public Character fire(int direction) {
        if(getAmmo().getShots() > 0) {
            getAmmo().removeRounds(1);
             return shoot(getHolder().getNode().getDirection(direction), direction, r - 1);
        }
        return null;
    }

    private Character shoot(MapNode m, int direction, int power) {
        if(Math.random() <= acc && m.isPassable()) {
            if (m.getCharacter() != null) {
                // Put something in for defense?
                m.getCharacter().addHealth(-aVal * (1 + (getHolder().getExp() / Character.MAX_LEVEL)));
                return m.getCharacter();
            } else if (power > 0) {
                return shoot(m.getDirection(direction), direction, power - 1);
            }
        }
        return null;  
    }

    // If ammo is correct, will load. Otherwise will just eject magazine.
    public abstract Ammo load(Ammo a);

    public void upgrade(Upgrade u) {
        upgrade = u;
        aVal += u.getBonus();
    }
    
    public Ammo getAmmo() {
        return magazine;
    }

    public int getAttack() {
        return aVal;
    }

    public void setMag(Ammo a) {
        magazine = a;
    }

    public int getRange() {
        return r;
    }
}