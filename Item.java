public abstract class Item {

    double weight;
    
    public Item(int w) {
	weight = w;
    }

    public abstract void use();

}