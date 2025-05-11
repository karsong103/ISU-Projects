package hw4;

import api.Point;
import api.PointPair;

/**
 * Base class for multi-connected links.
 * @author Karson Goone
 */
public class GetMultiConnected extends AbstractLink {

	/**
	 * The connections of this multi-connected link.
	 */
	private PointPair[] connections;

	/**
	 * Constructs a GetMultiConnected with the given connections.
	 * @param connections the array of point pairs representing connections
	 */
	public GetMultiConnected(PointPair[] connections) {
		this.connections = connections;
	}
	
	@Override
	public Point getConnectedPoint(Point point) {
		for (int i = 0; i < connections.length; i++) {
			if (point == connections[i].getPointA()) {
				return connections[i++].getPointB();
			}
		}
		return null;
	}

	@Override
	public int getNumPaths() {
		return connections.length;
	}

	/**
	 * Gets all connections of this link.
	 * @return the array of point pairs representing connections
	 */
	protected PointPair[] getConnections() {
		return connections;
	}
}