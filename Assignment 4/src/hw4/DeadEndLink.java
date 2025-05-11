package hw4;

import api.Point;
import api.PositionVector;

/**
 * A dead-end link that doesn't connect to any other points.
 * @author Karson Goone
 */
public class DeadEndLink extends AbstractLink{

	@Override
	public Point getConnectedPoint(Point point) {
		return null;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
	}
	
	@Override
	public int getNumPaths() {
		return 1;
	}
}