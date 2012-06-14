import java.io.*;
import java.util.*;

public class SpaceHack {
  
  private Level[] game;
  
  public SpaceHack() {
    
  }
  
  // Reader code from MazeSolver
  public void setGame() {
    
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
        else
          grid[x][y] = new Space(level,x,y);
      }
      y++;
    }
    level.setGrid(grid);
    return level;
  }
  
}