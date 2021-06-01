package simulation.cells.physicscells;

import simulation.GridManager;
import simulation.cells.Cell;

public abstract class PhysicsCell extends Cell
{
    /*
    possibleMovements represents the movements available to a physics cell in a given time step.
    - The first two elements of each row in the array represent the x and y coordinates, respectively, of a movement.
    - The third element represents the maximum allowed density of the cell in the target position before the movement can take place.
     */

    protected int[][] possibleMovements;

    public PhysicsCell(int x, int y)
    {
        super(x, y);
        possibleMovements = new int[0][0];
    }


    @Override
    public void update()
    {
        super.update();

        // Test all of the allowed movements for this cell
        for (int[] m : possibleMovements) {
            if (getDensity(getNeighborType(m[0], m[1])) <= m[2]) {
                move(m[0], m[1]);
                return;
            }
        }

    }


    // Move the cell down a value of dx and right a value of dy
    protected void move(int dx, int dy)
    {
        // If the new position is out of bounds, remove the cell
        if (x + dx >= GridManager.getMapWidth() || x + dx < 0 || y + dy >= GridManager.getMapHeight() || y + dy < 0) {
            GridManager.getCells()[x][y] = null;
            return;
        }

        //Otherwise, swap this cell with the one in the target position
        Cell other = GridManager.getCells()[x + dx][y + dy];
        GridManager.getCells()[x][y] = other;
        if (other != null) {
            other.setXY(x, y);
        }

        x += dx;
        y += dy;
        GridManager.getCells()[x][y] = this;
    }
}
