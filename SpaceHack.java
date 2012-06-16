import java.io.*;
import java.util.*;

public class SpaceHack {
  
  private Level[] game;
  private int currLevel;
  private Character player;
  private ArrayList<Character> characters;
  private int turns;
  
  private static final int NUM_LEVELS = 5;
  
  public SpaceHack() {
    game = new Level[NUM_LEVELS];
    setGame();
    turns = 0;
    currLevel = 3;
    characters = new ArrayList<Character>();
  }
  
  public void setGame() {
    game[3] = readLevel("TwilightDeck3.dat");
  }
  
  // Reads a level from a text file.
  private Level readLevel(String filename) {
    
    Level level = new Level(null);
    char[][] map = fileToCharArray("TwilightDeck3.dat");
    // This line is iffy, but will work provided that the file has something in it.
    MapNode[][] grid = new MapNode[map.length][map[0].length];
    int y = 0;
    char curr;
    for(int r = 0; r < map.length; r++) {
      for(int c = 0; c < map[r].length; c++) {
        curr = map[r][c];
        if(curr == Level.WALL_CHAR)
          grid[r][c] = new Wall(level,c,r);
        else if(curr == Level.FLOOR_CHAR)
          grid[r][c] = new Floor(level,c,r);
        else if(curr == Level.DOOR_CHAR)
          grid[r][c] = new Door(level,c,r);
        else if(curr == Level.ELEVATOR_CHAR)
          grid[r][c] = new Elevator(level,c,r);
        else if(curr == Level.BLAST_DOOR_CHAR)
          grid[r][c] = new BlastDoor(level,c,r);
        else if(curr == Level.PLAYER_BED_CHAR)
          grid[r][c] = new PlayerBed(level,c,r);
        else if(curr == Level.CREW_SPAWN_CHAR)
          grid[r][c] = new CrewSpawn(level,c,r);
        else
          grid[r][c] = new Space(level,c,r);
      }
    }
    level.setGrid(grid);
    return level;
  }
  
  public char[][] fileToCharArray(String filename) {
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
    
    char[][] charArray = new char[lines][lineLength];
    
    int y = 0;
    while(sc.hasNextLine()) {
      line = sc.nextLine();
      for(int x = 0; x < line.length(); x++) {
          charArray[y][x] = line.charAt(x);
      }
      y++;
    }
    return charArray;
  }
  
  public String printCurrLevel() {
      return game[currLevel].toString();
  }
  
  public String printPlayerStats() {
      return "";
  }
  
}