/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 24, 2021 */

import java.util.Random;

/* Attacker Player Class
 * Ability:  */
public class Attacker extends Player { 
  
  /*=============== CONSTRUCTOR ===============*/
  public Attacker(char playerChar, Mission playerMission) {
    super(playerChar, playerMission);
  }
  
  /*=============== PUBLIC METHODS ===============*/
  /* attacks from one country to another
   * @param playerPos   - country the player is attacking from
   *        enemyPos    - country that is being attacked
   *        numAttacker - number of troops being used to attack
   * @return            - returns the dice values in an array */
  public int[] Attack(int numAttacker) {
    int[] die = {1, 2, 3, 4, 5, 6};
    int[] rolls;
    // rolling die
    if (numAttacker == 1) {
      rolls = new int[2]; // attackers get extra roll
      rolls[0] = die[new Random().nextInt(die.length)];
      rolls[1] = die[new Random().nextInt(die.length)];
    }
    else if (numAttacker == 2) {
      rolls = new int[3]; // attackers get extra roll
      rolls[0] = die[new Random().nextInt(die.length)];
      rolls[1] = die[new Random().nextInt(die.length)];
      rolls[2] = die[new Random().nextInt(die.length)];
    }
    else {
      rolls = new int[4]; // attackers get extra roll
      rolls[0] = die[new Random().nextInt(die.length)];
      rolls[1] = die[new Random().nextInt(die.length)];
      rolls[2] = die[new Random().nextInt(die.length)];
      rolls[3] = die[new Random().nextInt(die.length)];
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