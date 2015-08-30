import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
/** AirPortSimulator class
*
* Simulates flights taking off, landing and crashing.
* 
* @author 	Matt Van Gundy
* 
* 
* Based on car wash simulator by Michael Main
* 
* Due 10/15/24
* 
 */

public class AirPortSimulator {

	/** simulate()
	 * 
	 * Processes airport data.
	 * 
	 * @param int LandTime time in minutes to land the plane
	 * @param double probability of a plane arriving
	 * @param int totalTIme time in minutes simulation runs
	 * @param int takeOffTime time in minutes it take a plane to take off
	 * @param double takeOffProb probability of a plane taking off
	 * @param int maxTimeInQueue minutes before a plane runs out a fuel and crashes
	 * @param int numberOfRunways number of runways
	 * @return string [] of results
	 * 
	 */
   public static String[] simulate
   
   (int landTime, double landProb, int totalTime, int takOffTime, double takeOffProb, int maxTimeInQueue, int numberOfRunways) {
	  
      Queue<Integer> arriving = new LinkedList( );  
      Queue<Integer> departing = new LinkedList( );
      
      
      int crash = 0 ;
      int landed = 0;
      int tookOff = 0;
    
      BooleanSource landing = new BooleanSource(landProb);
      BooleanSource takesOff = new BooleanSource(takeOffProb);
      
      RunWay runway1 = new RunWay(landTime, takOffTime);
      RunWay runway2 = new RunWay(landTime, takOffTime);
      
      Averager takeOffWaitTime = new Averager( );
      Averager landingWaitTime = new Averager( );
      
      int currentMinute;
      
      if (landTime <= 0  || takeOffProb < 0 || takOffTime <= 0 || landProb < 0 || landProb > 1 || totalTime < 0)
         throw new IllegalArgumentException("Values out of range"); 
      
      int next = 0;
      
      for (currentMinute = 0; currentMinute < totalTime; currentMinute++) { 
    	 
    	  if (landing.query())  {
         	 arriving.add(currentMinute);
    	  	 next = arriving.peek( );
    	  if ((currentMinute - next) >= maxTimeInQueue	){ 
			  crash++;
			  arriving.remove( ); 
	  		}
    	  }
    	  	
    	  if ((!runway1.isBusy( )) &&  (!arriving.isEmpty( ))) {
    		  next = arriving.remove( );
    		  landingWaitTime.addNumber(currentMinute - next);
              runway1.startLand();
              landed++;
            
    		  if (numberOfRunways == 2 && (!runway2.isBusy( )) &&  (!arriving.isEmpty( ))) {
    			  arriving.remove( );
    			  landingWaitTime.addNumber(currentMinute - next);
    			  runway2.startLand();
    			  landed++;
    			  
    		  }
    	  }
      

		if (takesOff.query( ))
        	 departing.add(currentMinute);
         	 
         if ((!runway1.isBusy( ))  &&  (!departing.isEmpty( ))  && (arriving.isEmpty( ))) {
             next = departing.remove( );
             runway1.startTakeOff();
             takeOffWaitTime.addNumber(currentMinute - next);
             tookOff++;
        	 
        	 if (numberOfRunways == 2 && (!runway2.isBusy( ))  &&  (!departing.isEmpty( )) && (arriving.isEmpty( ))) {
                 next = departing.remove( );
                 takeOffWaitTime.addNumber(currentMinute - next);
                 runway2.startTakeOff();
                 tookOff++;
        	 }
         }
    	  
         runway1.reduceRemainingTime();
         runway2.reduceRemainingTime();
      }
	  
      
     DecimalFormat df = new DecimalFormat("#.##");
     String wAvg = df.format(landingWaitTime.average( ));
     String toAvg = df.format(takeOffWaitTime.average( ));
     String landedString = Integer.toString(landed);
     String tookOffString = Integer.toString(tookOff);
     String crashString = Integer.toString(crash);
     String landingWaitTimeString = wAvg;
     String takeOffWaitTimeString = toAvg;
     
      String[] output = {"\t\t Planes Landed: " + landedString, "\t\t Planes Departed: " + tookOffString, 
    		  "\t\t Planes Crashed: " + crashString, "\t\t Average Landing Wait: " + landingWaitTimeString+ " Minutes",
    		  "\t\t Average Take Off Wait: " + takeOffWaitTimeString + " Minutes"};
      
      return output;
    	
   }

}