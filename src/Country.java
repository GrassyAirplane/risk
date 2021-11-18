/* Developed By: ------
 * Revised Date: Nov 17, 2021 */

/* Country Class */
public class Country { 
   
   //instance variables
   private String countryName; 
   private int countryId; 
   private int continendId;
   private int[] adjacency;
   
   //additional variables
   private int troopCount;  
   private Player owner; 
   
   //keeps track of num countries created
   public static int CountryCount = 0; 
   
   /* Country Constructor
    * @param countryName  - name of country
    *        continentId  - id of continent, object is apartof
    *        adjacency    - id's of country current object is adjacent to */
   public Country( String countryName, int continentId, int[] adjacency ) {
      this.countryName = countryName;
      this.continendId = continentId; 
      this.adjacency = adjacency;
   }
   
   /* Gets the number of Troops in the country 
    * @return - Troop Count*/
   public int GetTroopCount() {
      return this.troopCount; 
   }
   
   /* Sets the number of Troops in the country 
    * @param - New Troop Count*/
   public void SetTroopCount(int troopCount) {
      this.troopCount = troopCount; 
   }
   
    
}