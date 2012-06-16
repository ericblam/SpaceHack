public class SpaceHackRunner {
    
  public static void main(String[] args) {
      System.out.println("^[[2J");
      SpaceHack SHgame = new SpaceHack();
      SHgame.printCurrLevel();
      /*boolean continuing = true;
      while(continuing) {
          SHgame.doStuff();
          continuing = SHgame.prompt();
      }*/
  }
    
}
