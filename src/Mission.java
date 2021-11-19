/* Developed By: Euan
 * Revised Date: Nov 19, 2021 */

/* Mission Card Class
 * Parent  : Card */
public class Mission extends Card { 
   
  /* INSTANCE VARIABLES */
  private String missionDesc;
  private int missionId;
  
  //keeps track of num Cards created
   public static int MissionCount = 0;
   
  /*=============== CONSTRUCTOR ===============*/
  public Mission( String cardName, String missionDesc ) {
    super(cardName);
    this.missionDesc = missionDesc;
    this.missionId = MissionCount; 
      
      //Increments Card Count
      MissionCount++;
  }
  
  /*=============== PUBLIC METHODS ===============*/
  /* Gets Card's missionId 
    * @return - missionId */
   public int GetMissionId() {
      return this.missionId;
   }
  
}