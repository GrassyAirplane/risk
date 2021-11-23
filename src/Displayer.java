/* Developed By: Euan Lim
 * Revised Date: Nov 23, 2021 */

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
      System.out.println("All of the Mission Objectives rely on capturing sppecific combinations of continents");
      scan.nextLine();
      System.out.println("Alliances are allowed, though there will only be a single Victor.");
      scan.nextLine();
      System.out.println("The Victor is found when they complete their \"Secret\" Mission Objective, or are the last player remaining.");
      scan.nextLine();
      System.out.println("Player's lose when they no longer have any owned territories");
   }
   
   public void DisplayInitialiser() {
      System.out.println("\n█ █▄░█ █ ▀█▀ █ ▄▀█ █░░ █ ▀█ █▀▀   █▀█ █░░ ▄▀█ █▄█ █▀▀ █▀█ █▀");
      System.out.println("█ █░▀█ █ ░█░ █ █▀█ █▄▄ █ █▄ ██▄   █▀▀ █▄▄ █▀█ ░█░ ██▄ █▀▄ ▄█\n");
      System.out.print("Insert Number of Players [Must be 2 or More]: "); 
   }
   
   public void DisplayGlobe() {
      char icon = 't';
      System.out.printf("_____________________________________________________________________________________              \n");
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|-Player-                                \n");
      System.out.printf(".. . . . . . . .%c%c%c%c%c%c . %c . . . . . . . . . . . . . . . .|1.                               \n", getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/,getPlayerCharByCountryId(13)/*Iceland 13*/);
      System.out.printf(".. . .%c%c%c%c%c%c .%c. .%c%c%c%c .%c%c . .%c%c%c. . .%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . .|2.       \n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, getPlayerCharByCountryId(13), getPlayerCharByCountryId(13)/*Iceland 13*/, getPlayerCharByCountryId(14), getPlayerCharByCountryId(14)/*Scandinavia 14*/, getPlayerCharByCountryId(17)/*Ukraine 17*/, getPlayerCharByCountryId(27), getPlayerCharByCountryId(27)/*Siberia 27*/, getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28), getPlayerCharByCountryId(28)/*Yakutsk 28*/, getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30), getPlayerCharByCountryId(30)/*Kamchatka 30*/); 
      System.out.printf(".. . %c%c%c%c%c%c%c%c. %c%c. %c%c. . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c . . .|3. \n", getPlayerCharByCountryId(0), getPlayerCharByCountryId(0), getPlayerCharByCountryId(0)/*Alaska 0*/, getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1), getPlayerCharByCountryId(1)/*NorthWest 1*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(2), getPlayerCharByCountryId(2)/*Greenland 2*/, icon, icon, icon/*Great Britain 15*/, icon, icon/*Northern Europe 16*/, icon/*Ukraine 17*/, icon, icon, icon/*Ural 26*/, icon, icon, icon, icon, icon/*Siberia 27*/,icon, icon, icon, icon, icon, icon/*Irkutsk 29*/, icon, icon, icon, icon, icon/*Kamchatka 30*/);
      System.out.printf(".. . . %c%c%c%c%c%c%c%c%c%c. . . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . . . . .|4.      \n", getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3), getPlayerCharByCountryId(3)/*Alberta 3*/, getPlayerCharByCountryId(4), getPlayerCharByCountryId(4), getPlayerCharByCountryId(4)/*Ontario 4*/, getPlayerCharByCountryId(5), getPlayerCharByCountryId(5), getPlayerCharByCountryId(5)/*Quebec 5*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, icon, icon/*Southern Europe*/, icon/*Ukraine 17*/, icon/*Afghanistan 31*/, icon, icon/*Ural 26*/, icon, icon, icon, icon/*China 32*/, icon, icon, icon, icon, icon, icon, icon/*Mongolia 33*/);
      System.out.printf(".. . . .%c%c%c%c%c%c%c%c . . . .   %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c.%c%c . . . .|5.        \n", getPlayerCharByCountryId(6), getPlayerCharByCountryId(6), getPlayerCharByCountryId(6)/*Western US 6*/, getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7), getPlayerCharByCountryId(7)/*Eastern Us 7*/, getPlayerCharByCountryId(18), getPlayerCharByCountryId(18)/*Western Europe 18*/, getPlayerCharByCountryId(19), getPlayerCharByCountryId(19), getPlayerCharByCountryId(19)/*Southern Europe 19*/, icon/*Ukraine 17*/, icon, icon, icon/*Afghanistan 31*/, icon, icon, icon, icon, icon, icon, icon, icon/*China 32*/, icon, icon, icon/*Mongolia 33*/, icon, icon/*Japan 34*/);
      System.out.printf(".. . . . %c%c%c . . . . . .%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c%c%c%c%c. %c%c. . . . .|6.              \n", getPlayerCharByCountryId(8), getPlayerCharByCountryId(8), getPlayerCharByCountryId(8)/*Central America 8*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*India 36*/, icon, icon, icon, icon/*Siam 36*/, icon, icon/*Japan 34*/ );
      System.out.printf(".. . . . . %c%c%c%c%c . . . .%c%c%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c. . . . . . . .|                  \n", getPlayerCharByCountryId(9), getPlayerCharByCountryId(9), getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20), getPlayerCharByCountryId(20)/*North Africa 20*/, getPlayerCharByCountryId(21), getPlayerCharByCountryId(21), getPlayerCharByCountryId(21)/*Egypt 21*/, getPlayerCharByCountryId(35), getPlayerCharByCountryId(35)/*Middle East 35*/, icon, icon, icon, icon, icon/*India 36*/, icon, icon, icon/*Siam 37*/);
      System.out.printf(".. . . . . .%c%c%c%c%c%c . . . .%c%c%c%c . . . . .%c%c . . . . . . . .|View Option                 \n", getPlayerCharByCountryId(9)/*Venezuela 9*/, getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23), getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(36), getPlayerCharByCountryId(36)/*Siam 36*/);
      System.out.printf(".. . . . . . %c%c%c%c%c . . . .%c%c%c%c %c . . . . . %c%c%c%c. . . . . .|1. Globe     5. Africa    \n", getPlayerCharByCountryId(11), getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(10), getPlayerCharByCountryId(10), getPlayerCharByCountryId(10)/*Brazil 10*/, getPlayerCharByCountryId(22), getPlayerCharByCountryId(22)/*Congo 22*/, getPlayerCharByCountryId(23)/*East Africa 23*/, getPlayerCharByCountryId(24)/*South Africa 24*/, getPlayerCharByCountryId(25)/*Madagascar 25*/, getPlayerCharByCountryId(38), getPlayerCharByCountryId(38), getPlayerCharByCountryId(38)/*Indonesia 38*/, icon/*New Guinea 39*/);
      System.out.printf(".. . . . . . %c%c%c . . . . . %c%c. . . . . . . .%c%c%c%c . . . . .|2. N.America 6. Asia           \n", getPlayerCharByCountryId(11)/*Peru 11*/, getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(24), getPlayerCharByCountryId(24)/*South Affrica 24*/, getPlayerCharByCountryId(40), getPlayerCharByCountryId(40)/*Western Australia 40*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/);
      System.out.printf(".. . . . . . %c%c. . . . . . . . . . . . . . . %c%c. . . . . .|3. S.America 7. Australia           \n", getPlayerCharByCountryId(12), getPlayerCharByCountryId(12)/*Argentina 12*/, getPlayerCharByCountryId(41), getPlayerCharByCountryId(41)/*Eastern Australia 41*/ );   
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|4. Europe    8. Scoreboard              \n");
      System.out.printf("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾              \n");

   }
   
   
}