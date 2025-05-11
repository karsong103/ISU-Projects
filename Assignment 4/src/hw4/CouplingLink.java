package hw4;

import api.Point;

/**
 * A link that couples two points together.
 * @author Karson Goone
 */
public class CouplingLink extends AbstractLink{

	/**
	 * The first endpoint of this coupling link.
	 */
	private Point endpoint1;
	
	/**
	 * The second endpoint of this coupling link.
	 */
	private Point endpoint2;
	
	/**
	 * Constructs a CouplingLink with the given endpoints.
	 * @param endpoint1 the first endpoint
	 * @param endpoint2 the second endpoint
	 */
	public CouplingLink (Point endpoint1, Point endpoint2) {
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}
	
	@Override
	public Point getConnectedPoint(Point point) {
		if (point == endpoint1) {
			return endpoint2;
		}
		else if (point == endpoint2) {
			return endpoint1;
		}
		return null;
	}
	
	@Override
	public int getNumPaths() {
		return 2;
	}
}