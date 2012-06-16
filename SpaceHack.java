import java.io.*;
import java.util.*;

public class SpaceHack {
  
  private Level[] game;
  private int turns;
  
  private static final int NUM_LEVELS = 5;
  
  public SpaceHack() {
    setGame();
    turns = 0;    
  }
  
  public void setGame() {
    game = new Level[NUM_LEVELS];
    game[3] = readLevel("TwilightDeck3.txt");
  }
  
  public void main(String[] args) {
    
  }
  
  // Reads a level from a text file.
  private Level readLevel(String filename) {
    String line;
    Scanner sc = null;
    try {
      sc = new Scanner(new File(filename));
    }
    catch (Exception e) {
      System.out.println("Can't read " + filename);
      System.exit(0);
    }
    
    int lines = 0;
    int lineLength = 0;
    while(sc.hasNextLine()) {
      lines++;
      lineLength = sc.nextLine().length();
    }
    
    try {
      sc = new Scanner(new File(filename));
    }
    catch (Exception e) {
      System.out.println("Can't read " + filename);
      System.exit(0);
    }
    
    Level level = new Level(null);
    
    // This line is iffy, but will work provided that the file has something in it.
    MapNode[][] grid = new MapNode[lines][lineLength];
    int y = 0;
    char curr;
    while(sc.hasNextLine()) {
      line = sc.nextLine();
      for(int x = 0; x < line.length(); x++) {
        curr = line.charAt(x);
        if(curr == Level.WALL_CHAR)
          grid[x][y] = new Wall(level,x,y);
        else if(curr == Level.FLOOR_CHAR)
          grid[x][y] = new Floor(level,x,y);
        else if(curr == Level.DOOR_CHAR)
          grid[x][y] = new Door(level,x,y);
        else if(curr == Level.ELEVATOR_CHAR)
          grid[x][y] = new Elevator(level,x,y);
        else if(curr == Level.BLAST_DOOR_CHAR)
          grid[x][y] = new BlastDoor(level,x,y);
        else if(curr == Level.PLAYER_BED_CHAR)
          grid[x][y] = new PlayerBed(level,x,y);
        else
          grid[x][y] = new Space(level,x,y);
      }
      y++;
    }
    level.setGrid(grid);
    return level;
  }
  
}