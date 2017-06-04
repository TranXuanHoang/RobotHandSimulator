/**
 * Component type: Coordinator
 * Purpose: Move the robot's hand to the bottom of the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class MoveBottom implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.curRow < table.length - 1 &&
				rbh.posStatus == Status.UP) {
			rbh.curRow++;
		} else {
			System.out.println("Error: cannot move bottom");
		}
	} // end method execute

	public String toString() {
		return "MB";
	} // end method toString

	@Override
	public String getOpName() {
		return "Move Bottom";
	}
} // end class MoveBottom