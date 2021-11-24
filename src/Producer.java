/* Developed By: Zoe
 * Revised Date: Nov 22, 2021 */

/* Producer Player Class
 * Ability:  */
public class Producer extends Player {
  
  /*=============== CONSTRUCTOR ===============*/
  public Producer(char playerChar, Mission playerMission) {
    super(playerChar, playerMission);
  }
  
  /*=============== PUBLIC METHODS ===============*/
  /* calculates number of troop reinforcements player should have */
  public void SetReinforcement() {
    // setting the minimum number of reinforcements at the beginning of each turn
    int tempReinforcement = 3;
    // calculating number of reinforcements at the beginning of each turn
    // producers have a different calculation for reinforcements
    // instead of dividing by 3, producers divide by 2 and then subtract 1
    if (((GetCountryOwned().length / 2) - 1) > tempReinforcement) {
      tempReinforcement = ((GetCountryOwned().length / 2) - 1);
    }
    // adding continent bonus to reinforcement
    tempReinforcement += continentBonus();
    // setting temp reinforcement to reinforcement
    SetReinforcement(tempReinforcement);
  }

}