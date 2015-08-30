/** Averager class
*
* Averages takeoff times.
* 
* @author 	Matt Van Gundy
* 
* 
* Based on car wash simulator by Michael Main
* 
* Due 10/15/24
* 
 */
public class Averager  {
	
   private int count; 
   private double sum; 
   
   /** Averager()
   *
   * Average class constructor
   * 
   * 
   * 
    */                    
   public Averager( ) {
       count = 0;
       sum = 0;
   }  
   /** addNumber()
   *
   * gets number, adds it to sum, increments count
   *
   * @param double value
   * 
    */  
   public void addNumber(double value)  {
      if (count == Integer.MAX_VALUE)
         throw new IllegalStateException("Too many numbers");
      count++;
      sum += value;
   }
   /** average()
   *
   * averages sum by dividing it by count
   *
   * 
    */  
   public double average( )  {
      if (count == 0)
         return 0;
      else
         return sum/count;
   } 
   /** howManyNumbers()
   *
   * returns count
   *
   * @return count
    */ 
   public int howManyNumbers( )  {
      return count;
   }
}