/* Developed By: Euan Lim
 * Revised Date: Nov 19, 2021 */
/* Import Statements*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Database */
public class Database { 
   
   /* INSTANCE VARIABLE */
   private Country[] allCountries = new Country[42];
   private Card[] allCards;
   
   /* FILE VARIABLES */
   private final static String FILE_PATH_COUNTRY = "../Countries.txt";
   private final static String FILE_PATH_CARD = "";
   
   /*=============== CONSTRUCTOR ===============*/
   public Database() {
   }
   
   private void importCountries(String fileName) throws FileNotFoundException {
         //Create Scanner of file
         Scanner scan = new Scanner(new File(fileName)); 
   }
}