/* Developed By: Euan
 * Revised Date: Nov 23, 2021 */

import java.util.Scanner; 

public class MainRisk {
   public static void main(String [] args) {
      //Initial Variables
      GameSystem gs = new GameSystem(); 
      Displayer disp = new Displayer(gs, gs.GetDb());
      Scanner scan = new Scanner(System.in);
      
      //Game Variables      
      int menuOption, numPlayer, creationCycle = 0, turnPhase, gameOption; 
      
      //Player Creation
      int classType; 
      char playerChar; 
      
      //Player Input
      int reinforcementAmount, countryId; 
      
      //DisplayMenu
      disp.DisplayMenu();
      
      //Menu Looping
      do {
         menuOption = scan.nextInt();
         
         //Main menu 
         switch(menuOption) {
            //Start Game
            case 1: 
               
               //Makes sure the number of Players is 2 - 6
               while(true) {
                  disp.DisplayInitialiser();
                  numPlayer = scan.nextInt();
                  if(numPlayer >= 2 && numPlayer <= 6) {
                     break;
                  }
                  disp.ErrorMessage();
               }
               
               //Player Creation
               while(creationCycle < numPlayer) {
                  
                  disp.DisplayClassType();
                  while(true) {       
                     classType = scan.nextInt();
                     if(classType < 4 && classType > 0) {
                        break;
                     }
                     disp.ErrorMessage();
                     System.out.print("\nClass Selection : ");
                  }
                  
                  disp.DisplayPlayerChar(); 
                  playerChar = scan.next().charAt(0); 
                  
                  //Creates Player Instance
                  gs.CreatePlayer(classType, playerChar); 
                                
                  //Cycle increments
                  creationCycle++;
               }
               
               //Distributes Countries
               gs.DistributeCountry();
                              
               //Initial Player Turn
               gs.RotatePlayer();
               
               //Loops Through all players
               for(int i = 0; i < numPlayer; i++) {
                  //Place until Reinforcements are diminished
                  while(gs.GetCurrPlayer().GetReinforcement() > 0) {
                  disp.DisplayGlobe();
                  disp.DisplayPhase(Displayer.PHASE_PLACEMENT); 
                  gameOption = scan.nextInt();
                  //Placement Option
                     switch(gameOption) {
                        case Displayer.PLACE:
                           disp.DisplayPlace();
                           countryId = scan.nextInt();
                           System.out.print("Selection [Amount of Reinforcement] : ");
                           reinforcementAmount = scan.nextInt();
                           //Places Troops
                           switch(gs.PlaceTroops(gs.GetCountryByPos(gs.GetCountryPos(countryId)), reinforcementAmount)) {
                              case GameSystem.SUCCESSFUL:
                                 System.out.println("\nSuccess\n");
                                 break;
                              case GameSystem.INVALID_OWNER:
                                 System.out.println("\nCountry Not Owned by Player\n");
                                 break; 
                              case GameSystem.INADEQUATE_TROOPS:
                                 System.out.println("\nInvalid Amount of Troops\n");
                                 break;
                           }
                           break;
                         //Invalid Input
                         default:
                           disp.ErrorMessage();                 
                     }             
                  }
                  //Rotates Player
                  gs.RotatePlayer();
               }
               
               


               /* PHASE 1
                * Collect Reinforcement
                * Trade Card
                * Place Troops ZOE
                *
                * PHASE 2 
                * attack ZOE WHILE 
                * Check Loser, Check Winner ZOE
                * move to phase 3
                * check for bonus
                *
                * PHASE 3
                * move troops
                * end turn
                * switch currPlayer
                *
                * */
               
               
               break;
            //Display Rules
            case 2:
               disp.DisplayRules();
               disp.DisplayMenuOption();
               break;
            //Exits Game
            case 3:
               break;
            //Invalid Input 
            default:
               disp.ErrorMessage();
               disp.DisplayMenuOption();
         }
         
      }while(menuOption != 3 && menuOption != 1); 
   }  
}