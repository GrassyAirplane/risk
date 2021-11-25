/* Developed By: Euan
 * Revised Date: Nov 25, 2021 */

import java.util.Scanner; 

public class MainRisk {
   public static void main(String [] args) {
      //Initial Variables
      GameSystem gs = new GameSystem(); 
      Displayer disp = new Displayer(gs, gs.GetDb());
      Scanner scan = new Scanner(System.in);
      
      // WIN_CONDITION
      boolean hasWinner = false; 
      
      // SHARED VARIABLES      
      int menuOption, numPlayer, creationCycle = 0, turnPhase, gameOption, numTroops; 
      boolean exitPhase; 
      
      // PLAYER CREATION
      int classType; 
      char playerChar; 
      
      /* PHASE ZERO */
      int reinforcementAmount, countryId; 
     
      /* PHASE ONE */
      //Trading Variables
      int cardOne, cardTwo, cardThree; 
      boolean canTrade;
      
      /* PHASE TWO VARIABLES */
      //Attack Variables
      int countryAttack, countryDefend;
      
      /* PHASE THREE VARIABLES */
      int countryLocation, countryDestination;
      
      /* PROGRAM START - DisplayMenu */
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
               
               /*=============== PHASE 0 ===============*/
               
               //Loops Through all players
               for(int i = 0; i < numPlayer; i++) {
                  disp.DisplayGlobe();
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
                              System.out.print("\n");
                              
                           }
                           
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
                   * Place Troops 
                   * Read Mission
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
                              switch( gs.Bonus(cardOne, cardTwo, cardThree) ) {
                                 case GameSystem.SUCCESSFUL:
                                    System.out.println("\nSuccessful Placement\n");
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
                              
                              if(countryId > 41 || countryId < 0) {
                                 disp.ErrorMessage();
                              }
                                 
                           }while(countryId > 41 || countryId < 0);     
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
                           System.out.print("\n");                 
                        } 
                   }
                   
                   /*=============== PHASE 2 ===============*/
                   
                  /* PHASE 2 
                   * attack  
                   * Check Loser, Check Winner 
                   * move to phase 3
                   * check for bonus */
                   
                   //Sets Exit Condition False
                   exitPhase = false;
                   //Sets Get Bonus Card
                   gs.GetCurrPlayer().SetBonusStatus(false);
                   //Prints Display Globe
                   disp.DisplayGlobe();  
                          
                   //Loops Display Globe
                   while(!exitPhase) {
                     disp.DisplayPhase(Displayer.PHASE_TWO);
                     gameOption = scan.nextInt();
                     
                     //PHASE 2 Option
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
                        //Mission Option
                        case Displayer.MISSION:
                           disp.DisplayMission();
                           break;
                        //Attack Case 
                        case Displayer.ATTACK:
                             //Valid Attacking Country Input 
                           do{
                              System.out.print("\n        ATTACK ORDERS\n");
                              System.out.print("Selection [Owned Country Id]  : ");
                              countryAttack = scan.nextInt();
                              
                              if(countryAttack > 41 || countryAttack < 0) {
                                 disp.ErrorMessage();
                                 System.out.print("");
                              }
                                 
                           }while(countryAttack > 41 || countryAttack < 0);
                           
                            //Valid Defending Country Input 
                            do {
                              System.out.print("Selection [Enemey Country Id] : ");
                              countryDefend = scan.nextInt();
                              
                              if(countryDefend > 41 || countryDefend < 0) {
                                 disp.ErrorMessage();
                                 System.out.print("\n");
                              }
                              
                            }while(countryDefend > 41 || countryDefend < 0);
                            
                            //Valid numTroops input
                            do {
                              System.out.print("Selection [Number of Troops]  : ");
                              numTroops = scan.nextInt();
                              
                              if(numTroops < 1) {
                                 disp.ErrorMessage();
                                 System.out.print("\n");
                              }
      
                            }while(numTroops < 1);
                           
                           
                           //Attacking Conditions
                           switch(gs.Battle(countryAttack, countryDefend, numTroops)) {
                              //Attacker Wins
                              case GameSystem.SUCCESSFUL:
                                 //Gets curr plauer char & Gets country name by id
                                 System.out.printf("\n %c Has Successfully Conquered %s!\n\n", gs.GetCurrPlayer().GetPlayerChar(), gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetCountryName() );
                                 System.out.print("\n");
                                 
                                 /*============ Player Elimination ===============*/
                                 
                                 //Checks if Defender Loses all Territories
                                 if (gs.RemoveLoser(gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetOwner())) {
                                    System.out.printf(" Player %c Has Been Eliminated.\n", gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetOwner().GetPlayerChar());
                                 }
                                              
                                 break;
                              //Defender Wins
                              case 2:
                                 //Gets Defending Players Char, and Defende Country 
                                 System.out.printf("\n %c Has Successfully Defended %s!\n\n", gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetOwner().GetPlayerChar(), gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetCountryName() );
                                 System.out.print("\n");
                                 break;
                              //Player does not own the country
                              case GameSystem.INVALID_OWNER:
                                 //Gets Player char & Attacking Country name
                                 System.out.printf("\n %c Does not own %s.\n\n", gs.GetCurrPlayer().GetPlayerChar(), gs.GetCountryByPos(gs.GetCountryPos(countryAttack)).GetCountryName());
                                 System.out.print("\n");
                                 break;
                              //Player does not have enough troops
                              case GameSystem.INADEQUATE_TROOPS:
                                 //Gets Player char and Attacking Country Name 
                                 System.out.printf("\n %c Does not have enough Troops at %s.\n\n", gs.GetCurrPlayer().GetPlayerChar(), gs.GetCountryByPos(gs.GetCountryPos(countryAttack)).GetCountryName());
                                 System.out.print("\n");
                                 break;
                              //Countries are not adjacent
                              case GameSystem.NOT_ADJACENT:
                                 System.out.printf("\n %s is Not adjacent with %s.\n\n", gs.GetCountryByPos(gs.GetCountryPos(countryAttack)).GetCountryName(), gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetCountryName());
                                 System.out.print("\n");
                                 break;
                              //Countries are not adjacent
                              case GameSystem.SAME_COUNTRY:
                                 System.out.printf("\n %s and %s are not adjacent.\n\n", gs.GetCountryByPos(gs.GetCountryPos(countryAttack)).GetCountryName(), gs.GetCountryByPos(gs.GetCountryPos(countryDefend)).GetCountryName());
                                 System.out.print("\n");
                                 break;
                              
                           }
                        
                        //Attack Break
                        break;            
                           
                        case Displayer.END:
                           //Gets Bonus Card if conquered new country.
                           gs.GetBonusCard();
                           exitPhase = true; 
                           break;
                        
                        //Invalid Input
                        default:
                           disp.ErrorMessage(); 
                           System.out.print("\n");
                     }             
                   }
                   
                   /*=============== CHECKS WINNER ===============*/
                   if(hasWinner) {
                     System.out.printf("\n Player %c Has Won the Game.\n", gs.GetCurrPlayer().GetPlayerChar());
                     break;
                   }
                   
                   
                  /*=============== PHASE 3 ===============*/
                   
                  /* PHASE 3
                   * move troops
                   * end turn
                   * switch currPlayer
                   *
                   * */
                   
                   //Sets Exit Phase to false
                   exitPhase = false;
                   
                   
                   while(!exitPhase) {
                     disp.DisplayPhase(Displayer.PHASE_THREE);
                     gameOption = scan.nextInt();
                     
                     //PHASE 2 Option
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
                        //Mission Option
                        case Displayer.MISSION:
                           disp.DisplayMission();
                           break;
                        //Move Option
                        case Displayer.MOVE:
                                                 
                            //Valid Starting Country Input 
                           do{
                              System.out.print("\n        MOVEMENT ORDERS\n");
                              System.out.print("Selection [Owned Country Id]  : ");
                              countryLocation = scan.nextInt();
                              
                              if(countryLocation > 41 || countryLocation < 0) {
                                 disp.ErrorMessage();
                                 System.out.print("");
                              }
                                 
                           }while(countryLocation > 41 || countryLocation < 0);
                           
                            //Valid Destination Country Input 
                            do {
                              System.out.print("Selection [Destination Country Id] : ");
                              countryDestination = scan.nextInt();
                              
                              if(countryDestination > 41 || countryDestination < 0) {
                                 disp.ErrorMessage();
                                 System.out.print("\n");
                              }
                              
                            }while(countryDestination > 41 || countryDestination < 0);
                            
                            //Valid numTroops input
                            do {
                              System.out.print("Selection [Number of Troops]  : ");
                              numTroops = scan.nextInt();
                              
                              if(numTroops < 1) {
                                 disp.ErrorMessage();
                                 System.out.print("\n");
                              }
      
                            }while(numTroops < 1);
                            
                            //move options
                            
                           
                           
                           break;   
                        
                        case Displayer.END:
                           //Rotates Player
                           gs.RotatePlayer();
                           exitPhase = true; 
                           break;
                        
                        //Invalid Input
                        default:
                           disp.ErrorMessage(); 
                           System.out.print("\n");             
                     }   
                  }
               }
               
               //Break Out of Game Loop
               break;
            //Display Rules
            case 2:
               disp.DisplayRules();
               disp.DisplayMenuOption();
               break;
            //Exits Game
            case 3:
               System.out.println("\nExiting...");
               break;
            //Invalid Input 
            default:
               disp.ErrorMessage();
               disp.DisplayMenuOption();
         }
         
      }while(menuOption != 3 && menuOption != 1); 
   }  
}