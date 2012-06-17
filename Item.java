public abstract class Item extends BasicThing {

    private double weight;
    private Unit holder;

    public Item(double w, Unit h) {
        super();
        setSymbol('*');
        weight = w;
        holder = h;
    }

    public void pickedUpBy(Unit h) {
        holder = h;
    }
    
    public Unit getHolder() {
        return holder;
    }
    
    public void use(){
    }
    
    public double weight() {
        return weight;
    }
    
    public String inventoryPrint() {
        return getClass().getName();
    }
    
}