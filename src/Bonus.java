/* Developed By: Euan
 * Revised Date: Nov 19, 2021 */

/* Bonus Card Class
 * Parent  : Card */
public class Bonus extends Card { 
   /* INITIALIZED VARIABLES */
   private int troopBonusType; 
   
   /*=============== CONSTRUCTOR ===============*/
   /* Constructor class for Card
    * @param name             - Name of Card 
    *         troopBonusType  - Type of troop */
   public Bonus( String cardName, int troopBonusType ) {
      super(cardName);
      this.troopBonusType = troopBonusType; 
   }
   
   /* Gets troopBonusType of card
    * @return - troopBonusType*/
   public int GetTroopBonusType() {
      return this.troopBonusType;
   }
}