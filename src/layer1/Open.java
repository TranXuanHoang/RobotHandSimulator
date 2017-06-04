/**
 * Component type: Coordinator
 * Purpose: Open the robot's hand to leave an item on the table or
 * just open it and do nothing.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class Open implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.posStatus == Status.UP &&
				rbh.itemOnHand != Items.NONE) {
			System.out.println("Error: robot cannot open hand now");
		} else if (rbh.posStatus == Status.DOWN &&
				rbh.itemOnHand != Items.NONE) {
			// open hand
			rbh.actStatus = Status.OPEN;

			// place item on robot hand to table
			table[rbh.curRow][rbh.curCol].setItem(rbh.itemOnHand);

			// set robot hand to not holding any item
			rbh.itemOnHand = Items.NONE;
		} else {
			rbh.actStatus = Status.OPEN;
		}
	} // end method execute

	public String toString() {
		return "OP";
	} // end method toString

	@Override
	public String getOpName() {
		return "Open";
	}
} // end class Open