/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 24, 2021 */

import java.util.Random;

/* Defender Player Class
 * Ability:  */
public class Defender extends Player { 
  
  /*=============== CONSTRUCTOR ===============*/
  public Defender(char playerChar, Mission playerMission) {
    super(playerChar, playerMission);
  }
  
  /*=============== PUBLIC METHODS ===============*/
  /* player being attacked defends with troops
   * @param numDefender - number of troops player has to defend country
   * @return            - dice values in an array */
  public int[] Defend(int numDefender) {
    int[] die = {1, 2, 3, 4, 5, 6};
    int[] rolls;
    // rolling die
    if (numDefender == 1) {
      rolls = new int[2]; // defenders get extra roll
      rolls[0] = new Random().nextInt(die.length);
      rolls[1] = new Random().nextInt(die.length);
    }
    else {
      rolls = new int[3]; // defenders get extra roll
      rolls[0] = new Random().nextInt(die.length);
      rolls[1] = new Random().nextInt(die.length);
      rolls[2] = new Random().nextInt(die.length);
    }
    
    // sorting the rolls array highest to lowest value
    for (int i = 0; i < rolls.length - 1; i++) {
      int currMax = rolls[i];
      int currMaxIndex = i;
      for (int j = i + 1; j < rolls.length; j++) {
        if (rolls[j] > currMax) {
          currMax = rolls[j];
          currMaxIndex = j;
        }
      }
      if (currMaxIndex != i) {
        rolls[currMaxIndex] = rolls[i];
        rolls[i] = currMax;
      }
    }
    
    return rolls;
  }

}