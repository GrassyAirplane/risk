/* Developed By: Euan Lim
 * Revised Date: Nov 20, 2021 */

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
      return db.GetCountryByPos(db.GetCountryPos(countryId)).GetOwner().GetPlayerChar(); 
   }
   
   
   public void DisplayBoard() {
      char icon = 'p';
      System.out.printf("_____________________________________________________________________________________              \n");
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|-Player-                                \n");
      System.out.printf(".. . . . . . . .%c%c%c%c%c%c . %c . . . . . . . . . . . . . . . .|1.                               \n", icon, icon, icon, icon, icon,icon/*Greenland 2*/,icon/*Iceland 13*/);
      System.out.printf(".. . .%c%c%c%c%c%c .%c. .%c%c%c%c .%c%c . .%c%c%c. . .%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . .|2.       \n", icon, icon/*Alaska 0*/, icon, icon, icon, icon/*NorthWest 1*/, icon/*Quebec 5*/, icon, icon, icon, icon/*Greenland 2*/, icon, icon/*Iceland 13*/, icon, icon/*Scandinavia 14*/, icon/*Ukraine 17*/, icon, icon/*Siberia 27*/, icon, icon, icon, icon, icon/*Yakutsk 28*/, icon, icon, icon, icon, icon, icon, icon, icon/*Kamchatka 30*/); 
      System.out.printf(".. . %c%c%c%c%c%c%c%c. %c%c. %c%c. . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c . . .|3. \n", icon, icon, icon/*Alaska 0*/, icon, icon, icon, icon, icon/*NorthWest 1*/, icon, icon/*Quebec 5*/, icon, icon/*Greenland 2*/, icon, icon, icon/*Great Britain 15*/, icon, icon/*Northern Europe 16*/, icon/*Ukraine 17*/, icon, icon, icon/*Ural 26*/, icon, icon, icon, icon, icon/*Siberia 27*/,icon, icon, icon, icon, icon, icon/*Irkutsk 29*/, icon, icon, icon, icon, icon/*Kamchatka 30*/);
      System.out.printf(".. . . %c%c%c%c%c%c%c%c%c%c. . . . %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c. . . . . .|4.      \n", icon, icon, icon, icon/*Alberta 3*/, icon, icon, icon/*Ontario 4*/, icon, icon, icon/*Quebec 5*/, icon, icon, icon, icon, icon/*Western Europe 18*/, icon, icon/*Southern Europe*/, icon/*Ukraine 17*/, icon/*Afghanistan 31*/, icon, icon/*Ural 26*/, icon, icon, icon, icon/*China 32*/, icon, icon, icon, icon, icon, icon, icon/*Mongolia 33*/);
      System.out.printf(".. . . .%c%c%c%c%c%c%c%c . . . .   %c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c.%c%c . . . .|5.        \n", icon, icon, icon/*Western US 6*/, icon, icon, icon, icon, icon/*Eastern Us 7*/, icon, icon/*Western Europe*/, icon, icon, icon/*Southern Europe 19*/, icon/*Ukraine 17*/, icon, icon, icon/*Afghanistan 31*/, icon, icon, icon, icon, icon, icon, icon, icon/*China 32*/, icon, icon, icon/*Mongolia 33*/, icon, icon/*Japan 34*/);
      System.out.printf(".. . . . %c%c%c . . . . . .%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c%c%c%c%c. %c%c. . . . .|6.              \n", icon, icon, icon/*Central America 8*/, icon, icon, icon/*North Africa 20*/, icon, icon/*Egypt 21*/, icon, icon, icon, icon/*Middle East 35*/, icon, icon, icon, icon, icon, icon/*India 36*/, icon, icon, icon, icon/*Siam 36*/, icon, icon/*Japan 34*/ );
      System.out.printf(".. . . . . %c%c%c%c%c . . . .%c%c%c%c%c%c%c. %c%c%c%c%c%c%c%c%c%c. . . . . . . .|                  \n", icon, icon, icon/*Venezuela 9*/, icon, icon/*Brazil 10*/, icon, icon, icon, icon/*North Africa 20*/, icon, icon, icon/*Egypt 21*/, icon, icon/*Middle East 35*/, icon, icon, icon, icon, icon/*India 36*/, icon, icon, icon/*Siam*/);
      System.out.printf(".. . . . . .%c%c%c%c%c%c . . . .%c%c%c%c . . . . .%c%c . . . . . . . .|View Option                 \n", icon/*Venezuela 9*/, icon/*Peru 11*/, icon, icon, icon, icon/*Brazil 10*/, icon, icon/*Congo 22*/,icon, icon/*East Africa 23*/, icon, icon/*Siam 36*/);
      System.out.printf(".. . . . . . %c%c%c%c%c . . . .%c%c%c%c %c . . . . . %c%c%c%c. . . . . .|1. Globe     5. Africa    \n", icon, icon/*Peru 11*/, icon, icon, icon/*Brazil 10*/, icon, icon/*Congo 22*/, icon/*East Africa 23*/, icon /*South Africa 24*/, icon/*Madagascar 25*/, icon, icon, icon/*Indonesia 38*/, icon/*New Guinea 39*/);
      System.out.printf(".. . . . . . %c%c%c . . . . . %c%c. . . . . . . .%c%c%c%c . . . . .|2. N.America 6. Asia           \n", icon/*Peru 11*/, icon, icon/*Argentina 12*/, icon, icon/*South Affrica 24*/, icon, icon/*Western Australia 40*/, icon, icon/*Eastern Australia 41*/);
      System.out.printf(".. . . . . . %c%c. . . . . . . . . . . . . . . %c%c. . . . . .|3. S.America 7. Australia           \n", icon, icon/*Argentina 12*/, icon, icon/*Eastern Australia 41*/ );   
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|4. Europe    8. Scoreboard              \n");
      System.out.printf("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾              \n");

   }
   
   
}