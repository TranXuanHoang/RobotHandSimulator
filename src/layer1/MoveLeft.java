/**
 * Component type: Coordinator
 * Purpose: Move the robot's hand to the left of the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class MoveLeft implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.curCol > 0 && rbh.posStatus == Status.UP) {
			rbh.curCol--;
		} else {
			System.out.println("Error: cannot move left");
		}
	} // end method execute

	public String toString() {
		return "ML";
	} // end method toString

	@Override
	public String getOpName() {
		return "Move Left";
	}
} // end class MoveLeft