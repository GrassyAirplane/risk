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
      String icon = "#";
      System.out.printf("___________________________________________________________________________________\n");
      System.out.printf(".. . . . . . . . . . . . . . . . . . . . . . . . . . . . .|-Player-                \n");
      System.out.printf(".. . . . . . . .%s%s%s%s%s . %s . . . . . . . . . . . . . . . .|1.                      \n", icon, icon, icon, icon, icon/*Greenland 2*/,icon/*Iceland 13*/);
      System.out.printf(".. . .%s%s%s%s%s%s .%s. .%s%s%s%s .%s%s . .%s%s%s. . .%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s. . .|2.  \n", icon, icon/*Alaska 0*/, icon, icon, icon, icon/*NorthWest 1*/, icon/*Quebec*/, icon, icon, icon, icon/*Greenland*/, icon, icon/*Iceland*/);
      System.out.printf("");

   }
   
   
}