package rendering;

import simulation.CType;
import simulation.cells.Cell;
import simulation.GridManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RenderPanel extends JPanel
{
	private int pixelScale;

	public RenderPanel(int pixelScale)
	{
		super();
		this.pixelScale = pixelScale;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		// Draw the cells to the screen
		for (int y = 0; y < GridManager.getMapHeight(); y++) {
			for (int x = 0; x < GridManager.getMapWidth(); x++) {

				Cell cell = GridManager.getCells()[x][y];
				Color color = Color.BLACK;

				// Choose a color based on the cell type
				if (cell != null) {
					CType type = GridManager.getCells()[x][y].getType();
					switch (type) {
						case GROUND:
							color = Color.GRAY;
							break;
						case WATER:
							color = Color.BLUE;
							break;
						case SAND:
							color = Color.ORANGE;
							break;
						case STONE:
							color = Color.LIGHT_GRAY;
							break;
						case KINETIC_SAND:
							color = Color.PINK;
							break;
						default:
							throw new IllegalStateException("Type not assigned a color: " + type);
					}
				}

				g2.setColor(color);
				g2.fillRect(x * pixelScale, y * pixelScale, pixelScale, pixelScale);
			}
		}
	}
}
