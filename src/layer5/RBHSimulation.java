/**
 * Component type: Control
 * Purpose: represent the information to simulate the robot's hand
 * graphically on the GUI.
 * 
 * @author Tran Xuan Hoang
 */
package layer5;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class RBHSimulation {
	public Border rbhBD;
	public Border defaultBD;
	public Icon rbhIcon;
	public Icon nextIcon;

	public RBHSimulation(Icon rbhIcon, Icon nextIcon) {
		this.defaultBD = new JButton().getBorder();
		this.rbhBD = BorderFactory.createLineBorder(Color.GRAY, 4);
		this.rbhIcon = rbhIcon;
		this.nextIcon = nextIcon;
	} // end constructor
} // end class RBHSimulation