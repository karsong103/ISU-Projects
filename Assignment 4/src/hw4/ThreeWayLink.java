package hw4;

import api.Point;

/**
 * A link connecting three endpoints.
 * @author Karson Goone
 */
public class ThreeWayLink extends AbstractLink {

	/**
	 * The first endpoint of this link.
	 */
	private Point endpointA;
	
	/**
	 * The second endpoint of this link.
	 */
	private Point endpointB;
	
	/**
	 * The third endpoint of this link.
	 */
	private Point endpointC;
	
	/**
	 * Constructs a ThreeWayLink with the given endpoints.
	 * @param endpointA the first endpoint
	 * @param endpointB the second endpoint
	 * @param endpointC the third endpoint
	 */
	public ThreeWayLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}
	
	/**
	 * Gets the first endpoint.
	 * @return the first endpoint
	 */
	public Point getEndpointA() {
		return endpointA;
	}

	/**
	 * Sets the first endpoint.
	 * @param endpointA the new first endpoint
	 */
	public void setEndpointA(Point endpointA) {
		this.endpointA = endpointA;
	}

	/**
	 * Gets the second endpoint.
	 * @return the second endpoint
	 */
	public Point getEndpointB() {
		return endpointB;
	}

	/**
	 * Sets the second endpoint.
	 * @param endpointB the new second endpoint
	 */
	public void setEndpointB(Point endpointB) {
		this.endpointB = endpointB;
	}

	/**
	 * Gets the third endpoint.
	 * @return the third endpoint
	 */
	public Point getEndpointC() {
		return endpointC;
	}

	/**
	 * Sets the third endpoint.
	 * @param endpointC the new third endpoint
	 */
	public void setEndpointC(Point endpointC) {
		this.endpointC = endpointC;
	}

	@Override
	public Point getConnectedPoint(Point point) {
		if (point == endpointA) {
			return endpointB;
		}
		else if (point == endpointB) {
			return endpointA;
		}
		else if (point == endpointC) {
			return endpointA;
		}
		return null;
	}

	@Override
	public int getNumPaths() {
		return 3;
	}
}
