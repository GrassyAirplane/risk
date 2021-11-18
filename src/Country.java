/* Developed By: Euan Lim
 * Revised Date: Nov 18, 2021 */

/* Country Class */
public class Country { 
   
   //instance variables
   private String countryName; 
   private int countryId; 
   private int continentId;
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
      this.continentId = continentId; 
      this.adjacency = adjacency;
      //Sets country id to the current country count 
      this.countryId = CountryCount; 
      //Increments the county count
      CountryCount ++; 
   }
   
   /* Gets the number of Troops in the country 
    * @return - Troop Count */
   public int GetTroopCount() {
      return this.troopCount; 
   }
   
   /* Sets the number of Troops in the country 
    * @param - New Troop Count */
   public void SetTroopCount( int troopCount ) {
      this.troopCount = troopCount; 
   }
   
   /* Gets the owner of the country
    * @return - Owner object instance */
   public Player GetOwner() {
      return this.owner;
   }
   
   /* Sets the owner of this country
    * @param - owner object instance */
   public void SetOwner( Player newOwner ) {
      this.owner = newOwner;
   }
   
   /* Gets the Country id
    * @return - Country id int */
   public int GetCountryId() {
      return this.countryId; 
   }
   
   /* Gets the Continent id 
    * @return - Continent id int */
   public int GetContinentId() {
      return this.continentId; 
   }
   
   /* Checks if inputted country is adjacent to this country 
    * @param  - id of inputted country 
    * @return - returns true or false depending on wether it is adjacent */
   public boolean isAdjacent( int countryId ) {
      //isAdjacent status
      boolean isAdjacent = false; 
      
      //checks wether the inputted id is in the adjacency array
      for( int index = 0; index < this.adjacency.length; index++ ) {
         if( this.adjacency[index] == countryId ) {
            isAdjacent = true; 
         }
      }
      
      //returns status of adjacency
      return isAdjacent; 
   }
}