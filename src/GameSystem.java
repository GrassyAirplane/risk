/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 23, 2021 */

/* Attacker Player Class
 * Ability:  */
public class GameSystem {
  
  /* GLOBAL CONSTANT VARIABLES */
  // Troop Type
  public final static int INFANTRY_TROOP = 1;
  public final static int HORSE_TROOP    = 2;
  public final static int CANNON_TROOP   = 3;
  
  /* INSTANCE VARIABLES */
  private Player currPlayer;
  
  // array to store all players
  private Player[] allPlayer;

  private Database db = new Database(); 
  private Displayer disp = new Displayer(db);
  
  /*=============== PUBIC METHODS ===============*/
  
  /* gets db
   * @return - returns db */
  public Database GetDb() {
    return this.db;
  }
  
  /* creates a new player and adds it to allPlayer array
   * @param playerChar - player icon
   *        reinforcement - number of troop reinforcements at beginning of turn
   *        countryOwned - list of countries owned by player
   *        playerMission - personal win condition */
  public void CreatePlayer(int playerType, char playerChar, int reinforcement, int[] countryOwned, Mission playerMission) {
    switch (playerType) {
      case Player.ATTACKER:
        // creates attacker type player and adds it to allPlayer array
        AddPlayer(new Attacker(playerChar, reinforcement, countryOwned, playerMission));
        break;
      case Player.DEFENDER:
        // creates defender type player and adds it to allPlayer array
        AddPlayer(new Defender(playerChar, reinforcement, countryOwned, playerMission));
        break;
      case Player.PRODUCER:
        // creates producer type player and adds it to allPlayer array
        AddPlayer(new Producer(playerChar, reinforcement, countryOwned, playerMission));
        break;
    }
  }
  
  /* distribute countries among players at the start of game */
  public void DistributeCountry() {
    
  }
  
  /* adds new player to allPlayer array
   * @param player - player object to add */
  public void AddPlayer(Player player) {
    // creating new temporary array with bigger size
    Player[] temp = new Player[this.allPlayer.length + 1];
    // transferring items to temp array
    for (int i = 0; i < this.allPlayer.length; i++) {
      temp[i] = this.allPlayer[i];
    }
    // adding new player at the end of temp array
    temp[temp.length - 1] = player;
    // set allPlayer equal to temp array
    this.allPlayer = temp;
  }
  
  /* removes a player from allPlayer array
   * @param player - player object to remove */
  public void RemovePlayer(Player player) {
    // creating new temp array with smaller size
    Player[] temp = new Player[this.allPlayer.length - 1];
    // transferring items to temp array except item to remove
    for (int i = 0; i < this.allPlayer.length; i++) {
      if (allPlayer[i] != player) {
        temp[i] = this.allPlayer[i];
      }
    }
    // set allPlayer equal to temp array
    this.allPlayer = temp;
  }

  /* switch current player to give next player a turn.
   * @param currPlayer - player object of current player that just played their turn */
  public void RotatePlayer(Player currPlayer) {
    if (allPlayer[allPlayer.length - 1] == currPlayer) {
      this.currPlayer = allPlayer[0];
    }
    else {
      for (int i = 0; i < allPlayer.length - 1; i++) {
        if (allPlayer[i] == currPlayer) {
          this.currPlayer = allPlayer[i + 1];
        }
      }
    }
  }
  
  /* checks if player being attacked has lost
   * @param player - player to check if eliminated
   * @return       - 1 if player has lost and is removed
   *                 -1 if player has not lost */
  public int RemoveLoser(Player player) {
    // checks if player is eliminated by seeing if they no longer own any countries
    if (player.GetCountryOwned().length == 0) {
      RemovePlayer(player);
      return 1;
    }
    else {
      return -1;
    }
  }

  /* move troops from one country to another
   * @param fromCountry - ID of country the troops are moved from
   *        toCountry   - ID country the troops are moving to
   *        numTroops   - number of troops being moved
   * @return            - 1 if successful, -1 if unsuccessful */
  public int Move(Country fromCountry, Country toCountry, int numTroops) {
    // checks if countries are adjacents of one another
    if (fromCountry.isAdjacent(toCountry.GetCountryId())) {
      // checks if there are enough troops to move
      if (fromCountry.GetTroopCount() > numTroops) {
        fromCountry.SetTroopCount(fromCountry.GetTroopCount() - numTroops); // subtracting number of troops
        toCountry.SetTroopCount(toCountry.GetTroopCount() + numTroops); // adding troops to destination country
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

  /* Compares dice rolls and transfers ownership if attacker successfully conquered the country
   * @param attack        - attacking player
   *        defend        - defending player
   *        numAttackers  - number of troops the attacker is using to battle
   *        countryAttack - place the attacker is attacking from
   *        countryDefend - place the attacker wants to conquer */
  public void Battle(Player attack, Player defend, int numAttackers, Country countryAttack, Country countryDefend) {
    // checking if both countries are adjacent to one another
    if (countryAttack.isAdjacent(countryDefend.GetCountryId())) {
      int[] rollAttack = attack.Attack(numAttackers);
      int[] rollDefend = defend.Defend(countryDefend.GetTroopCount());
      if (rollAttack.length < 2 || rollDefend.length < 2) {
        if (rollAttack[0] > rollDefend[0]) {
          countryDefend.SetTroopCount(countryDefend.GetTroopCount() - 1);
        }
        else {
          countryAttack.SetTroopCount(countryAttack.GetTroopCount() - 1);
          numAttackers--;
        }
      }
      else {
        for (int i = 0; i < rollDefend.length; i++) {
          if (rollAttack[i] > rollDefend[i]) {
            countryDefend.SetTroopCount(countryDefend.GetTroopCount() - 1);
          }
          else {
            countryAttack.SetTroopCount(countryAttack.GetTroopCount() - 1);
            numAttackers--;
          }
        }
      }
      // if attacker successfully conquered the country
      if (countryDefend.GetTroopCount() < 1) {
        // transferring country ownership to attacker
        countryDefend.SetOwner(attack);
        // transferring troops over
        countryDefend.SetTroopCount(numAttackers);
        countryAttack.SetTroopCount(countryAttack.GetTroopCount() - numAttackers);
      }
    }
  }
  
  /* gets the current player
   * @return - returns player object that is the current player */
  public Player GetCurrPlayer() {
    return this.currPlayer;
  }
  
  /* sets the current player
   * @param player - player object to set the current player */
  public void SetCurrPlayer(Player player) {
    this.currPlayer = player;
  }
  
}