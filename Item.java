public abstract class Item {
  
  private double weight;
  
  public Item(int w) {
    weight = w;
  }
  
  public abstract void use();
  
}