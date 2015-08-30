/** RunWay class
*
* 
* Simulates run way.
* 
* @author 	Matt Van Gundy
* 
* 
* Based on car wash simulator by Michael Main
* 
* Due 10/15/24
* 
 */

public class RunWay
{
   private int landTime;  
   private int takeOffTime;
   private int timeLeft;   

   /** Runway()
   *
   * RunWay class constructor
   * 
   * @param int l land time of plane
   * @param int t takeoff time of plane
   * 
    */                       

   public RunWay(int l, int t)  {
	   landTime = l;
	   takeOffTime = t;
	   timeLeft = 0;
   }
   /** isBusy()
   *
   * used to check if runway is busy
   * 
   * @return boolean if timeleft is greater than zero
   * 
   * 
    */   
   public boolean isBusy( )  {
      return (timeLeft > 0);
   }
   /** reduceRemainingTime( )
   *
   * checks if timeleft is less than zero, decrements timeleft
   * 
   * 
   * 
    */   
   
   public void reduceRemainingTime( ) {
      if (timeLeft > 0)
    	  timeLeft--;
   } 
   
   /** startLand()
   *
   * if timeleft is greater than zero, set landtime to timeleft
   * 
   * 
   * 
    */ 
   public void startLand() {
      if (timeLeft > 0)
         throw new IllegalStateException("Runway is already busy.");
      timeLeft = landTime;
      
   }
   /** startTakeOff()
   *
   * if timeleft is greater than zero, set takeofftime to timeleft
   * 
   * 
   * 
    */ 
   public void startTakeOff() {
	   if (timeLeft > 0)
	         throw new IllegalStateException("Runway is already busy.");
	      timeLeft = takeOffTime;
   }
}