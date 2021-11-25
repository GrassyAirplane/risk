/* Developed By: Euan Lim
 * Revised Date: Nov 24, 2021 */

import java.util.Scanner; 

/* Displayer */
public class Displayer { 
   
   /* INSTANCE VARIABLE */
   Database db; 
   GameSystem gs;
   
   /* GLOBAL CONSTANT VARIABLES */
   public final static int DISPLAY_GLOBE = 1;
   public final static int N_AMERICA     = 2;
   public final static int S_AMERICA     = 3;
   public final static int EUROPE        = 4;
   public final static int AFRICA        = 5;
   public final static int ASIA          = 6;
   public final static int AUSTRALIA     = 7;
   public final static int SCOREBOARD    = 8;
   public final static int TRADE         = 9;
   public final static int PLACE         = 10;
   public final static int MISSION       = 11;
   public final static int ATTACK        = 12; 
   public final static int MOVE          = 13;
   public final static int END           = 14;
   
   
   public final static int PHASE_PLACEMENT = 20;
   public final static int PHASE_ONE = 21; 
   public final static int PHASE_TWO = 22;
   public final static int PHASE_THREE = 23;
   
   private String[] adjacency = {" 1,3,30", " 0,3,4,2", " 1,4,5,13",
                                 " 0,1,4,7,6", " 1,2,3,5,6,7", " 2,4,7",
                                 " 3,4,7,8", " 4,5,6,8", " 6,7,9",
                                 
                                 " 8,10,11", " 9,11,12,20", " 9,10,12",
                                 " 10,11", 
                                 
                                 " 2,14,15", " 13,15,16,17", " 13,14,16,18",
                                 " 14,15,17,18,19", " 14,16,19,26,31,35", " 15,16,19,20",
                                 " 16,17,18,20,21,35",
                                 
                                 
                                 " 10,18,19,21,22,23", " 19,20,23,35", " 20,23,24",
                                 " 20,21,22,24,25,35", " 22,23,25", " 23,24",
                                 
                                 " 17,27,31,32", " 26,28,29,32,33", " 27,29,30",
                                 " 27,28,30,33", " 0,28,29,33,34", " 17,26,32,35,36",
                                 " 26,27,29,31,33,36,37", " 27,29,30,32,34", " 30,33",
                                 " 17,19,21,23,31,36", " 31,32,35,37", " 32,36,38",
                                 
                                 " 37,39,40", " 38,40,41", " 38,39,41",
                                 " 39,40"};
   
   
   /*=============== CONSTRUCTOR ===============*/
   /* Displayer Constructor
    * @param db  - Database instance */
   public Displayer(GameSystem gs, Database db) {
      this.gs = gs;
      this.db = db; 
   }
   
   /*=============== PRIVATE METHODS ===============*/
   /* Accesses the owner of given country by id, and retrives owners char symbol 
    * @param countryId   - Id of specified country
    * @return            -   */
   private char getPlayerCharByCountryId( int countryId ) {
      //Checks if no current owner 
      if (gs.GetCountryByPos(gs.GetCountryPos(countryId)).GetOwner() == null) {
         return '#';
      }
      return gs.GetCountryByPos(gs.GetCountryPos(countryId)).GetOwner().GetPlayerChar(); 
   }
     
   /*=============== PUBLIC METHODS ===============*/
   /* Displaying the Menu*/ 
   public void DisplayMenu() {
      System.out.println("__________________________________________________________________"); 
      System.out.println("_____________        __      __      __             __        ___");
      System.out.println("\\______   \\__| _____|  | __ /  \\    /  \\___________|  |    __| _/");
      System.out.println(" |       _/  |/  ___/  |/ / \\   \\/\\/   /  _ \\_  __ \\  |   / __ | ");
      System.out.println(" |    |   \\  |\\___ \\|    <   \\        (  <_> )  | \\/  |__/ /_/ | ");
      System.out.println(" |____|_  /__/____  >__|_ \\   \\__/\\  / \\____/|__|  |____/\\____ | ");
      System.out.println("        \\/        \\/     \\/        \\/                         \\/ ");
      System.out.println("__________________________________________________________________");
      System.out.println("\n 1. Start Game                                                   ");
      System.out.println(" 2. Rules                                                        ");
      System.out.println(" 3. Exit Game                                                    "); 
      System.out.printf("    Selection: ");
   }
   
   /* Displaying only the Menu Options */
   public void DisplayMenuOption() {
      System.out.println("\n 1. Start Game                                                   ");
      System.out.println(" 2. Rules                                                        ");
      System.out.println(" 3. Exit Game                                                    "); 
      System.out.printf("    Selection: ");
   }
   
   /* Displays the error Message */
   public void ErrorMessage() {
      System.out.print("\nInvalid Input\n ");
   }
   
   /* Displays the Game Rules */
   public void DisplayRules() {
      
      Scanner scan = new Scanner(System.in);
   
      System.out.println("__________________________________________________________________\n");
      System.out.println("█▀▀ █▀█ █▀▄▀█ █▀█ █▀█ █▄░█ █▀▀ █▄░█ ▀█▀ █▀");
      System.out.println("█▄▄ █▄█ █░▀░█ █▀▀ █▄█ █░▀█ ██▄ █░▀█ ░█░ ▄█\n");
      System.out.println("The game board is a map of 6 continents divided into 42 territories.");
      scan.nextLine();
      System.out.println("42 Cards are marked with a territory and a troop bonus type");
      scan.nextLine();
      System.out.println("6 Cards are Distinguished as mission cards; complete their objective and receive a win");
      scan.nextLine();
      System.out.println("█▀▀ ▄▀█ █▀▄▀█ █▀▀ █▀█ █░░ ▄▀█ █▄█");
      System.out.println("█▄█ █▀█ █░▀░█ ██▄ █▀▀ █▄▄ █▀█ ░█░\n");
      System.out.println("The obvious goal outside of mission objectives is to survive whilst attacking other nations"); 
      scan.nextLine();
      System.out.println("Gameplay will begin with the placement of troops onto the board");
      scan.nextLine();
      System.out.println("Each Player will start with the amount of countries owned x 3; 42 divided among the players");
      scan.nextLine();
      System.out.println("Each territory will have a minimum of 1 troop");
      scan.nextLine();
      System.out.println("Players every turn will have a set amount of reinforcement troops to place");
      scan.nextLine();
      System.out.println("This will be based on the countries owned / 3, in addition to bonuses from continents");
      scan.nextLine();
      System.out.println("Players will also have the ability to purchase troops with specific card combinations");
      scan.nextLine();
      System.out.println("At the end of the placement phase, Players will be able to attack nations");
      scan.nextLine();
      System.out.println("Regular Attackers have a maximum of 3 di, Defenders will have 2 di");
      scan.nextLine();
      System.out.println("The highest value rolled by Attackers will be compared with the highest Defender di and so on");
      scan.nextLine();
      System.out.println("The final phase involves moving around troops to strengthen defences; remember defenders advantage");
      scan.nextLine();
      System.out.println("While players will have unlimited turns to attack and move, once the attacking phase has ended,");
      scan.nextLine();
      System.out.println("Players will no longer be able to attack.");
      scan.nextLine();
      System.out.println("▄▀█ █▀▄ █▀▄ █ ▀█▀ █ █▀█ █▄░█ ▄▀█ █░░");
      System.out.println("█▀█ █▄▀ █▄▀ █ ░█░ █ █▄█ █░▀█ █▀█ █▄▄\n");
      System.out.println("At the end of a Players turn, if they gain atleast one country they will be issued a card");
      scan.nextLine();
      System.out.println("Specific combiantions of cards can be traded for additional reinforcements.");
      scan.nextLine();
      System.out.println("All of the Mission Objectives rely on capturing specific combinations of continents");
      scan.nextLine();
      System.out.println("Alliances are allowed, though there will only be a single Victor.");
      scan.nextLine();
      System.out.println("The Victor is found when they complete their \"Secret\" Mission Objective, or are the last player remaining.");
      scan.nextLine();
      System.out.println("Be Careful, other players may have the same mission objectives, or similar objectives.");
      scan.nextLine();
      System.out.println("Player's lose when they no longer have any owned territories");
   }
   
   /* Initalizer Code */
   public void DisplayInitialiser() {
      System.out.println("\n█ █▄░█ █ ▀█▀ █ ▄▀█ █░░ █ ▀█ █▀▀   █▀█ █░░ ▄▀█ █▄█ █▀▀ █▀█ █▀");
      System.out.println("█ █░▀█ █ ░█░ █ █▀█ █▄▄ █ █▄ ██▄   █▀▀ █▄▄ █▀█ ░█░ ██▄ █▀▄ ▄█\n");
      System.out.print("Insert Number of Players [Must be 2 or More]: "); 
   }
   
   /* Displays Class Types*/
   public void DisplayClassType() {
      System.out.println("_____________________________________________________________________________________________________________________ ");
      System.out.println("\n▄▀█ ▀█▀ ▀█▀ ▄▀█ █▀▀ █▄▀ █▀▀ █▀█     |     █▀▄ █▀▀ █▀▀ █▀▀ █▄░█ █▀▄ █▀▀ █▀█     |     █▀█ █▀█ █▀█ █▀▄ █░█ █▀▀ █▀▀ █▀█ ");
      System.out.println("█▀█ ░█░ ░█░ █▀█ █▄▄ █░█ ██▄ █▀▄     |     █▄▀ ██▄ █▀░ ██▄ █░▀█ █▄▀ ██▄ █▀▄     |     █▀▀ █▀▄ █▄█ █▄▀ █▄█ █▄▄ ██▄ █▀▄\n ");   
      System.out.println(" 1. Attack Bonus when attacking     | 2. Defend Bonus when Defending           | 3. Producing Bonus Every Turn         "); 
      System.out.println("    Rolls [+1] Extra Dice           |    Rolls [+1] Extra Dice                 |    Formula [Countries Owned/2] -1     ");
      System.out.print(  "\nClass Selection : ");
      
   }
   
   /* Displays the playerCreation char */
   public void DisplayPlayerChar() {
      System.out.print("Enter a unique player char [i.e 'k' or '#' ] : ");
   }
   
   /* Displays Globe and information */
   public void DisplayGlobe() {
  
      String[] player = {"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"}; 
      Player[] allPlayer = db.GetAllPlayer();       
      
      //Converts Chars into Strings
      if(allPlayer != null) {
         for( int i = 0; i < allPlayer.length; i++ ) {
            player[i] = Character.toString(allPlayer[i].GetPlayerChar());
         }
      }

      System.out.printf("________________________________________________________________________________________________              \n");
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|-Player-                             | \n");
      System.out.printf(".. . . . . . . .%c%c%c%c%c%c . %c . . . . . . . . . . . . . . . .|1. %s                                 |    \n", getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/,getPlayerCharByCountryId(13)/*Iceland 13*/, player[0]);
      System.out.printf(".. . .%c%c%c%c%c%c .%c. .%c%c%c%c .%c%c . .%c%c%c. . .%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . .|2. %s                                 |\n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, getPlayerCharByCountryId(13), getPlayerCharByCountryId(13)/*Iceland 13*/, getPlayerCharByCountryId(14), getPlayerCharByCountryId(14)/*Scandinavia 14*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(27), getPlayerCharByCountryId(27)/*Siberia 27*/, getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28)/*Yakutsk 28*/, getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30)/*Kamchatka 30*/, player[1]); 
      System.out.printf(".. . %c%c%c%c%c%c%c%c. %c%c. %c%c. . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c . . .|3. %s                               |\n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, getPlayerCharByCountryId(15), getPlayerCharByCountryId(15), getPlayerCharByCountryId(15)/*Great Britain 15*/, getPlayerCharByCountryId(16), getPlayerCharByCountryId(16)/*Northern Europe 16*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(26), getPlayerCharByCountryId(26), getPlayerCharByCountryId(26)/*Ural 26*/, getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27)/*Siberia 27*/, getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29)/*Irkutsk 29*/, getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30)/*Kamchatka 30*/, player[2]);
      System.out.printf(".. . . %c%c%c%c%c%c%c%c%c%c. . . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . . . . .|4. %s                               | \n", getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3)/*Alberta 3*/, getPlayerCharByCountryId(4), getPlayerCharByCountryId(4), getPlayerCharByCountryId(4)/*Ontario 4*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, getPlayerCharByCountryId(19), getPlayerCharByCountryId(19)/*Southern Europe 19*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(31)/*Afghanistan 31*/, getPlayerCharByCountryId(26), getPlayerCharByCountryId(26)/*Ural 26*/, getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32)/*China 32*/, getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33)/*Mongolia 33*/, player[3]);
      System.out.printf(".. . . .%c%c%c%c%c%c%c%c . . . .   %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c.%c%c . . . .|5. %s                               |    \n", getPlayerCharByCountryId(6), getPlayerCharByCountryId(6), getPlayerCharByCountryId(6)/*Western US 6*/, getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7)/*Eastern Us 7*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, getPlayerCharByCountryId(19), getPlayerCharByCountryId(19), getPlayerCharByCountryId(19)/*Southern Europe 19*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(31), getPlayerCharByCountryId(31), getPlayerCharByCountryId(31)/*Afghanistan 31*/, getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32)/*China 32*/, getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33)/*Mongolia 33*/, getPlayerCharByCountryId(34), getPlayerCharByCountryId(34)/*Japan 34*/, player[4]);
      System.out.printf(".. . . . %c%c%c . . . . . .%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c%c%c%c%c. %c%c. . . . .|6. %s                               |      \n", getPlayerCharByCountryId(8), getPlayerCharByCountryId(8), getPlayerCharByCountryId(8)/*Central America 8*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*India 36*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*Siam 36*/, getPlayerCharByCountryId(34), getPlayerCharByCountryId(34)/*Japan 34*/, player[5]);
      System.out.printf(".. . . . . %c%c%c%c%c . . . .%c%c%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c. . . . . . . .|                                     |   \n", getPlayerCharByCountryId(9), getPlayerCharByCountryId(9), getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*India 36*/, getPlayerCharByCountryId(37), getPlayerCharByCountryId(37), getPlayerCharByCountryId(37)/*Siam 37*/);
      System.out.printf(".. . . . . .%c%c%c%c%c%c . . . .%c%c%c%c . . . . .%c%c . . . . . . . .|View Option                          |   \n", getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23), getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*Siam 36*/);
      System.out.printf(".. . . . . . %c%c%c%c%c . . . .%c%c%c%c %c . . . . . %c%c%c%c. . . . . .|1. Globe     5. Africa               |   \n", getPlayerCharByCountryId(11), getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(24)/*South Africa 24*/, getPlayerCharByCountryId(25)/*Madagascar 25*/, getPlayerCharByCountryId(38), getPlayerCharByCountryId(38), getPlayerCharByCountryId(38)/*Indonesia 38*/, getPlayerCharByCountryId(39)/*New Guinea 39*/);
      System.out.printf(".. . . . . . %c%c%c . . . . . %c%c. . . . . . . .%c%c%c%c . . . . .|2. N.America 6. Asia                 |   \n", getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(24), getPlayerCharByCountryId(24)/*South Affrica 24*/, getPlayerCharByCountryId(40), getPlayerCharByCountryId(40)/*Western Australia 40*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/);
      System.out.printf(".. . . . . . %c%c. . . . . . . . . . . . . . . %c%c. . . . . .|3. S.America 7. Australia            |   \n", getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/ );   
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|4. Europe                            |              \n");
      System.out.printf("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾      \n");

   }
   
   /* Displays NAmerica */
   public void DisplayNAmerica() {
      
      System.out.println("________________________________________________________________________________________________");
      for(int i = 0; i < 9; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   /* Displays SAmerica */
   public void DisplaySAmerica() {
      System.out.println("________________________________________________________________________________________________");
      for(int i = 9; i < 13; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   /* Displays Europe */
   public void DisplayEurope() {
      System.out.println("________________________________________________________________________________________________");
      for(int i =13; i < 20; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   /* Displays Africa */
   public void DisplayAfrica() {
      System.out.println("________________________________________________________________________________________________");
      for(int i = 20; i < 26; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   /* Displays Asia */
   public void DisplayAsia() {
      System.out.println("________________________________________________________________________________________________");
      for(int i = 26; i < 38; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   /* Displays Australia */
   public void DisplayAustralia() {
   System.out.println("________________________________________________________________________________________________");
      for(int i = 38; i < 42; i++) {
         Country country = gs.GetCountryByPos(gs.GetCountryPos(i));
         System.out.printf("Name: %-20s Id: %-2d TroopCount: %-3d Owner: %c Adjacency: %-25s | \n", country.GetCountryName(), country.GetCountryId(), country.GetTroopCount(), country.GetOwner().GetPlayerChar(), adjacency[i]);
      }
      System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
   }
   
   
   /* Displays Player information */
   public void DisplayPhase(int phaseNum) {
      
      //Card Name Array
      String[] cardName = {"EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY"};
      String[] cardPos = {"N/A", "N/A", "N/A", "N/A", "N/A"};
      String[] cardType = {"", "", "", "", ""};
      
      //Current Player
      Player currPlayer = gs.GetCurrPlayer();
      Bonus[] currPlayerDeck = currPlayer.GetBonusDeck();
      
      
      //Adds card variables into deck
      if(currPlayerDeck != null) {
         for(int i = 0; i < currPlayerDeck.length; i++) {
            Bonus currCard = currPlayerDeck[i];
            cardName[i] = currCard.GetCardName();
            cardPos[i] = cardPos[i] = Integer.toString(i);
            //Converts the value into the troop type string
            switch(currCard.GetTroopBonusType()) {
               case GameSystem.INFANTRY_TROOP:
                  cardType[i] = "Infantry";
                  break;
               case GameSystem.HORSE_TROOP:
                  cardType[i] = "Horse";
                  break;
               case GameSystem.CANNON_TROOP:
                  cardType[i] = "Cannon";
                  break;
            }
         }
      }
      
   
      //Checks PhaseNumber
      switch(phaseNum) {
         case PHASE_PLACEMENT: 
            System.out.printf("\n      PLACEMENT PHASE %c         ________________________________________________________________ \n", currPlayer.GetPlayerChar());
            System.out.printf("Receiving a total of %2d Troops |%-12s|%-12s|%-12s|%-12s|%-12s|\n", currPlayer.GetReinforcement(), cardName[0], cardName[1], cardName[2], cardName[3], cardName[4]); //Card Name
            System.out.printf("Options:                       |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |\n", cardPos[0], cardPos[1], cardPos[2], cardPos[3], cardPos[4]); //Card Id
            System.out.printf("9.  -----   12. ------         |            |            |            |            |            |\n");
            System.out.printf("10. Place   13. ----           |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |\n", cardType[0], cardType[1], cardType[2], cardType[3], cardType[4]); //Troop Type
            System.out.printf("11. ------- 14. --- -----      |____________|____________|____________|____________|____________|\n");
            System.out.printf(" Selection : "); 
            break;
         case PHASE_ONE:
            System.out.printf("\n      TURN PHASE ONE %c          ________________________________________________________________ \n", currPlayer.GetPlayerChar());
            System.out.printf("Receiving a total of %2d Troops |%-12s|%-12s|%-12s|%-12s|%-12s|\n", currPlayer.GetReinforcement(), cardName[0], cardName[1], cardName[2], cardName[3], cardName[4]); //Card Name
            System.out.printf("Options:                       |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |\n", cardPos[0], cardPos[1], cardPos[2], cardPos[3], cardPos[4]); //Card Id
            System.out.printf("9.  Trade   12. ------         |            |            |            |            |            |\n");
            System.out.printf("10. Place   13. ----           |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |\n", cardType[0], cardType[1], cardType[2], cardType[3], cardType[4]); //Troop Type
            System.out.printf("11. Mission 14. --- -----      |____________|____________|____________|____________|____________|\n");
            System.out.printf(" Selection : "); 
            break;
         case PHASE_TWO:
            System.out.printf("\n      TURN PHASE TWO %c          ________________________________________________________________ \n", currPlayer.GetPlayerChar());
            System.out.printf("                               |%-12s|%-12s|%-12s|%-12s|%-12s|\n", cardName[0], cardName[1], cardName[2], cardName[3], cardName[4]); //Card Name
            System.out.printf("Options:                       |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |\n", cardPos[0], cardPos[1], cardPos[2], cardPos[3], cardPos[4]); //Card Id
            System.out.printf("9.  -----   12. Attack         |            |            |            |            |            |\n");
            System.out.printf("10. -----   13. ----           |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |\n", cardType[0], cardType[1], cardType[2], cardType[3], cardType[4]); //Troop Type
            System.out.printf("11. Mission 14. End Phase      |____________|____________|____________|____________|____________|\n");
            System.out.printf(" Selection : ");
            break; 
         case PHASE_THREE:
            System.out.printf("\n     TURN PHASE THREE %c         ________________________________________________________________ \n", currPlayer.GetPlayerChar());
            System.out.printf("                               |%-12s|%-12s|%-12s|%-12s|%-12s|\n", cardName[0], cardName[1], cardName[2], cardName[3], cardName[4]); //Card Name
            System.out.printf("Options:                       |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |\n", cardPos[0], cardPos[1], cardPos[2], cardPos[3], cardPos[4]); //Card Id
            System.out.printf("9.  -----   12. ------         |            |            |            |            |            |\n");
            System.out.printf("10. -----   13. Move           |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |\n", cardType[0], cardType[1], cardType[2], cardType[3], cardType[4]); //Troop Type
            System.out.printf("11. Mission 14. End Turn       |____________|____________|____________|____________|____________|\n");
            System.out.printf(" Selection : ");
            break; 
      }
   }
   
   public void DisplayTrade() {
      
      //Card Name Array
      String[] cardName = {"EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY"};
      String[] cardPos = {"N/A", "N/A", "N/A", "N/A", "N/A"};
      String[] cardType = {"", "", "", "", ""};
      
      //Current Player
      Player currPlayer = gs.GetCurrPlayer();
      Bonus[] currPlayerDeck = currPlayer.GetBonusDeck();
      
      //Adds card variables into deck
      if(currPlayerDeck != null) {
         for(int i = 0; i < currPlayerDeck.length; i++) {
            Bonus currCard = currPlayerDeck[i];
            cardName[i] = currCard.GetCardName();
            cardPos[i] = cardPos[i] = Integer.toString(i);
            //Converts the value into the troop type string
            switch(currCard.GetTroopBonusType()) {
               case GameSystem.INFANTRY_TROOP:
                  cardType[i] = "Infantry";
                  break;
               case GameSystem.HORSE_TROOP:
                  cardType[i] = "Horse";
                  break;
               case GameSystem.CANNON_TROOP:
                  cardType[i] = "Cannon";
                  break;
            }
         }
      }
      
      System.out.printf("\n     TRADE SCHEMEATIC %c          ________________________________________________________________ \n", currPlayer.GetPlayerChar());
      System.out.printf("   Options:                     |%-12s|%-12s|%-12s|%-12s|%-12s|\n", cardName[0], cardName[1], cardName[2], cardName[3], cardName[4]); //Card Name
      System.out.printf("   Infantry X 3  = 4            |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |  ID %3s    |\n", cardPos[0], cardPos[1], cardPos[2], cardPos[3], cardPos[4]); //Card Id
      System.out.printf("   Horse X 3     = 6            |            |            |            |            |            |\n");
      System.out.printf("   Cannon X 3    = 8            |  %-8s  |  %-8s  |  %-8s  |  %-8s  |  %-8s  |\n", cardType[0], cardType[1], cardType[2], cardType[3], cardType[4]); //Troop Type
      System.out.printf("   One of Each   = 10           |____________|____________|____________|____________|____________|\n");
      System.out.printf(" Card 1 [Id] : ");
   
   }
   
   /* Placement Troops in country */
   public void DisplayPlace() {
      System.out.printf("\n        PLACEMENT TROOPS\n");
      System.out.printf("Receiving a total of %d Troops \n", gs.GetCurrPlayer().GetReinforcement());
      System.out.printf("Selection [Owned Country Id] : ");
   
   }
   
   /* Displays Mission Objective */
   public void DisplayMission() {
      Scanner scan = new Scanner(System.in);
      Player currPlayer = gs.GetCurrPlayer();
      Mission playerMission = currPlayer.GetPlayerMission();
      
      System.out.printf("\n        MISSION OBJECTIVE\n");
      System.out.printf("CONFIDENTIAL: READ THIS IN PRIVATE, ENTER WHEN READY & ENTER WHEN DONE\n");
      scan.nextLine(); 
      System.out.printf("%s: \n", playerMission.GetCardName());
      System.out.printf("%s\n", playerMission.GetMissionDesc());
      scan.nextLine(); 
      //Prints multiple spaces
      for(int i = 0; i < 50; i++){
         System.out.print("\n");
      }
   }

}