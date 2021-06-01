package simulation.cells.physicscells;

import simulation.CType;

import java.awt.*;

public class Water extends PhysicsCell
{
    public Water(int x, int y)
    {
        super(x, y);
        type = CType.WATER;
        color = Color.BLUE;

        // Enable disabled movements for more realistic simulation, but at the cost of water clipping through other cells
        possibleMovements = new int[][]{
            {0, 2, 0},
            {0, 1, 0}, {-1, 1, 0}, {1, 1, 0}, {-2, 1, 0}, {2, 1, 0}, /*{-3, 1, 0}, {3, 1, 0},*/
            {-1, 0, 0}, {1, 0, 0}
        };
    }
}
