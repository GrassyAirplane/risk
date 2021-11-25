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
      
      //Trading Variables
      int cardOne, cardTwo, cardThree; 
      boolean canTrade;
      
      
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
               disp.DisplayGlobe();
               
               /*=============== PHASE 0 ===============*/
               
               //Loops Through all players
               for(int i = 0; i < numPlayer; i++) {
                  //Place until Reinforcements are diminished
                  while(gs.GetCurrPlayer().GetReinforcement() > 0) {
                  disp.DisplayPhase(Displayer.PHASE_PLACEMENT); 
                  gameOption = scan.nextInt();
                  //Placement Option
                     switch(gameOption) {
                        case Displayer.DISPLAY_GLOBE:
                           disp.DisplayGlobe();
                           break;
                        case Displayer.N_AMERICA:
                           disp.DisplayNAmerica();
                           break;
                        case Displayer.S_AMERICA:
                           disp.DisplaySAmerica();
                           break;
                        case Displayer.EUROPE:
                           disp.DisplayEurope();
                           break;
                        case Displayer.AFRICA:
                           disp.DisplayAfrica();
                           break;
                        case Displayer.ASIA:
                           disp.DisplayAsia();
                           break;
                        case Displayer.SCOREBOARD:
                           disp.DisplayScoreboard();
                           break;
                        case Displayer.PLACE:
                           //Gurantee Inbound Input Error Checking
                           do{
                              disp.DisplayPlace();
                              countryId = scan.nextInt();
                              
                              if(countryId > 41 || countryId < 0) {
                                 disp.ErrorMessage();
                              }
                              
                           }while(countryId > 41 || countryId < 0);     
                           
                           while(true) {
                              System.out.print("Selection [Amount of Reinforcement] : ");
                              reinforcementAmount = scan.nextInt();
                              
                              //Checks for correct input amount
                              if(reinforcementAmount > 0) {
                                 break;
                              }
                              
                              disp.ErrorMessage();
                              
                           }
                           
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
                           System.out.print("\n");                
                     }             
                  }
                  //Rotates Player
                  gs.RotatePlayer();
               }
               
               
               while(true) {
                  /*=============== PHASE 1 ===============*/
                     
                  /* PHASE 1
                   * Collect Reinforcement
                   * Trade Card
                   * Place Troops ZOE
                   */
                   
                   /* Sets Reinforcement for curr Player */
                   gs.GetCurrPlayer().SetReinforcement(); 
                   disp.DisplayGlobe();
                   
                   while(gs.GetCurrPlayer().GetReinforcement() > 0) {
                     
                     disp.DisplayPhase(Displayer.PHASE_ONE);
                     gameOption = scan.nextInt();
                     //Placement Option
                        switch(gameOption) {
                           case Displayer.DISPLAY_GLOBE:
                              disp.DisplayGlobe();
                              break;
                           case Displayer.N_AMERICA:
                              disp.DisplayNAmerica();
                              break;
                           case Displayer.S_AMERICA:
                              disp.DisplaySAmerica();
                              break;
                           case Displayer.EUROPE:
                              disp.DisplayEurope();
                              break;
                           case Displayer.AFRICA:
                              disp.DisplayAfrica();
                              break;
                           case Displayer.ASIA:
                              disp.DisplayAsia();
                              break;
                           case Displayer.SCOREBOARD:
                              disp.DisplayScoreboard();
                              break;
                           //Trading System displayer
                           case Displayer.TRADE:
                                 
                              //Checks if deck is null
                              if(gs.GetCurrPlayer().GetBonusDeck() == null) {
                                 System.out.println("\nNo Cards In Deck\n");
                                 //breaks out of trade
                                 break;
                              }
                              
                              int deckLength = gs.GetCurrPlayer().GetBonusDeck().length - 1;

                              //Trading Displayer
                              disp.DisplayTrade();
                              cardOne = scan.nextInt();
                              System.out.printf(" Card 2 [Id] : ");
                              cardTwo = scan.nextInt();
                              System.out.printf(" Card 3 [Id] : ");
                              cardThree = scan.nextInt();
                                 
                              //checks that the cards are within the range
                              if(cardOne > deckLength || cardTwo > deckLength || cardThree > deckLength) {
                                 disp.ErrorMessage();
                                 canTrade = false; 
                              }
                              //Checks that the cards are not duplicates
                              else if(cardOne == cardTwo || cardOne == cardThree || cardTwo == cardThree) {
                                 disp.ErrorMessage();
                                 canTrade = false;
                              } 
                              else {
                                 canTrade = true;
                              }
                                                      
                              if(canTrade) {
                                 //Bonus Card Swap Results
                                 switch( gs.GetCurrPlayer().Bonus(cardOne, cardTwo, cardThree) ) {
                                    case GameSystem.SUCCESSFUL:
                                       System.out.println("\nSuccess\n");
                                       break;
                                    case GameSystem.INADEQUATE_BONUS:
                                       System.out.println("\nIncorrect Combination\n");
                                       break;  
                                 }
                              }
                              break;  
                           //Placement of Troops
                           case Displayer.PLACE:
                              //Gurantee Inbound Input Error Checking
                              do{
                                 disp.DisplayPlace();
                                 countryId = scan.nextInt();
                                 
                                 if(countryId > 42 || countryId < 0) {
                                    disp.ErrorMessage();
                                 }
                                 
                              }while(countryId > 42 || countryId < 0);     
                              System.out.print("Selection [Amount of Reinforcement] : ");
                              reinforcementAmount = scan.nextInt();
                              //Places Troops
                              switch(gs.PlaceTroops(gs.GetCountryByPos(gs.GetCountryPos(countryId)), reinforcementAmount)) {
                                 case GameSystem.SUCCESSFUL:
                                    System.out.println("\nSuccessful Placement\n");
                                    break;
                                 case GameSystem.INVALID_OWNER:
                                    System.out.println("\nCountry Not Owned by Player\n");
                                    break; 
                                 case GameSystem.INADEQUATE_TROOPS:
                                    System.out.println("\nInvalid Amount of Troops\n");
                                    break;
                              }
                              break;
                            //Display Mission
                            case Displayer.MISSION:
                              disp.DisplayMission();
                              break;
                            //Invalid Input
                            default:
                              disp.ErrorMessage();                 
                        } 
                   
                   }
                   
                   
                
                  
                   
                   
                   /*=============== PHASE 2 ===============*/
                   
                  /* PHASE 2 
                   * attack ZOE WHILE 
                   * Check Loser, Check Winner ZOE
                   * move to phase 3
                   * check for bonus */
                   
                   //disp.DisplayPhase(Displayer.PHASE_PLACEMENT); 
                   
                   
                   
                  /*=============== PHASE 3 ===============*/
                   
                  /* PHASE 3
                   * move troops
                   * end turn
                   * switch currPlayer
                   *
                   * */
                   
                   //disp.DisplayPhase(Displayer.PHASE_PLACEMENT); 
                   
                   break;
               }
               
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