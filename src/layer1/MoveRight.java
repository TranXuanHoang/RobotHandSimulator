/**
 * Component type: Coordinator
 * Purpose: Move the robot's hand to the right of the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class MoveRight implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.curCol < table[0].length - 1 &&
				rbh.posStatus == Status.UP) {
			rbh.curCol++;
		} else {
			System.out.println("Error: cannot move right");
		}
	} // end method execute

	public String toString() {
		return "MR";
	} // end method toString

	@Override
	public String getOpName() {
		return "Move Right";
	}
} // end class MoveRight