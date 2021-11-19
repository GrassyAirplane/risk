/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 19, 2021 */

import java.util.Random;

/* Abstract Player Class
 * Children  : Attacker
 *             Defender
 *             Producer */

public abstract class Player { 
  
  /* INSTANCE VARIABLES */
  private int playerId;
  private char playerChar;
  private int reinforcement;
  private Country[] countryOwned;
  private Bonus[] deck;
  private Mission playerMission;
  private boolean bonusStatus;
  
  // keeps track of number of players created
  public static int PlayerCount = 0;
  
  /*=============== CONSTRUCTOR ===============*/
  /* @param playerId      - ID of player
   *        reinforcement - number of troop reinforcements player can have at the start of each round
   *        countryOwned  - list of countries the player owns
   *        playerMission - player's personal win condition */
  public Player(char playerChar, int reinforcement, Country[] countryOwned, Mission playerMission) {
    this.playerChar = playerChar;
    this.reinforcement = reinforcement;
    this.countryOwned = countryOwned;
    this.playerMission = playerMission;
    // increments number of players created
    PlayerCount++;
    // setting player ID
    this.playerId = this.PlayerCount;
  }
  
  /*=============== PUBLIC METHODS ===============*/
  
  /* gets the icon used to represent the player.
   * @return - char that represents player */
  public char GetPlayerChar() {
    return this.playerChar;
  }
  
  /* gets the number of troops.
   * @return - value of reinforcement variable. */
  public int GetReinforcement() {
    return this.reinforcement;
  }
  
  /* reassigns the number of reinforcements.
   * @param reinforcement - number of troops to set to. */
  public void SetReinforcement(int reinforcement) {
    this.reinforcement = reinforcement;
  }
  
  /* cashes in card bonuses for extra troops.
   * @param bonusCount - number of troops to add to reinforcements. */
  public void Bonus(int bonusCount) {
    // checking if player has necessary cards to cash in
    if (bonusCount == 4) {
      int numInfantry = 0; // keeps track of number of infantry bonuses
      for (int i = 0; i < deck.length; i++) {
        
      }
    }
    else if (bonusCount == 6) {
      
    }
    else if (bonusCount == 8) {
      
    }
    else if (bonusCount == 10) {
      
    }
  }
  
  /* gets the status indicating if player can get a bonus,
   * the condition being that the player has conquered at least
   * one territory in his/her round.
   * @return - a boolean, true being the player can get a bonus
   *           false being the player cannot get a bonus. */
  public boolean GetBonusStatus() {
    return this.bonusStatus;
  }
  
  /* reassigns the bonus status
   * @param status - boolean value to set bonusStatus to */
  public void SetBonusStatus(boolean status) {
    this.bonusStatus = status;
  }
  
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
      rolls = new int[1];
      rolls[0] = die[new Random().nextInt(die.length)];
    }
    else if (numAttacker == 2) {
      rolls = new int[2];
      rolls[0] = die[new Random().nextInt(die.length)];
      rolls[1] = die[new Random().nextInt(die.length)];
    }
    else {
      rolls = new int[3];
      rolls[0] = die[new Random().nextInt(die.length)];
      rolls[1] = die[new Random().nextInt(die.length)];
      rolls[2] = die[new Random().nextInt(die.length)];
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
  
  /* player being attacked defends with troops
   * @param numDefender - number of troops player has to defend country
   * @return            - dice values in an array */
  public int[] Defend(int numDefender) {
    int[] die = {1, 2, 3, 4, 5, 6};
    int[] rolls;
    // rolling die
    if (numDefender == 1) {
      rolls = new int[1];
      rolls[0] = new Random().nextInt(die.length);
    }
    else {
      rolls = new int[2];
      rolls[0] = new Random().nextInt(die.length);
      rolls[1] = new Random().nextInt(die.length);
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