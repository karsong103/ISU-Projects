package hw4;

import api.Point;

/**
 * A switchable link between three points that can change direction.
 * @author Karson Goone
 */
public class SwitchLink extends ThreeWayLink {

	/**
	 * Indicates whether the link is currently turned.
	 */
	private boolean turn;
	
	/**
	 * Constructs a SwitchLink with the given endpoints.
	 * @param endpointA the first endpoint
	 * @param endpointB the second endpoint
	 * @param endpointC the third endpoint
	 */
	public SwitchLink (Point endpointA, Point endpointB, Point endpointC) {
		super (endpointA, endpointB, endpointC);
	}
	
	/**
	 * Sets the turn state of this link.
	 * @param turn true to turn the link, false otherwise
	 */
	public void setTurn​(boolean turn) {
		if (!super.isCrossing()) {
			this.turn = turn;
		}
	}
	
	@Override
	public Point getConnectedPoint(Point point) {
		if (point == super.getEndpointA() && !turn) {
			return super.getEndpointB();
		}
		else if (point == super.getEndpointA() && turn) { 
			return super.getEndpointC();
		}
		else if (point == super.getEndpointB()) {
			return super.getEndpointA();
		}
		else if (point == super.getEndpointC()) {
			return super.getEndpointA();
		}
		return null;
	}
}

