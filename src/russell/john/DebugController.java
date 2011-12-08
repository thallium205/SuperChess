/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package russell.john;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class DebugController implements ScreenController
{
    public DebugController()
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
}
