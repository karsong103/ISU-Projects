package hw4;

import api.PointPair;

/**
 * A multi-fixed link with permanent connections.
 * @author Karson Goone
 */
public class MultiFixedLink extends GetMultiConnected {
	
	/**
	 * Constructs a MultiFixedLink with the given connections.
	 * @param connections the array of point pairs representing connections
	 */
	public MultiFixedLink(PointPair[] connections) {
		super (connections);
	}
}