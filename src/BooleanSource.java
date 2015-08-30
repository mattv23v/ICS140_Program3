import javax.swing.JOptionPane;
/** BooleanSource
*
*
* finds probabilities
* 
* 
* @author 	Matt Van Gundy
* 
* 
* Based on car wash simulator by Michael Main
* 
* Due 10/15/24
* 
 */

public class BooleanSource  {
   private double probability; 
   /** BooleanSource()
    * 
	 * constructor, check if probability is a decimal
	 * 
	 * @param double p 
	 * 
	 */          
   public BooleanSource(double p)  {
       if ((p < 0) || (1 < p))
    	   JOptionPane.showMessageDialog(null,"only enter decimal values");
       probability = p;
   }
   /** query()
    * 
	 * constructor, check if probability is a decimal
	 * 
	 * @return double random probability 
	 * 
	 */      
   public boolean query( ) {
      return (Math.random( ) < probability);
      
   }
 
}