
public abstract class Weapon extends Item {

    private Ammo magazine;
    private Upgrade upgrade;
    // per shot
    private int aVal;
    private int r;

    public Weapon(int w, Ammo a, int attack, int range) {
        super(w, null);
        magazine = a;
        aVal = attack;
        r = range;
        upgrade = null;
    }

    public void fire() {
        shoot(getHolder().getNode().getDirection(getHolder().getHeading()), r - 1);
    }

    private void shoot(MapNode m, int power) {
        if (m.getCharacter() != null) {
            // Put something in for defense?
            m.getCharacter().addHealth(-aVal);
        } else if (power > 0) {
            shoot(m.getDirection(getHolder().getHeading()), power - 1);
        }
    }

    // If ammo is correct, will load. Otherwise will just eject magazine.
    public abstract Ammo load(Ammo a);

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