/**
 * Component type: Server
 * Purpose: make a work flow of operations that the robot’s hand
 * 			has to perform in order to finish its task.
 * 
 * @author Tran Xuan Hoang
 */
package layer2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import layer1.Close;
import layer1.Down;
import layer1.Items;
import layer1.MoveBottom;
import layer1.MoveLeft;
import layer1.MoveRight;
import layer1.MoveTop;
import layer1.Open;
import layer1.Operation;
import layer1.RobotHand;
import layer1.TableCell;
import layer1.Up;

public class OperationFlowPlanning {
	/**
	 * Call this method to generate the plan of work flow that is
	 * a list of Operations.
	 * @param table the table that contains items
	 * @param rbh the object that represent information about the
	 * 		  robot.
	 * @return list of Operations - work flow.
	 */
	public static List<Operation> make(
			TableCell[][] table, RobotHand rbh) {
		List<Operation> plan = new ArrayList<Operation>();

		int rows = table.length;
		int cols = table[0].length;

		int r0, c0; // the original coordinates of robot hand
		int r1, c1; // the original coordinates of item
		int r2, c2; // the coordinates of target cell where item will be moved to

		Map<Items, Integer> freq = getFreqOfItems(table);
		Set<Items> itemTypes = freq.keySet();

		r0 = rbh.curRow;
		c0 = rbh.curCol;
		r2 = 0;
		c2 = cols / 2;

		for (Items item : itemTypes) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols / 2; j++) {
					if (table[i][j].getItem() == item) {
						r1 = i;
						c1 = j;

						plan.addAll(moveItemToTarget(r0, c0, r1, c1, r2, c2));
						r0 = r2;
						c0 = c2;
						r2 = (c2 < cols - 1) ? r2 : r2 + 1;
						c2 = (c2 < cols - 1) ? c2 + 1 : cols / 2;
					}
				}
			}
		}

		System.out.println(plan);
		printTable(table);
		System.out.println(rbh);
		System.out.println(freq + "\n");

		return plan;
	} // end method make

	/**
	 * Create a map of 'items' and their 'frequency'
	 * @param table the table that contains items
	 * @return map of each item together with its frequency on 'table'
	 */
	public static Map<Items, Integer> getFreqOfItems(TableCell[][] table) {
		int rows = table.length;
		int cols = table[0].length;

		Map<Items, Integer> itemsFreq = new HashMap<Items, Integer>();

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols / 2; j++) {
				Items item = table[i][j].getItem();

				if (item != Items.NONE) {
					if (itemsFreq.containsKey(item)) {
						int count = itemsFreq.get(item);
						itemsFreq.put(item, count + 1);
					} else
						itemsFreq.put(item, 1);
				}
			}

		return itemsFreq;
	} // end method getMapOfItemsAndFrequencies

	/**
	 * Create a plan of operations to move the robot hand from the
	 * current position (fromR, fromC) to the target position
	 * (targetR, targetC) on the table.
	 * @param fromR the current row of the cell where robot hand is at
	 * @param fromC the current column of the cell where robot hand is at
	 * @param targetR the row of target cell
	 * @param targetC the column of target cell
	 * @return list of operations for which the robot hand will move
	 * to the target position
	 */
	public static List<Operation> move(
			int fromR, int fromC, int targetR, int targetC) {
		List<Operation> movePlan = new ArrayList<Operation>();

		if (fromR == targetR && fromC == targetC) {
			return movePlan;

		} else if (fromR < targetR && fromC > targetC) {
			movePlan.add(new MoveLeft());
			movePlan.add(new MoveBottom());
			fromR++;
			fromC--;
			movePlan.addAll(move(fromR, fromC, targetR, targetC));

		} else if (fromR == targetR && fromC > targetC) {
			for (int i = 0; i < fromC - targetC; i++)
				movePlan.add(new MoveLeft());

		} else if (fromR > targetR && fromC > targetC) {
			movePlan.add(new MoveLeft());
			movePlan.add(new MoveTop());
			fromR--;
			fromC--;
			movePlan.addAll(move(fromR, fromC, targetR, targetC));

		} else if (fromR > targetR && fromC == targetC) {
			for (int i = 0; i < fromR - targetR; i++)
				movePlan.add(new MoveTop());

		} else if (fromR > targetR && fromC < targetC) {
			movePlan.add(new MoveRight());
			movePlan.add(new MoveTop());
			fromR--;
			fromC++;
			movePlan.addAll(move(fromR, fromC, targetR, targetC));

		} else if (fromR == targetR && fromC < targetC) {
			for (int i = 0; i < targetC - fromC; i++)
				movePlan.add(new MoveRight());

		} else if (fromR < targetR && fromC < targetC) {
			movePlan.add(new MoveRight());
			movePlan.add(new MoveBottom());
			fromR++;
			fromC++;
			movePlan.addAll(move(fromR, fromC, targetR, targetC));

		} else if (fromR < targetR && fromC == targetC) {
			for (int i = 0; i < targetR - fromR; i++)
				movePlan.add(new MoveBottom());
		}

		return movePlan;
	} // end method move

	/**
	 * Move robot hand at (r0, c0) to pick up an item at (r1, c1)
	 * then move that item and put down it at (r2, c2). After putting
	 * down the item, robot hand will be UP again
	 * @param r0 the current row of cell that robot hand is on
	 * @param c0 the current column of cell that robot hand is on
	 * @param r1 the row of cell of item
	 * @param c1 the column of cell of item
	 * @param r2 the row of cell where item will be moved to
	 * @param c2 the column of cell where item will be moved to
	 * @return list of operations to move an item to the target cell
	 */
	public static List<Operation> moveItemToTarget(
			int r0, int c0, int r1, int c1, int r2, int c2) {
		List<Operation> movePlan = new ArrayList<Operation>();

		movePlan.addAll(move(r0, c0, r1, c1)); // move to item
		movePlan.add(new Down());
		movePlan.add(new Close());
		movePlan.add(new Up());
		movePlan.addAll(move(r1, c1, r2, c2)); // move item to target
		movePlan.add(new Down());
		movePlan.add(new Open());
		movePlan.add(new Up());

		return movePlan;
	} // end method moveItemToTarget

	public static void printTable(TableCell[][] table) {
		int rows = table.length;
		int cols = table[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%-8s", table[i][j]);
			}

			System.out.println();
		}
	} // end method printTable
} // end class OperationFlowPlanning