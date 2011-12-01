package russell.john;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuController extends SuperChess implements ScreenController 
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
     public void newGame() 
     {
         this.stop();
         this.start();         
     }
     
}
