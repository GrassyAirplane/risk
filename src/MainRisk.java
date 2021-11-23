/* Developed By: Euan
 * Revised Date: Nov 23, 2021 */

import java.util.Scanner; 

public class MainRisk {
   public static void main(String [] args) {
      //Initial Variables
      GameSystem gs = new GameSystem(); 
      Displayer disp = new Displayer(gs.GetDb());
      Scanner scan = new Scanner(System.in);
   
      //Main Code
      
      int turnPhase, menuOption, numPlayers = 0; 
      
      //DisplayMenu
      disp.DisplayMenu();
      
      //Menu Looping
      do {
         menuOption = scan.nextInt();
         
         switch(menuOption) {
            //Start Game
            case 1: 
               
               
               
               
              
            
               
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