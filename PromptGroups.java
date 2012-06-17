public class PromptGroups {
    
  public static boolean isAskingForHelp(String reading) {
      if(reading.equals("help")) {
          System.out.println("\nCommands:");
          System.out.println("help");
          System.out.println("move");
          System.out.println("open");
          System.out.println("close");
          System.out.println("shoot");
          System.out.println("pickup");
          System.out.println("weild");
          System.out.println("puton");
          System.out.println("quit");
          System.out.println("use\n");
          System.out.println("Directions: Use 1-9 numberpad as directions, can be used instead of \"move\" command. asdw can also be used.");
          System.out.println("For quick movement, enter \"open\" (or similar commands) and direction.");
          System.out.println("Ex: \"open 2\" Opens the door down. This doesn't work for blast doors, though.");
          System.out.println("\nReady to continue? (Press any key)");
          Keyboard.readWord();
          return true;
      }
      return false;
  }
  
  public static boolean isMoving(Unit player, String reading) {
      if(reading.equals("1"))
          player.move(MapNode.DOWN_LEFT);
      else if(reading.equals("2") || reading.equals("s"))
          player.move(MapNode.DOWN);
      else if(reading.equals("3"))
          player.move(MapNode.DOWN_RIGHT);
      else if(reading.equals("4") || reading.equals("a"))
          player.move(MapNode.LEFT);
      else if(reading.equals("5"))
          player.move(MapNode.THIS_SPACE);
      else if(reading.equals("6") || reading.equals("d"))
          player.move(MapNode.RIGHT);
      else if(reading.equals("7"))
          player.move(MapNode.UP_LEFT);
      else if(reading.equals("8") || reading.equals("w"))
          player.move(MapNode.UP);
      else if(reading.equals("9"))
          player.move(MapNode.UP_RIGHT);
      else if(reading.equals("move")) {
          System.out.println("In which direction?");
          player.move(directionPrompt());
      }
      else {
          return false;
      }
      return true;
  }
  
  public static boolean isOpeningDoor(Unit player, String reading) {
      if(reading.equals("open") || reading.equals("o")) {
          System.out.println("In which direction?");
          MapNode nextNode = player.getNode().getDirection(directionPrompt());
          if(nextNode instanceof Door) {
              ((Door)(nextNode)).open();
          }
          else if(nextNode instanceof BlastDoor &&player.getArmor() instanceof VacSuit) {
              ((BlastDoor)(nextNode)).open();
          }
      }
      else if((reading.equals("open1") || reading.equals("o1") ) && player.getNode().getDirection(MapNode.DOWN_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN_LEFT))).open();
      }
      else if((reading.equals("open2") || reading.equals("opens") || reading.equals("o2") || reading.equals("os"))
              && player.getNode().getDirection(MapNode.DOWN) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN))).open();
      }
      else if((reading.equals("open3") || reading.equals("o3") ) && player.getNode().getDirection(MapNode.DOWN_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN_RIGHT))).open();
      }
      else if((reading.equals("open4") || reading.equals("opena") || reading.equals("o4") || reading.equals("oa"))
              && player.getNode().getDirection(MapNode.LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.LEFT))).open();
      }
      else if((reading.equals("open6") || reading.equals("opend") || reading.equals("o6") || reading.equals("od"))
              && player.getNode().getDirection(MapNode.RIGHT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.RIGHT))).open();
      }
      else if((reading.equals("open7") || reading.equals("o7") ) && player.getNode().getDirection(MapNode.UP_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP_LEFT))).open();
      }
      else if((reading.equals("open8") || reading.equals("openw") || reading.equals("o8") || reading.equals("ow"))
              && player.getNode().getDirection(MapNode.UP) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP))).open();
      }
      else if((reading.equals("open9") || reading.equals("o9") ) && player.getNode().getDirection(MapNode.UP_RIGHT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP_RIGHT))).open();
      }
      else
          return false;
      return true;
  }
  
  public static boolean isClosingDoor(Unit player, String reading) {
      if(reading.equals("close") || reading.equals("c")) {
          System.out.println("In which direction?");
          MapNode nextNode = player.getNode().getDirection(directionPrompt());
          if(nextNode instanceof Door) {
              ((Door)(nextNode)).close();
          }
          else if(nextNode instanceof BlastDoor &&player.getArmor() instanceof VacSuit) {
              ((BlastDoor)(nextNode)).close();
          }
      }
      else if((reading.equals("close1") || reading.equals("c1") ) && player.getNode().getDirection(MapNode.DOWN_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN_LEFT))).close();
      }
      else if((reading.equals("close2") || reading.equals("closes") || reading.equals("c2") || reading.equals("cs"))
              && player.getNode().getDirection(MapNode.DOWN) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN))).close();
      }
      else if((reading.equals("close3") || reading.equals("c3") ) && player.getNode().getDirection(MapNode.DOWN_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.DOWN_RIGHT))).close();
      }
      else if((reading.equals("close4") || reading.equals("closea") || reading.equals("c4") || reading.equals("ca"))
              && player.getNode().getDirection(MapNode.LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.LEFT))).close();
      }
      else if((reading.equals("close6") || reading.equals("closed") || reading.equals("c6") || reading.equals("cd"))
              && player.getNode().getDirection(MapNode.RIGHT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.RIGHT))).close();
      }
      else if((reading.equals("close7") || reading.equals("c7") ) && player.getNode().getDirection(MapNode.UP_LEFT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP_LEFT))).close();
      }
      else if((reading.equals("close8") || reading.equals("closew") || reading.equals("c8") || reading.equals("cw"))
              && player.getNode().getDirection(MapNode.UP) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP))).close();
      }
      else if((reading.equals("close9") || reading.equals("c9") ) && player.getNode().getDirection(MapNode.UP_RIGHT) instanceof Door) {
          ((Door)(player.getNode().getDirection(MapNode.UP_RIGHT))).close();
      }
      else
          return false;
      return true;
  }
  
  public static boolean clarificationPrompt() {
      System.out.println("Are you sure?");
      boolean ans = false;
      String reading = Keyboard.readWord();
      if(reading.equals("yes") || reading.equals("y") || reading.equals("Y") || reading.equals("Yes"))
        ans = true;
      return ans;
  }
  
  public static int directionPrompt() {
      String reading = Keyboard.readWord();
      if(reading.equals("1"))
          return MapNode.DOWN_LEFT;
      if(reading.equals("2") || reading.equals("s"))
          return MapNode.DOWN;
      if(reading.equals("3"))
          return MapNode.DOWN_RIGHT;
      if(reading.equals("4") || reading.equals("a"))
          return MapNode.LEFT;
      if(reading.equals("5"))
          return MapNode.THIS_SPACE;
      if(reading.equals("6") || reading.equals("d"))
          return MapNode.RIGHT;
      if(reading.equals("7"))
          return MapNode.UP_LEFT;
      if(reading.equals("8") || reading.equals("w"))
          return MapNode.UP;
      if(reading.equals("9"))
          return MapNode.UP_RIGHT;
      System.out.println("Nevermind.");
      return MapNode.THIS_SPACE;
  }
  
}
