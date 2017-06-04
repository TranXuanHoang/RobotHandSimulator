/**
 * Component type: Server
 * Purpose: Represent information about the robot's hand, including:
 * 			the current rows and the current columns of the robot;
 * 			the position of the robot: UP or DOWN,
 * 			the status of the robot: OPEN or CLOSED;
 * 			the item on the hand of the robot.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

public class RobotHand {
	// enum with constants that represent the robot hand
	// is up, down, open or closed
	public enum Status { UP, DOWN, OPEN, CLOSED };
	
	public int curRow;
	public int curCol;
	public Status posStatus; // position status: UP or DOWN
	public Status actStatus; // action status: OPEN or CLOSED
	public Items itemOnHand;

	/**
	 * Constructor
	 * @param x current X coordinate
	 * @param y current Y coordinate
	 * @param p position status: UP or DOWN
	 * @param a action status: OPEN or CLOSED
	 * @param i item on hand
	 */
	public RobotHand(int r, int c, Status p, Status a, Items i) {
		curRow = r;
		curCol = c;
		posStatus = p;
		actStatus = a;
		itemOnHand = i;
	}
	
	public String toString() {
		return String.format( "Row: %d, Col: %d, %s, %s, Item on Hand: %s",
				curRow,
				curCol,
				posStatus,
				actStatus,
				itemOnHand );
	}
} // end class RobotHand