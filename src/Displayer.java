/* Developed By: Euan Lim
 * Revised Date: Nov 24, 2021 */

import java.util.Scanner; 

/* Displayer */
public class Displayer { 
   
   /* INSTANCE VARIABLE */
   Database db; 
   
   /*=============== CONSTRUCTOR ===============*/
   /* Displayer Constructor
    * @param db  - Database instance */
   public Displayer(Database db) {
      this.db = db; 
   }
   
   /*=============== PRIVATE METHODS ===============*/
   /* Accesses the owner of given country by id, and retrives owners char symbol 
    * @param countryId   - Id of specified country
    * @return            -   */
   private char getPlayerCharByCountryId( int countryId ) {
      //Checks if no current owner 
      if (db.GetCountryByPos(db.GetCountryPos(countryId)).GetOwner() == null) {
         return '#';
      }
      return db.GetCountryByPos(db.GetCountryPos(countryId)).GetOwner().GetPlayerChar(); 
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
      char icon = 't';
      
      String[] player = {"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"}; 
      Player[] allPlayer = db.GetAllPlayer();       
      
      //Converts Chars into Strings
      if(allPlayer != null) {
         for( int i = 0; i < allPlayer.length; i++ ) {
            player[i] = Character.toString(allPlayer[i].GetPlayerChar());
         }
      }

      System.out.printf("_____________________________________________________________________________________              \n");
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|-Player-                                \n");
      System.out.printf(".. . . . . . . .%c%c%c%c%c%c . %c . . . . . . . . . . . . . . . .|1. %s                               \n", getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/,getPlayerCharByCountryId(13)/*Iceland 13*/, player[0]);
      System.out.printf(".. . .%c%c%c%c%c%c .%c. .%c%c%c%c .%c%c . .%c%c%c. . .%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . .|2. %s       \n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, getPlayerCharByCountryId(13), getPlayerCharByCountryId(13)/*Iceland 13*/, getPlayerCharByCountryId(14), getPlayerCharByCountryId(14)/*Scandinavia 14*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(27), getPlayerCharByCountryId(27)/*Siberia 27*/, getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28)/*Yakutsk 28*/, getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30)/*Kamchatka 30*/, player[1]); 
      System.out.printf(".. . %c%c%c%c%c%c%c%c. %c%c. %c%c. . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c . . .|3. %s \n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, getPlayerCharByCountryId(15), getPlayerCharByCountryId(15), getPlayerCharByCountryId(15)/*Great Britain 15*/, getPlayerCharByCountryId(16), getPlayerCharByCountryId(16)/*Northern Europe 16*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(26), getPlayerCharByCountryId(26), getPlayerCharByCountryId(26)/*Ural 26*/, getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27), getPlayerCharByCountryId(27)/*Siberia 27*/, getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29), getPlayerCharByCountryId(29)/*Irkutsk 29*/, getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30)/*Kamchatka 30*/, player[2]);
      System.out.printf(".. . . %c%c%c%c%c%c%c%c%c%c. . . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . . . . .|4. %s      \n", getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3)/*Alberta 3*/, getPlayerCharByCountryId(4), getPlayerCharByCountryId(4), getPlayerCharByCountryId(4)/*Ontario 4*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, getPlayerCharByCountryId(19), getPlayerCharByCountryId(19)/*Southern Europe 19*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(31)/*Afghanistan 31*/, getPlayerCharByCountryId(26), getPlayerCharByCountryId(26)/*Ural 26*/, getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32)/*China 32*/, getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33)/*Mongolia 33*/, player[3]);
      System.out.printf(".. . . .%c%c%c%c%c%c%c%c . . . .   %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c.%c%c . . . .|5. %s        \n", getPlayerCharByCountryId(6), getPlayerCharByCountryId(6), getPlayerCharByCountryId(6)/*Western US 6*/, getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7)/*Eastern Us 7*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, getPlayerCharByCountryId(19), getPlayerCharByCountryId(19), getPlayerCharByCountryId(19)/*Southern Europe 19*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(31), getPlayerCharByCountryId(31), getPlayerCharByCountryId(31)/*Afghanistan 31*/, getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32), getPlayerCharByCountryId(32)/*China 32*/, getPlayerCharByCountryId(33), getPlayerCharByCountryId(33), getPlayerCharByCountryId(33)/*Mongolia 33*/, getPlayerCharByCountryId(34), getPlayerCharByCountryId(34)/*Japan 34*/, player[4]);
      System.out.printf(".. . . . %c%c%c . . . . . .%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c%c%c%c%c. %c%c. . . . .|6. %s              \n", getPlayerCharByCountryId(8), getPlayerCharByCountryId(8), getPlayerCharByCountryId(8)/*Central America 8*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*India 36*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*Siam 36*/, getPlayerCharByCountryId(34), getPlayerCharByCountryId(34)/*Japan 34*/, player[5]);
      System.out.printf(".. . . . . %c%c%c%c%c . . . .%c%c%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c. . . . . . . .|                  \n", getPlayerCharByCountryId(9), getPlayerCharByCountryId(9), getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*India 36*/, getPlayerCharByCountryId(37), getPlayerCharByCountryId(37), getPlayerCharByCountryId(37)/*Siam 37*/);
      System.out.printf(".. . . . . .%c%c%c%c%c%c . . . .%c%c%c%c . . . . .%c%c . . . . . . . .|View Option                 \n", getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23), getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*Siam 36*/);
      System.out.printf(".. . . . . . %c%c%c%c%c . . . .%c%c%c%c %c . . . . . %c%c%c%c. . . . . .|1. Globe     5. Africa    \n", getPlayerCharByCountryId(11), getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(24)/*South Africa 24*/, getPlayerCharByCountryId(25)/*Madagascar 25*/, getPlayerCharByCountryId(38), getPlayerCharByCountryId(38), getPlayerCharByCountryId(38)/*Indonesia 38*/, getPlayerCharByCountryId(39)/*New Guinea 39*/);
      System.out.printf(".. . . . . . %c%c%c . . . . . %c%c. . . . . . . .%c%c%c%c . . . . .|2. N.America 6. Asia           \n", getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(24), getPlayerCharByCountryId(24)/*South Affrica 24*/, getPlayerCharByCountryId(40), getPlayerCharByCountryId(40)/*Western Australia 40*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/);
      System.out.printf(".. . . . . . %c%c. . . . . . . . . . . . . . . %c%c. . . . . .|3. S.America 7. Australia           \n", getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/ );   
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|4. Europe    8. Scoreboard              \n");
      System.out.printf("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾              \n");

   }
   
   /* Displays NAmerica */
   public void DisplayNAmerica() {
   
   }
   
   /* Displays SAmerica */
   public void DisplaySAmerica() {
   
   }
   
   /* Displays Europe */
   public void DisplayEurope() {
   
   }
   
   /* Displays Africa */
   public void DisplayAfrica() {
   
   }
   
   /* Displays Asia */
   public void DisplayAsia() {
   
   }
   
   /* Displays Australia */
   public void DisplayAustralia() {
   
   }
   
   /* Displays Player information */
   public void DisplayPlayerInfo() {
   
   }
}