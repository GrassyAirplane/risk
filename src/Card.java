/* Developed By: Euan 
 * Revised Date: Nov 19, 2021 */

/* Card Class
 * Children  : Bonus
 *             Mission */
public abstract class Card { 
   /* INSTANCE VARIABLES */
   private String cardName; 
   private int cardId; 
   
   //keeps track of num Cards created
   public static int CardCount = 0;
   
   /*=============== CONSTRUCTOR ===============*/
   /* Constructor class for Card
    * @param name - Name of Card */
   public Card( String cardName ) {
      this.cardName = cardName; 
      this.cardId = CardCount; 
      
      //Increments Card Count
      CardCount++;
   }
   
   /*=============== PUBLIC METHODS ===============*/ 
   /* Gets Card name
    * @return     - returns the name of this card */
   public String GetCardName() {
      return this.cardName;
   }
   
   /* Gets Card Id
    * @return     - returns the id of this card */
   public int GetCardId() {
      return this.cardId; 
   }
}
