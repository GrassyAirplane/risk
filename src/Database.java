/* Developed By: Euan Lim
 * Revised Date: Nov 19, 2021 */
/* Import Statements*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Database */
public class Database { 
   
   /* INSTANCE VARIABLE */
   private Country[] allCountries = new Country[Country.NUM_COUNTRIES];
   private Card[] allCards;
   
   /* FILE VARIABLES */
   private final static String FILE_PATH_COUNTRY = "../Countries.txt";
   private final static String FILE_PATH_CARD = "";
   
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
   private void importCountries(String fileName)  /*if File not found in scanner*/ {
         /* Catching incorrect file input */
         try {
            //Create Scanner of file
            Scanner scan = new Scanner(new File(fileName)); 
            
            //indexAllCountries
            int index = 0; 
            
            //Accesses every line in the file
            while( scan.hasNextLine() ) {
               //Access line in file
               String scanData = scan.nextLine();
                 
               //Checks to make sure the current line is not empty 
               if( !scanData.equals("") ) {
                  
                  //converts scanData into an array
                  String[] dataArray = scanData.split(":");
   
                  //Initialize Country object into allCountry array
                  this.allCountries[index] = new Country(dataArray[0], stringToIntArray(dataArray[1]));
                  
                  //Increments index
                  index++;
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
   
   
   /*=============== PUBLIC METHODS ===============*/ 
   
   public void DisplayCountryNames() {
      for( int i = 0; i < allCountries.length; i++ ) {
         System.out.println(allCountries[i].GetCountryName() +" "+allCountries[i].GetCountryId());
      }
   }
   
   /*
   public Country getCountry(String countryName) {   
   }
   
   public Country getCountry(int countryId) {
   }
   */
}