class PlayerBed extends MapNode {

    public static final int PLAYER_BED_DEFENSE = 1;
    public static final int PLAYER_BED_TRACTION = 3;
    
    public PlayerBed(Level g, int x, int y) {
        super(g,x,y, "PlayerBed", PLAYER_BED_DEFENSE, PLAYER_BED_TRACTION, true);
    }
    
}
