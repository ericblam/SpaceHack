import java.io.*;
import java.util.*;

public class SpaceHack {
  
  private Level[] game;
  private int currLevel;
  private Unit player;
  private ArrayList<Unit> characters;
  private ArrayList<EnemySpawnPoint> spawns;
  private int turns;
  
  private static final int NUM_LEVELS = 5;
  
  // Depending on how stuff works, you may have to add "SpaceHack/" to the beginning of the filenames.
  public static final String DECK_3 = "SpaceHack/TwilightDeck3.dat";
  
  public SpaceHack() {
    game = new Level[NUM_LEVELS];
    turns = 0;
    currLevel = 3;
    characters = new ArrayList<Unit>();
    spawns = new ArrayList<EnemySpawnPoint>();
    
    setGame();
  }
  
  public void setGame() {
    game[3] = readLevel(DECK_3);
    char[][] map = fileToCharArray(DECK_3);
    MapNode playerStart = game[currLevel].getNode(0,0);
    outer:
    for(int r = 0; r < map.length; r++) {
      for(int c = 0; c < map[r].length; c++) {
          if(map[r][c] == Level.PLAYER_BED_CHAR) {
              playerStart = game[currLevel].getNode(c,r);
              break outer;
          }
      }
    }
    String name = namePrompt();
    String ans = classChoosePrompt();
    if(ans.equals("Soldier") || ans.equals("soldier")) {
        player = new Soldier(playerStart, name);
    }
    else if(ans.equals("Scout") || ans.equals("scout")) {
        player = new Soldier(playerStart, name);
    }
    else if(ans.equals("Sniper") || ans.equals("sniper")) {
        player = new Soldier(playerStart, name);
    }
    else if(ans.equals("Medic") || ans.equals("medic")) {
        player = new Soldier(playerStart, name);
    }
    else {
        player = new Soldier(playerStart, name);
    }
    System.out.println("Welcome back from your sleep, " + player.getName());
    System.out.println("Remember that you are a " + player.getClass().getName());
    System.out.println("When you awake, please head on to the bridge.");
  }
  
  // Reads a level from a text file.
  private Level readLevel(String filename) {
    
    Level level = new Level(this);
    char[][] map = fileToCharArray(filename);
    // This line is iffy, but will work provided that the file has something in it.
    MapNode[][] grid = new MapNode[map.length][map[0].length];
    int y = 0;
    char curr;
    for(int r = 0; r < map.length; r++) {
      for(int c = 0; c < map[r].length; c++) {
        curr = map[r][c];
        if(curr == Level.WALL_CHAR) {
          grid[r][c] = new Wall(level,c,r);
        }
        else if(curr == Level.FLOOR_CHAR) {
          grid[r][c] = new Floor(level,c,r);
        }
        else if(curr == Level.DOOR_CHAR) {
          grid[r][c] = new Door(level,c,r);
        }
        else if(curr == Level.ELEVATOR_CHAR) {
          grid[r][c] = new Elevator(level,c,r);
        }
        else if(curr == Level.BLAST_DOOR_CHAR) {
          grid[r][c] = new BlastDoor(level,c,r);
        }
        else if(curr == Level.PLAYER_BED_CHAR) {
          grid[r][c] = new PlayerBed(level,c,r);
        }
        else if(curr == Level.LOCKER_CHAR) {
          grid[r][c] = new Locker(level,c,r);
        }
        else if(curr == Level.ENEMYSPAWN_CHAR) {
          grid[r][c] = new EnemySpawnPoint(level,c,r);
          spawns.add((EnemySpawnPoint)grid[r][c]);
        }
        else if(curr == Level.CREW_SPAWN_CHAR) {
          grid[r][c] = new CrewSpawn(level,c,r);
          characters.add(new Crewman(grid[r][c], Unit.randomName()));
        }
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
      return player.statString() + "\tTurn: " + turns;
  }
  
  public String printPlayerInventory() {
      return player.getInventory().toString();
  }
  
  public String namePrompt() {
      System.out.println("What is your name?");
      return Keyboard.readString();
  }
  
  public String classChoosePrompt() {
      System.out.println("What class would you like to be?");
      System.out.println("(\"Soldier\",\"Scout\",\"Sniper\",\"Medic\",\"Heavy\")?");
      String ans = Keyboard.readWord();
      if(!ans.equals("Soldier") && !ans.equals("Scout") && !ans.equals("Sniper") && !ans.equals("Medic") && !ans.equals("Heavy") &&
              !ans.equals("soldier") && !ans.equals("scout") && !ans.equals("sniper") && !ans.equals("medic") && !ans.equals("heavy")) {
          System.out.println("Sorry, that is not a valid answer.");
          return classChoosePrompt();
      }
      else {
          return ans;
      }
  }
  
  public boolean prompt() {
      String reading = Keyboard.readString();
      if(reading.equals("Quit") || reading.equals("quit")) {
          return false;
      }
      else if(PromptGroups.isMoving(player, reading))
          return true;
      else if(PromptGroups.isOpeningDoor(player, reading))
          return true;
      else if(PromptGroups.isClosingDoor(player, reading))
          return true;
      else if(PromptGroups.isFiring(player, reading))
          return true;
      else if(PromptGroups.isReloading(player, reading))
          return true;
      else if(PromptGroups.isDropping(player, reading))
          return true;
      else if(PromptGroups.isPickingUp(player, reading))
          return true;
      else if(PromptGroups.isWeilding(player, reading))
          return true;
      else if(PromptGroups.isUsing(player, reading))
          return true;
      else if(PromptGroups.isWearing(player, reading))
          return true;
      else if(PromptGroups.isTakingOff(player, reading))
          return true;
      else if(reading.equals("pickupall") || reading.equals("pall")) {
          for(int i = player.getNode().getItems().size() - 1; i >= 0; i--) {
              player.pickUp(player.getNode().removeItem(i));
          }
          return true;
      }
      else if(PromptGroups.isAskingForHelp(reading)) {
          doStuff();
          return prompt();
      }
      else if(reading.equals("l") || reading.equals("look")) {
          System.out.println(player.getNode().printItems());
          System.out.println("Ready to continue? (Press any key)");
          Keyboard.readWord();
          doStuff();
          return prompt();
      }
      else if(reading.equals("i") || reading.equals("inventory")) {
          System.out.println(printPlayerInventory());
          System.out.println("Ready to continue? (Press any key)");
          Keyboard.readWord();
          doStuff();
          return prompt();
      }
      return true;
  }
  
  public void addUnit(Unit u) {
      characters.add(u);
  }
  
  public void doStuff() {
      System.out.println(printCurrLevel());
      System.out.println(printPlayerStats());
  }
  
  public void nextTurn() {
      if(turns == 100) {
          spawns.get(0).spawn();
      }
      for(int i = 0; i < characters.size(); i++) {
          if(characters.get(i) instanceof Enemy) {
              System.out.println(((Enemy)(characters.get(i))).isEnemyInRange());
              System.out.println(characters.get(i).getNode().getX() + "," + characters.get(i).getNode().getY());
              //((Enemy)(characters.get(i))).search();
          }
      }
      turns++;
  }
  
  public Unit getPlayer() {
      return player;
  }
  
}