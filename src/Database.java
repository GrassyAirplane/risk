/* Developed By: Euan Lim
 * Revised Date: Nov 19, 2021 */
/* Import Statements*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Database */
public class Database { 
   
   /* INSTANCE VARIABLE */
   private Country[] allCountries;
   private Bonus[] allBonus;
   private Mission[] allMission;;
   
   /* FILE VARIABLES */
   private final static String FILE_PATH_COUNTRY = "../Countries.txt";
   private final static String FILE_PATH_BONUS = "../Bonus.txt";
   private final static String FILE_PATH_MISSION = "../Mission.txt";
   
   /*=============== CONSTRUCTOR ===============*/
   /* Constructor method for Database */
   public Database()  /*if File not found in scanner*/ {
      //Imports Country data 
      importCountries(FILE_PATH_COUNTRY);
      importBonusCard(FILE_PATH_BONUS); 
      importMissionCard(FILE_PATH_MISSION); 
   }
   
   /*=============== PRIVATE METHODS ===============*/
   /* imports all of the country data from the inputted textfile, 
    * and initializing country instances which are then inserted
    * into allCountries Country array. 
    * @param fileName - path to access file */
   private void importCountries(String fileName) {
         /* Catching incorrect file input */
         try {
            //Create Scanner of file
            Scanner scan = new Scanner(new File(fileName)); 
            
            //Accesses every line in the file
            while( scan.hasNextLine() ) {
               //Access line in file
               String scanData = scan.nextLine();
                 
               //Checks to make sure the current line is not empty 
               if( !scanData.equals("") ) {
                  
                  //converts scanData into an array
                  String[] dataArray = scanData.split(":");
   
                  //Adds initialized country object           
                  addCountry( new Country(dataArray[0], stringToIntArray(dataArray[1]), Integer.parseInt(dataArray[2]) ));
               }
            }
         } catch( FileNotFoundException exception ) {
            System.out.println(exception); 
         }
   }
   
   /* imports all of the bonus Cards data from the inputted textfile,
    * and intializing Bonus Card instances inserte into allBonus array
    * @param fileName - path to access file */
   private void importBonusCard(String fileName) {
      /* Catching incorrect file input */
      try {
         //Create Scanner of file
         Scanner scan = new Scanner(new File(fileName)); 
         
         //Accesses every line in the file
            while( scan.hasNextLine() ) {
               //Access line in file
               String scanData = scan.nextLine();
                 
               //Checks to make sure the current line is not empty 
               if( !scanData.equals("") ) {
                  
                  //converts scanData into an array
                  String[] dataArray = scanData.split(":");
                  
                  //Adds initialized country object           
                  addBonus( new Bonus(dataArray[0], Integer.parseInt(dataArray[1])) );
               }
            }
      } catch( FileNotFoundException exception ) {
            System.out.println(exception); 
         }
   }
   
   /* imports all of the mission Cards data from the inputted textfile,
    * and intializing Mission Card instances inserted into allMission array
    * @param fileName - path to access file */
   private void importMissionCard(String fileName) {
      /* Catching incorrect file input */
      try {
         //Create Scanner of file
         Scanner scan = new Scanner(new File(fileName)); 
         
         //Accesses every line in the file
            while( scan.hasNextLine() ) {
               //Access line in file
               String scanData = scan.nextLine();
                 
               //Checks to make sure the current line is not empty 
               if( !scanData.equals("") ) {
                  
                  //converts scanData into an array
                  String[] dataArray = scanData.split(":");
                  
                  //Adds initialized country object           
                  addMission( new Mission(dataArray[0], dataArray[1]) ); 
               }
            }
      } catch( FileNotFoundException exception ) {
            System.out.println(exception); 
         }
   }
   
   
   /* Converts string into 
    * @param adjacencyData - string in format x,y,z...
    * @return              - int array of data */
   private int[] stringToIntArray(String adjacencyData) {
      //Converts string into array
      String[] tempArray = adjacencyData.split(",");
      
      //Creates an int array with size lenth of tempArray
      int[] returnArray = new int[tempArray.length];
      
      //parses through every item in tempArray
      for( int i = 0; i < tempArray.length; i++ ) {
      
         //slots in the values, converting the strings into ints
         returnArray[i] = Integer.parseInt(tempArray[i]);
      }
      
      //returned int array
      return returnArray; 
   }
   
   /* Increases the size of allCountry Array, adding the inputted country object in
    * @param    - Country instance to be inputted*/
   private void addCountry(Country country) {
      //Initilize empty temp array
      Country[] tempArray; 
      
      if( this.allCountries == null ) {
         //Creates temp array with length of one
         tempArray = new Country[1];
      }
      else {
         //Create temp array with length of one longer then allCountries
         tempArray = new Country[this.allCountries.length + 1];
         
         //copies allCountries into tempArray
         for( int i = 0; i < this.allCountries.length; i++ ) {
            tempArray[i] = this.allCountries[i];
         }
      }
      //Adds given country into the last position of the temp array
      tempArray[tempArray.length-1] = country;
      //Sets allCountries to equal tempArray
      this.allCountries = tempArray;
   }
   
   /* Increases the size of allBonus Array, adding the inputted Bonus Card object in
    * @param    - Bonus Card inputted*/
   private void addBonus(Bonus bonus) {
      //Initilize empty temp array
      Bonus[] tempArray; 
      
      if( this.allBonus == null ) {
         //Creates temp array with length of one
         tempArray = new Bonus[1];
      }
      
      else {
         //Create temp array with length of one longer then allBonus
         tempArray = new Bonus[this.allBonus.length + 1];
         
         //copies allCountries into tempArray
         for( int i = 0; i < this.allBonus.length; i++ ) {
            tempArray[i] = this.allBonus[i];
         }
      }
      
      //Adds given country into the last position of the temp array
      tempArray[tempArray.length-1] = bonus;
      //Sets allCountries to equal tempArray
      this.allBonus = tempArray;
   }
   
   /* Increases the size of allMission Array, adding the inputted Mission Card object in
    * @param    - Mission Card inputted*/
   private void addMission(Mission mission) {
      //Initilize empty temp array
      Mission[] tempArray;
      
      if( this.allMission == null ) {
         //Creates temp array with length of one
         tempArray = new Mission[1];
      }
      
      else {
         //Create temp array with length of one longer then allBonus
         tempArray = new Mission[this.allMission.length + 1];
         
         //copies allCountries into tempArray
         for( int i = 0; i < this.allMission.length; i++ ) {
            tempArray[i] = this.allMission[i];
         }
      }
      
      //Adds given country into the last position of the temp array
      tempArray[tempArray.length-1] = mission;
      //Sets allCountries to equal tempArray
      this.allMission = tempArray;
   }
   
   /*=============== PUBLIC METHODS ===============*/ 
   /* Gets Country Position in allCountries array by country name
    * @param countryName  -  name of Counrty
    * @return             -  position of country in allCountries
    *                     -1 if country doesn't exist */
   public int GetCountryPos(String countryName) {   
      for( int i = 0; i < allCountries.length; i++ ) {
         if( allCountries[i].GetCountryName().toUpperCase().equals(countryName.toUpperCase()) ) {
            return i; 
         }
      }
      return -1;
   }
   
   /* Gets Country Position in allCountries array by country id
    * @param countryId    -  id of Country 
    * @return             -  position of country in allCountries
    *                     -1 if country doesn't exist */
   public int GetCountryPos(int countryId) {
      for( int i = 0; i < allCountries.length; i++ ) {
         if( allCountries[i].GetCountryId() == countryId) {
            return i; 
         }
      }
      return -1; 
   }
   
   /* Gets Country in allCountries array by its pos index
    * @param countryPos   -  position of countrin in allCountries
    * @return             -  country in the position of allCountries*/
   public Country GetCountryByPos(int countryPos) {
      return allCountries[countryPos]; 
   }
   
   /* Gets Bonus Card Position in allBonus array by card id 
    * @param cardId       -  id of Card 
    * @return             -  position of country in allCountries
    *                     -1 if card doesn't exist */  
   public int GetBonusPos(int cardId) {
      for( int i = 0; i < allBonus.length; i++ ) {
         if( allBonus[i].GetCardId() == cardId ) {
            return i;
         }
      }
      return -1; 
   }
   
   /* Gets Card in allBonus array by its pos index
    * @param bonusPos     -  position of card in allBonus
    * @return             -  Bonus card instance at position in allBonus */
   public Bonus GetBonusByPos(int bonusPos) {
      return allBonus[bonusPos]; 
   }
   
   /* Gets Mission Card Position in allMission array by missionId 
    * @param missionId       -  id of Card 
    * @return                -  position of country in allCountries
    *                        -1 if card doesn't exist */  
   public int GetMissionPos(int missionId) {
      for ( int i = 0; i < allMission.length; i++ ) {
         if( allMission[i].GetMissionId() == missionId ) {
            return i;
         }
      }
      return -1;
   }
   
   /* Gets Mision Card in allMission array by its pos index
    * @param bonusPos     -  position of card in allMission
    * @return             -  Mission card instance at position in allMission */
   public Mission GetMissionByPos(int missionPos) {
      return allMission[missionPos]; 
   }
   
   
}