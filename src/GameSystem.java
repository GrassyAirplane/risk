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
  public final static int SAME_COUNTRY      = -4;
  public final static int SAME_OWNER        = -5;
  
  /* INSTANCE VARIABLES */
  private Player currPlayer;

  private Database db = new Database();
  
  /*=============== PRIVATE METHODS ===============*/
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
  
  /* checks if player being attacked has lost and removes the player if the player has lost
   * @param player - player to check if eliminated
   * @return       - true if player has lost and is removed
   *                 false if player has not lost */
  public boolean RemoveLoser(Player player) {
    // checks if player is eliminated by seeing if they no longer own any countries
    if (player.GetCountryOwned().length == 0) {
      // before removing player, the player's bonus cards are returned to the allBonus deck
      for (int i = 0; i < player.GetBonusDeck().length; i++) {
        db.AddBonus(player.GetBonusDeck()[i]);
      }
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
   *                    - 0 if player does not own the countries,
   *                    - -1 if not enough troops,
   *                    - -3 if not adjacent,
   *                    - -4 if both countries are the same */
  public int Move(int fromCountry, int toCountry, int numTroops) {
    // error checking
    if (GetCountryByPos(GetCountryPos(fromCountry)).GetOwner() != this.currPlayer) {
      // check if player owns the country
      return INVALID_OWNER;
    }
    else if (GetCountryByPos(GetCountryPos(toCountry)).GetOwner() != this.currPlayer) {
      // check if player owns the country
      return INVALID_OWNER;
    }
    else if (fromCountry == toCountry) {
      // check if both countries are the same
      return SAME_COUNTRY;
    }
    // checks if countries are adjacents of one another
    if (GetCountryByPos(GetCountryPos(fromCountry)).isAdjacent(toCountry)) {
      // checks if there are enough troops to move
      if (GetCountryByPos(GetCountryPos(fromCountry)).GetTroopCount() > numTroops) {
        GetCountryByPos(GetCountryPos(fromCountry)).SetTroopCount(GetCountryByPos(GetCountryPos(fromCountry)).GetTroopCount() - numTroops); // subtracting number of troops
        GetCountryByPos(GetCountryPos(toCountry)).SetTroopCount(GetCountryByPos(GetCountryPos(toCountry)).GetTroopCount() + numTroops); // adding troops to destination country
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
   *        numAttackers  - number of troops the attacker is using to battle
   *        countryAttack - ID of country the attacker is attacking from
   *        countryDefend - ID of country the attacker wants to conquer
   * @return              - 1 if attacker wins
   *                      - 2 if defender wins
   *                      - 0 if player does not own the country
   *                      - -1 if player does not have enough troops
   *                      - -3 if countries are not adjacent
   *                      - -4 if both countries are the same country
   *                      - -5 if both countries are owned by the same player */
  public int Battle(int countryAttack, int countryDefend, int numAttackers) {
    // if country has less than 2 troops
    if (GetCountryByPos(GetCountryPos(countryAttack)).GetTroopCount() < 2) {
      return INADEQUATE_TROOPS;
    }
    // check if countryAttack and countryDefend are the same
    if (countryAttack == countryDefend) {
      return SAME_COUNTRY;
    }
    // check if countryAttack and countryDefend have the same owner
    else if (GetCountryByPos(GetCountryPos(countryAttack)).GetOwner() == GetCountryByPos(GetCountryPos(countryDefend)).GetOwner()) {
      return SAME_OWNER;
    }
    // check if attacker owns country
    boolean ownsCountry = false;
    for (int i = 0; i < this.currPlayer.GetCountryOwned().length; i++) {
      if (this.currPlayer.GetCountryOwned()[i] == countryAttack) {
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
    if (GetCountryByPos(GetCountryPos(countryAttack)).isAdjacent(countryDefend)) {
      // checking if player has enough troops to attack
      if (GetCountryByPos(GetCountryPos(countryAttack)).GetTroopCount() > numAttackers) {
        while (true) {
          // creating arrays to store dice roll values for attacker and defender
          int[] rollAttack = this.currPlayer.Attack(numAttackers);
          int[] rollDefend = GetCountryByPos(GetCountryPos(countryDefend)).GetOwner().Defend(GetCountryByPos(GetCountryPos(countryDefend)).GetTroopCount());
          if (rollAttack.length < 2 || rollDefend.length < 2) {
            // if only one roll is compared
            if (rollAttack[0] > rollDefend[0]) {
              // if attacker wins, defender loses one troop
              GetCountryByPos(GetCountryPos(countryDefend)).SetTroopCount(GetCountryByPos(GetCountryPos(countryDefend)).GetTroopCount() - 1);
            }
            else {
              // if defender wins, attacker loses one troop
              GetCountryByPos(GetCountryPos(countryAttack)).SetTroopCount(GetCountryByPos(GetCountryPos(countryAttack)).GetTroopCount() - 1);
              numAttackers--;
            }
          }
          else {
            for (int i = 0; i < rollDefend.length; i++) {
              // if two rolls are compared
              if (rollAttack[i] > rollDefend[i]) {
                // if attacker wins, defender loses one troop
                GetCountryByPos(GetCountryPos(countryDefend)).SetTroopCount(GetCountryByPos(GetCountryPos(countryDefend)).GetTroopCount() - 1);
              }
              else {
                // if defender wins, attacker loses one troop
                GetCountryByPos(GetCountryPos(countryAttack)).SetTroopCount(GetCountryByPos(GetCountryPos(countryAttack)).GetTroopCount() - 1);
                numAttackers--;
              }
            }
          }
          // if attacker successfully conquered the country
          if (GetCountryByPos(GetCountryPos(countryDefend)).GetTroopCount() < 1) {
            // adding country to attacker's array
            this.currPlayer.AddCountry(countryDefend);
            // removing country from defender's array
            GetCountryByPos(GetCountryPos(countryDefend)).GetOwner().RemoveCountry(GetCountryByPos(GetCountryPos(countryDefend)).GetOwner().GetCountryOwnedPos(countryDefend));
            // transferring country ownership to attacker
            GetCountryByPos(GetCountryPos(countryDefend)).SetOwner(this.currPlayer);
            // transferring troops over
            GetCountryByPos(GetCountryPos(countryDefend)).SetTroopCount(numAttackers);
            GetCountryByPos(GetCountryPos(countryAttack)).SetTroopCount(GetCountryByPos(GetCountryPos(countryAttack)).GetTroopCount() - numAttackers);
            this.currPlayer.SetBonusStatus(true);
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
  
  /* Collects a bonus card after player's turn if the player has
   * conquered at least one country.
   * @return - 1 if successful
   *         - 0 if bonus status is false, so player cannot collect bonus */
  public int GetBonusCard() {
    if (this.currPlayer.GetBonusStatus()) {
      // selecting random bonus card from allBonus array by generating a random index
      int bonusIndex = new Random().nextInt(db.GetAllBonus().length - 1);
      // adding bonus to player's bonus deck
      this.currPlayer.AddBonusToPlayer(db.GetAllBonus()[bonusIndex]);
      // removing bonus from allBonus array
      db.RemoveBonus(bonusIndex);
      // setting bonus status back to false
      this.currPlayer.SetBonusStatus(false);
      return SUCCESSFUL;
    }
    else {
      return 0;
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
    *                     - -1 if country doesn't exist */
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
   
   /* Checks if current player has won.
    * @return - 1 if player won by world domination.
    *         - 0 if player won by completing mission.
    *         - -1 if player has not won. */
   public int CheckWinner() {
     // arrays to check if the player owns any of these specific continents
     int[] nAmerica = {0, 1, 2, 3, 4, 5, 6, 7, 8};
     int[] sAmerica = {9, 10, 11, 12};
     int[] europe = {13, 14, 15, 16, 17, 18, 19};
     int[] africa = {20, 21, 22, 23, 24, 25};
     int[] asia = {26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};
     int[] australia = {38, 39, 40, 41};
     if (this.currPlayer.GetCountryOwned().length == db.GetAllCountries().length) {
       return 1;
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 0) {
       if (this.currPlayer.CheckContinent(europe) && this.currPlayer.CheckContinent(nAmerica) && this.currPlayer.CheckContinent(africa)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 1) {
       if (this.currPlayer.CheckContinent(asia) && this.currPlayer.CheckContinent(nAmerica)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 2) {
       if (this.currPlayer.CheckContinent(europe) && this.currPlayer.CheckContinent(asia)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 3) {
       if (this.currPlayer.CheckContinent(australia) && this.currPlayer.CheckContinent(africa) && this.currPlayer.CheckContinent(sAmerica)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 4) {
       if (this.currPlayer.CheckContinent(africa) && this.currPlayer.CheckContinent(europe) && this.currPlayer.CheckContinent(sAmerica)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else if (this.currPlayer.GetPlayerMission().GetMissionId() == 5) {
       if (this.currPlayer.CheckContinent(europe) && this.currPlayer.CheckContinent(africa) && this.currPlayer.CheckContinent(asia)) {
         return 0;
       }
       else {
         return -1;
       }
     }
     else {
       return -1;
     }
   }
   
   /* cashes in card bonuses for extra troops.
   * @param t1 - position in deck array of first bonus card to cash in
   *        t2 - position in deck array of second bonus card to cash in
   *        t3 - position in deck array of third bonus card to cash in
   * @return   - 1 if bonus troops are successfully added to reinforcements
   *           - -2 if the player do not have the required bonuses to cash in */
  public int Bonus(int t1, int t2, int t3) {
    int numInfantry = 0; // keeps track of number of infantry bonuses
    int numHorse = 0; // keeps track of number of horse bonuses
    int numCannon = 0; // keeps track of number of cannon bonuses
    
    // counting number of troops for each type
    for (int i = 0; i < this.currPlayer.GetBonusDeck().length; i++) {
      if (this.currPlayer.GetBonusDeck()[i].GetTroopBonusType() == INFANTRY_TROOP) {
        numInfantry++;
      }
      else if (this.currPlayer.GetBonusDeck()[i].GetTroopBonusType() == HORSE_TROOP) {
        numHorse++;
      }
      else if (this.currPlayer.GetBonusDeck()[i].GetTroopBonusType() == CANNON_TROOP) {
        numCannon++;
      }
    }
    
    // checking if all troops are the same type
    if (this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() == this.currPlayer.GetBonusDeck()[t2].GetTroopBonusType() 
        && this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() == this.currPlayer.GetBonusDeck()[t3].GetTroopBonusType()) {
      if (this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() == INFANTRY_TROOP) {
        // checking for infantry bonus
        if (numInfantry >= 3) {
          this.currPlayer.SetReinforcement(this.currPlayer.GetReinforcement() + 4);
          // adding bonus cards back to main bonus deck
          db.AddBonus(db.GetBonusByPos(t1));
          db.AddBonus(db.GetBonusByPos(t2));
          db.AddBonus(db.GetBonusByPos(t3));
          // removing the bonus cards from the player
          this.currPlayer.RemoveBonusFromPlayer(t1);
          this.currPlayer.RemoveBonusFromPlayer(t2);
          this.currPlayer.RemoveBonusFromPlayer(t3);
          
          return SUCCESSFUL;
        }
        else {
          return INADEQUATE_BONUS;
        }
      }
      else if (this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() == HORSE_TROOP) {
        // checking for horse bonus
        if (numHorse >= 3) {
          this.currPlayer.SetReinforcement(this.currPlayer.GetReinforcement() + 6);
          // adding bonus cards back to main bonus deck
          db.AddBonus(db.GetBonusByPos(t1));
          db.AddBonus(db.GetBonusByPos(t2));
          db.AddBonus(db.GetBonusByPos(t3));
          // removing bonuses from player deck
          this.currPlayer.RemoveBonusFromPlayer(t1);
          this.currPlayer.RemoveBonusFromPlayer(t2);
          this.currPlayer.RemoveBonusFromPlayer(t3);
          return SUCCESSFUL;
        }
        else {
          return INADEQUATE_BONUS;
        }
      }
      else if (this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() == CANNON_TROOP) {
        // checking for cannon bonus
        if (numCannon >= 3) {
          this.currPlayer.SetReinforcement(this.currPlayer.GetReinforcement() + 8);
          // adding bonuses back to main bonus deck
          db.AddBonus(db.GetBonusByPos(t1));
          db.AddBonus(db.GetBonusByPos(t2));
          db.AddBonus(db.GetBonusByPos(t3));
          // removing bonuses from player deck
          this.currPlayer.RemoveBonusFromPlayer(t1);
          this.currPlayer.RemoveBonusFromPlayer(t2);
          this.currPlayer.RemoveBonusFromPlayer(t3);
          return SUCCESSFUL;
        }
        else {
          return INADEQUATE_BONUS;
        }
      }
      else {
        return INADEQUATE_BONUS;
      }
    }
    else if (this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() != this.currPlayer.GetBonusDeck()[t2].GetTroopBonusType() && 
             this.currPlayer.GetBonusDeck()[t2].GetTroopBonusType() != this.currPlayer.GetBonusDeck()[t3].GetTroopBonusType() && 
             this.currPlayer.GetBonusDeck()[t1].GetTroopBonusType() != this.currPlayer.GetBonusDeck()[t3].GetTroopBonusType()) {
      if (numInfantry > 0 && numHorse > 0 && numCannon > 0) {
        this.currPlayer.SetReinforcement(this.currPlayer.GetReinforcement() + 10);
        // adding bonuses back to main bonus deck
        db.AddBonus(db.GetBonusByPos(t1));
        db.AddBonus(db.GetBonusByPos(t2));
        db.AddBonus(db.GetBonusByPos(t3));
        // removing bonuses from player deck
        this.currPlayer.RemoveBonusFromPlayer(t1);
        this.currPlayer.RemoveBonusFromPlayer(t2-1);
        this.currPlayer.RemoveBonusFromPlayer(t3-2);
        return SUCCESSFUL;
      }
      else {
        return INADEQUATE_BONUS;
      }
    }
    else {
      return INADEQUATE_BONUS;
    }
  }
  
}