package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Abstract base class for all link types.
 * @author Karson Goone
 */

/**
 *  AbstractLink extends to: DeadEndLink, CouplingLink, ThreeWayLink, GetMultiConnected
 *  ThreeWayLink extends to: StraightLink, SwitchLink, TurnLink
 *  GetMultiConnected extends to: MultiSwitchLink, MultiFixedLink
 */
public abstract class AbstractLink implements Crossable {
	
	/**
	 * Indicates whether a train is currently crossing this link.
	 */
	private boolean crossing = false;
	
	/*
	 * Returns crossing for MultiSwitchLink to check if it's able to switch the track.
	 */
	protected boolean isCrossing() {
		return crossing;
	}
	
	/**
	 * Constructs an AbstractLink.
	 */
	public AbstractLink() {
		
	}
	
	@Override
	public void trainEnteredCrossing() {
		crossing = true;
	}

	@Override
	public void trainExitedCrossing() {
		crossing = false;
	}
	
	@Override
	public void shiftPoints(PositionVector positionVector) {
		Point connectedPoint = getConnectedPoint(positionVector.getPointB());
		
		if (connectedPoint != null) {
			positionVector.setPointA(connectedPoint);
			
			int Index = connectedPoint.getPointIndex();
			
			if (connectedPoint == connectedPoint.getPath().getLowpoint()) {
				positionVector.setPointB(connectedPoint.getPath().getPointByIndex(Index+1));
			}
			else if (connectedPoint == connectedPoint.getPath().getHighpoint()) {
				positionVector.setPointB(connectedPoint.getPath().getPointByIndex(Index-1));
			}
		}
	}
}
