/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 24, 2021 */

import java.util.Random;

/* Abstract Player Class
 * Children  : Attacker
 *             Defender
 *             Producer */

public abstract class Player { 
  
  /* GLOBAL CONSTANT VARIABLES */
  public final static int ATTACKER = 1;
  public final static int DEFENDER = 2;
  public final static int PRODUCER = 3;
  
  /* INSTANCE VARIABLES */
  private int playerId;
  private char playerChar;
  private int reinforcement;
  private int[] countryOwned;
  private Bonus[] deck;
  private Mission playerMission;
  private boolean bonusStatus;
  
  // keeps track of number of players created
  private static int PlayerCount = 0;
  
  /*=============== CONSTRUCTOR ===============*/
  /* @param playerId      - ID of player
   *        reinforcement - number of troop reinforcements player can have at the start of each round
   *        countryOwned  - list of countries the player owns
   *        playerMission - player's personal win condition */
  public Player(char playerChar, Mission playerMission) {
    this.playerChar = playerChar;
    this.playerMission = playerMission;
    // increments number of players created
    PlayerCount++;
    // setting player ID
    this.playerId = this.PlayerCount;
  }
  
  /*=============== PUBLIC METHODS ===============*/
  
  /* gets player ID
   * @return - returns the player ID */
  public int GetPlayerId() {
    return this.playerId;
  }
  
  /* gets the icon used to represent the player.
   * @return - char that represents player */
  public char GetPlayerChar() {
    return this.playerChar;
  }
  
  /* gets the player mission
   * @return - the mission object that is the player's mission */
  public Mission GetPlayerMission() {
    return this.playerMission;
  }
  
  /* gets the number of troops.
   * @return - value of reinforcement variable. */
  public int GetReinforcement() {
    return this.reinforcement;
  }
  
  public void SetReinforcement(int reinforcement) {
    this.reinforcement = reinforcement;
  }
  
  /* calculates number of troop reinforcements player should have */
  public void SetReinforcement() {
    // setting the minimum number of reinforcements at the beginning of each turn
    int tempReinforcement = 3;
    // calculating number of reinforcements at the beginning of each turn
    if ((this.countryOwned.length / 3) > tempReinforcement) {
      tempReinforcement = (this.countryOwned.length / 3);
    }
    // adding continent bonus to reinforcement
    tempReinforcement += ContinentBonus();
    // assigning to reinforcement instance variable
    this.reinforcement = tempReinforcement;
  }
  
  /* Checks if the player owns a countinent by checking if a list of countries 
   * that make a continent is within the the list of countries that the player owns
   * @param list - continent array to check if it exists in the player's countries list
   * @return     - true or false depending on whether the player
   *               has all the countries in the list */
  public boolean CheckContinent(int[] list) {
    
    int count = 0;
    
    //Loops through player list
    for(int i = 0; i < this.countryOwned.length; i++) {
      //loops through continent list
      for(int j = 0; j < list.length; j++) {
         if(this.countryOwned[i] == list[j]) {
            count ++;
         }
      }
    }
    
    return count == list.length;
  }
  
  /* checks if player owns a continent and gets the number of bonus troops
   * @return - number of bonus troops the player gets by owning the continent
   *           returns 0 if player does not own any continents */
  public int ContinentBonus() {
    int sumBonus = 0;
    int[] nAmerica = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] sAmerica = {9, 10, 11, 12};
    int[] europe = {13, 14, 15, 16, 17, 18, 19};
    int[] africa = {20, 21, 22, 23, 24, 25};
    int[] asia = {26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
    int[] australia = {38, 39, 40, 41};
    if (CheckContinent(nAmerica)) {
      sumBonus += Country.NORTH_AMERICA_BONUS;
    }
    if (CheckContinent(sAmerica)) {
      sumBonus += Country.SOUTH_AMERICA_BONUS;
    }
    if (CheckContinent(europe)) {
      sumBonus += Country.EUROPE_BONUS;
    }
    if (CheckContinent(africa)) {
      sumBonus += Country.AFRICA_BONUS;
    }
    if (CheckContinent(asia)) {
      sumBonus += Country.ASIA_BONUS;
    }
    if (CheckContinent(australia)) {
      sumBonus += Country.AUSTRALIA_BONUS;
    }
    return sumBonus;
  }
  
  /* gets the countries owned by the player
   * @return - array that stores the list of countries the player owns */
  public int[] GetCountryOwned() {
    return this.countryOwned;
  }
  
  /* gets the position of a country in the countryOwned array
   * @param id - ID of country to get the position of
   * @return   - position of the country in the countryOwned array
   *           - -1 if country does not exist */
  public int GetCountryOwnedPos(int id) {
    for (int i = 0; i < this.countryOwned.length; i++) {
      if (this.countryOwned[i] == id) {
        return i;
      }
    }
    return -1;
  }
  
  /* gets the bonus deck owned by the player
   * @return - array that stores the list of bonus deck */
  public Bonus[] GetBonusDeck() {
   return this.deck;
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
  
  /* adds a country to list of countries owned by player.
   * @param id - ID of the country to add */
  public void AddCountry(int id) {
    int[] temp;
    if (this.countryOwned == null) {
      temp = new int[1];
    }
    else {
      temp = new int[this.countryOwned.length + 1];
      
      // transferring all the items in countryOwned to temp
      for (int i = 0; i < this.countryOwned.length; i++) {
        temp[i] = this.countryOwned[i];
      }
    }
    
    // adding the new country in to the last position
    temp[temp.length - 1] = id;
    this.countryOwned = temp;
  }
  
  /* removes a country from the list of countries owned by player.
   * @param pos - position of the country to remove */
  public void RemoveCountry(int pos) {
    int[] temp = new int[this.countryOwned.length - 1];
    int newIndex = 0;
    
    // removing item at the position
    for( int i = 0; i < this.countryOwned.length; i++ ){
       if( i != pos ) {
          temp[newIndex] = this.countryOwned[i];
          newIndex++;
       }
    }
    
    // setting countryOwned to temp
    this.countryOwned = temp;
  }
  
  /* adds bonus card to player's bonus deck.
   * @param bonus - bonus object to add */
  public void AddBonusToPlayer(Bonus bonus) {
    Bonus[] temp;
    if (this.deck == null) {
      temp = new Bonus[1];
    }
    else {
      temp = new Bonus[this.deck.length + 1];
      
      // transferring all the items in deck to temp
      for (int i = 0; i < this.deck.length; i++) {
        temp[i] = this.deck[i];
      }
    }
    
    // adding the new bonus in to the last position
    temp[temp.length - 1] = bonus;
    this.deck = temp;
  }
  
  /* removes bonus card from player's bonus deck.
   * @param pos - position of bonus object to remove */
  public void RemoveBonusFromPlayer(int pos) {
    if (this.deck.length < 2) {
      this.deck = null;
    }
    else {
      Bonus[] temp = new Bonus[this.deck.length - 1];
      int newIndex = 0;
      
      // removing item at the position
      for( int i = 0; i < this.deck.length; i++ ){
        if( i != pos ) {
          temp[newIndex] = this.deck[i];
          newIndex++;
        }
      }
      
      // setting deck to temp
      this.deck = temp;
    }
  }
}