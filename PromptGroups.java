public class PromptGroups {
    
  public static boolean isAskingForHelp(String reading) {
      if(reading.equals("help")) {
          System.out.println("\nCommands:");
          System.out.println("help"); //
          System.out.println("move"); //
          System.out.println("open"); //
          System.out.println("close"); //
          System.out.println("shoot/fire/attack"); //
          System.out.println("reload"); //
          System.out.println("drop");
          System.out.println("pickup");
          System.out.println("look");
          System.out.println("weild");
          System.out.println("puton");
          System.out.println("quit"); //
          System.out.println("use\n");
          System.out.println("Directions: Use 1-9 numberpad as directions, can be used instead of \"move\" command. asdw can also be used.");
          System.out.println("For extra help, please view README file.");
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
  
  public static boolean isFiring(Unit player, String reading) {
      if(reading.equals("fire") || reading.equals("f") || reading.equals("shoot") ||
              reading.equals("s") || reading.equals("attack") || reading.equals("a")) {
          player.attack(directionPrompt());
      }
      else if(reading.equals("f1") || reading.equals("s1") || reading.equals("a1"))
          player.attack(1);
      else if(reading.equals("f2") || reading.equals("fs") || reading.equals("s2")
              || reading.equals("ss") || reading.equals("a2") || reading.equals("as"))
          player.attack(2);
      else if(reading.equals("f1") || reading.equals("s2") || reading.equals("a2"))
          player.attack(3);
      else if(reading.equals("f4") || reading.equals("fa") || reading.equals("s4")
              || reading.equals("sa") || reading.equals("a4") || reading.equals("aa"))
          player.attack(4);
      else if(reading.equals("f6") || reading.equals("fd") || reading.equals("s6")
              || reading.equals("sd") || reading.equals("a6") || reading.equals("ad"))
          player.attack(6);
      else if(reading.equals("f7") || reading.equals("s7") || reading.equals("a7"))
          player.attack(7);
      else if(reading.equals("f8") || reading.equals("fw") || reading.equals("s8")
              || reading.equals("sw") || reading.equals("a8") || reading.equals("aw"))
          player.attack(8);
      else if(reading.equals("f9") || reading.equals("s9") || reading.equals("a9"))
          player.attack(9);
      else
          return false;
      return true;
  }
  
  public static boolean isDropping(Unit player, String reading) {
      if(reading.equals("d") || reading.equals("drop")) {
          System.out.println("What do you want to drop?");
          Item dropped = inventoryPrompt(player);
          if(dropped == null) {
              System.out.println("You can't do that, silly!");
          }
          else {
              player.drop(dropped);
          }
      }
      else
          return false;
      return true;
  }
  
  public static boolean isReloading(Unit player, String reading) {
      if(reading.equals("r") || reading.equals("reload")) {
          System.out.println("Pick Ammo");
          Item ammo = inventoryPrompt(player);
          if(ammo == null || !(ammo instanceof Ammo)) {
              System.out.println("You can't do that, silly!");
          }
          else {
              player.drop(player.reload((Ammo)ammo));
          }
      }
      else
          return false;
      return true;
  }
  
  public static Item inventoryPrompt(Unit player) {
      System.out.println(player.getInventory());
      return player.getInventory().remove(Keyboard.readInt());
  }
  
}
