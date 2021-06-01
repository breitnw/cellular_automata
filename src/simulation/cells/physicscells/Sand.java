package simulation.cells.physicscells;

import simulation.CType;

public class Sand extends PhysicsCell
{
    public Sand(int x, int y)
    {
        super(x, y);
        type = CType.SAND;

        possibleMovements = new int[][]{
                {0, 2, 0},
                {0, 1, 4}, {1, 1, 4}, {2, 1, 0}, {-1, 1, 4}, {-2, 1, 0}
        };
    }
}