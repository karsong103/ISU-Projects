package hw1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class balloontest {
	
	private Balloon b1;
	@Before
	public void setup() {
		b1 = new Balloon(0, 40, 200, 20);
	}
	
	@Test
	public void testnegativeGP() {
		b1.adjustHorizontalVelocity(-1);
		b1.oneSecondUpdate();
		assertEquals(40,b1.getGroundPosition());
	}
	
	@Test
	public void testfuelneg() {
		b1.setMaxBurnRate(-5.0);
		b1.addFuel(2.0);
		b1.oneSecondUpdate();
	}
}
