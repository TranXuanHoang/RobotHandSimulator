/**
 * Component type: Coordinator
 * Purpose: Move the robot's hand to the top of the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class MoveTop implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.curRow > 0 && rbh.posStatus == Status.UP) {
			rbh.curRow--;
		} else {
			System.out.println("Error: cannot move top");
		}
	} // end method execute

	public String toString() {
		return "MT";
	} // end method toString

	@Override
	public String getOpName() {
		return "Move Top";
	}
} // end class MoveTop