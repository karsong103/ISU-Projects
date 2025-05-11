package hw4;

import api.PointPair;

/**
 * A multi-switch link that can change its connections.
 * @author Karson Goone
 */
public class MultiSwitchLink extends GetMultiConnected {
	
	/**
	 * Constructs a MultiSwitchLink with the given connections.
	 * @param connections the array of point pairs representing connections
	 */
	public MultiSwitchLink(PointPair[] connections) {
		super(connections);
	}

	/**
	 * Switches the connection at the specified index to the given point pair.
	 * @param pointPair the new point pair to connect
	 * @param index the index of the connection to switch
	 */
	public void switchConnection(PointPair pointPair, int index) {
		if(!super.isCrossing()) 
			super.getConnections()[index] = pointPair;
	}

	/**
	 * Gets all connections of this link.
	 * @return the array of point pairs representing connections
	 */
	protected PointPair[] getConnections() {
		return super.getConnections();
	}
}
