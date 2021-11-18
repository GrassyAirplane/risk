/* Developed By: ------
 * Revised Date: Nov 17, 2021 */

/* Abstract Player Class
 * Children  : Attacker
 *             Defender
 *             Producer */

public abstract class Player { 
  
  // instance variables
  private int playerId;
  private int reinforcement;
  private Country[] countryOwned;
  private Bonus[] deck;
  private Mission playerMission;
  private boolean bonusStatus;
  
  // keeps track of number of players created
  public static int PlayerCount = 0;
  
  /* constructs a new player
   * @param playerId      - ID of player
   *        reinforcement - number of troop reinforcements player can have at the start of each round
   *        countryOwned  - list of countries the player owns
   *        playerMission - player's personal win condition */
  public Player(int playerId, int reinforcement, Country[] countryOwned, Mission playerMission) {
    this.playerId = playerId;
    this.reinforcement = reinforcement;
    this.countryOwned = countryOwned;
    this.playerMission = playerMission;
    // increments number of players created
    PlayerCount++;
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
  
  public void SetBonusStatus(boolean status) {
    this.bonusStatus = status;
  }
  
  public int[] Attack(Country playerPos, Country enemyPos, int numAttacker) {
    int[] placeholder = {1};
    return placeholder;
  }
  
  public int[] Defender(int numDefender) {
    int[] placeholder = {1};
    return placeholder;
  }
  
}