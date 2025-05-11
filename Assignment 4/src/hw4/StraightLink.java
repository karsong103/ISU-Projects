package hw4;

import api.Point;

/**
 * A straight link between three points.
 * @author Karson Goone
 */
public class StraightLink extends ThreeWayLink {
	
	/**
	 * Constructs a StraightLink with the given endpoints.
	 * @param endpointA the first endpoint
	 * @param endpointB the second endpoint
	 * @param endpointC the third endpoint
	 */
	public StraightLink(Point endpointA, Point endpointB, Point endpointC) {
		super (endpointA, endpointB, endpointC);
	}
}
