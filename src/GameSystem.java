/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 24, 2021 */

import java.util.Random;

/* Attacker Player Class
 * Ability:  */
public class GameSystem {
  
  /* GLOBAL CONSTANT VARIABLES */
  // Troop Type
  public final static int INFANTRY_TROOP = 1;
  public final static int HORSE_TROOP    = 2;
  public final static int CANNON_TROOP   = 3;
  
  // return values for error checking
  public final static int SUCCESSFUL        = 1;
  public final static int INVALID_OWNER     = 0;
  public final static int INADEQUATE_TROOPS = -1;
  public final static int INADEQUATE_BONUS  = -2;
  public final static int NOT_ADJACENT      = -3;
  
  /* INSTANCE VARIABLES */
  private Player currPlayer;
  
  // array to store all players
  private Player[] allPlayer;

  private Database db = new Database(); 
  private Displayer disp = new Displayer(db);
  
  /*=============== PUBIC METHODS ===============*/
  
  public void print() {
  Country[] country = db.GetAllCountries();
   
   
  
   for(int i = 0; i < country.length; i++) {
      //System.out.println(country[i].GetOwner().GetPlayerChar());
      System.out.println(country[i].GetCountryName());
   }
  }
  
  
  
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
  public void CreatePlayer(int playerType, char playerChar) {
    
    Mission playerMission = db.GetAllMission()[new Random().nextInt(db.GetAllMission().length - 1)]; 
    
    switch (playerType) {
      case Player.ATTACKER:
        // creates attacker type player and adds it to allPlayer array
        db.AddPlayer(new Attacker(playerChar, playerMission));
        break;
      case Player.DEFENDER:
        // creates defender type player and adds it to allPlayer array
        db.AddPlayer(new Defender(playerChar, playerMission));
        break;
      case Player.PRODUCER:
        // creates producer type player and adds it to allPlayer array
        db.AddPlayer(new Producer(playerChar, playerMission));
        break;
    }
  }
  
  /* distribute countries among players at the start of game */
  /*
  public void DistributeCountry() {
    int numCountry = db.GetAllCountries().length / db.GetAllPlayer().length; // number of countries each player gets
    
    System.out.println("works");
    
    for (int i = 0; i < db.GetAllPlayer().length; i++) {
      System.out.println("works 2");
      for (int j = 0; j <= numCountry; j++) {
         System.out.println("works 3");
        // randomly choose a country by randomizing the index
        Country countryAdd = db.GetAllCountries()[new Random().nextInt(db.GetAllCountries().length - 1)];
        // check if the country has an owner already
        if (countryAdd.GetOwner() == null) {
        System.out.println("works 4");
          // setting owner to player if there is no owner
          countryAdd.SetOwner(db.GetAllPlayer()[i]);
          // adding country to player's individual list of countries
          db.GetAllPlayer()[i].AddCountry(countryAdd.GetCountryId());
        }
        else {
          System.out.println("works 5");
          // don't do anything and repeat the process of taking a random country from list
          // j has to be decremented for player to end up with the same number of countries as intended
          j--;
        }
      }
    }
    System.out.println("works 6");
    
    int playerIndex = 0; // keeps track of player index
    // gets the remainder countries that are not assigned and assigns to players
    // players getting assigned the remainder countries are rotated
    for (int i = 0; i < db.GetAllCountries().length; i++) {
      if (db.GetAllCountries()[i].GetOwner() == null) {
        // sets owner to player
        db.GetAllCountries()[i].SetOwner(db.GetAllPlayer()[playerIndex]);
        // adds country to player's list of countries
        db.GetAllPlayer()[playerIndex].AddCountry(db.GetAllCountries()[i].GetCountryId());
        playerIndex++; // increments player index for rotation
      }
    }
  }
  */
  
  public void DistributeCountry() {
    int numCountry = db.GetAllCountries().length / db.GetAllPlayer().length;
    boolean breakStatus = false; // to check if should break when checking if all players have enough countries
    int i;
    for (i = 0; i < db.GetAllCountries().length; i++) {
      // check if all players have enough countries
      for (int j = 0; j < db.GetAllPlayer().length; j++) {
         if(db.GetAllPlayer()[j].GetCountryOwned() == null) {
            breakStatus = false;
        }
      
        else if (db.GetAllPlayer()[j].GetCountryOwned().length < numCountry) {
          // if player's country list isn't full, don't break out of outer loop
          breakStatus = false;
        }
        else {
          // don't break until the end of the list to check if all players' lists are full
          // if all the players' country lists are full, break out of outer loop
          breakStatus = true;
        }
        if (!breakStatus) {
          // breaks out of inner loop for player's that don't have a full list
          break;
        }
      }
      if (breakStatus) {
        // breaks out of outer loop if all players have a full list
        break;
      }
      // randomize player index
      int playerIndex = new Random().nextInt(db.GetAllPlayer().length - 1);
      // check if player's list is full
      if (db.GetAllPlayer()[playerIndex].GetCountryOwned().length < numCountry) {
        // set player as owner of the country
        db.GetAllCountries()[i].SetOwner(db.GetAllPlayer()[playerIndex]);
        // add country to player's country list
        db.GetAllPlayer()[playerIndex].AddCountry(db.GetAllCountries()[i].GetCountryId());
      }
      else {
        // if full
        i--; // don't increment i to find another player to add the country to
      }
    }
    int playerIndex = db.GetAllPlayer().length - 1; // set player index to the last position
    // assigns remaining countries to players starting from the last position
    for (int j = i; j < db.GetAllCountries().length; j++) {
      // set player as owner of the country
      db.GetAllCountries()[j].SetOwner(db.GetAllPlayer()[playerIndex]);
      // add country to player's country list
      db.GetAllPlayer()[playerIndex].AddCountry(db.GetAllCountries()[j].GetCountryId());
      // rotating player index
      if (playerIndex == 0) {
        playerIndex = db.GetAllPlayer().length - 1;
      }
      else {
        playerIndex--;
      }
    }
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
   * @return       - true if player has lost and is removed
   *                 false if player has not lost */
  public boolean RemoveLoser(Player player) {
    // checks if player is eliminated by seeing if they no longer own any countries
    if (player.GetCountryOwned().length == 0) {
      db.RemovePlayer(player);
      return true;
    }
    else {
      return false;
    }
  }

  /* move troops from one country to another
   * @param fromCountry - ID of country the troops are moved from
   *        toCountry   - ID country the troops are moving to
   *        numTroops   - number of troops being moved
   * @return            - 1 if successful,
   *                    - -1 if not enough troops,
   *                    - -3 if not adjacent */
  public int Move(Country fromCountry, Country toCountry, int numTroops) {
    // checks if countries are adjacents of one another
    if (fromCountry.isAdjacent(toCountry.GetCountryId())) {
      // checks if there are enough troops to move
      if (fromCountry.GetTroopCount() > numTroops) {
        fromCountry.SetTroopCount(fromCountry.GetTroopCount() - numTroops); // subtracting number of troops
        toCountry.SetTroopCount(toCountry.GetTroopCount() + numTroops); // adding troops to destination country
        return SUCCESSFUL;
      }
      else {
        return INADEQUATE_TROOPS;
      }
    }
    else {
      return NOT_ADJACENT;
    }
  }

  /* Compares dice rolls and transfers ownership if attacker successfully conquered the country
   * @param attack        - attacking player
   *        defend        - defending player
   *        numAttackers  - number of troops the attacker is using to battle
   *        countryAttack - place the attacker is attacking from
   *        countryDefend - place the attacker wants to conquer
   * @return              - 1 if successful
   *                      - 0 if player does not own the country
   *                      - -1 if player does not have enough troops
   *                      - -3 if countries are not adjacent */
  public int Battle(Player attack, Player defend, int numAttackers, Country countryAttack, Country countryDefend) {
    for (int i = 0; i < attack.GetCountryOwned().length; i++) {
      boolean ownsCountry = false;
      if (attack.GetCountryOwned()[i] == countryAttack.GetCountryId()) {
        ownsCountry = true;
      }
      if (!ownsCountry) {
        return INVALID_OWNER;
      }
    }
    for (int i = 0; i < defend.GetCountryOwned().length; i++) {
      boolean ownsCountry = false;
      if (defend.GetCountryOwned()[i] == countryDefend.GetCountryId()) {
        ownsCountry = true;
      }
      if (!ownsCountry) {
        return INVALID_OWNER;
      }
    }
    // checking if both countries are adjacent to one another
    if (countryAttack.isAdjacent(countryDefend.GetCountryId())) {
      // creating arrays to store dice roll values for attacker and defender
      if (countryDefend.GetTroopCount() > numAttackers) {
        // checking if player has enough troops to attack
        int[] rollAttack = attack.Attack(numAttackers);
        int[] rollDefend = defend.Defend(countryDefend.GetTroopCount());
        if (rollAttack.length < 2 || rollDefend.length < 2) {
          // if only one roll is compared
          if (rollAttack[0] > rollDefend[0]) {
            // if attacker wins, defender loses one troop
            countryDefend.SetTroopCount(countryDefend.GetTroopCount() - 1);
          }
          else {
            // if defender wins, attacker loses one troop
            countryAttack.SetTroopCount(countryAttack.GetTroopCount() - 1);
            numAttackers--;
          }
        }
        else {
          for (int i = 0; i < rollDefend.length; i++) {
            // if two rolls are compared
            if (rollAttack[i] > rollDefend[i]) {
              // if attacker wins, defender loses one troop
              countryDefend.SetTroopCount(countryDefend.GetTroopCount() - 1);
            }
            else {
              // if defender wins, attacker loses one troop
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
        return SUCCESSFUL;
      }
      else {
        return INADEQUATE_TROOPS;
      }
    }
    else {
      // if countries are not adjacent
      return NOT_ADJACENT;
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