/* Developed By: ------
 * Revised Date: Nov 17, 2021 */

/* Mission Card Class
 * Parent  : Card */
public class Mission extends Card { 
   
  /* INSTANCE VARIABLES */
  private String missionDesc;
  private int missionId;
  
  /*=============== CONSTRUCTOR ===============*/
  public Mission(String missionDesc) {
    this.missionDesc = missionDesc;
  }
  
}