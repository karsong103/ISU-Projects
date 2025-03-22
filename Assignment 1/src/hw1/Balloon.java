package hw1;

public class Balloon {
	private double fuel, tankSize, MaxBurnRate = 0.0;
	private int groundPosition, maxGroundPosition = 0;
	private int altitude, MaxAltitude = 0;
	private int VerticalVelocity, HorizontalVelocity = 0;
	private int Time = 0;
	
	public Balloon(int groundposition, int maxgroundPosition, int maxAltitude, double tankCapacity) {
		tankSize = tankCapacity;
		groundPosition = groundposition;
		maxGroundPosition = maxgroundPosition;
		MaxAltitude = maxAltitude;
	}
	/**
	 * Gets the current velocity of the balloon in the vertical direction.
	 * @return
	 */
	public int getVerticalVelocity(){
		return VerticalVelocity;
	}
	
	/**
	 * Gets the current velocity of the balloon in the horizontal direction.
	 * @return
	 */
	public int getHorizontalVelocity() {
		return HorizontalVelocity;
	}
	
	/**
	 * Gets the altitude of the balloon.
	 * @return
	 */
	public int getAltitude() {
		return altitude;
	}
	
	/**
	 * Gets the maximum altitude of the balloon. The balloon may not fly above this value.
	 * @return
	 */
	public int getMaxAltitude() {
		return MaxAltitude;
	}
	
	/**
	 *Gets the current position of the balloon with respect to the ground (i.e., what ground is it flying
over).
	 * @return
	 */
	public int getGroundPosition() {
		return groundPosition;
	}
	
	/**
	 * Gets the farthest ground position the balloon can travel before wrapping back around (in a circle)
to position 0.
	 * @return
	 */
	public int getMaxGroundPosition() {
		return maxGroundPosition;
	}
	
	/**
	 * Gets the amount of fuel the balloon has in its fuel tank
	 * @return
	 */
	public double getFuel() {
		return fuel;
	}
	
	/**
	 * Gets the maximum capacity of the balloon’s fuel tank
	 * @return
	 */
	public double getTankCapacity() {
		return tankSize;
	}
	
	/**
	 * Gets the maximum rate of fuel burn (assuming there is enough fuel to burn at this rate).
	 * @return
	 */
	public double getMaxBurnRate() {
		return MaxBurnRate;
	}
	
	/**
	 * Sets the capacity of the fuel tank to the given parameter.
	 * @param tankCapacity
	 */
	public void setTankCapacity(double tankCapacity) {
		tankSize = tankCapacity;
	}
	
	/**
	 * Gets the maximum rate of fuel burn (assuming there is enough fuel to burn at this rate).
	 * @param maxBurnRate
	 */
	public void setMaxBurnRate(double maxBurnRate) {
		MaxBurnRate = Math.abs(maxBurnRate);
	}
	
	/**
	 * Sets the total time the simulation has run in seconds to the given parameter.
	 * @param time
	 */
	public void setTime(int time) {
		Time = Math.abs(time);
	}
	
	/**
	 * Set the ground position back to where it was set by the constructor.
	 */
	public void restoreInitialGroundPosition() {
		groundPosition = 0;
	}
	
	/**
	 * Gets the number of second past the current minute. The returned value must be between 0 and
60. For example, if the current time is 100, the seconds past the current minute are 40.
	 * @return
	 */
	public int getSeconds() {
		return ((Time % 3600) % 60);
	}
	
	/**
	 * Gets the number of full minutes past the current hour. The returned value must be between 0 and
60. For example, if the current time is 100, the minutes past the current hour is 1.
	 * @return
	 */
	public int getMinutes() {
		return (Time % 3600) / 60;
	}
	
	/**
	 * Gets the number of full hours that have passed. For example, if the current time is 10,000, the
number of full hours is 2.
	 * @return
	 */
	public int getHours() {
		return Time / 3600;
	}
	
	/**
	 * Change the vertical velocity by the given delta (i.e., simply add delta to the current velocity).
	 * @param delta
	 */
	public void adjustVerticalVelocity(int delta) {
		VerticalVelocity += delta;
	}
	
	/**
	 * Change the horizontal velocity by the given delta (i.e., simply add delta to the current velocity).
	 * @param delta
	 */
	public void adjustHorizontalVelocity(int delta) {
		HorizontalVelocity += delta;
	}
	
	/**
	 * Add the given amount of fuel to the tank, however the tank cannot fill past its maximum
capacity. Return the amount of fuel actually added (may be less than the requested amount if the
tank capacity has been reached).
	 * @param amount
	 * @return
	 */
	public double addFuel(double amount) {
		double fuel = this.fuel;
		this.fuel = Math.min(Math.max(this.fuel + amount, 0), tankSize);
		return this.fuel - fuel;
	}
	
	/**
	 * Simulate the passing of one second of time.
	 * @return
	 */
	public double oneSecondUpdate() {
		double fuel = this.fuel;
		altitude = Math.min(Math.max((altitude + VerticalVelocity), 0), MaxAltitude);
		groundPosition = ((groundPosition + HorizontalVelocity) + maxGroundPosition) % maxGroundPosition;
		this.fuel = Math.max(this.fuel - MaxBurnRate, 0);
		Time += 1;
		return fuel - this.fuel;
	}
}
