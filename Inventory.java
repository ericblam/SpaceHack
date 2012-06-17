public class Inventory {
  
  public static final int DEFAULT_BAG_SIZE = 15;
  
  private Item[] bag;
  private double weight;
  
  public Inventory(int size) {
    bag = new Item[size];
    weight = 0;
  }
  
  public boolean add(Item item) {
    for(int i = 0; i < bag.length; i++) {
      if(bag[i] == null) {
        bag[i] = item;
        weight += item.weight();
        return true;
      }
    }
    return false;
  }
  
  public Item get(int i) {
      return bag[i];
  }
  
  public int bagSize() {
      return bag.length;
  }
  
  public double weight() {
      return weight;
  }
  
  public String toString() {
      String inv = "";
      for(int i = 0; i < bag.length; i++) {
          if(bag[i] != null) {
              inv += "(" + i + "): " + bag[i].getClass().getName();
          }
      }
      return inv;
  }
  
}