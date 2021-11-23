/* Developed By: Zoe Tay Shiao Shuen
 * Revised Date: Nov 22, 2021 */

/* Attacker Player Class
 * Ability:  */
public class GameSystem_Backup {
  
  /* GLOBAL CONSTANT VARIABLES */
  // Troop Type
  public final static int INFANTRY_TROOP = 1;
  public final static int HORSE_TROOP    = 2;
  public final static int CANNON_TROOP   = 3;
  
  private Database db = new Database(); 
  private Displayer disp = new Displayer(db);
  
  /* switch current player to give next player a turn.
   * @param currPlayer - ID of current player that just played their turn
   * @return           - ID of new player */
  public int RotatePlayer(int currPlayer) {
    if (currPlayer < Player.PlayerCount) {
      return currPlayer + 1;
    }
    else {
      return 1;
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
  
}