/* Developed By: ------
 * Revised Date: Nov 17, 2021 */

import java.io.FileNotFoundException;

public class MainRisk {
   public static void main(String [] args) {
      //Main Code
      
      /*
      int[] blank = {0,5,3};
      Country Malaysia = new Country("Malaysia", blank);
      Country Singapore = new Country("Singapore", blank);
      
      System.out.println(Malaysia.GetCountryId() + " " + Malaysia.GetContinentId());
      System.out.println(Singapore.GetCountryId() + " " + Singapore.GetContinentId());
      
      System.out.println(Malaysia.isAdjacent(Singapore.GetCountryId()));
      
      System.out.println(Singapore.isAdjacent(Malaysia.GetCountryId()));
      //public Country( String countryName, int continentId, int[] adjacency ) {
      */
      
      Database db = new Database();
      db.DisplayCountryNames(); 
      
   }  
}