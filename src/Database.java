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
   private Card[] allBonus;
   private Card[] allMission;
   
   /* FILE VARIABLES */
   private final static String FILE_PATH_COUNTRY = "../Countries.txt";
   private final static String FILE_PATH_BONUS = "../Bonus.txt";
   
   /*=============== CONSTRUCTOR ===============*/
   public Database()  /*if File not found in scanner*/ {
      //Imports Country data 
      importCountries(FILE_PATH_COUNTRY);
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
                  addCountry( new Country(dataArray[0], stringToIntArray(dataArray[1])) );
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
   private void addBonus(Card bonus) {
      //Initilize empty temp array
      Card[] tempArray; 
      
      if( this.allBonus == null ) {
         //Creates temp array with length of one
         tempArray = new Card[1];
      }
      
      else {
         //Create temp array with length of one longer then allCountries
         tempArray = new Card[this.allBonus.length + 1];
         
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
   
}