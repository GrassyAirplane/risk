/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 23, 2021 */

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
  private int[] countryOwned;
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
  public Player(char playerChar, int reinforcement, int[] countryOwned, Mission playerMission) {
    this.playerChar = playerChar;
    this.reinforcement = reinforcement;
    this.countryOwned = countryOwned;
    this.playerMission = playerMission;
    // increments number of players created
    PlayerCount++;
    // setting player ID
    this.playerId = this.PlayerCount;
  }
  
  /*=============== PRIVATE METHODS ===============*/
  
  /* checks if player owns a continent and gets the number of bonus troops
   * @return - number of bonus troops the player gets by owning the continent
   *           returns 0 if player does not own any continents */
  private int continentBonus() {
    int sumBonus = 0;
    int[] nAmerica = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] sAmerica = {9, 10, 11, 12};
    int[] europe = {13, 14, 15, 16, 17, 18, 19};
    int[] africa = {20, 21, 22, 23, 24, 25};
    int[] asia = {26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
    int[] australia = {38, 39, 40, 41};
    if (checkContinent(nAmerica)) {
      sumBonus += Country.NORTH_AMERICA_BONUS;
    }
    if (checkContinent(sAmerica)) {
      sumBonus += Country.SOUTH_AMERICA_BONUS;
    }
    if (checkContinent(europe)) {
      sumBonus += Country.EUROPE_BONUS;
    }
    if (checkContinent(africa)) {
      sumBonus += Country.AFRICA_BONUS;
    }
    if (checkContinent(asia)) {
      sumBonus += Country.ASIA_BONUS;
    }
    if (checkContinent(australia)) {
      sumBonus += Country.AUSTRALIA_BONUS;
    }
    return sumBonus;
  }
  
  /* checks if a list of countries that make a continent 
   * is within the the list of countries that the player owns
   * @param list1 - list to check if it is in another list
   *        list2 - list to check if it contains list1
   * @return      - true or false depending on whether the player
   *                has all the countries in the list */
  private boolean checkContinent(int[] list) {
    boolean hasList = false;
    for (int i = 0; i < list.length; i++) {
      hasList = false;
      for (int j = 0; j < this.countryOwned.length; j++) {
        if (list[i] == this.countryOwned[i]) {
          hasList = true;
          break;
        }
      }
      if (!hasList) {
        break;
      }
    }
    return hasList;
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
  
  /* calculates number of troop reinforcements player should have */
  public void SetReinforcement() {
    // setting the minimum number of reinforcements at the beginning of each turn
    int tempReinforcement = 3;
    // calculating number of reinforcements at the beginning of each turn
    if ((this.countryOwned.length / 3) > tempReinforcement) {
      tempReinforcement = (this.countryOwned.length / 3);
    }
    // adding continent bonus to reinforcement
    tempReinforcement += continentBonus();
    // assigning to reinforcement instance variable
    this.reinforcement = tempReinforcement;
  }
  
  /* gets the countries owned by the player
   * @return - array that stores the list of countries the player owns */
  public int[] GetCountryOwned() {
    return this.countryOwned;
  }
  
  /* cashes in card bonuses for extra troops.
   * @param t1 - position in deck array of first bonus card to cash in
   *        t2 - position in deck array of second bonus card to cash in
   *        t3 - position in deck array of third bonus card to cash in
   * @return   - 1 if bonus troops are successfully added to reinforcements
   *           - -1 if the player do not have the required bonuses to cash in */
  public int Bonus(int t1, int t2, int t3) {
    int numInfantry = 0; // keeps track of number of infantry bonuses
    int numHorse = 0; // keeps track of number of horse bonuses
    int numCannon = 0; // keeps track of number of cannon bonuses
    
    // counting number of troops for each type
    for (int i = 0; i < deck.length; i++) {
      if (deck[i].GetTroopBonusType() == GameSystem.INFANTRY_TROOP) {
        numInfantry++;
      }
      else if (deck[i].GetTroopBonusType() == GameSystem.HORSE_TROOP) {
        numHorse++;
      }
      else if (deck[i].GetTroopBonusType() == GameSystem.CANNON_TROOP) {
        numCannon++;
      }
    }
    
    // checking if all troops are the same type
    if (deck[t1].GetTroopBonusType() == deck[t2].GetTroopBonusType() && deck[t1].GetTroopBonusType() == deck[t3].GetTroopBonusType()) {
      if (deck[t1].GetTroopBonusType() == GameSystem.INFANTRY_TROOP) {
        // checking for infantry bonus
        if (numInfantry >= 3) {
          this.reinforcement += 4;
          RemoveBonus(t1);
          RemoveBonus(t2);
          RemoveBonus(t3);
          return 1;
        }
        else {
          return -1;
        }
      }
      else if (deck[t1].GetTroopBonusType() == GameSystem.HORSE_TROOP) {
        // checking for horse bonus
        if (numHorse >= 3) {
          this.reinforcement += 6;
          RemoveBonus(t1);
          RemoveBonus(t2);
          RemoveBonus(t3);
          return 1;
        }
        else {
          return -1;
        }
      }
      else if (deck[t1].GetTroopBonusType() == GameSystem.CANNON_TROOP) {
        // checking for cannon bonus
        if (numCannon >= 3) {
          this.reinforcement += 8;
          RemoveBonus(t1);
          RemoveBonus(t2);
          RemoveBonus(t3);
          return 1;
        }
        else {
          return -1;
        }
      }
      else {
        return -1;
      }
    }
    else if (deck[t1].GetTroopBonusType() != deck[t2].GetTroopBonusType() && 
             deck[t2].GetTroopBonusType() != deck[t3].GetTroopBonusType() && 
             deck[t1].GetTroopBonusType() != deck[t3].GetTroopBonusType()) {
      if (numInfantry > 0 && numHorse > 0 && numCannon > 0) {
        this.reinforcement += 10;
        RemoveBonus(t1);
        RemoveBonus(t2);
        RemoveBonus(t3);
        return 1;
      }
      else {
        return -1;
      }
    }
    else {
      return -1;
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
  public void AddBonus(Bonus bonus) {
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
  public void RemoveBonus(int pos) {
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