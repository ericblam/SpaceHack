public class Battery extends Ammo {
    
    public static final double BATTERY_WEIGHT = 2.0;
    public static final int BATTERY_MAG_SIZE = 10;

    public Battery() {
        super(BATTERY_WEIGHT, BATTERY_MAG_SIZE, Ammo.STANDARD_AMMO_BONUS, BATTERY_MAG_SIZE);
    }

    public Battery(int shots, int bonus) {
        super(BATTERY_WEIGHT, shots, bonus, BATTERY_MAG_SIZE);
    }
    
}
