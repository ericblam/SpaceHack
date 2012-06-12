public abstract class Armor extends Item {

    private int aVal;
    private int dVal;

    public Armor(int w, int a, int d) {
	super(w);
	aVal = a;
	dVal = d;
    }

}