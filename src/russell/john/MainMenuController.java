package russell.john;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuController implements ScreenController
{
    public MainMenuController()
    { /** constructor */ 
    }
    
    public void bind(Nifty nifty, Screen screen) 
    {
    }
    
    public void onStartScreen() 
    {
         System.out.println("THE SCREEN HAS BEEN STARTED");
    }
    
    public void onEndScreen() 
    {
    }
    
    // user defined functions
     public void startGame() 
     {
         System.out.println("Game Started!");
     }
     
     public void openGame()
     {
         System.out.println("Game Opened!");         
     }
     
     public void closeGame()
     {
         System.out.println("Game Closed!"); 
     }
     
}
