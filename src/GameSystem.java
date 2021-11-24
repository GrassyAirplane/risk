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

  private Database db = new Database();
  
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
  
  /* Implementation of the Fisher-Yates Shuffle */ 
  private void shuffleDeck(int[] indexArray) {
    Random rand = new Random();
    //Parses through the indexArray backwards
    for(int i = indexArray.length - 1; i > 0; i--) {
      int index = rand.nextInt(i + 1);
      //Swaps the values
      int temp = indexArray[index];
      indexArray[index] = indexArray[i];
      indexArray[i] = temp; 
    }
  }
   
   /* distribute countries among players at the start of game */
   public void DistributeCountry() {
     // Creates an index Array with the same size of countries
     int[] indexArray = new int[db.GetAllCountries().length];
     
     // Populates the array with values 0-41 based on the index position
     for(int i = 0; i < db.GetAllCountries().length; i++) {
       indexArray[i] = i;
     }
     
     // Shuffles the array of 0-41
     shuffleDeck(indexArray);
     
     // To get number of countries each player should get
     int numCountry = db.GetAllCountries().length / db.GetAllPlayer().length;
     
     // To get number of countries left over
     int remainder = db.GetAllCountries().length % db.GetAllPlayer().length;
     
     // Assigns countries to players
     for (int i = 0; i < db.GetAllPlayer().length; i++) {
       for (int j = (numCountry * i); j < numCountry * (i + 1); j++) {
         // setting owner
         db.GetAllCountries()[indexArray[j]].SetOwner(db.GetAllPlayer()[i]);
         // adding to player's countryOwned array
         db.GetAllPlayer()[i].AddCountry(db.GetAllCountries()[indexArray[j]].GetCountryId());
       }
     }
     
     int playerIndex = db.GetAllPlayer().length - 1; // keeps track of player index
     
     // Assigns remainder countries
     if (remainder != 0) {
       for (int i = db.GetAllCountries().length - 1; i >= 0; i--) {
         if (db.GetAllCountries()[i].GetOwner() == null) {
           break;
         }
         else {
           // setting owner
           db.GetAllCountries()[indexArray[i]].SetOwner(db.GetAllPlayer()[playerIndex]);
           // adding country to player's countryOwned array
           db.GetAllPlayer()[playerIndex].AddCountry(db.GetAllCountries()[indexArray[i]].GetCountryId());
           if (playerIndex == 0) {
             playerIndex = db.GetAllPlayer().length - 1;
           }
           else {
             playerIndex--;
           }
         }
       }
     }
     
     // Setting initial reinforcements for each player
     for (int i = 0; i < db.GetAllPlayer().length; i++) {
       db.GetAllPlayer()[i].SetReinforcement(db.GetAllPlayer()[i].GetCountryOwned().length * 2);
     }
     
   }
   
   /* Places troops in a country.
    * @param country   - country to place the troops in
    *        numTroops - number of troops to place
    * @return          - 1 if successful
    *                  - 0 if player does not own the country
    *                  - -1 if player does not have enough troops */
   public int PlaceTroops(Country country, int numTroops) {
     // check if player owns the country
     boolean ownsCountry = false;
     for (int i = 0; i < this.currPlayer.GetCountryOwned().length; i++) {
       if (this.currPlayer.GetCountryOwned()[i] == country.GetCountryId()) {
         ownsCountry = true;
         break;
       }
       else {
         ownsCountry = false;
       }
     }
     if (!ownsCountry) {
       return INVALID_OWNER;
     }
     // check if player has enough troops
     if (this.currPlayer.GetReinforcement() < numTroops) {
       return INADEQUATE_TROOPS;
     }
     // adding troops
     country.SetTroopCount(country.GetTroopCount() + numTroops);
     // subtracting added troops from reinforcements
     this.currPlayer.SetReinforcement(this.currPlayer.GetReinforcement() - numTroops);
     return SUCCESSFUL;
   }
   
  /* switch current player to give next player a turn. */
  public void RotatePlayer() {
    if (this.currPlayer == null) {
      // if no current player, set it to the first player
      this.currPlayer = db.GetAllPlayer()[0];
    }
    else if (db.GetAllPlayer()[db.GetAllPlayer().length - 1] == this.currPlayer) {
      // if current player is the last player, rotate it to the first
      this.currPlayer = db.GetAllPlayer()[0];
    }
    else {
      for (int i = 0; i < db.GetAllPlayer().length - 1; i++) {
        // rotate current player
        if (db.GetAllPlayer()[i] == this.currPlayer) {
          this.currPlayer = db.GetAllPlayer()[i + 1];
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
  /*public int Battle(Player attack, Player defend, int numAttackers, Country countryAttack, Country countryDefend) {
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
  }*/
  
  /* Compares dice rolls and transfers ownership if attacker successfully conquered the country
   * @param attack        - attacking player
   *        defend        - defending player
   *        numAttackers  - number of troops the attacker is using to battle
   *        countryAttack - place the attacker is attacking from
   *        countryDefend - place the attacker wants to conquer
   * @return              - 1 if attacker wins
   *                      - 2 if defender wins
   *                      - 0 if player does not own the country
   *                      - -1 if player does not have enough troops
   *                      - -3 if countries are not adjacent */
  public int Battle(Player attack, Player defend, int numAttackers, Country countryAttack, Country countryDefend) {
    // check if attacker owns country
    boolean ownsCountry = false;
    for (int i = 0; i < attack.GetCountryOwned().length; i++) {
      if (attack.GetCountryOwned()[i] == countryAttack.GetCountryId()) {
        ownsCountry = true;
        break;
      }
      else {
        ownsCountry = false;
      }
    }
    if (!ownsCountry) {
      return INVALID_OWNER;
    }
    ownsCountry = false;
    // check if defender owns country
    for (int i = 0; i < defend.GetCountryOwned().length; i++) {
      if (defend.GetCountryOwned()[i] == countryDefend.GetCountryId()) {
        ownsCountry = true;
        break;
      }
      else {
        ownsCountry = false;
      }
    }
    if (!ownsCountry) {
      return INVALID_OWNER;
    }
    // checking if both countries are adjacent to one another
    if (countryAttack.isAdjacent(countryDefend.GetCountryId())) {
      // checking if player has enough troops to attack
      if (countryDefend.GetTroopCount() > numAttackers) {
        while (true) {
          // creating arrays to store dice roll values for attacker and defender
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
            return 1;
          }
          // if defender wins and attacker runs out of troops to attack
          else if (numAttackers == 0) {
            return 2;
          }
        }
      }
      else {
        // player has not enough troops
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
  
  /* Gets Country Position in allCountries array by country name
    * @param countryName  -  name of Counrty
    * @return             -  position of country in allCountries
    *                     -1 if country doesn't exist */
   public int GetCountryPos(String countryName) {   
      for( int i = 0; i < db.GetAllCountries().length; i++ ) {
         if( db.GetAllCountries()[i].GetCountryName().toUpperCase().equals(countryName.toUpperCase()) ) {
            return i; 
         }
      }
      return -1;
   }
   
   /* Gets Country Position in allCountries array by country id
    * @param countryId    -  id of Country 
    * @return             -  position of country in allCountries
    *                     -1 if country doesn't exist */
   public int GetCountryPos(int countryId) {
      for( int i = 0; i < db.GetAllCountries().length; i++ ) {
         if( db.GetAllCountries()[i].GetCountryId() == countryId) {
            return i; 
         }
      }
      return -1; 
   }
   
   /* Gets Country in allCountries array by its pos index
    * @param countryPos   -  position of countrin in allCountries
    * @return             -  country in the position of allCountries*/
   public Country GetCountryByPos(int countryPos) {
      return db.GetAllCountries()[countryPos]; 
   }
  
}