package hw4;

import api.Point;

/**
 * A link that always turns between three points.
 * @author Karson Goone
 */
public class TurnLink extends ThreeWayLink{
	
	/**
	 * Constructs a TurnLink with the given endpoints.
	 * @param endpointA the first endpoint
	 * @param endpointB the second endpoint
	 * @param endpointC the third endpoint
	 */
	public TurnLink (Point endpointA, Point endpointB, Point endpointC) {
		super (endpointA, endpointB, endpointC);
	}
	
	@Override
	public Point getConnectedPoint(Point point) {
		if (point == super.getEndpointA()) {
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
