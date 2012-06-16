public abstract class Item {

    private double weight;
    private Character holder;

    public Item(double w, Character h) {
        weight = w;
        holder = h;
    }

    public void pickedUpBy(Character h) {
        holder = h;
    }
    
    public Character getHolder() {
        return holder;
    }
    
    public void use(){
    }
    
    public double weight() {
        return weight;
    }
    
}