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
      if(i < bag.length)
        return bag[i];
      else
        return null;
  }
  
  public Item remove(int i) {
      Item oldItem = bag[i];
      bag[i] = null;
      return oldItem;
  }
  
  public int bagSize() {
      return bag.length;
  }
  
  public double weight() {
      return weight;
  }
  
  public String toString() {
      String inv = "";
      int counter = 0;
      for(int i = 0; i < bag.length; i++) {
          if(bag[i] != null) {
              inv += "(" + i + "):" + bag[i].inventoryPrint() + " ";
              counter++;
          }
          if(counter != 0 && counter % 5 == 0)
              inv += "\n";
      }
      return inv;
  }
  
}