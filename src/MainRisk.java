/* Developed By: ------
 * Revised Date: Nov 17, 2021 */

//Zoe Added Comment

public class MainRisk {
   public static void main(String [] args) {
      //Main Code
      
      int[] blank = {0,5,3};
      Country Malaysia = new Country("Malaysia", 1, blank);
      Country Singapore = new Country("Singapore", 1, blank);
      
      System.out.println(Malaysia.GetCountryId() + " " + Malaysia.GetContinentId());
      System.out.println(Singapore.GetCountryId() + " " + Singapore.GetContinentId());
      
      System.out.println(Malaysia.isAdjacent(Singapore.GetCountryId()));
      
      System.out.println(Singapore.isAdjacent(Malaysia.GetCountryId()));
      //public Country( String countryName, int continentId, int[] adjacency ) {

   }
}