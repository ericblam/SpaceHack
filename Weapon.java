public abstract class Weapon extends Item {

    private Ammo magazine;

    public Weapon(int w, Ammo a) {
	super(w);
	magazine = a;
    }

    public abstract void fire();

    public Ammo load(Ammo a) {
	Ammo oldMag = magazine;
	magazine = a;
	return oldMag;
    }

    public void use() {
	fire();
    }

}