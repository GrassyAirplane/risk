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
   public Database() {
   }
   
   /*=============== PRIVATE METHODS ===============*/
   /* imports all of the country data from the inputted textfile, 
    * and initializing country instances which are then inserted
    * into allCountries Country array. 
    * @param fileName - path to access file */
   private void importCountries(String fileName) throws FileNotFoundException /*if File not found in scanner*/ {
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
               
               
            
            }
            
            
         
         }
         
   }
   
   
   /*=============== PUBLIC METHODS ===============*/ 
   
   /*
   public Country getCountry(String countryName) {   
   }
   
   public Country getCountry(int countryId) {
   }
   */
}