public class Medpack extends Item {
    
    public static final int MEDPACK_WEIGHT = 5;
    public static final int DEFAULT_RATING = 40;
    
    private int rating;
    
    public Medpack() {
        super(MEDPACK_WEIGHT, null);
        rating = DEFAULT_RATING;
    }
    
    public Medpack(int health) {
        this();
        rating = health;
    }
    
    public void use() {
        getHolder().addHealth(rating);
    }
    
}
