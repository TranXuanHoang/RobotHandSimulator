/**
 * Component type: Control
 * Purpose: Control the simulation of these robot’s operations.
 * 
 * @author Tran Xuan Hoang
 */
package layer5;

import javax.swing.JButton;

import layer1.Close;
import layer1.Down;
import layer1.MoveBottom;
import layer1.MoveLeft;
import layer1.MoveRight;
import layer1.MoveTop;
import layer1.Open;
import layer1.Operation;
import layer1.RobotHand;
import layer1.Up;

public class RBHOperationsSimulation {
	/**
	 * Simulate 'op' on GUI.
	 * @param op operation will be simulated.
	 * @param op1 the first previously executed operation.
	 * @param op2 the second previously executed operation.
	 * @param itemButtons the array of button used to simulate
	 * 		  the table and the items on the table.
	 * @param rbh the object contains information about the
	 * 		  robot.
	 * @param rbhSimulation the object contains information
	 * 		  that supports the simulation of the robot.
	 */
	public static void simulate(Operation op,
			Operation op1, Operation op2,
			JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation) {

		if (op instanceof MoveTop) {
			moveTop(itemButtons, rbh, rbhSimulation, op1, op2);

		} else if (op instanceof MoveBottom) {
			moveBottom(itemButtons, rbh, rbhSimulation, op1, op2);

		} else if (op instanceof MoveLeft) {
			moveLeft(itemButtons, rbh, rbhSimulation, op1, op2);

		} else if (op instanceof MoveRight) {
			moveRight(itemButtons, rbh, rbhSimulation, op1, op2);

		} else if (op instanceof Open) {
			open(itemButtons, rbh, rbhSimulation);

		} else if (op instanceof Close) {
			close(itemButtons, rbh, rbhSimulation);

		} else if (op instanceof Up) {
			up(itemButtons, rbh, rbhSimulation);

		} else if (op instanceof Down) {
			down(itemButtons, rbh, rbhSimulation);
		}
	} // end method simulate

	public static void moveTop(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation,
			Operation op1, Operation op2) {
		itemButtons[rbh.curRow][rbh.curCol].setBorder(rbhSimulation.defaultBD);
		itemButtons[rbh.curRow - 1][rbh.curCol].setBorder(rbhSimulation.rbhBD);

		if (op1 instanceof Up && op2 instanceof Close)
			itemButtons[rbh.curRow][rbh.curCol].setIcon(null);
		else if (op1 instanceof Up && op2 instanceof Open) {
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
			rbhSimulation.rbhIcon = null;
		} else
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.nextIcon);

		rbhSimulation.nextIcon =
				itemButtons[rbh.curRow - 1][rbh.curCol].getIcon();

		if (rbhSimulation.rbhIcon != null) {
			itemButtons[rbh.curRow - 1][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
		}
	} // end method moveTop

	public static void moveBottom(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation,
			Operation op1, Operation op2) {
		itemButtons[rbh.curRow][rbh.curCol].setBorder(rbhSimulation.defaultBD);
		itemButtons[rbh.curRow + 1][rbh.curCol].setBorder(rbhSimulation.rbhBD);

		if (op1 instanceof Up && op2 instanceof Close)
			itemButtons[rbh.curRow][rbh.curCol].setIcon(null);
		else if (op1 instanceof Up && op2 instanceof Open) {
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
			rbhSimulation.rbhIcon = null;
		} else
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.nextIcon);

		rbhSimulation.nextIcon =
				itemButtons[rbh.curRow + 1][rbh.curCol].getIcon();

		if (rbhSimulation.rbhIcon != null) {
			itemButtons[rbh.curRow + 1][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
		}
	} // end method moveBottom

	public static void moveLeft(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation,
			Operation op1, Operation op2) {
		itemButtons[rbh.curRow][rbh.curCol].setBorder(rbhSimulation.defaultBD);
		itemButtons[rbh.curRow][rbh.curCol - 1].setBorder(rbhSimulation.rbhBD);

		if (op1 instanceof Up && op2 instanceof Close)
			itemButtons[rbh.curRow][rbh.curCol].setIcon(null);
		else if (op1 instanceof Up && op2 instanceof Open) {
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
			rbhSimulation.rbhIcon = null;
		} else
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.nextIcon);

		rbhSimulation.nextIcon =
				itemButtons[rbh.curRow][rbh.curCol - 1].getIcon();

		if (rbhSimulation.rbhIcon != null) {
			itemButtons[rbh.curRow][rbh.curCol - 1].setIcon(rbhSimulation.rbhIcon);
		}
	} // end method moveLeft

	public static void moveRight(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation,
			Operation op1, Operation op2) {
		itemButtons[rbh.curRow][rbh.curCol].setBorder(rbhSimulation.defaultBD);
		itemButtons[rbh.curRow][rbh.curCol + 1].setBorder(rbhSimulation.rbhBD);

		if (op1 instanceof Up && op2 instanceof Close)
			itemButtons[rbh.curRow][rbh.curCol].setIcon(null);
		else if (op1 instanceof Up && op2 instanceof Open) {
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
			rbhSimulation.rbhIcon = null;
		} else
			itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.nextIcon);

		rbhSimulation.nextIcon =
				itemButtons[rbh.curRow][rbh.curCol + 1].getIcon();

		if (rbhSimulation.rbhIcon != null) {
			itemButtons[rbh.curRow][rbh.curCol + 1].setIcon(rbhSimulation.rbhIcon);
		}
	} // end method moveRight

	public static void open(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation) {
		itemButtons[rbh.curRow][rbh.curCol].setIcon(rbhSimulation.rbhIcon);
	} // end method open

	public static void close(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation) {
		rbhSimulation.rbhIcon = itemButtons[rbh.curRow][rbh.curCol].getIcon();
	} // end method close

	public static void up(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation) {
		// Temporarily, this method is leaved as 'do nothing'
	} // end method up

	public static void down(JButton[][] itemButtons,
			RobotHand rbh,
			RBHSimulation rbhSimulation) {
		// Temporarily, this method is leaved as 'do nothing'
	} // end method down
} // end class RBHOperationsSimulation