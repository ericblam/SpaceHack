public class Inventory {
  
  public static final DEFAULT_BAG_SIZE = 15;
  
  private Item[] bag;
  
  public Inventory(int size) {
    bag = new Item[size];
  }
  
  public boolean add(Item i) {
    for(int i = 0; i < bag.length; i++) {
      if(bag[i] == null) {
        bag[i] = i;
        return true;
      }
    }
    return false;
  }
  
}