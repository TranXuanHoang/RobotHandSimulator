/**
 * Component type: Coordinator
 * Purpose: Put down the robot's hand near the surface of the
 * table to put down an item on its hand, or to prepare to
 * pick an item on the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class Down implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		rbh.posStatus = Status.DOWN;
	} // end method execute

	public String toString() {
		return "DN";
	} // end method toString

	@Override
	public String getOpName() {
		return "Down";
	}
} // end class Down