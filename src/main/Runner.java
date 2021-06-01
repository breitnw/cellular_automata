package main;

import rendering.Frame;
import rendering.RenderPanel;
import simulation.GridManager;

public class Runner
{
	private static Frame fm;
	private static RenderPanel rp;
	private static GridManager gm;

	private static final int timeStep = 33;


	public static void main(String[] args) throws InterruptedException
	{
		Thread.sleep(1000);

		gm = new GridManager();
		fm = new Frame();
		rp = fm.getRenderPanel();

		while(true) {
			update();
			Thread.sleep(timeStep);
		}
	}

	public static GridManager getGrid() { return gm; }

	public static void update()
	{
		gm.update();
		rp.repaint();
	}
}
