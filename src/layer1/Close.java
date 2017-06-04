/**
 * Component type: Coordinator
 * Purpose: Close the robot's hand making it like
 * holding the object and be ready to pick the object up.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

import layer1.RobotHand.Status;

public class Close implements Operation {
	@Override
	public void execute(TableCell[][] table, RobotHand rbh) {
		if (rbh.posStatus == Status.DOWN &&
				rbh.itemOnHand == Items.NONE) {
			// close hand
			rbh.actStatus = Status.CLOSED;

			if (table[rbh.curRow][rbh.curCol].getItem() != Items.NONE) {
				// item on table now belongs to robot hand
				rbh.itemOnHand = table[rbh.curRow][rbh.curCol].getItem();

				// table does not have an item at the position that
				// robot hand also be on
				table[rbh.curRow][rbh.curCol].setItem(Items.NONE);
			}
		} else {
			rbh.actStatus = Status.CLOSED;
		}
	} // end method close

	public String toString() {
		return "CL";
	} // end method toString

	@Override
	public String getOpName() {
		return "Close";
	}
} // end class Close