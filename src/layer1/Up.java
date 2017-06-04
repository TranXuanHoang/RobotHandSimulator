/**
 * Component type: Coordinator
 * Purpose: Move the robot's hand to up/leave the robot's hand
 * from ther surface of the table.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class Up implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		rbh.posStatus = Status.UP;
	} // end method execute
	
	public String toString() {
		return "UP";
	} // end method toString

	@Override
	public String getOpName() {
		return "Up";
	}
} // end class Up