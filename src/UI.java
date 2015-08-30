import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/** UI class
*
* User interface.
* 
* Uses swing
* 
* @author 	Matt Van Gundy
* 
* 
* Based on car wash simulator by Michael Main
* 
* Due 10/15/24
* 
 */
	public class UI extends JFrame {
		private JTextField landProbTextField;
		private JTextField takeOffProbTextField;
		private JTextField landTimeTextField;
		private JTextField takOffTimeTextField;
		private JTextField maxTimeInQueueTextField;
		private JTextField numberOfRunwaysTextField;
		private JTextField totalTimeTextField;
		private JButton runSimulation;
		AirPortSimulator airport = new AirPortSimulator();
		
		 /** UI()
		   * 
		   * sets jframe components
		   * 
		    */ 
		
		public UI() {
			super("Airport Simulator");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setBounds(500, 50, 500, 650);
			JPanel contentPane = new JPanel();
			contentPane.setLayout(new GridLayout(0, 2));
			final JPanel results = new JPanel();
			results.setLayout(new GridLayout(1, 1));
			landProbTextField = new JTextField();
			takeOffProbTextField = new JTextField();
			landTimeTextField = new JTextField();
			takOffTimeTextField = new JTextField();
			maxTimeInQueueTextField = new JTextField();
			numberOfRunwaysTextField = new JTextField();
			totalTimeTextField = new JTextField();
			runSimulation = new JButton("Run Simulation");
			JLabel landProbLabel = new JLabel("Arrival Probability: ");
			JLabel takeOffProbLabel = new JLabel("Deprarture Probability: ");
			JLabel landTimeLabel = new JLabel("Time to Land: ");
			JLabel takOffTimeLabel = new JLabel("Time to Takeoff: ");
			JLabel maxTimeInQueueLabel = new JLabel("Minutes of Fuel Remaining: ");
			JLabel numberOfRunwaysLabel = new JLabel("Number of Runways: ");
			JLabel totalTimeLabel = new JLabel("Simulation Time: ");
			landProbLabel.setHorizontalAlignment(SwingConstants.CENTER);
			takeOffProbLabel.setHorizontalAlignment(SwingConstants.CENTER);
			landTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			takOffTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			maxTimeInQueueLabel.setHorizontalAlignment(SwingConstants.CENTER);
			numberOfRunwaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
			totalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.setBackground(Color.LIGHT_GRAY);
			contentPane.add(landProbLabel);
			contentPane.add(landProbTextField);
			contentPane.add(takeOffProbLabel);
			contentPane.add(takeOffProbTextField);
			contentPane.add(landTimeLabel);
			contentPane.add(landTimeTextField);
			contentPane.add(takOffTimeLabel);
			contentPane.add(takOffTimeTextField);
			contentPane.add(maxTimeInQueueLabel);
			contentPane.add(maxTimeInQueueTextField);
			contentPane.add(numberOfRunwaysLabel);
			contentPane.add(numberOfRunwaysTextField);
			contentPane.add(totalTimeLabel);
			contentPane.add(totalTimeTextField);
			contentPane.add(runSimulation);
			final TextArea textArea = new TextArea(5,30);  
			textArea.setBackground(Color.BLACK);
			textArea.setForeground(Color.GREEN);
			textArea.setEditable(false);
			results.add(textArea);
			add(contentPane, BorderLayout.NORTH);
			add(results, BorderLayout.CENTER);
			// sends data to simulation on click
			runSimulation.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JTextField[] txtFieldA = new JTextField[7];
					 txtFieldA[0] = landProbTextField;
					 txtFieldA[1] = takeOffProbTextField;
					 txtFieldA[2] = landTimeTextField;
					 txtFieldA[3] = takOffTimeTextField;
					 txtFieldA[4] = maxTimeInQueueTextField;
					 txtFieldA[5] = numberOfRunwaysTextField;
					 txtFieldA[6] = totalTimeTextField;
					 for(JTextField txtField : txtFieldA) {
						 if(txtField.getText().equals("") ) {
						      JOptionPane.showMessageDialog(null,"Please enter data in all fields");
						      return;
						 }
					   try{
						   Double num = Double.parseDouble(txtField.getText());
						 } catch (NumberFormatException e1) {
							 JOptionPane.showMessageDialog(null,"Please enter a numberic values");
							 return;
							 }
					   if (Integer.parseInt (numberOfRunwaysTextField.getText()) > 2){
						   JOptionPane.showMessageDialog(null,"only enter up to two runways");
							 return;
					   		}
					   }
					textArea.setText(null);
					String[] data = airport.simulate(Integer.parseInt (landTimeTextField.getText()), Double.parseDouble (landProbTextField.getText()), 
							Integer.parseInt (totalTimeTextField.getText()),Integer.parseInt (takOffTimeTextField.getText()), 
							Double.parseDouble (takeOffProbTextField.getText()), Integer.parseInt (maxTimeInQueueTextField.getText()),
							Integer.parseInt (numberOfRunwaysTextField.getText()));
					textArea.append("\n\n\n");
					textArea.append("\t Arrival Probability: " + landProbTextField.getText() +"\n"+"\t Time to Land: " + landTimeTextField.getText() +"\n"+
							"\t Time to Takeoff: " + takOffTimeTextField.getText() +"\n"+ "\t Minutes of Fuel Remaining: " + maxTimeInQueueTextField.getText()
							 +"\n"+ "\t Number of Runways: "+numberOfRunwaysTextField.getText()+"\n"+ "\t Deprarture Probability: " + takeOffProbTextField.getText()
							+"\n"+ "\t Simulation Time: " + totalTimeTextField.getText()+"\n");
					textArea.append("\n");
					 for(int i = 0; i < data.length; i++){
						 textArea.append(data[i] + "\n");
						
					 }
				  }
			});
		}
	}
