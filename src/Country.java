/* Developed By: Euan Lim
 * Revised Date: Nov 18, 2021 */

/* Country Class */
public class Country { 
   
   /* GLOBAL VARIABLES */
   public final static int NORTH_AMERICA_CONTINENT_ID      = 0;
   public final static int SOUTH_AMERICA_CONTINENT_ID      = 1;
   public final static int EUROPE_CONTINENT_ID             = 2;
   public final static int AFRICA_CONTINENT_ID             = 3;
   public final static int ASIA_CONTINENT_ID               = 4;
   public final static int AUSTRALIA_CONTINENT_ID          = 5;

   /* INSTANCE VARIABLES */
   private String countryName; 
   private int countryId; 
   private int continentId;
   private int[] adjacency;
   
   /* ADDITIONAL VARIABLES */
   private int troopCount;  
   private Player owner; 
   
   //keeps track of num countries created
   public static int CountryCount = 0; 
   
   /*=============== CONSTRUCTOR ===============*/
   /* Country Constructor
    * @param countryName  - name of country
    *        continentId  - id of continent, object is apartof
    *        adjacency    - id's of country current object is adjacent to */
   public Country( String countryName, int[] adjacency ) {
      this.countryName = countryName;
      this.adjacency = adjacency;
      //Sets country id to the current country count 
      this.countryId = CountryCount; 
      //Sets continent id
      setContinentId();
      //Increments the county count
      CountryCount ++; 
   }
   
   /*=============== PRIVATE METHODS ===============*/
   /* Sets the ContinentId of this country */
   private void setContinentId() {
      if( this.countryId >= 0 && this.countryId <= 8 ) {
         this.continentId = NORTH_AMERICA_CONTINENT_ID;
      }
      else if( this.countryId >= 9 && this.countryId <= 12 ) {
         this.continentId = SOUTH_AMERICA_CONTINENT_ID; 
      }
      else if( this.countryId >= 13 && this.countryId <= 19 ) {
         this.continentId = EUROPE_CONTINENT_ID; 
      }
      else if( this.countryId >= 20 && this.countryId <= 25 ) {
         this.continentId = AFRICA_CONTINENT_ID; 
      }
      else if( this.countryId >= 26 && this.countryId <= 37 ) {
         this.continentId = ASIA_CONTINENT_ID;
      }
      else {
         this.continentId = AUSTRALIA_CONTINENT_ID;
      }
   }
   
   /*=============== PUBLIC METHODS ===============*/ 
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