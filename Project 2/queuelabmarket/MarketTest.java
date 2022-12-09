package queuelabmarket;

public class MarketTest {

	public static void main(String[] args) {
		Checkout tst = new Checkout(8);
		tst.enterData();
		tst.runSim();
		tst.displayStats();
		System.exit(0);
	}
}
