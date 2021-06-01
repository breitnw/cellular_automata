package simulation.cells.physicscells;

import simulation.CType;

public class Stone extends PhysicsCell
{
    public Stone(int x, int y)
    {
        super(x, y);
        type = CType.STONE;

        possibleMovements = new int[][]{
                {0, 2, 4},
                {0, 1, 4}
        };
    }
}