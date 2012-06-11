public class SpaceHack {
  
    public static final char WALL_CHAR = '#';
    public static final char FLOOR_CHAR = '-';
    public static final char SPACE_CHAR = '.';
  
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
	while(sc.hasNextLine()) {
	    lines++;
	}

	try {
	    sc = new Scanner(new File(filename));
	}
	catch (Exception e) {
	    System.out.println("Can't read " + filename);
	    System.exit(0);
	}

	// This line is iffy, but will work provided that the file has something in it.
	MapNode[][] grid = new MapNode[lines][sc.hasNextLine()];
	int y = 0;
	char curr;
	while(sc.hasNextLine()) {
	    line = sc.nextLine();
	    for(int i = 0; i < line.length(); i++) {
		curr = line.charAt(i);
		if(curr == WALL_CHAR)
		    grid[i][y] = new Wall();
		else if(curr == FLOOR_CHAR)
		    grid[i][y] = new Floor();
		else
		    grid[i][y] = new Space();
	    }
	    y++;
	}

	return new Level(grid);
    }

}