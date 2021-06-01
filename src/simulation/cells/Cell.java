package simulation.cells;

import simulation.CType;
import simulation.GridManager;

import java.awt.*;

public abstract class Cell
{
    protected CType type;
    protected int x, y;
    protected boolean updatedThisStep;
    protected Color color;


    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    // Update the cell. Overridden based on the cell type
    public void update() { updatedThisStep = true; }


    // Get a value corresponding to the cell's type.
    public CType getType() { return type; }


    // Return the color that the cell should be rendered with.
    public Color getColor() { return color; }


    // Return the type of a particle a given distance away
    protected CType getNeighborType(int dx, int dy)
    {
        if (!GridManager.inBounds(x + dx, y + dy))
            return CType.AIR;

        Cell other = GridManager.getCells()[x + dx][y + dy];
        if (other == null)
            return CType.AIR;
        return other.getType();
    }


    // At the beginning of each step, the value of updatedThisStep is reset.
    // This variable prevents a given cell from being updated multiple times in a single step as a result of movement.
    public void resetUpdated() { updatedThisStep = false; }
    public boolean hasUpdated() { return updatedThisStep; }


    // Get the density of a particle type. Used to determine where a physics cell can move in a given simulation step
    public static int getDensity(CType t) {
        int density;
        switch(t) {
            case GROUND:
                density = 20; break;
            case WATER:
                density = 2; break;
            case SAND:
                density = 10; break;
            case STONE:
                density = 15; break;
            case KINETIC_SAND:
                density = 8; break;
            case AIR:
                density = 0; break;
            default:
                throw new IllegalStateException("Type not assigned a density: " + t);
        }
        return density;
    }


    // Set the x and y values of this particle
    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
