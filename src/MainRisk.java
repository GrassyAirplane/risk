/* Developed By: Euan
 * Revised Date: Nov 23, 2021 */

import java.util.Scanner; 

public class MainRisk {
   public static void main(String [] args) {
      //Initial Variables
      GameSystem gs = new GameSystem(); 
      Displayer disp = new Displayer(gs.GetDb());
      Scanner scan = new Scanner(System.in);
   
      //Game Variables      
      int menuOption, numPlayer, creationCycle = 0, turnPhase; 
      
      //Player Creation
      disp.DisplayGlobe();

      
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
                  
                  /* Insert Code */
                  
                  creationCycle++;
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